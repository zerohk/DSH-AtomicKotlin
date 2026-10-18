# Part C 加练 · 属性访问器与 init 专项（4 题）

> 用途：第 15 题（`Temperature`）需要同时用到 `init` 块、自定义 `get`/`set`、以及"两个属性共享同一套校验"这三样东西。若其中任何一个的写法想不起来，先做这 4 道小题。
>
> 每题**只练一个机制**，全部大约 20 分钟。代码骨架在 `src/main/kotlin/round01/drills/DrillsC.kt`（一个 `TODO()` 都没有答案）。

---

## 先记住三个概念

### 1. `init` 块 = 构造时执行的代码

```kotlin
class Score(val value: Int) {
    init {
        if (value !in 0..100) throw IllegalArgumentException("分数越界: $value")
    }
}
```

- 它在对象创建时执行，**每个实例执行一次**
- 和属性初始化器**按声明顺序**交错执行
- 主构造器参数如果不是属性（`class A(x: Int)` 里的 `x`），只能靠 `init` 校验
- ⚠️ **声明顺序有硬性要求**：`init` 不能给"声明在它下面"的属性赋值，否则编译报
  `variable cannot be initialized before declaration`

### 2. 自定义 `get` / `set`

```kotlin
class Tank(val capacity: Int) {
    private var levelValue: Int = 0

    var level: Int
        get() = levelValue                       // 取值时执行
        set(value) {                             // 赋值时执行，value 是隐式参数名
            if (value < 0 || value > capacity) throw IllegalArgumentException("越界: $value")
            levelValue = value
        }
}
```

要点：

| 写法 | 含义 |
|---|---|
| `val x: T get() = ...` | 只读、**没有**背后存储（计算属性） |
| `var x: T get() = ... / set(v) {...}` | 有背后存储，读写都经过你写的函数 |
| `set(value)` 里的 `value` | setter 的隐式参数，名字固定叫 `value`（也可自己起） |
| `field` | 指代"背后的存储"，只有当你需要用默认存储时才用 |
| `private var xxxValue` | 手写 backing property 的方式，和 `field` 二选一 |

**`field` 的用法**（比额外声明 `xxxValue` 更简洁）：

```kotlin
var balance: Int = 0
    set(value) {
        if (value < 0) throw IllegalArgumentException("余额不能为负")
        field = value          // field 就是背后那个存储
    }
```

### 3. "委托"在这里指什么

**不是** Kotlin 的 `by` 关键字委托（那是后面 Section V 的内容），这里只是"**一个 setter 把活交给另一个 setter**"：

```kotlin
var kilometers: Double
    get() = gramsValue / 1000.0          // 读的时候换算
    set(value) {
        grams = value * 1000.0           // 写的时候换算完，交给 grams 的 setter
    }
```

好处：**校验只写一份**。`kilograms` 的 setter 不需要再判断"是否为负"，因为 `grams` 的 setter 已经判断了。两个单位描述同一个量，不变式自然一致。

---

## D1 · `Score`：只练 `init` 校验

**目标**：把校验放进 `init`。

**任务**：`value` 必须在 `0..100`，否则构造时抛 `IllegalArgumentException`。

**验收标准**：
- `Score(85).value` → `85`；`Score(85).passed` → `true`
- `Score(0)`、`Score(100)` 合法
- `Score(-1)`、`Score(101)` 抛 `IllegalArgumentException`

---

## D2 · `Tank`：只练自定义 `setter`

**目标**：在 setter 里校验，并且**先校验、后赋值**。

**任务**：
1. `capacity` 必须 `> 0`，否则抛 `IllegalArgumentException`（`init` 里做）
2. `level` 的 setter：值必须在 `0..capacity`，否则抛 `IllegalArgumentException`

**验收标准**：
- `Tank(10).level` → `0`；`.isEmpty` → `true`
- `t.level = 5` 后 `.level` → `5`；`.isFull` → `false`
- `t.level = 10` 后 `.isFull` → `true`
- `t.level = -1`、`t.level = 11` 抛异常，且**之后 `t.level` 仍是原值**
- `Tank(0)`、`Tank(-5)` 构造抛异常

---

## D3 · `Weight`：练"委托式 setter" + "构造也走校验"

> 这题和第 15 题 `Temperature` **结构完全一样**，只是换成重量。做完这题，`Temperature` 的骨架你就已经会写了。

**目标**：两个单位共享同一套校验；构造时也走校验。

**任务**：
1. `grams` 的 setter：必须 `>= 0` 且有限（`isFinite`），否则抛 `IllegalArgumentException`
2. `kilograms` 的 getter：`gramsValue / 1000.0`
3. `kilograms` 的 setter：换算成克后**交给 `grams` 的 setter**（不要重复写校验）
4. `init`：构造时也走 `grams` 的 setter
5. `describe()`：形如 `"1500.0g / 1.5kg"`

**验收标准**：
- `Weight(1500.0).grams` → `1500.0`；`.kilograms` → `1.5`
- `w.kilograms = 2.5` 后 `w.grams` → `2500.0`
- `w.grams = -1.0` 抛异常，且**之后 `w.grams` 保持原值**
- `w.kilograms = -3.0` 抛异常（说明委托生效了），且 `w.grams` 不变
- `Weight(-5.0)`、`Weight(Double.NaN)` 构造抛异常
- `Weight(1500.0).describe()` → `"1500.0g / 1.5kg"`

---

## D4 · `Account`：练 `field` 关键字

**目标**：不额外声明 `xxxValue`，直接用 `field` 指代背后存储。

**任务**：
1. `balance` 的 setter：`< 0` 抛 `IllegalArgumentException`，否则 `field = value`
2. `deposit(amount)`：`amount <= 0` 抛 `IllegalArgumentException`；否则 `balance += amount`
3. `withdraw(amount)`：`amount <= 0` 抛 `IllegalArgumentException`；余额不足抛 `IllegalStateException`；否则 `balance -= amount`

**验收标准**：
- 新建账户 `balance` → `0`
- `deposit(100)` 后 → `100`；再 `withdraw(30)` 后 → `70`
- `deposit(0)`、`deposit(-5)`、`withdraw(-5)` 抛 `IllegalArgumentException`
- `withdraw(1000)` 抛 `IllegalStateException`，且余额仍为 `70`
- `a.balance = -1` 抛 `IllegalArgumentException`（setter 生效）

---

## 做完之后

回到第 15 题 `Temperature`，你会发现它只是把 D3 的"克/千克"换成"摄氏/华氏"，再补上一条特殊的校验线：

- `Weight` 的下界是 `0`
- `Temperature` 的下界是 `-273.15`（绝对零度）

其余结构一模一样：`private var` backing property、`init` 走 setter、派生属性的 setter 委托回主属性的 setter。

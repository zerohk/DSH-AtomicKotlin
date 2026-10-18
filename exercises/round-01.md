# Round 01 练习题 · Atomic Kotlin（Section I + II + Extension Functions）

> 本套题**严格**限定在你已学完的内容之内。共 **28 道编程练习题 + 4 个综合项目**。
> 全部不给答案 —— 只在"验收标准"里给出**期望结果**（这是规格，不是解法）。

---

## 一、知识边界（严格约束）

### 1.1 本轮允许使用的内容

| 章节 | 已学 atom |
|---|---|
| **Section I**<br>Programming Basics | Hello, World! · var & val · Data Types · Functions · if Expressions · String Templates · Number Types · Booleans · Repetition with `while` · Looping & Ranges · The `in` Keyword · Expressions & Statements |
| **Section II**<br>Introduction to Objects | Objects Everywhere · Creating Classes · Properties · Constructors · Constraining Visibility · Packages · Testing · Exceptions · Lists · Variable Argument Lists · Sets · Maps · Property Accessors |
| **Section III**<br>Usability | **Extension Functions**（本轮的"新知识"重点，共 5 道题） |

### 1.2 本轮**禁止**使用（这些还在后面，用了就失去练习意义）

- `when` 表达式、Enumerations、`data class`
- 具名参数 / 默认参数、函数重载（**注意：所以同名函数要换成不同名字**）
- 解构声明（**所以遍历 Map 不要写 `for ((k, v) in map)`，要写 `for (k in map.keys)`**）
- 可空类型与相关语法：`?`、`?:`、`!!`、安全调用、`toIntOrNull()` 等一切返回可空值的函数
- 自定义泛型声明（`<T>`）、扩展属性
- lambda / 高阶函数 / 成员引用 / 序列 / 局部函数 / 折叠 / **递归**
- 集合的高阶操作：`map`、`filter`、`forEach`、`sorted`、`sortedBy`、`sum`、`distinct`、`groupingBy`、`joinToString` 等
- 继承、接口、抽象类、密封类、类型检查、嵌套类 / 内部类、`object`、伴生对象
- `require()` / `check()` / `assert()`（属于 Section VI "Check Instructions"）
- **`break` 与 `continue`**（它俩在 Section III 里排在 Extension Functions **之后**）

### 1.3 三个"替代写法"提示（很重要的练习点）

既然不能用 `break` / `continue` / `require`，请在循环与校验中改用：

1. **提前 `return`** 代替 `break`（`return` 是函数级概念，不属于 `break`）
2. **`while` 的条件表达式** 或 **布尔标志变量** 控制循环退出
3. **显式 `throw IllegalArgumentException("...")`** 代替 `require(...)`

> 这三个替代写法本身就是很好的练习：它会强迫你想清楚"循环不变式"和"校验点"。

### 1.4 唯一的两处"超出书本 atom"的允许项

- **`readln()`**：仅允许用在 4 个综合项目的交互菜单里。如果你不想碰它，每个项目我都给了"用 `List<String>` 作为指令队列"的无输入版本，两种都可以。
- **标准库的基础函数**：`String` 的 `length`/`get`/`substring`/`lowercase`/`uppercase`/`split`/`trim`，集合的 `size`/`add`/`removeAt`/`contains`/`toList` 等**无 lambda 参数**的基础函数允许使用。
  但凡是题目明确要求"手工实现"的地方（如排序、求最值、求和），**必须自己写循环**，不许调用现成的聚合函数。

---

## 二、文件与包结构约定

请在项目里按下表建文件，**一道题一个文件**（我已经把带签名的空壳放在对应位置，函数体是 `TODO()`）。

```
E:\Kotlin\DSH-AtomicKotlin\
├── build.gradle.kts
├── settings.gradle.kts
├── exercises\
│   └── round-01.md              ← 本文件
└── src\main\kotlin\
    ├── util\Check.kt            ← 我提供的自测小工具（不用改）
    └── round01\
        ├── Main.kt              ← 自测入口（可自由修改）
        ├── Ex01Temperature.kt
        ├── Ex02NameCard.kt
        └── ...（见每题标题下的"文件"一行）
```

- 统一 `package round01`，**例外**是第 25 题（需要跨包 import，见该题说明）。
- 每题都保留我给的函数 / 类**签名**（名字、参数类型、返回类型），只替换 `TODO()`。签名的形状本身也是题目要求的一部分。
- 自测：跑 `gradlew.bat run`，或在 IDEA 里直接运行 `Main.kt`。`Check.kt` 提供 `checkEq(expected, actual, label)` 这类小工具，用 `println` 输出 PASS / FAIL，**不引入任何你没学过的语法**。

### 提交与 review 流程

1. 你写完（或写到一半卡住了）后告诉我，我会：**真实编译 + 运行**你的代码（`gradlew.bat --offline compileKotlin` / `run`），然后逐题 review。
2. Review 会包含：编译/运行结果、逐题正确性、**代码风格与惯用写法**（命名、`val`/`var` 使用、可见性、表达式 vs 语句、扩展函数的放置位置）、你踩到的知识点漏洞。
3. 最后我会**根据你实际暴露的问题**生成 Round 02（新一轮练习），针对薄弱点加深。

---

## Part A · 变量、类型、字符串模板、函数、if 表达式

### 1. 温度转换与格式化

**文件**：`Ex01Temperature.kt`　**知识点**：var & val、Data Types、Functions、if Expressions、String Templates、Number Types、Expressions & Statements

**任务**：实现三个函数。

- `celsiusToFahrenheit(c: Double): Double`
- `fahrenheitToCelsius(f: Double): Double`
- `describe(c: Double): String` —— 返回形如 `"25.0°C = 77.0°F"` 的一行文本

**约束**：
- `describe` 必须是**单表达式函数**（`= ` 形式），且用字符串模板拼装，不许用字符串相加。
- 不许用 `String.format`、不许用 lambda、不许用 `when`。
- 只允许 `val`，全题不允许出现 `var`。

**验收标准**：
- `describe(0.0)` → `"0.0°C = 32.0°F"`
- `describe(100.0)` → `"100.0°C = 212.0°F"`
- `describe(37.0)` → 结果里包含 `98.6`
- `fahrenheitToCelsius(32.0)` → `0.0`

---

### 2. 定宽名片生成器

**文件**：`Ex02NameCard.kt`　**知识点**：String Templates、Functions、Repetition with while、Expressions & Statements

**任务**：实现 `nameCard(name: String, age: Int, city: String): String`，生成一张**总宽度固定为 30 个字符**的多行名片：

```
==============================
姓名: Ada
年龄: 36
城市: London
==============================
```

**约束**：
- 边框行由 `=` 组成，恰好 30 个。
- 每一条信息行**总长度也必须是 30**：内容靠左，右侧用空格补齐。
- **必须用循环手工补空格**（不允许 `padEnd`、不允许 `repeat`）。
- 内容超长时（比如名字特别长）允许截断到 30 以内，但**绝不允许**出现超过 30 的行 —— 这点要在代码里明确处理。
- ⚠️ **不要用三引号原始字符串（`"""..."""`）来拼这张名片。** 原始字符串**不处理转义序列** —— 你在里面写 `\n`，得到的是**字面上的两个字符** `\` 和 `n`，不是换行。要换行就直接在源码里回车，要拼接就用**普通双引号字符串**里的 `"\n"`（只有在普通字符串里它才是换行）。
  推荐做法：把 5 行分别算出来，用 `两端边框 + "\n" + 中间三行 + "\n" + 边框` 的显式拼接；或者装进 `MutableList<String>` 再用循环手工拼 `"\n"`。
- 不许用 lambda、不许用 `when`。

**验收标准**：
- `nameCard("Ada", 36, "London")` 返回 5 行，**每一行 `length` 都等于 30**。
- **第 1 行与第 5 行必须恰好是 30 个 `=` 字符**（即 30 个 `=` 连成一条实线）。
  ⚠️ 注意：**不是**"1 个 `=` 后面补 29 个空格" —— 那种写法长度也是 30，但边框看起来只有一个小横杠，肉眼和验收标准都能抓出来。
- 第 2~4 行分别以 `姓名: `、`年龄: `、`城市: ` 开头，且分别包含 `Ada`、`36`、`London`。
- 用超长名字（如 40 个字符）调用时，每一行长度仍为 30（不崩、不越界）。

> 关于"30 个字符"的口径（这里容易混淆）：Kotlin 的 `String.length` 数的是 **UTF-16 码元个数**，每个汉字算 **1 个**，尽管它在终端里占 **2 列**。
> 本轮的验收一律以 `.length == 30` 为准，**不要求显示宽度对齐 30 列**。但你必须知道这两个口径不同：`"姓名: Ada".length` 是 **7**，而它在终端里看着比 7 列要宽。

> 提示：先写一个 `private fun padRight(text: String, width: Int): String` 作为辅助函数，再在 `nameCard` 里复用它。**这种"先抽辅助函数"的习惯，本身就是本轮要练的。**

---

### 3. Int 溢出侦探

**文件**：`Ex03Overflow.kt`　**知识点**：Number Types、Data Types、Booleans、if Expressions

**任务**：

- `willOverflow(a: Int, b: Int): Boolean` —— 判断 `a + b` 是否会超出 `Int` 的表示范围。
- `safeAdd(a: Int, b: Int): Long` —— 返回 `a + b` 的 `Long` 结果（无论是否溢出都要正确）。

**约束**：
- `willOverflow` 的思路：**先把操作数提升为 `Long` 再相加**，然后和 `Int.MAX_VALUE` / `Int.MIN_VALUE` 比较。不许依赖"真的让它溢出再观察"。
- 不许用 `try/catch`（那是第 19 题的内容）。
- 不许用 `Math.*`、不许用 `kotlin.math.*`。
- 不许用 lambda、不许用 `when`。

**验收标准**：
- `willOverflow(Int.MAX_VALUE, 1)` → `true`
- `willOverflow(Int.MIN_VALUE, -1)` → `true`
- `willOverflow(1, 2)` → `false`
- `willOverflow(-5, 5)` → `false`
- `safeAdd(Int.MAX_VALUE, 1)` → `2147483648L`

---

### 4. 三数极值与排序（只用 if 表达式）

**文件**：`Ex04ThreeNumbers.kt`　**知识点**：if Expressions、Expressions & Statements、Functions、Booleans

**任务**：

- `maxOf3(a: Int, b: Int, c: Int): Int`
- `minOf3(a: Int, b: Int, c: Int): Int`
- `middleOf3(a: Int, b: Int, c: Int): Int` —— 返回三个数的中间值
- `sort3(a: Int, b: Int, c: Int): String` —— 返回 `"最小值,中间值,最大值"`，如 `"3,5,9"`

**约束**：
- 全部实现为**单表达式函数**，内部只用**嵌套的 `if` 表达式**。
- 不许用 `kotlin.math` 的 `maxOf`/`minOf`、不许用 `sorted()`、不许用 lambda、不许用 `when`。
- `middleOf3` 的经典陷阱：`a + b + c - max - min` 虽然能算出结果，**但本题禁止这种写法** —— 请用纯比较逻辑写出来（想想为什么这种"总和减去极值"的写法在溢出时不可靠）。

**验收标准**：
- `maxOf3(3, 9, 5)` → `9`；`maxOf3(9, 3, 5)` → `9`；`maxOf3(5, 3, 9)` → `9`（六种参数顺序都要对）
- `minOf3(3, 9, 5)` → `3`
- `middleOf3(3, 9, 5)` → `5`
- `sort3(3, 9, 5)` → `"3,5,9"`
- 三个数相等时：`maxOf3(7,7,7)` → `7`，`sort3(7,7,7)` → `"7,7,7"`

---

### 5. BMI 计算与分级

**文件**：`Ex05Bmi.kt`　**知识点**：if Expressions、Number Types、Functions、String Templates

**任务**：

- `bmi(weightKg: Double, heightM: Double): Double`
- `bmiCategory(value: Double): String` —— 按下面的档位返回中文分类
- `formatOneDecimal(value: Double): String` —— 把数值格式化成**恰好 1 位小数**的字符串

档位（**边界归属必须严格按此实现**）：

| BMI 区间 | 返回 |
|---|---|
| `value < 18.5` | `"偏瘦"` |
| `18.5 <= value < 24.0` | `"正常"` |
| `24.0 <= value < 28.0` | `"超重"` |
| `value >= 28.0` | `"肥胖"` |

**约束**：
- `bmiCategory` 必须是**单表达式函数**，用 `if / else if / else` 链，不许用 `when`。
- `formatOneDecimal` **不许用 `String.format`**，也不许用 `"%.1f"`。用"乘 10 → 加 0.5 → 取整 → 除 10.0"的思路手工四舍五入。
- ⚠️ **负数必须先处理符号**：直接对负数写 `(value * 10 + 0.5).toInt()` 会出错，因为 `toInt()` 是**向零截断**。实测：`-2.0` 会被算成 `-1.9`，`-3.456` 会被算成 `-3.4`。
  正确做法：**先取绝对值 → 四舍五入 → 再补回负号**。
- 不许用 lambda。

**验收标准**：
- `bmi(70.0, 1.75)` → 约 `22.857142857142858`
- `bmiCategory(18.4)` → `"偏瘦"`；`bmiCategory(18.5)` → `"正常"` ← **边界值，重点检查**
- `bmiCategory(23.99)` → `"正常"`；`bmiCategory(24.0)` → `"超重"`
- `bmiCategory(28.0)` → `"肥胖"`
- `formatOneDecimal(22.857142857142858)` → `"22.9"`
- `formatOneDecimal(18.0)` → `"18.0"` ← **注意个位数必须补 `.0`，不能变成 `"18"`**
- `formatOneDecimal(-2.0)` → `"-2.0"` ← **陷阱：直接 `(value*10+0.5).toInt()` 会得到 `"-1.9"`**
- `formatOneDecimal(-3.456)` → `"-3.5"` ← 负数也要正确进位

> `formatOneDecimal` 是本轮第一个"看起来简单、细节很多"的题。负数、整数、以及 `x.05` 这类进位情形都要自己试一遍。

---

### 6. 闰年判定

**文件**：`Ex06LeapYear.kt`　**知识点**：if Expressions、Booleans、Expressions & Statements

**任务**：`isLeapYear(year: Int): Boolean` —— 单表达式函数。

**约束**：
- 必须是单表达式函数，只用布尔运算符和 `if` 表达式。
- 不许用 lambda、不许用 `when`、不许用任何日期时间库。

**验收标准**：
- `isLeapYear(2000)` → `true`（能被 400 整除）
- `isLeapYear(1900)` → `false`（能被 100 整除但不能被 400 整除）
- `isLeapYear(2024)` → `true`
- `isLeapYear(2023)` → `false`

---

### 7. 成绩等级（含非法输入）

**文件**：`Ex07Grade.kt`　**知识点**：if Expressions、Exceptions、Functions

**任务**：`grade(score: Int): Char` —— 返回等级字符。

| 分数 | 等级 |
|---|---|
| 90 ~ 100 | `'A'` |
| 80 ~ 89 | `'B'` |
| 70 ~ 79 | `'C'` |
| 60 ~ 69 | `'D'` |
| 0 ~ 59 | `'F'` |

**约束**：
- 分数小于 0 或大于 100 时，**必须 `throw IllegalArgumentException("...")`**，异常消息里要带上那个非法分数。
- 不许用 `require()` / `check()`（还没学）。
- 不许用 `when`、不许用 lambda。

**验收标准**：
- `grade(100)` → `'A'`；`grade(90)` → `'A'`；`grade(89)` → `'B'`
- `grade(60)` → `'D'`；`grade(59)` → `'F'`；`grade(0)` → `'F'`
- `grade(-1)` 和 `grade(101)` 都抛 `IllegalArgumentException`，且消息包含 `-1` / `101`

---

## Part B · while、区间、for、`in` 关键字

> 本 Part 的铁律：**不许用 `break` / `continue`，不许用递归**。请用 `while` 条件、布尔标志、或提前 `return` 来组织控制流。

### 8. 数字反转与回文判断

**文件**：`Ex08Palindrome.kt`　**知识点**：Repetition with while、Number Types、The in Keyword、Functions

**任务**：

- `reverseNumber(n: Int): Int` —— 反转十进制数字，如 `1234` → `4321`
- `isPalindromeNumber(n: Int): Boolean`
- `isPalindromeText(text: String): Boolean` —— **区分大小写**

> 注意函数名：因为本轮**不能重载**，所以数字版和文本版必须用不同的名字。

**约束**：
- `reverseNumber` 必须用 `while` + 取模 `/`、取余 `%` 实现。`n` 为负数时返回负数反转（如 `-123` → `-321`）。
- 反转后**末尾的 0 会消失**（整数不保留前导零）：`1200` → `21`、`10` → `1`。这是自然的数学结果，不要为了"看起来对称"去特判。
- `isPalindromeText` **不许用 `reversed()`**，请用手工循环比较（双指针或单指针均可）。
- **不许用 `break`/`continue`、不许用递归**。

**验收标准**：
- `reverseNumber(1234)` → `4321`；`reverseNumber(0)` → `0`；`reverseNumber(-123)` → `-321`
- `reverseNumber(1200)` → `21`；`reverseNumber(10)` → `1` ← **末尾的 0 反转后消失**
- `isPalindromeNumber(12321)` → `true`；`isPalindromeNumber(1234)` → `false`
- `isPalindromeText("level")` → `true`；`isPalindromeText("Level")` → `false`；`isPalindromeText("ab")` → `false`
- `isPalindromeText("")` → `true`（空串按回文处理）；`isPalindromeText("a")` → `true`（单字符也是回文）
- `isPalindromeText("racecar")` → `true`；`isPalindromeText("abcba")` → `true`
- ⚠️ **`isPalindromeText("abca")` → `false`**、**`isPalindromeText("xyzzx")` → `false`**
  这两条是专门用来抓"**只比较了首尾**"这个错误的：它们的首字符和末字符都相同，但中间不对称。只比较首尾的实现会把它们误判成 `true`，而上面 `"level"`、`"ab"`、`"a"` 这些例子**全都抓不出来**（它们的正确答案恰好与"首尾相同"一致）。
- `isPalindromeText("xyzzyx")` → `true`（对照组，首尾相同**且**中间对称）

---

### 9. 累加、阶乘与斐波那契（迭代版）

**文件**：`Ex09Series.kt`　**知识点**：Looping & Ranges、Repetition with while、Number Types、Exceptions

**任务**：

- `sumTo(n: Int): Long` —— 返回 `1 + 2 + ... + n`
- `factorial(n: Int): Long`
- `fib(n: Int): Long` —— 第 n 个斐波那契数，约定 `fib(0) == 0`、`fib(1) == 1`

**约束**：
- `sumTo` 与 `factorial` 用 `for` + 区间实现（不许用等差数列求和公式，要真的循环累加）。
- `fib` 用 `while` 迭代实现。
- **严禁递归**（Recursion 是 Section IV 的内容）。
- `n` 为负数时抛 `IllegalArgumentException`。

**验收标准**：
- `sumTo(0)` → `0L`；`sumTo(1)` → `1L`；`sumTo(100)` → `5050L`
- `factorial(0)` → `1L`；`factorial(1)` → `1L`；`factorial(5)` → `120L`；`factorial(20)` → `2432902008176640000L`
- `fib` 必须**逐个**验证小 n：`fib(0..10)` → `0, 1, 1, 2, 3, 5, 8, 13, 21, 34, 55`
  ⚠️ **只测 `fib(0)`、`fib(1)`、`fib(10)` 是不够的** —— `fib(2)` 恰恰是最容易写错的那个：如果把滚动变量的初值设成 `1, 1` 且计数器从 `2` 起步，循环会为 `n = 2` 跳过一整次迭代，从而错误地返回 `2`。请务必单独列出 `fib(2) == 1L` 与 `fib(3) == 2L`。
- `fib(50)` → `12586269025L`
- `sumTo(-1)`、`factorial(-1)`、`fib(-1)` 都抛异常

> 想一想：`factorial(21)` 会发生什么？为什么？把结论写在代码注释里。

---

### 10. 素数判定与素数表

**文件**：`Ex10Primes.kt`　**知识点**：Repetition with while、Looping & Ranges、Lists、Booleans

**任务**：

- `isPrime(n: Int): Boolean`
- `primesUpTo(limit: Int): List<Int>` —— 返回 `[2, limit]` 内所有素数，升序

**约束**：
- `isPrime` 要**提前 `return`**，不许用 `break`。
- **只用试除法，且必须只试到"平方根上界"** —— 不许用 `kotlin.math.sqrt`。判断上界**必须写成除法形式 `d <= n / d`**（整数除法）。
  ⚠️ 不要写 `d * d <= n`：当 `n` 接近 `Int.MAX_VALUE` 时，`d` 到 46341 时 `d * d` 会**溢出成负数**，于是 `d * d <= n` 永远成立，循环**永远不会退出**（真死循环，不是算错答案）。`d <= n / d` 用除法避开了这个坑，并且 `d <= n / d` 与 `d * d <= n` 在数学上等价。
- `primesUpTo` 用 `for` 循环 + `MutableList` 的 `add` 逐个收集（不许用 `filter`/`forEach` 等 lambda 写法）。
- `n <= 1` 时 `isPrime` 返回 `false`（不抛异常）。

**验收标准**：
- `isPrime(1)` → `false`；`isPrime(2)` → `true`；`isPrime(3)` → `true`；`isPrime(4)` → `false`
- `isPrime(97)` → `true`；`isPrime(100)` → `false`；`isPrime(Int.MAX_VALUE)` → `true`
- `primesUpTo(20)` → `[2, 3, 5, 7, 11, 13, 17, 19]`
- `primesUpTo(1)` → `[]`（空列表）
- `primesUpTo(2)` → `[2]`

> `isPrime(Int.MAX_VALUE)` 是本题的"杀手用例"：`2147483647` 恰好是素数（2³¹−1）。如果你写成 `d * d <= n`，程序会在这里**卡死**；写成 `d <= n / d` 才会正常返回 `true`。这就是"边界值测试"的实际价值 —— 换个小数字测，两种写法都对，只有这个用例能把 bug 逼出来。

---

### 11. FizzBuzz 与区间步进

**文件**：`Ex11FizzBuzz.kt`　**知识点**：Looping & Ranges、The in Keyword、Repetition with while、Lists

**任务**：

- `fizzBuzzUpTo(n: Int): String` —— 从 1 到 n，每行一个结果，用换行符 `"\n"` 连接
  - 3 的倍数 → `Fizz`；5 的倍数 → `Buzz`；15 的倍数 → `FizzBuzz`；否则输出数字本身
  - `n <= 0` 时返回空字符串 `""`
- `everyNth(from: Int, to: Int, step: Int): List<Int>` —— 返回 `from` 到 `to` 每隔 `step` 取一个数
  - 要求用**区间 + 步进**实现；`step <= 0` 时抛 `IllegalArgumentException`
  - `from > to` 时返回空列表（想想看：用 `downTo` 还是直接返回空？请按"返回空列表"实现，保持语义单纯）

**约束**：
- 不许用 `when`。
- 不许用 `joinToString`（它带 lambda 形态），请手工用 `StringBuilder` 或字符串累加拼接。
- 不许用 `break`/`continue`、不许用 lambda。

**验收标准**：
- `fizzBuzzUpTo(5)` → `"1\n2\nFizz\n4\nBuzz"`
- `fizzBuzzUpTo(15)` 的最后一行 → `"FizzBuzz"`
- `fizzBuzzUpTo(0)` → `""`；`fizzBuzzUpTo(-3)` → `""`
- `everyNth(1, 10, 3)` → `[1, 4, 7, 10]`
- `everyNth(5, 5, 2)` → `[5]`
- `everyNth(1, 10, 100)` → `[1]`（步长比区间还大时，只剩下起点）
- `everyNth(1, 10, 0)` 抛异常

---

### 12. 最大公约数与最小公倍数

**文件**：`Ex12Gcd.kt`　**知识点**：Repetition with while、Number Types、Expressions & Statements

**任务**：

- `absInt(n: Int): Int` —— **手工**实现绝对值，不许用 `kotlin.math.abs`
- `gcd(a: Int, b: Int): Int` —— 欧几里得算法（`while` 版）
- `lcm(a: Int, b: Int): Long` —— 返回 `Long`，避免中间结果溢出

**约束**：
- `gcd` 必须能正确处理负数与 0：`gcd(0, 0) == 0`、`gcd(0, 5) == 5`
- `lcm` 的中间乘法要用 `Long`，且要**先除后乘**以避免不必要的溢出（想想为什么 `a / gcd * b` 比 `a * b / gcd` 稳）。
  另外：`lcm(-4, 6)` 要返回 `12`，所以**负数必须先取绝对值**再参与计算（用你自己写的 `absInt`），否则会得到 `-12`。`lcm(0, x)` 约定返回 `0`。
- 不许用递归（欧几里得有递归版，本题**只收 while 版**）。
- 不许用 `kotlin.math`、不许用 lambda。

**验收标准**：
- `absInt(-7)` → `7`；`absInt(7)` → `7`；`absInt(0)` → `0`
- `gcd(48, 18)` → `6`；`gcd(-12, 18)` → `6`；`gcd(0, 5)` → `5`；`gcd(0, 0)` → `0`
- ⚠️ **两个参数顺序都要测**：`gcd(18, 48)` → `6`；**`gcd(5, 0)` → `5`**
  `gcd` 是**对称**的，只测一个方向不可靠。尤其 `gcd(0, 5)` 与 `gcd(5, 0)`：若实现写成 `if (b == 0) return 0`，则 `gcd(5, 0)` 会错返 `0`（数学约定 `gcd(a, 0) = |a|`，应为 `5`），而 `gcd(0, 5)` 却完全正常。
- ⚠️ **零与负数同时出现**：`gcd(0, -5)` → `5`（**不是** `-5`）
  这是上一条的镜像陷阱。很多人会把 `if (b == 0) return absInt(a)` 修好，却漏掉对称的 `if (a == 0) return b` —— 那个分支返回的是**未取绝对值**的 `b`，于是 `gcd(0, -5)` 得到 `-5`。两条一起改，或者干脆删掉 `a == 0` 那个特判（主循环本身就能处理：`0 % |b| == 0`，直接返回 `|b|`）。
- `lcm(4, 6)` → `12L`；`lcm(0, 5)` → `0L`；`lcm(-4, 6)` → `12L`；`lcm(6, 4)` → `12L`

> 小陷阱：`absInt(Int.MIN_VALUE)` 会怎样？在注释里写下你的分析（不用真的修好它）。

---

### 13. 图形打印（嵌套循环）

**文件**：`Ex13Shapes.kt`　**知识点**：Looping & Ranges、Repetition with while、String Templates

**任务**：三个函数，都返回用 `"\n"` 连接的多行字符串（**末尾不带换行**）。

- `rightTriangle(n: Int): String` —— 直角三角形，第 i 行有 i 个 `*`
- `pyramid(n: Int): String` —— 等腰三角形，共 n 行，**固定总宽度 `2 * n - 1`**，每行前面用空格补齐使 `*` 居中
- `multiplicationTable(n: Int): String` —— n × n 乘法表，每个乘积**占 4 个字符宽、右对齐**

`rightTriangle(3)` 应为：
```
*
**
***
```

`pyramid(3)` 应为：
```
  *
 ***
*****
```

**约束**：
- 不许用 `repeat`（要求用嵌套 `for`/`while` 手工拼）。
- 不许用 lambda、不许用 `joinToString`。
- `n <= 0` 时三个函数都返回 `""`。
- 乘法表的对齐**必须手工补空格**（不许用 `String.format`）。
- ⚠️ **列宽与可读性规则（重要）**：乘法表最大乘积是 `n * n`。列宽固定 4 且**不加分隔符**时，若某格正好占满 4 位，它就会和右边那格**直接粘连**（如 `9921024` 其实是 `992` 与 `1024`），表格无法阅读。
  - 因此本题把上限定为 **`n <= 31`**：`31 * 31 = 961` 是 3 位，右对齐后**至少保留 1 个前导空格**，列与列始终有间隔。
  - `n > 31` 时抛 `IllegalArgumentException`，消息说明列宽不够。
  - **严禁截断数字**（`1024` 不许变成 `102`）：截断会改掉数值，还会让该行长度不再是 `4 * n`。
  - 想支持更大的 `n`，需要改格式（属改需求，本题不要求）：要么**每格 4 位 + 1 个分隔空格**（行宽变 `5 * n`），要么用**动态列宽** `width = max(4, (n * n) 的位数 + 1)`。

**验收标准**：
- `rightTriangle(3)` → 恰好 3 行，各行 `*` 数为 1/2/3
- `rightTriangle(0)` → `""`；`pyramid(0)` → `""`；`multiplicationTable(-1)` → `""`
  （约束里写了 `n <= 0` 返回空串，但很容易漏测 —— 显式过一遍）
- `pyramid(3)` → 3 行，每行长度都是 5，`*` 数依次为 1/3/5 且居中
- `multiplicationTable(3)` → 3 行，**每行长度都是 12**（3 列 × 4 字符）
- `multiplicationTable(3)` 第一行 → `"   1   2   3"`；**最后一行** → `"   3   6   9"`
- `multiplicationTable(10)` → 10 行，**每行长度都是 40**
- `multiplicationTable(31)` → 31 行，**每行长度都是 124**；最后一行结尾为 `" 806 837 868 899 930 961"`（6 个格子，每格都是"1 个前导空格 + 3 位数字"，最大格 `961` 前面有空格）
- `multiplicationTable(32)` 抛 `IllegalArgumentException`（32 × 32 = `1024` 占满 4 位，会与相邻格粘连）

> ⚠️ 这里有个**测试设计上的盲区**值得记住：乘法表是**对称**的（`i * j == j * i`），所以"行和列写反了"这种 bug，**任何断言都抓不出来** —— 因为它是自己的转置。
> 遇到"对称性导致不可测"的情形，别指望测试：只能靠人工检查代码结构（比如确认外层循环变量用在了行首）。这是进阶的测试思维。

> 乘法表每行长度恰好 12 这个断言，能一次性抓出所有对齐 bug —— 这就是"可验证的规格"的价值。

---

## Part C · 类、属性、构造器、可见性、属性访问器

### 14. Rectangle 类与计算属性

**文件**：`Ex14Rectangle.kt`　**知识点**：Creating Classes、Properties、Constructors、Constraining Visibility、Property Accessors

**任务**：实现 `class Rectangle(val width: Double, val height: Double)`，并加上：

- `val area: Double` —— 计算属性（**不允许有 backing field**，用 `get() = ...`）
- `val perimeter: Double` —— 同上
- `val isSquare: Boolean` —— 同上
- `fun describe(): String` —— 形如 `"3.0x4.0 area=12.0 perimeter=14.0 square=false"`

**约束**：
- `width` / `height` 用 `val` 且**不可为负**：构造时若 `< 0` 或不是有限数，抛 `IllegalArgumentException`（在 `init` 块里做）。
- 不许用 `data class`（还没学）。不许 `require()`。
- `area` / `perimeter` / `isSquare` 必须是 `val ... get() = ...` 形式，**不许写成普通函数**，也**不许**在构造时预算好存进字段。
- ⚠️ **校验必须把"负数"和"非有限数"分开处理**。两个常见误区：
  - `Double.MIN_VALUE` **不是**最负的数，而是**最小正数** `4.9E-324`。写 `width < Double.MIN_VALUE` 会把这个正数当上界 —— 结果是 **`0.0` 也被判为非法**（`0.0 < 4.9E-324` 为真），而 0 恰恰是合法的。
  - `width > Double.MAX_VALUE` 除了 `+Infinity` 之外永远为假，同样抓不住 `NaN`：**`NaN` 与任何值比较都是 `false`**，所以 `NaN < 0`、`NaN > MAX_VALUE`、`NaN < MIN_VALUE` 全部为假，`NaN` 会**悄悄通过校验**。
  - 推荐写法：用 `Double.isFinite()`（stdlib 成员函数，无需 import，不属于 `kotlin.math`）把"是否有限"一次判掉，再单独判 `< 0`：
    ```kotlin
    if (!width.isFinite() || !height.isFinite() || width < 0 || height < 0)
        throw IllegalArgumentException("...")
    ```
- 不许用 lambda。

**验收标准**：
- `Rectangle(3.0, 4.0).area` → `12.0`；`.perimeter` → `14.0`；`.isSquare` → `false`
- `Rectangle(2.0, 2.0).isSquare` → `true`
- `Rectangle(0.0, 5.0).area` → `0.0`（0 是合法的）
- `Rectangle(-1.0, 5.0)` 抛 `IllegalArgumentException`
- ⚠️ **非有限数必须拒绝**：`Rectangle(Double.NaN, 1.0)`、`Rectangle(1.0, Double.NaN)`、`Rectangle(Double.POSITIVE_INFINITY, 1.0)`、`Rectangle(1.0, Double.NEGATIVE_INFINITY)` 全部抛 `IllegalArgumentException`
  （`NaN` 最容易漏：它与任何值比较都为 `false`，所有 `x < 0` / `x > MAX` 形式的判断都放它过去）
- `Rectangle(0.0, 0.0)` 是合法的（两个 0 都得允许），`area` → `0.0`、`isSquare` → `true`
- `Rectangle(3.0, 4.0).describe()` → **必须恰好等于** `"3.0x4.0 area=12.0 perimeter=14.0 square=false"`
- `Rectangle(2.0, 2.0).describe()` → `"2.0x2.0 area=4.0 perimeter=8.0 square=true"`
  （原标准只写了"包含 `12.0` 和 `14.0`"，格式写错也能通过 —— 现在改成全等比较）

---

### 15. Temperature 类与自定义 getter / setter

**文件**：`Ex15Temperature.kt`　**知识点**：Property Accessors、Properties、Constraining Visibility、Exceptions

**任务**：实现 `class Temperature(celsius: Double)`：

- 构造函数参数 `celsius` 存进一个 **`private var`** 的内部属性（名字自定，比如 `celsiusValue`）
- `var celsius: Double` —— 自定义 `get()` 和 `set()`
  - `get()` 返回内部值
  - `set(value)` 先校验：`value < -273.15` 时抛 `IllegalArgumentException("低于绝对零度")`，否则写入内部属性
- `var fahrenheit: Double` —— 自定义 `get()` 和 `set()`
  - `get()` 由摄氏换算得到
  - `set(value)` 换算回摄氏，并经 `celsius` 的 setter 走一遍校验（**即：不要重复写校验逻辑**）
- `fun describe(): String` —— 形如 `"25.0°C / 77.0°F"`

**约束**：
- `fahrenheit` 的 setter 必须"委托"给 `celsius` 的 setter 完成校验（把 `celsius = ...` 写在里面），这是本题的核心练习点：**两个属性共享同一套不变式**。
- 不许用 `data class`、不许 `require()`、不许 lambda、不许 `when`。
- 构造时的初始值也必须走校验（别只在 setter 里校验，构造函数直接赋值会绕过 —— 想清楚这里该怎么写）。

**验收标准**：
- `Temperature(25.0).fahrenheit` → `77.0`
- `Temperature(0.0).fahrenheit` → `32.0`
- 令 `t = Temperature(25.0)`；`t.fahrenheit = 212.0` 后 `t.celsius` → `100.0`
- `t.celsius = -300.0` 抛 `IllegalArgumentException`，且**抛异常后 `t.celsius` 保持原值不变**（不变式不能被破坏）
- `Temperature(-300.0)` 抛 `IllegalArgumentException`
- `Temperature(25.0).describe()` → **必须恰好等于** `"25.0°C / 77.0°F"`
- `Temperature(0.0).describe()` → `"0.0°C / 32.0°F"`
  ⚠️ 这里的 `°C` 是 `°`(U+00B0) **加上**字母 `C`，共两个字符 —— 不要用输入法给出的 `℃`(U+2103) 合体字，理由见附录 B

> "抛异常后状态不变"是本题最容易做错的地方，请专门为它设计一个自测。

---

### 16. BankAccount 类（封装 + 只读余额）

**文件**：`Ex16BankAccount.kt`　**知识点**：Constraining Visibility、Properties、Exceptions、Number Types

**任务**：实现 `class BankAccount(val owner: String)`：

- `private var balanceInCents: Long = 0`
- `val balanceInCentsValue: Long` —— **只读**暴露（只有 getter，没有 setter）
- `fun deposit(cents: Long)` —— 金额必须 `> 0`，否则抛 `IllegalArgumentException`
- `fun withdraw(cents: Long)` —— 金额必须 `> 0`；余额不足时抛 `IllegalStateException("余额不足")`
- `fun transferTo(other: BankAccount, cents: Long)` —— 从本账户转到另一个账户；**任一步失败时两边余额都必须保持原样**（想清楚校验该放在动手之前还是之后）
- `fun statement(): String` —— 形如 `"Ada: 10000 分"`

**约束**：
- `balanceInCents` 必须是 `private`，外部**无法**直接读写。
- 不许用 `data class`、不许 `require()`/`check()`、不许 lambda、不许 `when`。
- 金额一律用 `Long` 的"分"为单位，**不许出现 `Double`**（想想为什么钱不该用 Double）。

**验收标准**：
- 新账户 `balanceInCentsValue` → `0L`
- `deposit(10000)` 后 → `10000L`
- `withdraw(3000)` 后 → `7000L`
- `withdraw(999999)` 抛 `IllegalStateException`，且余额仍为 `7000L`
- `deposit(-1)`、`deposit(0)`、`withdraw(-5)` 都抛 `IllegalArgumentException`
- A 有 `7000`，`A.transferTo(B, 10000)` 抛异常后，A 仍是 `7000`、B 仍是 `0`
- 令 `a = BankAccount("Ada")`；`a.deposit(10000)` 后 `a.statement()` → **必须恰好等于** `"Ada: 10000 分"`

---

### 17. Email 与 Password 校验类

**文件**：`Ex17Validation.kt`　**知识点**：Constructors、Constraining Visibility、Properties、Property Accessors、The in Keyword、Loops

**任务**：

**(a)** `class Email(address: String)`

- 构造时校验：非空、无空格、恰好包含一个 `@`、`@` 前后都非空、域名部分包含 `.`；任一不满足抛 `IllegalArgumentException`
- `val address: String` —— 只读
- `fun domain(): String` —— 返回 `@` 之后的部分
- `fun localPart(): String` —— 返回 `@` 之前的部分

**(b)** `class Password(raw: String)`

- 构造时校验：长度 `>= 8`，否则抛 `IllegalArgumentException("密码太短")`
- 暴露 `val strength: Int` —— **计算属性**（`get() = ...`），返回 0~3 分：满足"含数字"得 1 分、"含大写字母"得 1 分、"含小写字母"得 1 分
- `fun masked(): String` —— 返回等长的 `*`，如 `"Abc12345"` → `"********"`

**约束**：
- **不许用 `Regex`**（正则不在本轮范围），必须用循环逐字符检查。
- 判断字符是否数字 / 大写 / 小写，**必须用 `in` 关键字配区间**（如 `c in '0'..'9'`、`c in 'A'..'Z'`），不许用 `isDigit()`/`isUpperCase()`。
- 不许用 `require()`、不许 lambda、不许 `when`、不许解构。
- `Password` 的 `strength` 不许预先算好存字段，必须是 `val ... get() = ...`。

**验收标准**：
- `Email("ada@example.com").domain()` → `"example.com"`；`.localPart()` → `"ada"`
- `Email("bad")`、`Email("a b@c.com")`、`Email("a@@b.com")`、`Email("@b.com")`、`Email("a@bcom")` 全部抛 `IllegalArgumentException`
  （注意最后一个：域名没有 `.`，也要拒绝）
- `Password("Abc12345").strength` → `3`
- `Password("abcdefgh").strength` → `1`
- `Password("abc")` 抛 `IllegalArgumentException`
- `Password("Abc12345").masked()` → `"********"`

---

### 18. Stack 类（用 MutableList 实现）

**文件**：`Ex18Stack.kt`　**知识点**：Lists、Constraining Visibility、Properties、Exceptions、Functions

**任务**：实现 `class Stack`（**注意：不是泛型栈，只存 `Int`** —— 自定义泛型还没学）：

- `private val items: MutableList<Int> = mutableListOf()`
- `fun push(value: Int)`
- `fun pop(): Int` —— 空栈时抛 `IllegalStateException("栈为空")`
- `fun peek(): Int` —— 空栈时抛 `IllegalStateException("栈为空")`
- `fun size(): Int`
- `fun isEmpty(): Boolean`：注意 —— **不能用 `items.isEmpty()` 直接返回**？不，这个可以用；但要求你另写一个 `isEmptyByHand()`，用 `size` 手工判断，体会两者区别
- `fun contains(value: Int): Boolean` —— 手工循环查找，**不许用 `items.contains()`**
- `fun toList(): List<Int>` —— 返回一个**快照副本**：调用方修改它不能影响栈内部
- `fun clear()`

**约束**：
- 内部集合必须 `private`，外部只能通过方法访问。
- 出栈请用 `items.removeAt(items.size - 1)` 这种显式下标写法（练下标），**不许用 `removeLast()`/`removeFirst()`**。
- 不许用 `java.util.Stack` / `ArrayDeque`。
- 不许 lambda、不许解构、不许 `data class`。
- `toList()` 返回的副本**必须与原集合解耦**（这是本题的核心考点）。

**验收标准**：
- 依次 `push(1)`, `push(2)`, `push(3)` 后 `size()` → `3`，`peek()` → `3`
- 连续 `pop()` → `3`，然后 `2`，然后 `1`（LIFO）
- 空栈 `pop()` / `peek()` 抛 `IllegalStateException`
- `push(5)` 后 `contains(5)` → `true`，`contains(6)` → `false`
- 关键断言：取 `val snap = stack.toList()`，对 `snap` 里元素做修改（或 `clear()` 栈）后，`stack.size()` 不受影响
- `isEmpty()` 与 `isEmptyByHand()` 在任何状态下结果一致

---

## Part D · 异常

### 19. 安全求商与整数解析

**文件**：`Ex19SafeMath.kt`　**知识点**：Exceptions、Number Types、String、Functions

**任务**：

- `divideWithTryCatch(a: Int, b: Int): String` —— **用 `try/catch` 捕获 `ArithmeticException`**，成功返回 `"结果: 5"`，除零返回 `"除数不能为 0"`
- `divideWithCheck(a: Int, b: Int): String` —— **不用 `try/catch`**，先判断再算，返回同样的字符串
- `parsePositiveInt(text: String): Int` —— 用 `try/catch` 捕获 `NumberFormatException`；解析成功且 `> 0` 时返回该值，否则抛 `IllegalArgumentException("不是正整数")`
- `sumOfInts(texts: List<String>): Long` —— 逐个解析并累加；**遇到第一个非法项就抛 `IllegalArgumentException`**，消息里要包含那个非法文本

**约束**：
- 不许用 `toIntOrNull()`（返回可空值，还没学），只能用会抛异常的 `toInt()`。
- 不许用可空类型语法（`?`、`?:`、`!!`）。
- 不许用 `require()`/`check()`、不许 lambda、不许 `when`、不许解构。
- `divideWithTryCatch` 和 `divideWithCheck` 必须在注释里各写一句：**你更倾向哪种写法、为什么**。

**验收标准**：
- `divideWithTryCatch(10, 2)` → `"结果: 5"`
- `divideWithTryCatch(10, 0)` → `"除数不能为 0"`
- `divideWithCheck(10, 0)` → `"除数不能为 0"`（与上一个结果一致）
- `parsePositiveInt("42")` → `42`
- `parsePositiveInt("abc")` → 抛 `IllegalArgumentException`
- `parsePositiveInt("-5")` → 抛 `IllegalArgumentException`
- `parsePositiveInt("0")` → 抛 `IllegalArgumentException`
- `sumOfInts(listOf("1", "2", "3"))` → `6L`
- `sumOfInts(listOf("1", "x", "3"))` 抛异常，消息包含 `x`

---

### 20. `finally` 执行顺序实验

**文件**：`Ex20Finally.kt`　**知识点**：Exceptions、Lists、Functions

**任务**：

- `fun runNormal(log: MutableList<String>): String`
- `fun runThrowing(log: MutableList<String>): String`
- `fun runWithReturnInTry(log: MutableList<String>): String`
- `fun runWithThrowInFinally(log: MutableList<String>): String`

四个函数都把执行轨迹 `add` 进传入的 `log`，用来**观察 `finally` 到底在什么时候执行**。

具体规格（请严格照此写，这样轨迹才可比对）：

| 函数 | 行为 |
|---|---|
| `runNormal` | try 里记录 `"try"`、返回 `"ok"`；finally 里记录 `"finally"` |
| `runThrowing` | try 里记录 `"try"`、抛 `IllegalStateException`；catch 里记录 `"catch"` 并返回 `"caught"`；finally 里记录 `"finally"` |
| `runWithReturnInTry` | try 里记录 `"try"`、`return "from-try"`；finally 里记录 `"finally"`。**问：返回值是哪个？轨迹里 `"finally"` 有没有出现？** |
| `runWithThrowInFinally` | try 里记录 `"try"`、抛 `IllegalStateException("try")`；catch 里记录 `"catch"`；finally 里记录 `"finally"` 并抛 `IllegalArgumentException("finally")`。**问：最终抛出来的是哪个异常？原来的那个去哪了？** |

**约束**：
- 不许用可空类型、不许 `require()`、不许 lambda、不许 `when`、不许解构。
- 每个函数都要在**注释里写出你预测的 `log` 内容**，然后再运行验证（先预测、后验证，这是重点）。

**验收标准**：
- `runNormal` 的 `log` → `["try", "finally"]`，返回 `"ok"`
- `runThrowing` 的 `log` → `["try", "catch", "finally"]`，返回 `"caught"`
- `runWithReturnInTry` 的 `log` → `["try", "finally"]`，返回 `"from-try"`
- `runWithThrowInFinally` 的 `log` → `["try", "catch", "finally"]`，且**最终抛出的是 `IllegalArgumentException`**（消息 `finally`），原来那个 `IllegalStateException` 被**抑制/替换**掉了

> 这一题不写算法，但它是整轮里**最能把"异常控制流"讲清楚**的一题。请务必先写下预测再运行。

---

## Part E · 集合与 vararg

### 21. 手工集合运算

**文件**：`Ex21SetOps.kt`　**知识点**：Lists、Sets、Repetition with while、The in Keyword

**任务**：

- `uniqueInOrder(values: List<Int>): List<Int>` —— 去重，**保持首次出现的顺序**
- `intersectionOf(a: Set<Int>, b: Set<Int>): Set<Int>`
- `unionOf(a: Set<Int>, b: Set<Int>): Set<Int>`
- `differenceOf(a: Set<Int>, b: Set<Int>): Set<Int>` —— 在 a 中但不在 b 中
- `symmetricDifferenceOf(a: Set<Int>, b: Set<Int>): Set<Int>` —— 只在一个集合里出现的元素

**约束**：
- **全部手工用循环实现**。禁止 `distinct()`、`intersect()`、`union()`、`subtract()`、`filter`、`forEach`、`map`、`toSet()` 之外的高阶函数。
  （`toSet()`、`toList()`、`add()`、`contains()` 这些**无 lambda** 的基础函数可以用。）
- 不许用解构、不许 lambda。
- 注意 `uniqueInOrder` 的复杂度：用 `MutableList.contains()` 判重是可以接受的（本题不要求性能），但请**在注释里说明**为什么它在数据量大时会变慢。

**验收标准**：
- `uniqueInOrder(listOf(3, 1, 3, 2, 1))` → `[3, 1, 2]`
- `uniqueInOrder(listOf())` → `[]`
- `intersectionOf(setOf(1,2,3), setOf(2,3,4))` → `{2, 3}`
- `unionOf(setOf(1,2,3), setOf(2,3,4))` → `{1, 2, 3, 4}`
- `differenceOf(setOf(1,2,3), setOf(2,3,4))` → `{1}`
- `symmetricDifferenceOf(setOf(1,2,3), setOf(2,3,4))` → `{1, 4}`

---

### 22. 词频统计与 Map 遍历

**文件**：`Ex22WordCount.kt`　**知识点**：Maps、Lists、Sets、The in Keyword

**任务**：

- `wordCount(text: String): Map<String, Int>` —— 按空白切分，**忽略空串**，统计每个词出现次数
- `mostFrequent(counts: Map<String, Int>): String` —— 返回出现次数最多的词；**并列时返回字典序最小的那个**；空 Map 抛 `IllegalArgumentException`
- `wordsWithCount(counts: Map<String, Int>, n: Int): Set<String>` —— 返回恰好出现 n 次的词集合
- `totalWords(counts: Map<String, Int>): Int` —— 所有词频之和

**约束**：
- 遍历 Map **必须用 `for (key in counts.keys)`**，**严禁解构写法 `for ((k, v) in counts)`**（解构是本轮禁止项）。
  提示：取值用 `counts[key]` —— 注意这返回可空类型，而可空类型本轮禁止。**请改用 `counts.getValue(key)`**，并想清楚为什么它能避开可空性。
- 不许用 `groupingBy`、`count`、`forEach`、`maxBy`、`sortedBy`、`joinToString` 等任何带 lambda 的 API。
- 字符串切分可以用 `split(" ")`，但**必须自己处理连续空格产生的空串**。
- 不许 lambda、不许 `when`。

**验收标准**：
- `wordCount("a b a c b a")` → `{a=3, b=2, c=1}`
- `wordCount("")` → `{}`（空 Map）
- `wordCount("  a   a  ")` → `{a=2}`（连续空格要正确处理）
- `mostFrequent(wordCount("a b a c b a"))` → `"a"`
- 并列测试：`mostFrequent(mapOf("b" to 1, "a" to 1))` → `"a"`（字典序最小）
- `wordsWithCount(wordCount("a b a c b a"), 1)` → `{c}`
- `totalWords(wordCount("a b a c b a"))` → `6`

---

### 23. vararg 工具集与 spread 运算符

**文件**：`Ex23Varargs.kt`　**知识点**：Variable Argument Lists、Lists、Number Types、Exceptions

**任务**：

- `sumAll(vararg nums: Int): Long` —— 空参返回 `0L`
- `average(vararg nums: Double): Double` —— 空参抛 `IllegalArgumentException`
- `maxAll(vararg nums: Int): Int` —— 空参抛 `IllegalArgumentException`
- `joinWith(separator: String, vararg parts: String): String` —— 空 parts 返回 `""`；单元素不加分隔符
- `demoSpread(values: IntArray): Long` —— **必须用 spread 运算符 `*`** 把数组传给 `sumAll`，返回结果

**约束**：
- 不许用 `joinToString`、不许用 `kotlin.math`、不许 lambda、不许 `when`、不许解构。
- 注意 `vararg` 参数在函数体内是**数组**：请分别用"下标 `while` 循环"和"`for (x in nums)`"各写一次，比较两种写法的手感（在注释里写结论）。
- `maxAll` 不允许用 `nums.max()` 之类的聚合函数，手工比较。

**验收标准**：
- `sumAll(1, 2, 3)` → `6L`；`sumAll()` → `0L`
- `average(1.0, 2.0, 3.0)` → `2.0`；`average()` 抛 `IllegalArgumentException`
- `maxAll(3, 9, 2)` → `9`；`maxAll(-5, -9)` → `-5`；`maxAll()` 抛异常
- 单元素退化情形（容易被忽略）：`maxAll(7)` → `7`；`average(5.0)` → `5.0`
- `joinWith("-", "a", "b", "c")` → `"a-b-c"`；`joinWith("-")` → `""`；`joinWith("-", "a")` → `"a"`
- `demoSpread(intArrayOf(1, 2, 3))` → `6L`
- 额外：`sumAll(*intArrayOf(4, 5))` → `9L`（在 `Main.kt` 里直接调用验证）

---

## Part F · 扩展函数（Section III 第一个 atom，本轮重点）

> 这 5 道题是本轮的**新知识**，也是 Round 02 出题的基准。请特别注意：**扩展函数是"外部"函数，不能访问类的 private 成员** —— 这个特性会在第 27 题里变成考点。

### 24. String 扩展函数包

**文件**：`Ex24StringExt.kt`　**知识点**：Extension Functions、Functions、Loops、The in Keyword

**任务**：全部实现为 **`String` 的扩展函数**（必须是扩展，不许改成 `fun foo(s: String)` 的普通函数）：

- `fun String.isPalindrome(): Boolean` —— **忽略大小写与所有空格**
- `fun String.shout(): String` —— 返回大写并在末尾加 `!`；空串返回 `"!"`
- `fun String.initials(): String` —— 取每个单词首字母大写，用 `.` 分隔并**以 `.` 结尾**，如 `"john ronald reuel tolkien"` → `"J.R.R.T."`
- `fun String.wordCount(): Int` —— 统计单词个数（连续空格算一个分隔）
- `fun String.reverseByHand(): String` —— 手工反转（不许用 `reversed()`）

**约束**：
- `isPalindrome` 必须能处理 `"A man a plan a canal Panama"` → `true`，即要**先过滤掉空格**；请手工构造一个"去掉空格并转小写"的字符串再比较（可以用 `lowercase()`，但过滤空格要自己写循环）。
- 不许用 `Regex`、不许 lambda、不许 `when`、不许 `break`/`continue`、不许解构。
- 不许用 `reversed()`、`filter`、`map`。

**验收标准**：
- `"level".isPalindrome()` → `true`；`"Level".isPalindrome()` → `true`（忽略大小写）
- `"A man a plan a canal Panama".isPalindrome()` → `true`
- `"hello".isPalindrome()` → `false`
- `"Hello".shout()` → `"HELLO!"`；`"".shout()` → `"!"`
- `"john ronald reuel tolkien".initials()` → `"J.R.R.T."`
- `"  a   b  c ".wordCount()` → `3`；`"".wordCount()` → `0`
- `"abc".reverseByHand()` → `"cba"`；`"".reverseByHand()` → `""`

---

### 25. Int 扩展函数 + 跨包 import

**文件**：两个包，各一个文件
- `src/main/kotlin/round01/ext/numberx/IntExtensions.kt` → `package round01.ext.numberx`
- `src/main/kotlin/round01/Ex25IntExt.kt` → `package round01`

**知识点**：Extension Functions、Packages、Loops、Number Types、Booleans

**任务**：

**(a)** 在 `package round01.ext.numberx` 中定义 `Int` 的扩展函数：

- `fun Int.isEven(): Boolean`
- `fun Int.isOdd(): Boolean`
- `fun Int.isPrimeX(): Boolean` —— 独立实现一遍素数判定（**不许调用第 10 题的实现**）
- `fun Int.factorialX(): Long` —— 负数抛 `IllegalArgumentException`
- `fun Int.digitsSum(): Int` —— 各位数字之和；负数按其绝对值处理，如 `-1234` → `10`
- `fun Int.digitsCount(): Int` —— 位数；`0` 返回 `1`

**(b)** 在 `package round01` 的 `Ex25IntExt.kt` 中 **import 这些扩展函数** 并实现：

- `fun demoImportedExtensions(): String` —— 依次调用上面 6 个扩展，返回一行摘要，如 `"4 even=true odd=false prime=false digitsSum=4 digitsCount=1"`
- `fun sumOfAllPrimesUpTo(limit: Int): Long` —— 在循环里调用 `isPrimeX()` 累加

**约束**：
- 扩展函数**必须**定义在 `round01.ext.numberx` 包里，`Ex25IntExt.kt` 里**必须**用 `import` 引入才能编译通过 —— 这是本题的核心目的：**亲手体验"扩展函数受包的可见性约束，需要 import"**。
- 不许用 `kotlin.math`、不许 lambda、不许 `when`、不许解构、不许递归。
- `digitsSum` 对 `Int.MIN_VALUE` 的处理不要求完美，但要在注释里说明你的取舍。

**验收标准**：
- `4.isEven()` → `true`；`7.isOdd()` → `true`
- `97.isPrimeX()` → `true`；`1.isPrimeX()` → `false`
- `5.factorialX()` → `120L`；`(-1).factorialX()` 抛异常
- `1234.digitsSum()` → `10`；`(-1234).digitsSum()` → `10`
- `0.digitsCount()` → `1`；`1234.digitsCount()` → `4`
- `sumOfAllPrimesUpTo(10)` → `17L`（2+3+5+7）
- `demoImportedExtensions()` 返回的字符串包含 `even=true`

---

### 26. List<Int> 的扩展统计工具

**文件**：`Ex26ListExt.kt`　**知识点**：Extension Functions、Lists、Loops、Exceptions

**任务**：全部实现为 **`List<Int>` 的扩展函数**：

- `fun List<Int>.secondMax(): Int` —— 第二大**不同**的值；元素不足 2 个、或所有元素相等时抛 `IllegalArgumentException`
- `fun List<Int>.rangeOfValues(): Int` —— 最大值 - 最小值（空列表抛 `IllegalArgumentException`）
- `fun List<Int>.median(): Double` —— 中位数；空列表抛 `IllegalArgumentException`
- `fun List<Int>.countOccurrences(target: Int): Int`
- `fun List<Int>.sortedByHand(): List<Int>` —— **手工排序**（冒泡或选择排序皆可），返回**新列表**，不修改原列表
- `fun List<Int>.isSortedAscending(): Boolean`

**约束**：
- **严禁使用** `sorted()`、`sortedDescending()`、`max()`、`min()`、`maxOrNull()`、`sum()`、`average()`、`count()`、`filter`、`map`、`forEach`、`distinct` —— 全部手工循环。
- `median` 必须先调用你自己写的 `sortedByHand()`（练习"用自己写的扩展代替标准库"）。
- 不许用 `break`/`continue`、不许递归、不许 lambda、不许解构、不许 `when`。
- 返回类型注意：`rangeOfValues` 返回 `Int`，`median` 返回 `Double`（偶数个元素时取中间两个的**平均值**）。

**验收标准**：
- `listOf(3, 1, 4, 1, 5).secondMax()` → `4`（注意去重后是 {1,3,4,5}，第二大为 4）
- `listOf(1, 1, 1).secondMax()` 抛异常；`listOf(5).secondMax()` 抛异常
- `listOf(5, 5, 3).secondMax()` → `3` ← **最大值重复时，第二大指的是"第二大的不同值"，不是另一个 5**
- `listOf(5, 2, 9).rangeOfValues()` → `7`
- `listOf(1, 2, 3, 4).median()` → `2.5`；`listOf(3, 1, 2).median()` → `2.0`
- `listOf(1, 2, 1).countOccurrences(1)` → `2`
- `listOf(3, 1, 2).sortedByHand()` → `[1, 2, 3]`，且**原列表仍为 `[3, 1, 2]`**
- `listOf(1, 2, 3).isSortedAscending()` → `true`；`listOf(1, 3, 2)` → `false`

> `secondMax` 是最容易写错的一题：请用一次遍历维护"最大"和"第二大"两个变量，并特意测试 `[5, 5, 3]` 这种有重复最大值的输入。

---

### 27. 为"第三方类"添加扩展（Product）

**文件**：
- `src/main/kotlin/round01/external/Product.kt` → `package round01.external`
- `src/main/kotlin/round01/Ex27ProductExt.kt` → `package round01`

**知识点**：Extension Functions、Creating Classes、Properties、Lists、String 处理

**任务**：

**(a)** 在 `package round01.external` 里定义（**视为第三方代码，本轮不许修改它**）：

```kotlin
class Product(val name: String, val priceInCents: Long)
```

**(b)** 在 `package round01` 里，用**扩展函数**为它补上功能：

- `fun Product.priceInYuan(): String` —— `1999` → `"19.99"`；`5` → `"0.05"`；`1000` → `"10.00"`
  （**手工格式化**，不许 `String.format`；注意补零）
- `fun Product.withDiscount(percent: Int): Product` —— 返回**新的** `Product`，价格按百分比下调并**向下取整**到分；`percent` 不在 `0..100` 时抛 `IllegalArgumentException`
- `fun Product.isAffordable(budgetInCents: Long): Boolean`
- `fun Product.describe(): String` —— 形如 `"Widget: 19.99 元"`
- `fun List<Product>.totalPriceInCents(): Long`
- `fun List<Product>.mostExpensive(): Product` —— 空列表抛 `IllegalArgumentException`；并列时返回**名字字典序最小**的

**约束**：
- 不许修改 `Product` 的源码（不许给它加方法、不许加扩展属性）。
- 不许用 `data class`、不许 `String.format`、不许 lambda、不许 `when`、不许解构、不许 `sortedBy`/`maxBy`。
- `priceInYuan` 的补零逻辑请手工处理（`19.99`、`0.05`、`10.00` 三种形态都要对）。

**验收标准**：
- `Product("A", 1999).priceInYuan()` → `"19.99"`
- `Product("A", 5).priceInYuan()` → `"0.05"` ← **易错：别输出 `0.5`**
- `Product("A", 1000).priceInYuan()` → `"10.00"`
- `Product("A", 0).priceInYuan()` → `"0.00"`
- `Product("A", 1999).withDiscount(10).priceInCents` → `1799L`，且原对象价格仍为 `1999L`
- `Product("A", 1999).withDiscount(101)` 抛异常
- `Product("A", 1999).isAffordable(2000)` → `true`；`isAffordable(1999)` → `true`；`isAffordable(1998)` → `false`
- `listOf(Product("A",100), Product("B",250)).totalPriceInCents()` → `350L`
- `listOf(Product("B",100), Product("A",100)).mostExpensive().name` → `"A"`（并列取字典序最小）
- `Product("Widget", 1999).describe()` → **必须恰好等于** `"Widget: 19.99 元"`
- 折扣边界：`Product("A", 1999).withDiscount(0).priceInCents` → `1999L`；`withDiscount(100).priceInCents` → `0L`

---

### 28. 命令行风格参数解析扩展（本轮收官题）

**文件**：`Ex28FlagParsing.kt`　**知识点**：Extension Functions、Lists、Sets、Maps、Exceptions、Loops

**任务**：为 `String` 和 `List<String>` 写扩展函数，模拟命令行参数解析：

- `fun String.isValidFlag(): Boolean` —— 合法形如 `-x`（短）或 `--xyz`（长）；即：以 `-` 开头、去掉前导 `-` 后剩余部分非空、且**全部是字母**。`"-"`、`""`、`"abc"`、`"-a1"`、`"---"` 都非法
- `fun List<String>.flagNames(): Set<String>` —— 提取所有合法 flag 名（**保持原样、去重**）
- `fun List<String>.valueOfFlag(flag: String): String` —— 返回紧随该 flag 之后的那个元素；flag 不存在、或后面没有元素、或该 flag 非法时抛 `IllegalArgumentException`
- `fun List<String>.hasFlag(flag: String): Boolean`
- `fun List<String>.positionalArgs(): List<String>` —— 返回"位置参数"：自身不是合法 flag，**且**它前面紧邻的元素也不是合法 flag（也就是它不是任何 flag 的值）。按原顺序返回
- `fun List<String>.toFlagMap(): Map<String, String>` —— 把 `["-a","1","-b","2"]` 变成 `{-a=1, -b=2}`；遇到"flag 后面没有值"时抛 `IllegalArgumentException`

**约束**：
- 不许用 `Regex`、不许 lambda、不许 `when`、不许解构（`toFlagMap` 里遍历 Map 时用 `keys`）、不许 `break`/`continue`。
- 判断字母必须用 `in` 配区间（`c in 'a'..'z' || c in 'A'..'Z'`），不许用 `isLetter()`。
- 不许用可空类型。注意 `valueOfFlag`：**下标越界要用 `if (index < list.size)` 先判断**，不要靠 `try/catch` 兜底（想清楚为什么"能预判就不该用异常"）。
- `positionalArgs` 的判定规则就是"往前看一格"：下标 0 没有前一个元素，**这是最容易写出越界的地方**；其余元素只要前一个不是合法 flag，它就算位置参数。

**验收标准**：
- `"-v".isValidFlag()` → `true`；`"--verbose".isValidFlag()` → `true`
- `"-".isValidFlag()`、`"".isValidFlag()`、`"abc".isValidFlag()`、`"-a1".isValidFlag()`、`"---".isValidFlag()` → 全部 `false`
- `listOf("-a", "1", "-b", "2").flagNames()` → `{-a, -b}`
- `listOf("-a", "1", "-b", "2").valueOfFlag("-a")` → `"1"`
- `listOf("-a", "1").valueOfFlag("-z")` 抛异常；`listOf("-a").valueOfFlag("-a")` 抛异常（后面没值）
- `listOf("-v", "file.txt", "-o").positionalArgs()` → `[]`
  （`file.txt` 紧跟在 flag `-v` 之后，所以它是 `-v` 的值；`-o` 自身是 flag。两个都不算位置参数）
- `listOf("input.txt", "-o", "out.txt", "extra").positionalArgs()` → `["input.txt", "extra"]`
  （`input.txt` 前面没有元素；`out.txt` 紧跟在 flag `-o` 之后所以是值；`extra` 前一个是 `out.txt`，不是 flag，所以它算位置参数）
- `listOf("-a", "1", "-b", "2").toFlagMap()` → 内容等价于 `{-a=1, -b=2}`
- `listOf("-a").toFlagMap()` 抛 `IllegalArgumentException`

> 这题把"语义边界"摊开给你看：`positionalArgs` 的规则已经给死（往前看一格），但**"flag 后面没值"这件事在不同函数里的处理方式并不相同** —— 它是 `toFlagMap` 的错误条件，却不影响 `positionalArgs`（`-o` 后面没值，只意味着 `-o` 是个 flag，不产生任何位置参数）。
> 请在代码顶部用注释写下这条规则，并保证实现与之一致 —— **"先定义语义、再实现、再自测"是工程能力，不只是语法能力。**

---

# 综合项目练习（4 个）

> 规则：每个项目都要**独立成文件**（可以多个文件），必须用到**至少 6 个不同 atom**的知识，并且必须包含**扩展函数**（因为这是你现在学的新东西）。
> 每个项目都提供两种输入方式，任选其一：
> **(A) 交互版**：用 `readln()` 读输入（唯一允许的超范围项）；
> **(B) 无输入版**：把用户指令做成 `List<String>` 队列（如 `listOf("add BookA", "list", "quit")`），逐条消费。
> 我**推荐先写 (B) 版** —— 它可复现、可自动验证，我 review 时也能直接跑出结果。

---

### 项目 P1 · 控制台图书管理系统

**文件**：`ProjectP1Library.kt`

**必须覆盖的知识点**：Creating Classes、Properties、Constructors、Constraining Visibility、Lists、Maps、Sets、Exceptions、Property Accessors、Extension Functions、Loops

**功能需求**：

1. `class Book(val isbn: String, val title: String, val author: String, var available: Boolean)`
   - `val summary: String` 计算属性，形如 `"[978-7-111] Kotlin 实战 / 张三 / 可借"`
   - 构造时校验：isbn、title、author 均非空且无首尾空格，否则抛 `IllegalArgumentException`
2. `class Library`：
   - `private val booksByIsbn: MutableMap<String, Book>`
   - `private val borrowedBy: MutableMap<String, String>` —— isbn → 借阅人
   - `fun add(book: Book): Boolean` —— isbn 重复时返回 `false` 且不改动任何状态
   - `fun borrow(isbn: String, borrower: String): Boolean` —— 不存在或已借出时返回 `false` 且**状态不变**
   - `fun giveBack(isbn: String): Boolean`
   - `fun findByAuthor(author: String): List<Book>` —— 手工循环，大小写不敏感
   - `fun availableBooks(): List<Book>`
   - `fun borrowedBooks(): List<Book>`
   - `fun report(): String` —— 多行统计报表：总数、可借数、已借数、以及每个借阅人和他借的书
3. 扩展函数（**必须写在 `Library` 类之外**）：
   - `fun Library.titlesOfAvailable(): Set<String>`
   - `fun Library.isFullyBorrowed(): Boolean` —— 非空且全部借出
4. 命令解析：`fun runLibrary(commands: List<String>): String`
   - 支持 `add <isbn> <title> <author>`、`borrow <isbn> <name>`、`return <isbn>`、`list`、`report`、`quit`
   - 非法命令要输出可读的错误行（**不许让整个程序崩掉**）；`quit` 之后停止处理后续命令
   - 注意：这里**不能用 `break`**！请用 `while` 条件 + 布尔标志（如 `var running = true`）实现"遇到 quit 就停"

**验收标准（请务必自测这几条）**：
- 加两本同 isbn 的书，第二本返回 `false`，`report()` 里总数仍为 1
- 借出后再借同一本返回 `false`
- `borrow` 一个不存在的 isbn 返回 `false`
- `giveBack` 后该书重新变为可借
- 故意给一条只有 2 个字段的 `add` 命令：程序**输出错误行且继续运行**，不抛异常到顶层
- `quit` 之后的命令不被执行
- 借阅人为空字符串时 `borrow` 返回 `false`

---

### 项目 P2 · 学生成绩分析器

**文件**：`ProjectP2Scores.kt`

**必须覆盖的知识点**：Variable Argument Lists、Lists、Maps、Sets、Exceptions、Number Types、Property Accessors、Extension Functions、Loops、String Templates

**功能需求**：

1. `class Student(val id: String, val name: String)`，构造校验 id/name 非空
2. `class ScoreSheet(private val maxScore: Int)`：
   - `fun addScore(student: Student, score: Int)` —— score 必须在 `0..maxScore` 内，否则抛 `IllegalArgumentException`；同一学生可加多次（记全部成绩）
   - `val count: Int`、`val average: Double`、`val highest: Int`、`val lowest: Int` —— **全部为计算属性**（`get() = ...`），空数据时 `average` 等抛 `IllegalStateException`
   - `fun scoresOf(student: Student): List<Int>`
   - `fun passRate(): Double` —— 及格线为 `maxScore * 0.6`（**取整规则自己定义并写进注释**）
   - `fun distribution(buckets: Int): Map<String, Int>` —— 把 `0..maxScore` 等分成 `buckets` 段，返回每段的计数；key 形如 `"0-19"`
   - `fun ranking(): List<Student>` —— 按**平均分降序**；平均分相同时按 **id 升序**
   - `fun report(): String` —— 多行报表
3. 扩展函数：
   - `fun List<Int>.median2(): Double` —— 直接复用你第 26 题手工排序的思路（**注意：这里函数名不能和第 26 题重名冲突，所以叫 `median2`**）
   - `fun ScoreSheet.topN(n: Int): List<Student>` —— 前 n 名；`n` 非法时抛 `IllegalArgumentException`
   - `fun ScoreSheet.studentsById(): Set<String>`
4. `fun summarize(vararg scores: Int): String` —— 用 vararg 直接给一组成绩，返回 `"n=5 min=10 max=90 avg=48.0"` 之类的一行摘要（平均分保留 1 位小数，手工格式化）

**验收标准**：
- 加 3 个学生、各 2 次成绩，`count` → `6`
- 加一个越界成绩（`-1` 或 `maxScore + 1`）抛 `IllegalArgumentException`，且**不影响已有统计**
- `ranking()` 在平均分相同时按 id 升序（请专门造一组并列数据验证）
- `distribution(5)` 的 key 数量为 5，且所有计数之和等于总成绩数
- `passRate()` 在全部及格时 → `1.0`，全部不及格时 → `0.0`
- `summarize(10, 20, 90)` 的返回包含 `n=3`、`min=10`、`max=90`
- `topN(0)` / `topN(-1)` 抛异常

---

### 项目 P3 · 银行账户管理系统

**文件**：`ProjectP3Bank.kt`

**必须覆盖的知识点**：Constraining Visibility、Properties、Constructors、Exceptions、Maps、Lists、Sets、Extension Functions、Loops、Number Types、Property Accessors

**功能需求**：

1. `class Account(val number: String, val owner: String, private var balance: Long)`
   - 构造校验：number 非空且无空格；`balance >= 0`；否则抛 `IllegalArgumentException`
   - `val balanceCents: Long` 只读；**不允许外部写入**
   - `fun deposit(cents: Long)`、`fun withdraw(cents: Long)`（余额不足抛 `IllegalStateException`）
   - `private val history: MutableList<String>` + `fun historySnapshot(): List<String>`（返回**副本**）
   - `fun statement(): String`
2. `class Bank`：
   - `private val accounts: MutableMap<String, Account>`
   - `private val closedNumbers: MutableSet<String>` —— 已销户的账号（**销户后不允许重新开户**）
   - `fun open(number: String, owner: String, initial: Long): Boolean` —— 已存在或已销户时返回 `false`
   - `fun close(number: String): Boolean` —— 余额不为 0 时返回 `false`（并要求在 `report()` 里说明原因）
   - `fun transfer(from: String, to: String, cents: Long): Boolean` —— **任一环节失败时两边余额都必须保持原样**（先校验全部前置条件，再动手扣款）
   - `fun totalAssets(): Long`
   - `fun topAccounts(n: Int): List<Account>` —— 按余额降序，余额并列时按账号升序；`n` 非法抛异常
   - `fun report(): String`
3. 扩展函数：
   - `fun Account.isZeroBalance(): Boolean` —— 注意：`balanceCents` 是公开的只读属性，所以这个扩展**能**读到它。请同时在注释里回答：**如果 `balance` 不是公开只读属性，这个扩展函数还能写吗？为什么？**（这是扩展函数边界的关键理解）
   - `fun Bank.allOwners(): Set<String>`
   - `fun Bank.isKnown(number: String): Boolean`
4. `fun runBank(commands: List<String>): String` —— 支持 `open`、`deposit`、`withdraw`、`transfer`、`close`、`report`、`quit`；同样**不能用 `break`**，用布尔标志退出；命令解析失败要输出错误行并继续

**验收标准**：
- 开户后 `report()` 含该账号
- 重复开户返回 `false`；销户后再开户返回 `false`
- 余额非 0 时销户返回 `false`
- `transfer` 超出余额时返回 `false`，且**双方余额都不变**（核心断言）
- 自己转给自己：请定义语义（拒绝或等价于无操作）并写进注释，保证自测与之一致
- `totalAssets()` 等于所有账户余额之和
- `withdraw` 负数金额抛异常
- `historySnapshot()` 返回的列表被外部修改后，账户内部历史不受影响
- `quit` 后停止处理

---

### 项目 P4 · 井字棋（Tic-Tac-Toe）双人对战

**文件**：`ProjectP4TicTacToe.kt`

**必须覆盖的知识点**：Lists、Sets、Maps、Exceptions、Loops、The in Keyword、Properties、Constraining Visibility、Extension Functions、String Templates

**功能需求**：

1. `class Board`：
   - 用 `private val cells: MutableList<Char>` 存 9 个格子，初始全为 `'.'`
   - `fun cellAt(row: Int, col: Int): Char` —— 下标越界抛 `IllegalArgumentException`
   - `fun place(row: Int, col: Int, mark: Char): Boolean` —— mark 只能是 `'X'`/`'O'`；该格已被占用时返回 `false` 且状态不变
   - `fun isFull(): Boolean`
   - `fun winner(): Char` —— 返回 `'X'` / `'O'` / `'.'`（无胜者时返回 `'.'`，**不用可空类型**）
   - `fun render(): String` —— 3 行 3 列，用 `|` 分隔，形如 `"X|O|.\n.|X|.\n.|.|X"`
   - `fun availableMoves(): List<Int>` —— 返回空格的下标列表
2. `class Game`：
   - `private var current: Char = 'X'`
   - `fun board(): Board`（返回内部棋盘；想想这样暴露合不合适，在注释里写你的判断）
   - `fun play(row: Int, col: Int): String` —— 返回一行结果描述；非法落子要返回错误说明而**不抛异常**；成功落子后切换 `current`
   - `fun isOver(): Boolean` —— 有胜者或棋盘已满
   - `fun result(): String`
   - `fun reset()`
3. 扩展函数：
   - `fun List<Char>.hasTriple(mark: Char): Boolean` —— 判断 9 个格子里是否存在三连（行 / 列 / 对角线）
   - `fun Board.marksCount(mark: Char): Int`
   - `fun Board.lineIndices(): List<List<Int>>` —— 返回全部 8 条获胜线的下标组合（**注意：这里不能自定义泛型，但 `List<List<Int>>` 是使用已有泛型类型，允许**）
4. `fun runGame(moves: List<String>): String` —— 每条命令形如 `"1 2"`（行 列），输出每步后的棋盘与结果；非法输入输出错误行并继续；有人获胜后停止处理（同样**不用 `break`**）

**验收标准**：
- `Board()` 初始 `isFull()` → `false`，`winner()` → `'.'`
- 同一格落子两次，第二次返回 `false` 且棋盘不变
- `place(0,0,'X')`、`place(0,1,'X')`、`place(0,2,'X')` 后 `winner()` → `'X'`
- 所有 8 条线都能被正确识别（请逐一构造测试：3 行、3 列、2 对角线）
- 平局场景 `winner()` → `'.'` 且 `isFull()` → `true`
- `mark = 'Z'` 抛 `IllegalArgumentException`
- `cellAt(3, 0)` 抛 `IllegalArgumentException`
- `render()` 恰好 3 行、每行恰好 5 个字符（`X|O|.` 形式）
- `lineIndices()` 返回 **8** 条线，每条线恰好 3 个下标
- 有人获胜后，后续命令不再改变棋盘（用 `runGame` 验证）

> 小提示：`place` 越界检查要**先**做（`row` / `col` 在 `0..2`），再做占用检查 —— 顺序错了会出现"下标越界异常泄漏到调用方"，这是很典型的 bug。

---

# 附：本轮自测清单（提交前自己过一遍）

- [ ] `gradlew.bat --offline compileKotlin` 通过，**没有任何 warning 被忽略**（尤其 `unused variable` / `variable never modified`）
- [ ] `gradlew.bat run`（`Main.kt`）里每道题都至少跑过一次，`Check` 输出符合"验收标准"
- [ ] 全项目搜索一遍：**没有出现** `when`、`?:`、`!!`、`data class`、`require(`、`break`、`continue`、`recursive`、`filter`、`map {`、`forEach`、`sorted`、`joinToString`
- [ ] 所有"不该被外部修改"的状态都是 `private`（余额、栈内部列表、棋格……）
- [ ] 所有"抛异常后状态不变"的断言都真的自测过（第 15、16、28 题 + P1、P3）
- [ ] 每个扩展函数都写在**被扩展类的源码之外**（第 27 题尤其要注意：`Product` 源码一行都不许改）
- [ ] 每个 `TODO()` 都被替换掉了（搜一下 `TODO` 还剩几个）

完成后告诉我，我会真实编译并逐题 review，然后根据暴露的问题生成 Round 02。

---

# 附录 B · 当 Check 说 FAIL 时怎么排查（含"看起来一样却不相等"）

> 这一节是从第 1 题的真实失分里长出来的，不是理论。

## B.1 先按顺序读 Check 的输出

`checkString` 失败时会打印四行，照这个顺序读：

```
FAIL  describe(0.0)   expected=[0.0°C = 32.0°F]   actual=[0.0℃ = 32.0℉]
      长度: expected=14  actual=12          <-- ① 长度不同，先看这里
      首个不同处在下标 3                     <-- ② 直接跳到这个下标
        expected[3] = '°'  U+B0             <-- ③ 码点才是真相
        actual[3]   = '℃'  U+2103
```

1. **长度**：长度不同，说明有字符被"合并"或"漏掉"了。这是最快的信号。
2. **下标**：不用逐字符找，直接定位。
3. **码点**：`°C` 是**两个**字符（U+00B0 + U+0043），`℃` 是**一个**字符（U+2103）。渲染几乎一样，字节完全不同。

## B.2 为什么会这样

Kotlin/Java 的 `String` 比较是**按码点逐个比**，而不是"看起来像就算相等"。
`℃` 是 CJK 兼容字符，很多字体把它渲染得和 `°C` 一模一样 —— 所以**人眼不可靠，只有程序可靠**。

`equalsIgnoreCase` 也救不了：它只忽略大小写，不会合并不同的码点。

## B.3 常见"长得像"的字符（踩过一次就记住）

| 看起来 | 你以为的 | 实际可能是 | 码点对比 |
|---|---|---|---|
| `°` | 度符号 | CJK 摄氏度合体字 | U+00B0 vs U+2103 |
| `°F` | 度符号 + F | CJK 华氏度合体字 | U+00B0+U+0046 vs U+2109 |
| `( )` | 半角括号 | 全角括号 | U+0028 vs U+FF08 |
| `,` | 半角逗号 | 全角逗号 | U+002C vs U+FF0C |
| 空格 | 普通空格 | 不换行空格 / 全角空格 | U+0020 vs U+00A0 / U+3000 |
| `"` | 英文引号 | 中文弯引号 | U+0022 vs U+201C / U+201D |
| `-` | 连字符 | 短/长破折号 | U+002D vs U+2013 / U+2014 |
| `1` `l` `I` | 数字一 | 小写 L / 大写 I | U+0031 vs U+006C vs U+0049 |
| （看不见） | 什么都没有 | 零宽空格 / BOM | U+200B / U+FEFF |

这些在真实项目里会造成：测试莫名失败、数据库里出现"重复"记录、搜索匹配不到、接口对接报错。**不是你粗心，是工具默认不告诉你。**

## B.4 三个排查手法

**手法 1 —— 打印长度**：`println(actual.length)`。和期望差几个字符，立刻就知道有没有"合体字"。

**手法 2 —— 逐字符打印码点**：把下面这个辅助函数加进 `Main.kt`（只用你学过的语法，没有 lambda）：

```kotlin
// 把字符串逐字符打印成码点，用来找"看起来一样"的字符
fun dumpCodes(text: String) {
    var i = 0
    while (i < text.length) {
        val ch = text[i]
        println("  [$i] '$ch'  U+" + ch.code.toString(16).uppercase())
        i = i + 1
    }
}
```

**手法 3 —— 看 IDEA 状态栏**：把光标放到可疑字符上，右下角状态栏会显示它的 Unicode 码点。

## B.5 预防（比排查更值钱）

- **期望字符串一律从规格里复制，不要手打。** 手打 `°C` 时，中文输入法很可能给你的是 `℃`。
- **固定格式提成常量**：`private const val DEG_C = "°C"`，全项目只写一次。
- **永远用 `==` 判断，不要用眼睛判断。** 眼睛分不出 U+00B0 和 U+2103，`Check` 一秒就能。
- 真要手输 `°`：Windows 下按住 Alt + 小键盘 `0176`；但最省事的仍然是复制。

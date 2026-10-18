package round01.drills

// ============================================================
// Part C 加练 · 属性访问器与 init 专项（4 题）
//
// 这 4 题每个只练一个机制，做完全部大约 20 分钟：
//   D1 init 校验          D2 自定义 setter
//   D3 委托式 setter      D4 field 关键字
//
// 做完 D3，第 15 题（Temperature）的骨架你就已经会写了。
// ============================================================


// ------------------------------------------------------------
// D1 · Score：只练 init 校验
//
// 要求：value 必须在 0..100，否则构造时抛 IllegalArgumentException
// 例子：Score(85) 合法；Score(-1)、Score(101) 都抛异常
// 注意：value 是 val，没有 setter，所以校验只能放在 init 里
// ------------------------------------------------------------
class Score(val value: Int) {

    init {
        TODO("TODO: 校验 value in 0..100，否则抛 IllegalArgumentException")
    }

    // 下面这个已经写好了，作为"计算属性"的示范（注意：没有 backing field）
    val passed: Boolean
        get() = value >= 60
}


// ------------------------------------------------------------
// D2 · Tank：只练自定义 setter
//
// 要求：
//   1. capacity 必须 > 0，否则抛 IllegalArgumentException（init 里做）
//   2. level 的 setter：值必须在 0..capacity，否则抛 IllegalArgumentException
//   3. 失败时 levelValue 不能被改（先校验、后赋值）
// 例子：Tank(10).level = 5 合法；= -1 或 = 11 抛异常，且 level 保持原值
// ------------------------------------------------------------
class Tank(val capacity: Int) {

    private var levelValue: Int = 0

    init {
        TODO("TODO: capacity 必须 > 0")
    }

    var level: Int
        get() = levelValue
        set(value) {
            TODO("TODO: 先校验 0..capacity，再写 levelValue")
        }

    val isEmpty: Boolean
        get() = levelValue == 0

    val isFull: Boolean
        get() = levelValue == capacity
}


// ------------------------------------------------------------
// D3 · Weight：练"委托式 setter" + "构造也走校验"
//      —— 这题和第 15 题 Temperature 结构完全一样，只是换成重量
//
// 要求：
//   1. grams 的 setter：必须 >= 0 且有限（isFinite），否则抛 IllegalArgumentException
//   2. kilograms 的 getter：gramsValue / 1000.0
//   3. kilograms 的 setter：换算成克之后"交给 grams 的 setter"（不要重复写校验！）
//   4. init：构造时也走 grams 的 setter，这样 Weight(-1.0) 会抛异常
//   5. describe()：形如 "1500.0g / 1.5kg"
//
// 注意声明顺序：init 必须写在它要赋值的属性"之后"，
// 否则编译报 "variable cannot be initialized before declaration"
// ------------------------------------------------------------
class Weight(grams: Double) {

    private var gramsValue: Double = 0.0

    var grams: Double
        get() = gramsValue
        set(value) {
            TODO("TODO: 校验 >= 0 且有限，再写 gramsValue")
        }

    var kilograms: Double
        get() = gramsValue / 1000.0
        set(value) {
            TODO("TODO: 换算成克，然后交给 grams 的 setter")
        }

    init {
        TODO("TODO: 构造时也走 grams 的 setter，别忘了")
    }

    fun describe(): String = TODO("TODO: \"\${grams}g / \${kilograms}kg\"")
}


// ------------------------------------------------------------
// D4 · Account：练 field 关键字
//
// 当你只想"校验一下、然后照常存进背后的字段"时，不需要额外声明
// 一个 xxxValue 属性 —— 在访问器里用 field 就能指代背后的存储：
//
//     var balance: Int = 0
//         set(value) {
//             if (value < 0) throw IllegalArgumentException(...)
//             field = value          // field 就是背后的存储
//         }
//
// 要求：
//   1. balance 的 setter：< 0 抛 IllegalArgumentException，否则 field = value
//   2. deposit(amount)：amount <= 0 抛 IllegalArgumentException；否则 balance += amount
//   3. withdraw(amount)：amount <= 0 抛 IllegalArgumentException；
//      余额不足抛 IllegalStateException；否则 balance -= amount
// ------------------------------------------------------------
class Account {

    var balance: Int = 0
        set(value) {
            TODO("TODO: value < 0 抛 IllegalArgumentException，否则 field = value")
        }

    fun deposit(amount: Int) {
        TODO("TODO: amount <= 0 抛 IllegalArgumentException，否则 balance += amount")
    }

    fun withdraw(amount: Int) {
        TODO("TODO: 金额非法抛 IllegalArgumentException；余额不足抛 IllegalStateException；否则 balance -= amount")
    }
}

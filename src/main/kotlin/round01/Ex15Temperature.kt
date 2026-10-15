package round01

// 第 15 题 · Temperature 类与自定义 getter / setter
// celsius 的 setter 负责校验（< -273.15 抛 IllegalArgumentException）
// fahrenheit 的 setter 必须委托给 celsius 的 setter，不要重复写校验
// 注意：构造时的初值也必须走校验 —— 下面这种直接赋值的写法是**故意留下的坑**，请自己改对
// 不许 data class、不许 require()

class Temperature(celsius: Double) {

    private var celsiusValue: Double = celsius

    var celsius: Double
        get() = celsiusValue
        set(value) {
            TODO("TODO: 校验 value >= -273.15（否则抛 IllegalArgumentException），再写入 celsiusValue")
        }

    var fahrenheit: Double
        get() = TODO("TODO: 由 celsiusValue 换算得到")
        set(value) {
            TODO("TODO: 换算成摄氏，再委托给 celsius 的 setter")
        }

    fun describe(): String = TODO()
}

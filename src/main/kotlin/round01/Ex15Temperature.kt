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
            if (value < -273.15) throw IllegalArgumentException("低于绝对零度")
            celsiusValue = value
        }

    init {
        this.celsius = celsius
    }

    var fahrenheit: Double
        get() = celsiusValue * 9 / 5 + 32.0
        set(value) {
            celsius = (value - 32.0) * 5 / 9
        }

    fun describe(): String = "${celsius}°C / ${fahrenheit}°F"
}

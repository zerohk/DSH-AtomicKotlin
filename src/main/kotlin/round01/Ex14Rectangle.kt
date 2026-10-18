package round01

// 第 14 题 · Rectangle 类与计算属性
// width/height 为 val 且不可为负（构造时校验，抛 IllegalArgumentException）
// area / perimeter / isSquare 必须是 val ... get() = ...（不许有 backing field）
// 不许 data class、不许 require()、不许 lambda

class Rectangle(val width: Double, val height: Double) {

    init {
        if (!width.isFinite() || !height.isFinite() || width < 0 || height < 0)
            throw IllegalArgumentException("width/height 必须是非负的有限数")
    }

    val area: Double
        get() = width * height

    val perimeter: Double
        get() = 2 * (width + height)

    val isSquare: Boolean
        get() = width == height

    fun describe(): String =
        "${width}x$height area=${this.area} perimeter=${this.perimeter} square=${this.isSquare}"
}

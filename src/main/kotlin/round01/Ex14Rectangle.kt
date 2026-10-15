package round01

// 第 14 题 · Rectangle 类与计算属性
// width/height 为 val 且不可为负（构造时校验，抛 IllegalArgumentException）
// area / perimeter / isSquare 必须是 val ... get() = ...（不许有 backing field）
// 不许 data class、不许 require()、不许 lambda

class Rectangle(val width: Double, val height: Double) {

    init {
        TODO("TODO: width / height 不可为负，且必须是有限数")
    }

    val area: Double
        get() = TODO()

    val perimeter: Double
        get() = TODO()

    val isSquare: Boolean
        get() = TODO()

    fun describe(): String = TODO()
}

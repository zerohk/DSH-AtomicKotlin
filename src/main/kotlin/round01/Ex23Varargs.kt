package round01

// 第 23 题 · vararg 工具集与 spread 运算符
// 不许 joinToString、不许 kotlin.math、不许 lambda/when、不许解构

// 空参返回 0L
fun sumAll(vararg nums: Int): Long = TODO()

// 空参抛 IllegalArgumentException
fun average(vararg nums: Double): Double = TODO()

// 空参抛 IllegalArgumentException；手工比较，不许 nums.max()
fun maxAll(vararg nums: Int): Int = TODO()

// 空 parts 返回 ""；单元素不加分隔符
fun joinWith(separator: String, vararg parts: String): String = TODO()

// 必须用 spread 运算符 * 把数组传给 sumAll
fun demoSpread(values: IntArray): Long = TODO()

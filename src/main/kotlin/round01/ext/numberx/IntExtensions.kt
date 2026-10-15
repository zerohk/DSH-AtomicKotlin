package round01.ext.numberx

// 第 25 题 (a) · Int 的扩展函数，必须定义在本包内，
// 这样才能在 Ex25IntExt.kt 里"必须 import 才可用"。
// 不许 kotlin.math、lambda、when、解构、递归

fun Int.isEven(): Boolean = TODO()

fun Int.isOdd(): Boolean = TODO()

// 独立实现一遍（不许调用第 10 题的 isPrime）
fun Int.isPrimeX(): Boolean = TODO()

// 负数抛 IllegalArgumentException
fun Int.factorialX(): Long = TODO()

// 各位数字之和；负数按绝对值处理：-1234 -> 10
fun Int.digitsSum(): Int = TODO()

// 位数；0 返回 1
fun Int.digitsCount(): Int = TODO()

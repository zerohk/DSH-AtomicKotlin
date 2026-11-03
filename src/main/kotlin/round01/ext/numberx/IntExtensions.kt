package round01.ext.numberx

// 第 25 题 (a) · Int 的扩展函数，必须定义在本包内，
// 这样才能在 Ex25IntExt.kt 里"必须 import 才可用"。
// 不许 kotlin.math、lambda、when、解构、递归

fun Int.isEven(): Boolean = this % 2 == 0

fun Int.isOdd(): Boolean = this % 2 != 0

// 独立实现一遍（不许调用第 10 题的 isPrime）
fun Int.isPrimeX(): Boolean {
    if (this <= 1) return false
    for (i in 2..this - 1) {
        if (this % i == 0) return false
    }
    return true
}

// 负数抛 IllegalArgumentException
fun Int.factorialX(): Long {
    if (this < 0) throw IllegalArgumentException("参数不能为负数")
    var factorial = 1L
    for (i in 1..this) factorial *= i
    return factorial
}

// 各位数字之和；负数按绝对值处理：-1234 -> 10
fun Int.digitsSum(): Int {
    var num = if (this < 0) -this else this
    var sumOfDigits = 0
    while (num > 0) {
        sumOfDigits += num % 10
        num /= 10
    }
    return sumOfDigits
}

// 位数；0 返回 1
fun Int.digitsCount(): Int {
    var num = if (this < 0) -this else this
    var digits = 1
    while (num / 10 > 0) {
        digits++
        num /= 10
    }
    return digits
}


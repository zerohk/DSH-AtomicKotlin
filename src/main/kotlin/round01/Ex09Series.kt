package round01

// 第 9 题 · 累加、阶乘与斐波那契（迭代版）
// 严禁递归；不许用求和公式；n < 0 抛 IllegalArgumentException

fun sumTo(n: Int): Long {
    if (n < 0) throw IllegalArgumentException("$n 为负数")
    var sum: Long = 0
    for (i in 1..n) {
        sum += i
    }
    return sum
}

// factorial(21)会超过LONG_MAX,产生溢出
fun factorial(n: Int): Long {
    if (n < 0) throw IllegalArgumentException("$n 为负数")
    if (n == 0 || n == 1) return 1
    var result: Long = 1
    for (i in 2..n) result *= i
    return result
}

// 约定 fib(0) == 0L，fib(1) == 1L
fun fib(n: Int): Long {
    if (n < 0) throw IllegalArgumentException("$n 为负数")
    if (n == 0) return 0
    if (n == 1) return 1
    var count = 1
    var fibPreOne: Long = 0
    var fibPreTwo: Long = 1
    var fib: Long = fibPreOne + fibPreTwo
    while (count <= n) {
        fib = fibPreOne + fibPreTwo
        fibPreTwo = fibPreOne
        fibPreOne = fib
        count++
    }
    return fib
}
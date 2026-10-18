package round01

// 第 11 题 · FizzBuzz 与区间步进
// 不许 when、不许 joinToString、不许 break/continue

// 3 的倍数 Fizz；5 的倍数 Buzz；15 的倍数 FizzBuzz；否则数字本身。每行一个，用 "\n" 连接
fun fizzBuzzUpTo(n: Int): String {
    if (n <= 0) return ""
    val result = mutableListOf<String>()
    for (i in 1..n) {
        if (i % 15 == 0) result.add("FizzBuzz")
        else if (i % 5 == 0) result.add("Buzz")
        else if (i % 3 == 0) result.add("Fizz")
        else result.add(i.toString())
    }
    var resultStr = ""
    for (i in 0 until result.size - 1) {
        resultStr = resultStr + result[i] + "\n"
    }
    return resultStr + result[result.size - 1]
}

// from 到 to 每隔 step 取一个；step <= 0 抛异常；from > to 返回空列表
fun everyNth(from: Int, to: Int, step: Int): List<Int> {
    if (step <= 0) throw IllegalArgumentException("$step 为负数")
    val resultList = mutableListOf<Int>()
    for (i in from..to step step) {
        resultList.add(i)
    }
    return resultList
}

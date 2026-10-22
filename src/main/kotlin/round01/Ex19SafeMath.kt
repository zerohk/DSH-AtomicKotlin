package round01

// 第 19 题 · 安全求商与整数解析
// 不许 toIntOrNull()、不许可空类型（? ?: !!）、不许 require()/check()、不许 lambda/when

// 用 try/catch 捕获 ArithmeticException；成功 "结果: 5"，除零 "除数不能为 0"
fun divideWithTryCatch(a: Int, b: Int): String {
    return try {
        "结果: ${a / b}"
    } catch (e: ArithmeticException) {
        "除数不能为 0"
    }
    // 我更倾向这种写法，因为能处理更多异常输入
}

// 不用 try/catch，先判断再算，返回同样的字符串
fun divideWithCheck(a: Int, b: Int): String = if (b == 0) "除数不能为 0" else "结果: ${a / b}"

// 用 try/catch 捕获 NumberFormatException；成功且 > 0 才返回，否则抛 IllegalArgumentException("不是正整数")
fun parsePositiveInt(text: String): Int {
    val n = try {
        text.toInt()
    } catch (e: NumberFormatException) {
        throw IllegalArgumentException("不是正整数")
    }
    return if (n <= 0) throw IllegalArgumentException("不是正整数") else n
}

// 逐个解析累加；遇到第一个非法项就抛 IllegalArgumentException（消息含该文本）
fun sumOfInts(texts: List<String>): Long {
    var sum = 0L
    for (text in texts) {
        try {
            sum += text.toLong()
        } catch (e: NumberFormatException) {
            throw IllegalArgumentException("参数$text 格式错误")
        }
    }
    return sum
}

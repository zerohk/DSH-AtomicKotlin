package round01

// 第 19 题 · 安全求商与整数解析
// 不许 toIntOrNull()、不许可空类型（? ?: !!）、不许 require()/check()、不许 lambda/when

// 用 try/catch 捕获 ArithmeticException；成功 "结果: 5"，除零 "除数不能为 0"
fun divideWithTryCatch(a: Int, b: Int): String = TODO()

// 不用 try/catch，先判断再算，返回同样的字符串
fun divideWithCheck(a: Int, b: Int): String = TODO()

// 用 try/catch 捕获 NumberFormatException；成功且 > 0 才返回，否则抛 IllegalArgumentException("不是正整数")
fun parsePositiveInt(text: String): Int = TODO()

// 逐个解析累加；遇到第一个非法项就抛 IllegalArgumentException（消息含该文本）
fun sumOfInts(texts: List<String>): Long = TODO()

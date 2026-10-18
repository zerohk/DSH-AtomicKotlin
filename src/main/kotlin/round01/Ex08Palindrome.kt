package round01

// 第 8 题 · 数字反转与回文判断
// 不许 break/continue、不许递归、不许 reversed()、不许重载（所以同名函数要换名字）

// 1234 -> 4321；-123 -> -321；末尾的 0 反转后消失：1200 -> 21、10 -> 1
fun reverseNumber(n: Int): Int {
    var num = if (n >= 0) n else -n
    var result = 0
    while (num >= 1) {
        result = result * 10 + num % 10
        num /= 10
    }
    return if (n >= 0) result else -result
}

fun isPalindromeNumber(n: Int): Boolean = n == reverseNumber(n)

// 区分大小写；不许用 reversed()；"" 与单字符都算回文（返回 true）
fun isPalindromeText(text: String): Boolean {
    if (text.isEmpty()) return true
    val length = text.length
    var i = 0
    var j = length - 1
    while (i < j) {
        if (text[i] != text[j]) return false
        i++
        j--
    }
    return true
}


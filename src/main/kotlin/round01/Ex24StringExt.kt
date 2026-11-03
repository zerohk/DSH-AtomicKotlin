package round01

// 第 24 题 · String 扩展函数包（必须是扩展函数，不能改成普通函数）
// 不许 Regex、lambda、when、break/continue、解构；不许 reversed()/filter/map

// 忽略大小写与所有空格
fun String.isPalindrome(): Boolean {
    // 忽略大小写与空格，先改成小写
    val str = this.lowercase()
    // 再去掉所有空格
    var noBlankStr = ""
    for (c in str) {
        if (c != ' ') noBlankStr += c
    }
    // 遍历字符串，只要两头不相等，就返回false
    val length = noBlankStr.length
    for (i in 0..length - 1) {
        if (noBlankStr[i] != noBlankStr[length - 1 - i]) return false
    }
    return true
}

// 大写并在末尾加 "!"；空串返回 "!"
fun String.shout(): String =
    if (!this.isEmpty() && this[this.length - 1] == '!') this.uppercase() else this.uppercase() + "!"

// "john ronald reuel tolkien" -> "J.R.R.T."
fun String.initials(): String {
    if (this.isEmpty()) return this
    val strArr = this.split(" ")
    var result = ""
    for (str in strArr) {
        if (!str.isEmpty())
            result += str[0].uppercase() + "."
    }
    return result
}

// 连续空格算一个分隔
fun String.wordCount(): Int {
    var count = 0
    for(c in this.split(" ")) {
        if(c.isNotEmpty()) count++
    }
    return count
}

// 手工反转，不许用 reversed()
fun String.reverseByHand(): String {
    var result = ""
    for (i in this.length - 1 downTo 0) {
        result += this[i]
    }
    return result
}


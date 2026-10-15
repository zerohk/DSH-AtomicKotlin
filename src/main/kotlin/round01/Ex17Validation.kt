package round01

// 第 17 题 · Email 与 Password 校验类
// 不许 Regex、不许 require()、不许 lambda、不许 when、不许解构
// 判断字符必须用 in 配区间（c in '0'..'9' 等），不许用 isDigit()/isUpperCase()

class Email(address: String) {

    init {
        TODO("TODO: 非空、无空格、恰好一个 @、@ 前后非空、域名含 .")
    }

    val address: String = address

    fun domain(): String = TODO()

    fun localPart(): String = TODO()
}

class Password(private val raw: String) {

    init {
        TODO("TODO: raw.length >= 8，否则抛 IllegalArgumentException(\"密码太短\")")
    }

    // 计算属性：含数字 +1、含大写 +1、含小写 +1（0..3）
    val strength: Int
        get() = TODO()

    // "Abc12345" -> "********"
    fun masked(): String = TODO()
}

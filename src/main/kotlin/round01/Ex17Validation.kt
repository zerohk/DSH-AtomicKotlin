package round01

// 第 17 题 · Email 与 Password 校验类
// 不许 Regex、不许 require()、不许 lambda、不许 when、不许解构
// 判断字符必须用 in 配区间（c in '0'..'9' 等），不许用 isDigit()/isUpperCase()

class Email(address: String) {

    init {
        // 地址为空，或者包含空格则抛异常
        if (address.isEmpty() || " " in address) throw IllegalArgumentException("地址不能为空，不能包含空格，且只能包含一个@，且@前后不能包含空格，域名需包含.")
        // 统计@个数，不等于1则抛异常
        var countChar = 0
        for (char in address) {
            if (char == '@') countChar++
        }
        if (countChar != 1) throw IllegalArgumentException("地址不能为空，不能包含空格，且只能包含一个@，且@前后不能包含空格，域名需包含.")
        // @前后不为空，则说明@不在最前或最后
        if (address[0] == '@' || address[address.length - 1] == '@') throw IllegalArgumentException("地址不能为空，不能包含空格，且只能包含一个@，且@前后不能包含空格，域名需包含.")
        // @之后需为包含.的域名
        if ('.' !in address.substringAfter('@')) throw IllegalArgumentException("地址不能为空，不能包含空格，且只能包含一个@，且@前后不能包含空格，域名需包含.")
    }

    val address: String = address

    fun domain(): String = address.substringAfter('@')

    fun localPart(): String = address.substringBefore('@')
}

class Password(private val raw: String) {

    init {
        if (raw.length < 8) throw IllegalArgumentException("密码太短")
    }

    // 计算属性：含数字 +1、含大写 +1、含小写 +1（0..3）
    val strength: Int
        get() {
            var isHaveNum = 0
            var isHaveUpper = 0
            var isHaveLower = 0
            for (c in raw) {
                if (c in '0'..'9') isHaveNum = 1
                if (c in 'a'..'z') isHaveLower = 1
                if (c in 'A'..'Z') isHaveUpper = 1
            }
            return isHaveNum + isHaveLower + isHaveUpper
        }

    // "Abc12345" -> "********"
    fun masked(): String {
        var result = ""
        for (i in 1..raw.length) {
            result += '*'
        }
        return result
    }
}



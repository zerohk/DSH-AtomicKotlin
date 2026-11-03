package round01

// 第 28 题 · 命令行风格参数解析扩展（本轮收官题）
// 不许 Regex、lambda、when、解构、break/continue
// 判断字母必须用 in 配区间，不许 isLetter()
//
// positionalArgs 规则：自身不是合法 flag，且它前面紧邻的元素也不是合法 flag
// （下标 0 没有前一个元素 —— 注意别越界）

fun String.isValidFlag(): Boolean {
    // 字符串长度小于2，直接返回false
    if (this.length < 2) return false
    // 第一位不是 - 直接返回false
    if (this[0] != '-') return false
    var start = 1
    if (this[1] == '-') start = 2
    if (start >= this.length) return false
    for (i in start until this.length) {
        if (this[i] !in 'a'..'z' && this[i] !in 'A'..'Z') return false
    }
    return true
}

fun List<String>.flagNames(): Set<String> {
    val resultSet = mutableSetOf<String>()
    for (flag in this) {
        if (flag.isValidFlag()) resultSet.add(flag)
    }
    return resultSet
}

// flag 不存在、或后面没有元素、或 flag 非法时抛 IllegalArgumentException
fun List<String>.valueOfFlag(flag: String): String {
    // flag不存在时，抛出异常
//    if (!this.contains(flag)) throw IllegalArgumentException("$flag 不存在")
    // 改用扩展函数判断
    if (!this.hasFlag(flag)) throw IllegalArgumentException("$flag 不存在")
    // flag非法时，抛出异常
    if (!flag.isValidFlag()) throw IllegalArgumentException("$flag 有误")
    // 获取flag在List中的索引
    val index = this.indexOf(flag)
    // 如果是最后一项，表明flag后无元素，直接抛异常
    if (index == this.size - 1) throw IllegalArgumentException("$flag 后无元素")
    // index后一位不是flag，则返回；如果还是flag，则抛异常
    if (!this[index + 1].isValidFlag()) return this[index + 1] else throw IllegalArgumentException("$flag 后为flag")
}

fun List<String>.hasFlag(flag: String): Boolean {
    // 非法flag抛异常---这里不抛异常，只判断存不存在，合不合法交给上层函数处理
//    if (!flag.isValidFlag()) throw IllegalArgumentException("$flag 有误")
    return this.contains(flag)
}

fun List<String>.positionalArgs(): List<String> {
    // 先定义一个位置参数列表，用于接收并返回位置参数
    val argsList = mutableListOf<String>()
    // 如果第一个参数不是flag，则是位置参数
    if (!this.isEmpty()) {
        if (!this[0].isValidFlag()) argsList.add(this[0])
        // 以索引遍历，从索引1开始，前一个元素不是合法flag,且自身不是flag,则为位置参数
        for (i in 1..this.size - 1) {
            if (!this[i].isValidFlag() && !this[i - 1].isValidFlag()) argsList.add(this[i])
        }
    }
    return argsList
}

// ["-a","1","-b","2"] -> {-a=1, -b=2}；flag 后面没值时抛 IllegalArgumentException
fun List<String>.toFlagMap(): Map<String, String> {
    val resultMap = mutableMapOf<String, String>()
    var i = 0
    while (i < this.size) {
        if (this[i].isValidFlag()) {
            if (i + 1 >= this.size) throw IllegalArgumentException("flag ${this[i]} 后无值")
            if (this[i + 1].isValidFlag()) throw IllegalArgumentException("flag ${this[i]} 后无值")
            resultMap[this[i]] = this[i + 1]
            i += 2                     // flag 和它的值一起跳过
        } else {
            i += 1
        }
    }
    return resultMap
}

fun main() {
    println("--".isValidFlag())
}


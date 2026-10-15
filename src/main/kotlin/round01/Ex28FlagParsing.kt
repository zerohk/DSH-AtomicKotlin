package round01

// 第 28 题 · 命令行风格参数解析扩展（本轮收官题）
// 不许 Regex、lambda、when、解构、break/continue
// 判断字母必须用 in 配区间，不许 isLetter()
//
// positionalArgs 规则：自身不是合法 flag，且它前面紧邻的元素也不是合法 flag
// （下标 0 没有前一个元素 —— 注意别越界）

fun String.isValidFlag(): Boolean = TODO()

fun List<String>.flagNames(): Set<String> = TODO()

// flag 不存在、或后面没有元素、或 flag 非法时抛 IllegalArgumentException
fun List<String>.valueOfFlag(flag: String): String = TODO()

fun List<String>.hasFlag(flag: String): Boolean = TODO()

fun List<String>.positionalArgs(): List<String> = TODO()

// ["-a","1","-b","2"] -> {-a=1, -b=2}；flag 后面没值时抛 IllegalArgumentException
fun List<String>.toFlagMap(): Map<String, String> = TODO()

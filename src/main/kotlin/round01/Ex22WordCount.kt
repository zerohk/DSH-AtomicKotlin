package round01

// 第 22 题 · 词频统计与 Map 遍历
// 遍历 Map 必须用 for (key in counts.keys)；取值用 counts.getValue(key)
// 严禁解构 for ((k, v) in ...)；严禁 groupingBy/count/forEach/sortedBy

fun wordCount(text: String): Map<String, Int> = TODO()

// 次数最多；并列取字典序最小；空 Map 抛 IllegalArgumentException
fun mostFrequent(counts: Map<String, Int>): String = TODO()

// 恰好出现 n 次的词
fun wordsWithCount(counts: Map<String, Int>, n: Int): Set<String> = TODO()

// 所有词频之和
fun totalWords(counts: Map<String, Int>): Int = TODO()

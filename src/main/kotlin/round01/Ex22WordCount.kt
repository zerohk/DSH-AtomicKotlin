package round01

// 第 22 题 · 词频统计与 Map 遍历
// 遍历 Map 必须用 for (key in counts.keys)；取值用 counts.getValue(key)
// 严禁解构 for ((k, v) in ...)；严禁 groupingBy/count/forEach/sortedBy

fun wordCount(text: String): Map<String, Int> {
    val strArr = text.split(" ")
    val result = mutableMapOf<String, Int>()
    for (word in strArr) {
        if (word != " " && word.isNotEmpty())
            result[word] = result.getOrDefault(word, 0) + 1
    }
    return result
}

// 次数最多；并列取字典序最小；空 Map 抛 IllegalArgumentException
fun mostFrequent(counts: Map<String, Int>): String {
    if (counts.isEmpty()) throw IllegalArgumentException("map为空")
    var frequencyTimes = 0
    var frequencyWord = ""
    var isSmallest = true
    for (key in counts.keys) {
        val times = counts.getValue(key)
        if (isSmallest) {
            frequencyTimes = times
            frequencyWord = key
            isSmallest = false
        } else if (times > frequencyTimes || (times == frequencyTimes && key < frequencyWord)) {
            frequencyTimes = times
            frequencyWord = key
        }
    }
    return frequencyWord
}

// 恰好出现 n 次的词
fun wordsWithCount(counts: Map<String, Int>, n: Int): Set<String> {
    val resultSet = mutableSetOf<String>()
    for (key in counts.keys) {
        if (counts.getValue(key) == n)
            resultSet.add(key)
    }
    return resultSet
}

// 所有词频之和
fun totalWords(counts: Map<String, Int>): Int {
    var totalCounts = 0
    for (key in counts.keys) {
        totalCounts += counts.getValue(key)
    }
    return totalCounts
}


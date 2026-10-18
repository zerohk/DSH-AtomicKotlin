package round01

// 第 13 题 · 图形打印（嵌套循环）
// 不许 repeat、不许 joinToString、不许 lambda；n <= 0 返回 ""

fun rightTriangle(n: Int): String {
    var result = ""
    for (i in 1..n - 1) {
        for (j in 1..i) {
            result += "*"
        }
        result += "\n"
    }
    for (i in 1..n) {
        result += "*"
    }
    return result
}

// 共 n 行，每行长度都是 2 * n - 1，星号居中
fun pyramid(n: Int): String {
    var result = ""
    val TOTAL_WIDTH = 2 * n - 1
    // 前n-1行，每一行最后添加\n
    for (i in 1..n - 1) {
        // 这一行的*数量
        val starNum = 2 * i - 1
        // 两端空格数量 = (TOTAL_WIDTH - starNum) / 2
        val blackNum = (TOTAL_WIDTH - starNum) / 2
        // 先添加左侧空格，然后添加*，再添加右侧空格
        for (a in 1..blackNum) {
            result += " "
        }
        for (b in 1..starNum) {
            result += "*"
        }
        for (c in 1..blackNum) {
            result += " "
        }
        result += "\n"
    }
    // 最后一行直接添加2n-1个*
    for (i in 1..2 * n - 1) {
        result += "*"
    }
    return result
}

// 每列占 4 个字符、右对齐；每行长度都是 4 * n
fun multiplicationTable(n: Int): String {
    val COL_WIDTH = 4
    if (n > 31) throw IllegalArgumentException("$n 太大：4 位列宽最多支持到 31")
    var result = ""
    for (i in 1..n) {
        for (j in 1..n) {
            // 先求出乘积
            val product = i * j
            // 如果乘积的字符大于等于4，只取前4位
            val numWidth = product.toString().length

            // 小于4，则在前补空格
            val blackNum = COL_WIDTH - numWidth
            for (c in 1..blackNum) {
                result += " "
            }
            // 然后加上乘积
            result += product

        }
        // 不是最后一行则在最后加上\n
        if (i != n) {
            result += "\n"
        }
    }
    return result
}


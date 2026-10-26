package round01

// 第 21 题 · 手工集合运算
// 全部手工循环：禁止 distinct/intersect/union/subtract/filter/forEach/map

// 去重且保持首次出现顺序
fun uniqueInOrder(values: List<Int>): List<Int> {
    val result = mutableSetOf<Int>()
    for (value in values) {
        result.add(value)
    }
    return result.toList()
}

fun intersectionOf(a: Set<Int>, b: Set<Int>): Set<Int> {
    val result = mutableSetOf<Int>()
    for (valueA in a) {
        for (valueB in b) {
            if (valueA == valueB) {
                result.add(valueA)
            }
        }
    }
    return result
}

fun unionOf(a: Set<Int>, b: Set<Int>): Set<Int> {
    val result = mutableSetOf<Int>()
    for (value in a) result.add(value)
    for (value in b) result.add(value)
    return result
}

// 在 a 中但不在 b 中
fun differenceOf(a: Set<Int>, b: Set<Int>): Set<Int> {
    val result = mutableSetOf<Int>()
    for (valueA in a) {
        var flag = true
        for (valueB in b) {
            if (valueA == valueB) flag = false
        }
        if (flag) result.add(valueA)
    }
    return result
}

// 只在一个集合里出现
fun symmetricDifferenceOf(a: Set<Int>, b: Set<Int>): Set<Int> = unionOf(differenceOf(a, b), differenceOf(b, a))



package round01

// 第 26 题 · List<Int> 的扩展统计工具
// 严禁 sorted()/max()/min()/sum()/average()/count()/filter/map/forEach/distinct —— 全部手工循环
// 不许 break/continue、不许递归、不许 lambda/解构/when

// 第二大"不同"的值；不足 2 个或全相等时抛 IllegalArgumentException
fun List<Int>.secondMax(): Int {
    // 元素不足两个时，抛异常
    if (this.size < 2) throw IllegalArgumentException("元素个数不能小于2")
    // 可以将元素放入set中，去重
    val numSet = mutableSetOf<Int>()
    for (num in this) {
        numSet.add(num)
    }
    // 如果set只有一个元素，表明全等，抛异常
    if (numSet.size == 1) throw IllegalArgumentException("元素不能全等")
    // 再将set转为list
    val numList = numSet.toList()
    // 比较新list第一二个元素大小，分别赋值给最大和次大
    // 直接比较原list会出现[5,5,3]->5的结果
    var max = if (numList[0] > numList[1]) numList[0] else numList[1]
    var secondMax = if (numList[0] < numList[1]) numList[0] else numList[1]
    // 遍历set，找出最大和次大
    for (num in numSet) {
        // 如果元素大于最大，则将次大值更新为原来最大值；最大值更新为新的最大值，即当前元素
        if (num > max) {
            secondMax = max
            max = num
        }
    }
    return secondMax
}

// 最大值 - 最小值；空列表抛 IllegalArgumentException
fun List<Int>.rangeOfValues(): Int {
    // 空列表抛异常
    if (this.isEmpty()) throw IllegalArgumentException("列表为空")
    var max = this[0]
    var min = this[0]
    for (num in this) {
        if (num > max) max = num
        if (num < min) min = num
    }
    return max - min
}

// 中位数；空列表抛 IllegalArgumentException；偶数个取中间两个的平均
fun List<Int>.median(): Double {
    // 空列表抛异常
    if (this.isEmpty()) throw IllegalArgumentException("列表为空")
    // 先对列表按小到大排序
    // 调用排序
    val numList = this.sortedByHand()
    // 如果个数为奇数，则取中间那个
    return if (size % 2 != 0)
        numList[size / 2].toDouble()
    else
        (numList[size / 2] + numList[size / 2 - 1]) / 2.0
}

fun List<Int>.countOccurrences(target: Int): Int {
    var count = 0
    for (num in this) {
        if (num == target) count++
    }
    return count
}

// 手工排序，返回新列表，不修改原列表
fun List<Int>.sortedByHand(): List<Int> {
    // 冒泡排序
    val numList = this.toMutableList()
    val size = numList.size
    for (i in 0..size - 1) {
        var minIndex = i
        for (j in i..size - 1) {
            if (numList[j] < numList[minIndex]) {
                minIndex = j
            }
        }
        val tmp = numList[i]
        numList[i] = numList[minIndex]
        numList[minIndex] = tmp
    }
    return numList
}

fun List<Int>.isSortedAscending(): Boolean {
    // 将原始列表与排序后列表逐元素比较，如果不等，则直接返回false
//    val sortedList = this.sortedByHand()
    for (i in 0..this.size - 1) {
//        if (sortedList[i] != this[i]) return false
        // 直接比较当前元素与下一个元素大小即可，时间复杂度会更小O(n)
        if (this[i] > this[i + 1]) return false
    }
    return true
}


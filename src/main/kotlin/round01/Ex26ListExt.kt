package round01

// 第 26 题 · List<Int> 的扩展统计工具
// 严禁 sorted()/max()/min()/sum()/average()/count()/filter/map/forEach/distinct —— 全部手工循环
// 不许 break/continue、不许递归、不许 lambda/解构/when

// 第二大"不同"的值；不足 2 个或全相等时抛 IllegalArgumentException
fun List<Int>.secondMax(): Int = TODO()

// 最大值 - 最小值；空列表抛 IllegalArgumentException
fun List<Int>.rangeOfValues(): Int = TODO()

// 中位数；空列表抛 IllegalArgumentException；偶数个取中间两个的平均
fun List<Int>.median(): Double = TODO()

fun List<Int>.countOccurrences(target: Int): Int = TODO()

// 手工排序，返回新列表，不修改原列表
fun List<Int>.sortedByHand(): List<Int> = TODO()

fun List<Int>.isSortedAscending(): Boolean = TODO()

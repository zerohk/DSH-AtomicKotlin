package round01

// 第 23 题 · vararg 工具集与 spread 运算符
// 不许 joinToString、不许 kotlin.math、不许 lambda/when、不许解构

// 空参返回 0L
fun sumAll(vararg nums: Int): Long {
    var sum = 0L
    for (num in nums) {
        sum += num
    }
    return sum
}

// 空参抛 IllegalArgumentException
fun average(vararg nums: Double): Double {
    if (nums.isEmpty()) throw IllegalArgumentException("参数为空")
    var sum = 0.0
    for (num in nums) {
        sum += num
    }
    return sum / nums.size
}

// 空参抛 IllegalArgumentException；手工比较，不许 nums.max()
fun maxAll(vararg nums: Int): Int {
    if (nums.isEmpty()) throw IllegalArgumentException("参数为空")
    var max = nums[0]
    for (i in 1 until nums.size) {
        if (nums[i] >= max) max = nums[i]
    }
    return max
}

// 空 parts 返回 ""；单元素不加分隔符
fun joinWith(separator: String, vararg parts: String): String {
    if (parts.isEmpty()) return ""
    if (parts.size == 1) return parts[0]
    var result = ""
    for (i in 0 until parts.size - 1) {
        result += parts[i] + separator
    }
    result += parts[parts.size - 1]
    return result
}

// 必须用 spread 运算符 * 把数组传给 sumAll
fun demoSpread(values: IntArray): Long = sumAll(*values)

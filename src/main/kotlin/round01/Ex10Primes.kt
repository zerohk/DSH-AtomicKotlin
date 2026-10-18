package round01

// 第 10 题 · 素数判定与素数表
// 不许 break；不许 kotlin.math.sqrt
// 上界必须写成 d <= n / d（除法）；写 d * d <= n 会在 isPrime(Int.MAX_VALUE) 处死循环
// primesUpTo 不许用 filter/forEach

fun isPrime(n: Int): Boolean {
    if (n <= 1) return false
    var start = 2
    while (start <= n / start) {
        if (n % start == 0) return false
        start++
    }
    return true
}

fun primesUpTo(limit: Int): List<Int> {
    val resultList = mutableListOf<Int>()
    for (i in 1..limit) {
        if (isPrime(i)) resultList.add(i)
    }
    return resultList
}

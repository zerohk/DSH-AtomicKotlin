package round01

// 第 12 题 · 最大公约数与最小公倍数
// 手工实现 absInt（不许 kotlin.math.abs）；gcd 只收 while 版（不许递归）

fun absInt(n: Int): Int = if (n >= 0) n else -n

fun gcd(a: Int, b: Int): Int {
    if (b == 0) return absInt(a)
//    if (a == 0) return b
    var a1 = absInt(a)
    var b1 = absInt(b)
    var mod = a1 % b1
    while (mod > 0) {
        a1 = b1
        b1 = mod
        mod = a1 % b1
    }
    return b1
}

// 先用 Long，且先除后乘；负数先取绝对值（lcm(-4, 6) == 12）；lcm(0, x) == 0
fun lcm(a: Int, b: Int): Long {
    if (a == 0 || b == 0) return 0
    val a1 = absInt(a)
    val b1 = absInt(b)
    return a1.toLong() / gcd(a1, b1) * b1
}



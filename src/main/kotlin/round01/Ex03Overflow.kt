package round01

// 第 3 题 · Int 溢出侦探
// 不许 try/catch、不许 kotlin.math、不许 lambda

fun willOverflow(a: Int, b: Int): Boolean = (a.toLong() + b.toLong()) > Int.MAX_VALUE ||
        (a.toLong() + b.toLong()) < Int.MIN_VALUE

fun safeAdd(a: Int, b: Int): Long = a.toLong() + b.toLong()

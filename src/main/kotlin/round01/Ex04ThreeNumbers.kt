package round01

// 第 4 题 · 三数极值与排序（只用 if 表达式）
// 禁止 a+b+c-max-min 这种写法；禁止 kotlin.math 的 maxOf/minOf；禁止 sorted()

fun maxOf3(a: Int, b: Int, c: Int): Int = if (a >= b && a >= c) a
else if (b >= a && b >= c) b else c

fun minOf3(a: Int, b: Int, c: Int): Int = if (a <= b && a <= c) a
else if (b <= a && b <= c) b else c

fun middleOf3(a: Int, b: Int, c: Int): Int =
    if (a >= b && a >= c) {
        if (b >= c) b
        else c
    } else if (b >= a && b >= c) {
        if (a >= c) a
        else c
    } else {
        if (a >= b) a
        else b
    }

// 期望：sort3(3, 9, 5) == "3,5,9"
fun sort3(a: Int, b: Int, c: Int): String =
    if (a >= b && a >= c) {
        if (b >= c) "$c,$b,$a"
        else "$b,$c,$a"
    } else if (b >= a && b >= c) {
        if (a >= c) "$c,$a,$b"
        else "$a,$c,$b"
    } else {
        if (a >= b) "$b,$a,$c"
        else "$a,$b,$c"
    }

package round01

// 第 6 题 · 闰年判定（必须是单表达式函数）

fun isLeapYear(year: Int): Boolean = (year % 400 == 0) || (year % 100 != 0 && year % 4 == 0)

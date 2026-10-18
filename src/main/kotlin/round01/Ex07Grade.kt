package round01

// 第 7 题 · 成绩等级（含非法输入）
// 90..100 -> 'A'，80..89 -> 'B'，70..79 -> 'C'，60..69 -> 'D'，0..59 -> 'F'
// 非法分数必须 throw IllegalArgumentException（消息里要带该分数）
// 不许 require()/check()、不许 when

fun grade(score: Int): Char = if (score in 90..100) 'A'
else if (score in 80..89) 'B'
else if (score in 70..79) 'C'
else if (score in 60..69) 'D'
else if (score in 0..59) 'F'
else throw IllegalArgumentException("$score")

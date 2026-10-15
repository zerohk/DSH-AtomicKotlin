package round01

import util.checkSummary

// ============================================================
// Round 01 自测入口（你可以自由修改、增删）
//
// 运行方式：
//   gradlew.bat --offline run
//   或在 IDEA 里直接点 main() 左边的小三角
//
// 用法：每实现完一题，就把对应的断言取消注释。
// 下面是三种常用断言写法，照抄改改即可。
// ============================================================

fun main() {
    println("=== Round 01 自测 ===")
    println("提示：把 Main.kt 里的断言逐条取消注释，实现一题、验证一题。")
    println()

    // ---------- 写法 1：普通值比较 ----------
    // checkString("Ex01 describe(0.0)", "0.0°C = 32.0°F", describe(0.0))
    // checkString("Ex01 describe(100.0)", "100.0°C = 212.0°F", describe(100.0))
    // checkInt("Ex06 闰年 2000", 1, if (isLeapYear(2000)) 1 else 0)

    // ---------- 写法 2：布尔 ----------
    // checkTrue("Ex06 isLeapYear(2000)", isLeapYear(2000))
    // checkFalse("Ex06 isLeapYear(1900)", isLeapYear(1900))

    // ---------- 写法 3：断言"抛异常"（本轮不能用 lambda，所以手写 try/catch）----------
    // var threw = false
    // try {
    //     grade(-1)
    // } catch (e: IllegalArgumentException) {
    //     threw = e.message!!.contains("-1")   // 注意：!! 在本轮是禁止项，这里只是演示；
    // }                                        // 你自己的代码里请改成先判空或用其它方式
    // checkTrue("Ex07 grade(-1) 抛 IllegalArgumentException", threw)

    // ---------- 集合类题目 ----------
    // checkIntList("Ex10 primesUpTo(20)", listOf(2,3,5,7,11,13,17,19), primesUpTo(20))
    // checkIntSet("Ex21 intersectionOf", setOf(2,3), intersectionOf(setOf(1,2,3), setOf(2,3,4)))
    // checkStringIntMap("Ex22 wordCount", mapOf("a" to 3, "b" to 2, "c" to 1), wordCount("a b a c b a"))

    checkSummary()
}

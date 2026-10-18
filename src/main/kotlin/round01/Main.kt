package round01

import util.checkBoolean
import util.checkDouble
import util.checkFalse
import util.checkInt
import util.checkIntList
import util.checkLong
import util.checkString
import util.checkSummary
import util.checkTrue


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
    checkString("Ex01 describe(0.0)", "0.0°C = 32.0°F", describe(0.0))
    checkString("Ex01 describe(100.0)", "100.0°C = 212.0°F", describe(100.0))
    checkInt("Ex06 闰年 2000", 1, if (isLeapYear(2000)) 1 else 0)
    checkInt("Ex04 maxOf3(3, 9, 10)", 10, maxOf3(3, 9, 10))
    checkInt("Ex04 maxOf3(3, 10, 2)", 10, maxOf3(3, 10, 2))
    checkInt("Ex04 maxOf3(1, 3, 3)", 3, maxOf3(1, 3, 3))
    checkInt("Ex04 maxOf3(4, 3, 3)", 4, maxOf3(4, 3, 3))
    checkInt("Ex04 maxOf3(4, 3, 6)", 6, maxOf3(4, 3, 6))
    checkInt("Ex04 maxOf3(4, 4, 6)", 6, maxOf3(4, 4, 6))
    checkInt("Ex04 minOf3(4, 4, 4)", 4, minOf3(4, 4, 4))
    checkInt("Ex04 minOf3(1, 4, 4)", 1, minOf3(1, 4, 4))
    checkInt("Ex04 minOf3(2, 1, 4)", 1, minOf3(2, 1, 4))
    checkInt("Ex04 minOf3(2, 1, 0)", 0, minOf3(2, 1, 0))
    checkInt("Ex04 minOf3(2, 1, 1)", 1, minOf3(2, 1, 1))
    checkInt("Ex04 minOf3(2, 1, 1)", 1, minOf3(2, 1, 1))
    checkInt("Ex04 middleOf3(1, 1, 1)", 1, middleOf3(1, 1, 1))
    checkInt("Ex04 middleOf3(1, 2, 1)", 1, middleOf3(1, 2, 1))
    checkInt("Ex04 middleOf3(1, 2, 2)", 2, middleOf3(1, 2, 2))
    checkInt("Ex04 middleOf3(3, 2, 1)", 2, middleOf3(3, 2, 1))
    checkInt("Ex04 middleOf3(3, 2, 2)", 2, middleOf3(3, 2, 2))
    checkInt("Ex04 middleOf3(3, 4, 2)", 3, middleOf3(3, 4, 2))
    checkInt("Ex04 middleOf3(6, 4, 2)", 4, middleOf3(6, 4, 2))
    checkInt("Ex04 middleOf3(3, 5, 9)", 5, middleOf3(3, 5, 9))
    checkInt("Ex04 middleOf3(9, 3, 5)", 5, middleOf3(9, 3, 5))
    checkString("Ex04 sort3(6, 6, 6)", "6,6,6", sort3(6, 6, 6))
    checkString("Ex04 sort3(1, 2, 3)", "1,2,3", sort3(1, 2, 3))
    checkString("Ex04 sort3(1, 3, 2)", "1,2,3", sort3(1, 3, 2))
    checkString("Ex04 sort3(5, 3, 1)", "1,3,5", sort3(5, 3, 1))
    checkString("Ex04 sort3(5, 5, 1)", "1,5,5", sort3(1, 5, 5))
    checkString("Ex04 sort3(5, 1, 1)", "1,1,5", sort3(5, 1, 1))
    checkString("Ex04 sort3(5, 3, 9)", "3,5,9", sort3(5, 3, 9))
    checkString("Ex04 sort3(5, 9, 3)", "3,5,9", sort3(5, 9, 3))
    checkString("Ex04 sort3(9, 3, 5)", "3,5,9", sort3(9, 3, 5))

    checkDouble("Ex05 bmi(70.0, 1.75)", 22.857142857142858, bmi(70.0, 1.75))
    checkString("Ex05 bmiCategory(18.4)", "偏瘦", bmiCategory(18.4))
    checkString("Ex05 bmiCategory(23.99)", "正常", bmiCategory(23.99))
    checkString("Ex05 bmiCategory(28.0)", "肥胖", bmiCategory(28.0))
    checkString("Ex05 formatOneDecimal(22.857142857142858)", "22.9", formatOneDecimal(22.857142857142858))
    checkString("Ex05 formatOneDecimal(18.0)", "18.0", formatOneDecimal(18.0))
    checkString("Ex05 formatOneDecimal(-2.0)", "-2.0", formatOneDecimal(-2.0))
    checkString("Ex05 formatOneDecimal(-3.456)", "-3.5", formatOneDecimal(-3.456))
    checkTrue("Ex06 isLeapYear(2000)", isLeapYear(2000))
    checkFalse("Ex06 isLeapYear(1900)", isLeapYear(1900))
    checkTrue("Ex06 isLeapYear(2024)", isLeapYear(2024))
    checkFalse("Ex06 isLeapYear(2023)", isLeapYear(2023))


    // ---------- 写法 2：布尔 ----------
    // checkTrue("Ex06 isLeapYear(2000)", isLeapYear(2000))
    // checkFalse("Ex06 isLeapYear(1900)", isLeapYear(1900))

    // ---------- 写法 3：断言"抛异常"（本轮不能用 lambda，所以手写 try/catch）----------
    var threw = false
    try {
        grade(-1)
    } catch (e: IllegalArgumentException) {
        threw = e.message!!.contains("-1")   // 注意：!! 在本轮是禁止项，这里只是演示；
    }                                        // 你自己的代码里请改成先判空或用其它方式
    checkTrue("Ex07 grade(-1) 抛 IllegalArgumentException", threw)

    checkInt("Ex08 reverseNumber(1234)", 4321, reverseNumber(1234))
    checkInt("Ex08 reverseNumber(0)", 0, reverseNumber(0))
    checkInt("Ex08 reverseNumber(-123)", -321, reverseNumber(-123))
    checkInt("Ex08 reverseNumber(-1200)", 21, reverseNumber(1200))
    checkInt("Ex08 reverseNumber(10)", 1, reverseNumber(10))
    checkTrue("Ex08 isPalindromeNumber(12321)", isPalindromeNumber(12321))
    checkFalse("Ex08 isPalindromeNumber(1234)", isPalindromeNumber(1234))
    checkTrue("Ex08 isPalindromeText(\"level\")", isPalindromeText("level"))
    checkTrue("Ex08 isPalindromeText(\"\")", isPalindromeText(""))
    checkTrue("Ex08 isPalindromeText(\"a\")", isPalindromeText("a"))
    checkTrue("Ex08 isPalindromeText(\"ababa\")", isPalindromeText("ababa"))
    checkFalse("Ex08 isPalindromeText(\"ab\")", isPalindromeText("ab"))
    checkFalse("Ex08 isPalindromeText(\"Level\")", isPalindromeText("Level"))
    checkLong("Ex09 sumTo(1)", 1L, sumTo(1))
    checkLong("Ex09 sumTo(100)", 5050L, sumTo(100))
    checkLong("Ex09 factorial(0)", 1L, factorial(0))
    checkLong("Ex09 factorial(5)", 120L, factorial(5))
    checkLong("Ex09 factorial(20)", 2432902008176640000L, factorial(20))
    checkLong("Ex09 fib(0)", 0L, fib(0))
    checkLong("Ex09 fib(1)", 1L, fib(1))
    checkLong("Ex09 fib(10)", 55L, fib(10))
    checkLong("Ex09 fib(50)", 12586269025L, fib(50))
    threw = false
    try {
        sumTo(-1)
    } catch (e: IllegalArgumentException) {
        threw = true   // 注意：!! 在本轮是禁止项，这里只是演示；
    }                                        // 你自己的代码里请改成先判空或用其它方式
    checkTrue("Ex09 sumTo(-1) 抛 IllegalArgumentException", threw)
    threw = false
    try {
        factorial(-1)
    } catch (e: IllegalArgumentException) {
        threw = true   // 注意：!! 在本轮是禁止项，这里只是演示；
    }                                        // 你自己的代码里请改成先判空或用其它方式
    checkTrue("Ex09 factorial(-1) 抛 IllegalArgumentException", threw)

    threw = false
    try {
        fib(-1)
    } catch (e: IllegalArgumentException) {
        threw = true   // 注意：!! 在本轮是禁止项，这里只是演示；
    }                                        // 你自己的代码里请改成先判空或用其它方式
    checkTrue("Ex09 fib(-1) 抛 IllegalArgumentException", threw)
    // ---------- 集合类题目 ----------
    checkTrue("Ex10 isPrime(2)",isPrime(2))
    checkTrue("Ex10 isPrime(3)",isPrime(3))
    checkTrue("Ex10 isPrime(97)",isPrime(97))
    checkTrue("Ex10 isPrime(Int.MAX_VALUE))",isPrime(Int.MAX_VALUE))
    checkFalse("Ex10 isPrime(1)",isPrime(1))
    checkFalse("Ex10 isPrime(4)",isPrime(4))
    checkFalse("Ex10 isPrime(100)",isPrime(100))
     checkIntList("Ex10 primesUpTo(20)", listOf(2,3,5,7,11,13,17,19), primesUpTo(20))
     checkIntList("Ex10 primesUpTo(1)", listOf(), primesUpTo(1))
     checkIntList("Ex10 primesUpTo(2)", listOf(2), primesUpTo(2))
    // checkIntSet("Ex21 intersectionOf", setOf(2,3), intersectionOf(setOf(1,2,3), setOf(2,3,4)))
    // checkStringIntMap("Ex22 wordCount", mapOf("a" to 3, "b" to 2, "c" to 1), wordCount("a b a c b a"))

    checkSummary()
}

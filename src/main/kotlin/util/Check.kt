package util

// 自测小工具（我已写好，你不必修改）。
// 刻意只用你已学过的语法：顶层属性、函数、if、字符串模板、变量。
//
// 注意：本轮不能使用 lambda / 高阶函数，所以这里**没有** "断言抛异常" 的现成函数。
// 验证异常的标准写法见 Main.kt 里的注释示例。

private var passed = 0
private var failed = 0

private fun pass(label: String) {
    passed = passed + 1
    println("PASS  $label")
}

private fun fail(label: String, expected: String, actual: String) {
    failed = failed + 1
    println("FAIL  $label   expected=[$expected]   actual=[$actual]")
}

fun checkInt(label: String, expected: Int, actual: Int) {
    if (expected == actual) pass(label) else fail(label, "$expected", "$actual")
}

fun checkLong(label: String, expected: Long, actual: Long) {
    if (expected == actual) pass(label) else fail(label, "$expected", "$actual")
}

fun checkBoolean(label: String, expected: Boolean, actual: Boolean) {
    if (expected == actual) pass(label) else fail(label, "$expected", "$actual")
}

fun checkTrue(label: String, actual: Boolean) {
    if (actual) pass(label) else fail(label, "true", "false")
}

fun checkFalse(label: String, actual: Boolean) {
    if (actual) fail(label, "false", "true") else pass(label)
}

fun checkString(label: String, expected: String, actual: String) {
    if (expected == actual) {
        pass(label)
        return
    }
    fail(label, expected, actual)
    reportFirstDiff(expected, actual)
}

// 专治"看起来一模一样"的失败：定位第一个不同的字符，并打印它的码点。
// 例：℃(U+2103) 与 °C(U+00B0 U+0043) 肉眼几乎无法区分，长度还不同（1 个字符 vs 2 个字符）。
private fun reportFirstDiff(expected: String, actual: String) {
    var i = 0
    var limit = expected.length
    if (actual.length < limit) limit = actual.length
    while (i < limit && expected[i] == actual[i]) i = i + 1

    println("      长度: expected=${expected.length}  actual=${actual.length}")
    println("      首个不同处在下标 $i")
    if (i < expected.length) println("        expected[$i] = ${charInfo(expected[i])}")
    else println("        expected[$i] = <已到末尾>")
    if (i < actual.length) println("        actual[$i]   = ${charInfo(actual[i])}")
    else println("        actual[$i]   = <已到末尾>")
}

private fun charInfo(ch: Char): String = "'$ch'  U+${ch.code.toString(16).uppercase()}"

fun checkContains(label: String, expectedPart: String, actual: String) {
    var i = 0
    var found = false
    while (i + expectedPart.length <= actual.length) {
        if (actual.substring(i, i + expectedPart.length) == expectedPart) {
            found = true
            i = actual.length
        } else {
            i = i + 1
        }
    }
    if (found) pass(label) else fail(label, "包含 $expectedPart", actual)
}

fun checkDouble(label: String, expected: Double, actual: Double) {
    var diff = expected - actual
    if (diff < 0.0) diff = -diff
    if (diff < 0.000001) pass(label) else fail(label, "$expected", "$actual")
}

fun checkIntList(label: String, expected: List<Int>, actual: List<Int>) {
    var ok = expected.size == actual.size
    var i = 0
    while (i < expected.size && i < actual.size) {
        if (expected[i] != actual[i]) ok = false
        i = i + 1
    }
    if (ok) pass(label) else fail(label, "$expected", "$actual")
}

fun checkIntSet(label: String, expected: Set<Int>, actual: Set<Int>) {
    if (expected == actual) pass(label) else fail(label, "$expected", "$actual")
}

fun checkStringSet(label: String, expected: Set<String>, actual: Set<String>) {
    if (expected == actual) pass(label) else fail(label, "$expected", "$actual")
}

fun checkStringIntMap(label: String, expected: Map<String, Int>, actual: Map<String, Int>) {
    if (expected == actual) pass(label) else fail(label, "$expected", "$actual")
}

fun checkStringStringMap(label: String, expected: Map<String, String>, actual: Map<String, String>) {
    if (expected == actual) pass(label) else fail(label, "$expected", "$actual")
}

// 返回失败数量，方便你一眼看出还有多少没做
fun checkSummary(): Int {
    println("----------------------------------------")
    println("PASS = $passed    FAIL = $failed")
    if (failed == 0) println("本轮全部通过") else println("还有 $failed 项没通过")
    return failed
}

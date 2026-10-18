package round01

// 第 1 题 · 温度转换与格式化
// 约束：全题只用 val；describe 必须是单表达式函数；不许 String.format / lambda / when

fun celsiusToFahrenheit(c: Double): Double = c * 9 / 5 + 32

fun fahrenheitToCelsius(f: Double): Double = (f - 32) * 5 / 9

// 期望：describe(0.0) == "0.0°C = 32.0°F"
fun describe(c: Double): String = "$c°C = ${celsiusToFahrenheit(c)}°F"

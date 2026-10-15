package round01

// 第 5 题 · BMI 计算与分级
// bmiCategory 必须是单表达式函数；formatOneDecimal 不许用 String.format / "%.1f"

fun bmi(weightKg: Double, heightM: Double): Double = TODO()

// 边界：<18.5 偏瘦；18.5~<24.0 正常；24.0~<28.0 超重；>=28.0 肥胖
fun bmiCategory(value: Double): String = TODO()

// 期望：formatOneDecimal(22.857142857142858) == "22.9"；formatOneDecimal(18.0) == "18.0"
fun formatOneDecimal(value: Double): String = TODO()

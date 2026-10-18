package round01

// 第 5 题 · BMI 计算与分级
// bmiCategory 必须是单表达式函数；formatOneDecimal 不许用 String.format / "%.1f"
// 陷阱：formatOneDecimal 对负数要先取绝对值再四舍五入。
//       直接写 (value*10+0.5).toInt() 会把 -2.0 算成 -1.9（toInt 是向零截断）

fun bmi(weightKg: Double, heightM: Double): Double = weightKg / (heightM * heightM)

// 边界：<18.5 偏瘦；18.5~<24.0 正常；24.0~<28.0 超重；>=28.0 肥胖
fun bmiCategory(value: Double): String = if (value < 18.5) "偏瘦"
else if (value >= 18.5 && value < 24.0) "正常"
else if (value >= 24.0 && value < 28.0) "超重"
else "肥胖"

// 期望：formatOneDecimal(22.857142857142858) == "22.9"；formatOneDecimal(18.0) == "18.0"
fun formatOneDecimal(value: Double): String =
    if (value >= 0.0) ((value * 10.0 + 0.5).toInt() / 10.0).toString()
    else (-(-value * 10.0 + 0.5).toInt() / 10.0).toString()



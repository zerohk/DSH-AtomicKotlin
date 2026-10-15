package round01

// 第 20 题 · finally 执行顺序实验
// 四个函数都把轨迹 add 进 log。请先在注释里写下你预测的 log，再运行验证。
// 不许可空类型、不许 require()、不许 lambda/when

// try 记录 "try" 并返回 "ok"；finally 记录 "finally"
fun runNormal(log: MutableList<String>): String = TODO()

// try 记录 "try" 并抛 IllegalStateException；catch 记录 "catch" 并返回 "caught"；finally 记录 "finally"
fun runThrowing(log: MutableList<String>): String = TODO()

// try 记录 "try" 并 return "from-try"；finally 记录 "finally"
fun runWithReturnInTry(log: MutableList<String>): String = TODO()

// try 记录 "try" 并抛 IllegalStateException("try")；catch 记录 "catch"；
// finally 记录 "finally" 并抛 IllegalArgumentException("finally")
fun runWithThrowInFinally(log: MutableList<String>): String = TODO()

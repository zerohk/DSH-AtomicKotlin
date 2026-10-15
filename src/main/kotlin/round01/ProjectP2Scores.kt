package round01

// 综合项目 P2 · 学生成绩分析器
// 计算属性全部用 val ... get() = ...；不许 lambda/when/解构

class Student(val id: String, val name: String) {

    init {
        TODO("TODO: id / name 非空，否则抛 IllegalArgumentException")
    }
}

class ScoreSheet(private val maxScore: Int) {

    // 同一学生可加多次；score 必须在 0..maxScore 内，否则抛 IllegalArgumentException
    fun addScore(student: Student, score: Int) { TODO() }

    val count: Int
        get() = TODO()

    // 空数据时抛 IllegalStateException
    val average: Double
        get() = TODO()

    val highest: Int
        get() = TODO()

    val lowest: Int
        get() = TODO()

    fun scoresOf(student: Student): List<Int> = TODO()

    // 及格线 maxScore * 0.6（取整规则自己定义并写进注释）
    fun passRate(): Double = TODO()

    // 把 0..maxScore 等分成 buckets 段，key 形如 "0-19"
    fun distribution(buckets: Int): Map<String, Int> = TODO()

    // 平均分降序；并列按 id 升序
    fun ranking(): List<Student> = TODO()

    fun report(): String = TODO()
}

fun List<Int>.median2(): Double = TODO()

// 前 n 名；n 非法抛 IllegalArgumentException
fun ScoreSheet.topN(n: Int): List<Student> = TODO()

fun ScoreSheet.studentsById(): Set<String> = TODO()

// 用 vararg 直接给一组成绩，返回 "n=5 min=10 max=90 avg=48.0" 之类的一行
fun summarize(vararg scores: Int): String = TODO()

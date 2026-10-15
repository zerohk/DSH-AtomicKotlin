package round01

// 综合项目 P4 · 井字棋（双人对战）
// 不用可空类型：winner() 无胜者时返回 '.'；不能用 break

class Board {

    private val cells: MutableList<Char> = mutableListOf(
        '.', '.', '.',
        '.', '.', '.',
        '.', '.', '.'
    )

    // 下标越界抛 IllegalArgumentException
    fun cellAt(row: Int, col: Int): Char = TODO()

    // mark 只能是 'X'/'O'；该格已占用返回 false 且状态不变
    fun place(row: Int, col: Int, mark: Char): Boolean = TODO()

    fun isFull(): Boolean = TODO()

    // 'X' / 'O' / '.'（无胜者）
    fun winner(): Char = TODO()

    // "X|O|.\n.|X|.\n.|.|X"  —— 恰好 3 行，每行恰好 5 个字符
    fun render(): String = TODO()

    // 空格的下标列表
    fun availableMoves(): List<Int> = TODO()
}

class Game {

    private val board: Board = Board()
    private var current: Char = 'X'

    // 想想这样直接暴露内部棋盘合不合适，在注释里写你的判断
    fun board(): Board = board

    // 非法落子返回错误说明而不抛异常；成功落子后切换 current
    fun play(row: Int, col: Int): String = TODO()

    fun isOver(): Boolean = TODO()

    fun result(): String = TODO()

    fun reset() { TODO() }
}

// 9 个格子里是否存在三连（行 / 列 / 对角线）
fun List<Char>.hasTriple(mark: Char): Boolean = TODO()

fun Board.marksCount(mark: Char): Int = TODO()

// 全部 8 条获胜线的下标组合
fun Board.lineIndices(): List<List<Int>> = TODO()

// 每条命令形如 "1 2"；输出每步后的棋盘与结果；有人获胜后停止处理
fun runGame(moves: List<String>): String = TODO()

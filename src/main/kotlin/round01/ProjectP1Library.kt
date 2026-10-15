package round01

// 综合项目 P1 · 控制台图书管理系统
// 推荐先写"无输入版"：runLibrary(commands) 消费一个指令队列（可复现、可自动验证）
// 注意：不能用 break —— 用布尔标志退出循环

class Book(val isbn: String, val title: String, val author: String, var available: Boolean) {

    init {
        TODO("TODO: isbn/title/author 均非空且无首尾空格，否则抛 IllegalArgumentException")
    }

    // "[978-7-111] Kotlin 实战 / 张三 / 可借"
    val summary: String
        get() = TODO()
}

class Library {

    private val booksByIsbn: MutableMap<String, Book> = mutableMapOf()
    private val borrowedBy: MutableMap<String, String> = mutableMapOf()

    // isbn 重复时返回 false 且不改动状态
    fun add(book: Book): Boolean = TODO()

    // 不存在或已借出返回 false 且状态不变
    fun borrow(isbn: String, borrower: String): Boolean = TODO()

    fun giveBack(isbn: String): Boolean = TODO()

    // 大小写不敏感，手工循环
    fun findByAuthor(author: String): List<Book> = TODO()

    fun availableBooks(): List<Book> = TODO()

    fun borrowedBooks(): List<Book> = TODO()

    fun report(): String = TODO()
}

// 扩展函数必须写在 Library 类之外
fun Library.titlesOfAvailable(): Set<String> = TODO()

fun Library.isFullyBorrowed(): Boolean = TODO()

// 支持 add / borrow / return / list / report / quit；非法命令输出错误行但不崩
fun runLibrary(commands: List<String>): String = TODO()

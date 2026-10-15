package round01

// 综合项目 P3 · 银行账户管理系统
// 核心断言：transfer 任一环节失败时，两边余额都必须保持原样
// 不许 lambda/when/解构；不能用 break

class Account(val number: String, val owner: String, private var balance: Long) {

    init {
        TODO("TODO: number 非空且无空格；balance >= 0；否则抛 IllegalArgumentException")
    }

    private val history: MutableList<String> = mutableListOf()

    val balanceCents: Long
        get() = balance

    fun deposit(cents: Long) { TODO() }

    // 余额不足抛 IllegalStateException
    fun withdraw(cents: Long) { TODO() }

    // 返回副本：外部修改它不能影响内部历史
    fun historySnapshot(): List<String> = TODO()

    fun statement(): String = TODO()
}

class Bank {

    private val accounts: MutableMap<String, Account> = mutableMapOf()
    private val closedNumbers: MutableSet<String> = mutableSetOf()

    // 已存在或已销户时返回 false
    fun open(number: String, owner: String, initial: Long): Boolean = TODO()

    // 余额不为 0 时返回 false
    fun close(number: String): Boolean = TODO()

    // 任一环节失败时两边余额保持原样
    fun transfer(from: String, to: String, cents: Long): Boolean = TODO()

    fun totalAssets(): Long = TODO()

    // 余额降序；并列按账号升序；n 非法抛异常
    fun topAccounts(n: Int): List<Account> = TODO()

    fun report(): String = TODO()
}

// 注释里回答：如果 Account.balance 不是公开只读属性，这个扩展还能写吗？为什么？
fun Account.isZeroBalance(): Boolean = TODO()

fun Bank.allOwners(): Set<String> = TODO()

fun Bank.isKnown(number: String): Boolean = TODO()

fun runBank(commands: List<String>): String = TODO()

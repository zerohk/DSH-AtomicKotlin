package round01

// 第 16 题 · BankAccount 类（封装 + 只读余额）
// balanceInCents 必须 private；对外只能只读；金额一律用 Long 的"分"
// 不许 data class、不许 require()/check()、不许 lambda、不许 when

class BankAccount(val owner: String) {

    private var balanceInCents: Long = 0

    // 只读暴露（只有 getter）
    val balanceInCentsValue: Long
        get() = balanceInCents

    // cents 必须 > 0，否则抛 IllegalArgumentException
    fun deposit(cents: Long) {
        if (cents <= 0) throw IllegalArgumentException("金额不能小于等于0")
        balanceInCents += cents
    }

    // cents 必须 > 0；余额不足抛 IllegalStateException("余额不足")
    fun withdraw(cents: Long) {
        if (cents <= 0) throw IllegalArgumentException("金额不能小于等于0")
        if (cents > balanceInCents) throw IllegalStateException("余额不足")
        balanceInCents -= cents
    }

    // 转账：任一环节失败时两边余额都必须保持原样
    fun transferTo(other: BankAccount, cents: Long) {
        if (cents <= 0) throw IllegalArgumentException("金额不能小于等于0")
        if (cents > this.balanceInCents) throw IllegalStateException("余额不足")
        this.withdraw(cents)
        other.deposit(cents)
    }

    fun statement(): String = "$owner: $balanceInCents 分"
}



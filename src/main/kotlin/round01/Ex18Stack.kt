package round01

// 第 18 题 · Stack 类（用 MutableList 实现，只存 Int）
// 内部集合必须 private；出栈用 items.removeAt(items.size - 1)（不许 removeLast/removeFirst）
// 不许 java.util.Stack / ArrayDeque、不许 lambda、不许 data class

class Stack {

    private val items: MutableList<Int> = mutableListOf()

    fun push(value: Int) { TODO() }

    // 空栈抛 IllegalStateException("栈为空")
    fun pop(): Int = TODO()

    // 空栈抛 IllegalStateException("栈为空")
    fun peek(): Int = TODO()

    fun size(): Int = TODO()

    fun isEmpty(): Boolean = TODO()

    // 用 size 手工判断
    fun isEmptyByHand(): Boolean = TODO()

    // 手工循环查找（不许 items.contains）
    fun contains(value: Int): Boolean = TODO()

    // 必须返回与内部解耦的快照副本
    fun toList(): List<Int> = TODO()

    fun clear() { TODO() }
}

package round01

// 第 18 题 · Stack 类（用 MutableList 实现，只存 Int）
// 内部集合必须 private；出栈用 items.removeAt(items.size - 1)（不许 removeLast/removeFirst）
// 不许 java.util.Stack / ArrayDeque、不许 lambda、不许 data class

class Stack {

    private val items: MutableList<Int> = mutableListOf()

    fun push(value: Int) {
        items.add(value)
    }

    // 空栈抛 IllegalStateException("栈为空")
    fun pop(): Int {
        if (items.isEmpty()) throw IllegalStateException("栈为空")
        val ele = items[items.size - 1]
        items.removeAt(items.size - 1)
        return ele
    }

    // 空栈抛 IllegalStateException("栈为空")
    fun peek(): Int = if (items.isEmpty()) throw IllegalStateException("栈为空") else items[items.size - 1]

    fun size(): Int = items.size

    fun isEmpty(): Boolean = items.isEmpty()

    // 用 size 手工判断
    fun isEmptyByHand(): Boolean = items.size == 0

    // 手工循环查找（不许 items.contains）
    fun contains(value: Int): Boolean {
        for (item in items) {
            if (value == item) return true
        }
        return false
    }

    // 必须返回与内部解耦的快照副本
    fun toList(): List<Int> {
        val resultList = mutableListOf<Int>()
        for (item in items) {
            resultList.add(item)
        }
        return resultList
    }

    fun clear() {
        val copyList = items.toList()
        for (item in copyList) {
            items.remove(item)
        }
    }
}

package round01

// 第 2 题 · 定宽名片生成器
// 每行长度必须恰好为 30；必须用循环手工补空格（不许 padEnd / repeat）

private const val CARD_WIDTH = 30

// 内容靠左，右侧补空格到 width；text 超长时要截断到 width 以内
private fun padRight(text: String, width: Int): String = TODO()

fun nameCard(name: String, age: Int, city: String): String = TODO()

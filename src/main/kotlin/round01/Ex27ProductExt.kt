package round01

import round01.external.Product

// 第 27 题 · 用扩展函数给"改不了的类"补功能
// 不许改 Product 源码、不许 data class、不许 String.format、不许 lambda/when/解构

// 1999 -> "19.99"；5 -> "0.05"；1000 -> "10.00"；手工格式化与补零
fun Product.priceInYuan(): String = TODO()

// 返回新 Product，价格按百分比下调并向下取整到分；percent 不在 0..100 抛 IllegalArgumentException
fun Product.withDiscount(percent: Int): Product = TODO()

fun Product.isAffordable(budgetInCents: Long): Boolean = TODO()

// "Widget: 19.99 元"
fun Product.describe(): String = TODO()

fun List<Product>.totalPriceInCents(): Long = TODO()

// 空列表抛 IllegalArgumentException；并列时返回名字字典序最小的
fun List<Product>.mostExpensive(): Product = TODO()

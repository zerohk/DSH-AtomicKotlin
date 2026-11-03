package round01

import round01.external.Product

// 第 27 题 · 用扩展函数给"改不了的类"补功能
// 不许改 Product 源码、不许 data class、不许 String.format、不许 lambda/when/解构

// 1999 -> "19.99"；5 -> "0.05"；1000 -> "10.00"；手工格式化与补零
fun Product.priceInYuan(): String {
    val yuanPart = this.priceInCents / 100
    val centPart = this.priceInCents % 100
    return if (centPart < 10) "${yuanPart}.0${centPart}" else "$yuanPart.$centPart"
}

// 返回新 Product，价格按百分比下调并向下取整到分；percent 不在 0..100 抛 IllegalArgumentException
fun Product.withDiscount(percent: Int): Product {
    if (percent !in 0..100) throw IllegalArgumentException("参数错误")
    val priceWithDiscount = this.priceInCents * (100 - percent) / 100
    return Product(this.name, priceWithDiscount.toLong())
}

fun Product.isAffordable(budgetInCents: Long): Boolean = this.priceInCents <= budgetInCents

// "Widget: 19.99 元"
fun Product.describe(): String = "${this.name}: ${this.priceInYuan()} 元"

fun List<Product>.totalPriceInCents(): Long {
    var totalPrice = 0L
    for (product in this) {
        totalPrice += product.priceInCents
    }
    return totalPrice
}

// 空列表抛 IllegalArgumentException；并列时返回名字字典序最小的
fun List<Product>.mostExpensive(): Product {
    if (this.isEmpty()) throw IllegalArgumentException("列表为空")
//    var mostExpensivePrice = this[0].priceInCents
//    var mostExpensiveName = this[0].name
    var mostExpensiveProduct = this[0]
    for (product in this) {
        if (product.priceInCents > mostExpensiveProduct.priceInCents) {
            mostExpensiveProduct = product
        } else if (product.priceInCents == mostExpensiveProduct.priceInCents && product.name < mostExpensiveProduct.name) {
            mostExpensiveProduct = product
        }
    }
    return mostExpensiveProduct
}



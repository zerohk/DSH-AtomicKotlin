package round01

// 第 25 题 (b) · 必须 import 上面的扩展函数才能编译通过（这就是本题的目的）
import round01.ext.numberx.isEven
import round01.ext.numberx.isOdd
import round01.ext.numberx.isPrimeX
import round01.ext.numberx.factorialX
import round01.ext.numberx.digitsSum
import round01.ext.numberx.digitsCount

// 依次调用 6 个扩展，返回一行摘要，如 "4 even=true odd=false prime=false digitsSum=4 digitsCount=1"
fun demoImportedExtensions(): String =
    "4 even=${4.isEven()} odd=${4.isOdd()} prime=${4.isPrimeX()} factorial=${4.factorialX()} digitsSum=${4.digitsSum()} digitsCount=${4.digitsCount()}"

// 在循环里调用 isPrimeX() 累加素数：sumOfAllPrimesUpTo(10) == 17L
fun sumOfAllPrimesUpTo(limit: Int): Long {
    var sum = 0L
    for (i in 1..limit) {
        if (i.isPrimeX()) sum += i
    }
    return sum
}

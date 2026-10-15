package round01

// 第 25 题 (b) · 必须 import 上面的扩展函数才能编译通过（这就是本题的目的）
import round01.ext.numberx.isEven
import round01.ext.numberx.isOdd
import round01.ext.numberx.isPrimeX
import round01.ext.numberx.factorialX
import round01.ext.numberx.digitsSum
import round01.ext.numberx.digitsCount

// 依次调用 6 个扩展，返回一行摘要，如 "4 even=true odd=false prime=false digitsSum=4 digitsCount=1"
fun demoImportedExtensions(): String = TODO()

// 在循环里调用 isPrimeX() 累加素数：sumOfAllPrimesUpTo(10) == 17L
fun sumOfAllPrimesUpTo(limit: Int): Long = TODO()

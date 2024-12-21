 package day13

import utils.getDataScanner
        import java.util.Scanner

fun main() {
    val isDemo = true
    val scanner = getDataScanner(13, if (isDemo) arrayOf("demo") else emptyArray())

    val input = Day13Input(scanner)


    val result = Day13Solution(input).solve()
    
    println(result)
}
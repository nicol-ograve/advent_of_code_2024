 package year2024.day13

import utils.getDataScanner

 fun main() {
    val isDemo = false
    val scanner = getDataScanner(13, if (isDemo) arrayOf("demo") else emptyArray())

    val input = Day13Input(scanner)


    val result = Day13Solution(input).solve()
    
    println(result)
}
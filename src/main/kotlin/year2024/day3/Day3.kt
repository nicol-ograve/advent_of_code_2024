package year2024.day3

import utils.getDataScanner

fun main() {
    val isDemo = false
    val scanner = getDataScanner(3, if (isDemo) arrayOf("demo") else emptyArray())

    val solution = Day3Solution(scanner)

    val result = solution.solve()

    println(result)
}


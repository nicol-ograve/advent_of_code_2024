package year2024.day5

import utils.getDataScanner

fun main() {
    val isDemo = false
    val scanner = getDataScanner(5, if (isDemo) arrayOf("demo") else emptyArray())
    val input = Day5Input(scanner)

    val solution = Day5Solution(input)
    val result = solution.sortIncorrectPrints()

    println(result)
}
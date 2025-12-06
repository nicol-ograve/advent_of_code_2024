package year2024.day6

import utils.getDataLines

fun main() {
    val isDemo = false
    val scanner = getDataLines(6, if (isDemo) arrayOf("demo") else emptyArray())

    val input = Day6Input(scanner)

    println(Day6Solution(input.matrix, input.initialGuardPosition).solvePart2())

}
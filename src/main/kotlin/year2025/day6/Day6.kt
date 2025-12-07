package year2025.day6

import utils.getDataLinesWithYear

fun main() {
    val isDemo = false
    val lines = getDataLinesWithYear(6, 2025, if (isDemo) arrayOf("demo") else emptyArray())

    val numberLines = lines.map { line ->
        line.split(" ").filter { it.isNotEmpty() }
    }
    val problems = mutableListOf<Problem>()
    for (p in numberLines[0].indices) {
        val numbers = numberLines.take(numberLines.size - 1).map { it[p].toLong() }
        val operation = if (numberLines.last()[p] == "*") Operation.Multiply else Operation.Sum
        problems.add(Problem(numbers, operation))
    }
    val result = problems.sumOf { it.solve() }

    println(result)
}

enum class Operation { Sum, Multiply }
data class Problem(val numbers: List<Long>, val operation: Operation) {
    fun solve(): Long {
        return if (operation == Operation.Sum) {
            numbers.sum()
        } else {
            numbers.reduce { a, b -> a * b }
        }
    }
}
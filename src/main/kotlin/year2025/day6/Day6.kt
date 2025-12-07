package year2025.day6

import utils.getDataLinesWithYear

fun main() {
    val isDemo = false
    val lines = getDataLinesWithYear(6, 2025, if (isDemo) arrayOf("demo") else emptyArray())

    val numberLines = lines.take(lines.size - 1)
    val breakpoints = mutableListOf<Int>()
    for (i in numberLines[0].indices) {
        if (numberLines.all { it[i] == ' ' }) {
            breakpoints.add(i)
        }
    }

    var i = 0
    var startIndex: Int
    var endIndex: Int

    var result = 0L

    do {
        startIndex = if (i == 0) 0 else breakpoints[i - 1] + 1
        endIndex = if (i == breakpoints.size) numberLines[0].count() - 1 else breakpoints[i] - 1

        val problemNumbers = mutableListOf<Long>()
        for (index in startIndex..endIndex) {

            var numberString = ""
            for (r in 0 until numberLines.size) {
                val nextChar = numberLines[r][index]
                if (nextChar != ' ') {
                    numberString += nextChar
                }
            }
            problemNumbers.add(numberString.toLong())
        }

        val operation = if (lines.last()[startIndex] == '+') Operation.Sum else Operation.Multiply
        val problem = Problem(problemNumbers, operation)
        result += problem.solve()

        i++
    } while (i < breakpoints.size + 1)

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
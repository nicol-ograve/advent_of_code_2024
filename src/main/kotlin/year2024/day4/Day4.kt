package year2024.day4

import shared.Matrix
import utils.getDataLines

fun main() {
    val isDemo = false
    val lines = getDataLines(4, if (isDemo) arrayOf("demo") else emptyArray())

    val matrix: Matrix<Char> = Array(lines.size) { r ->
        Array(lines[r].length) { c ->
            lines[r][c]
        }
    }

    val solution = Day4Solution(matrix)

    val result = solution.findMasCross()

    println(result)
}
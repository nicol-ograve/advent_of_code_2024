package year2025.day2

import shared.Range
import utils.getDataScannerWithYear

fun main() {
    val isDemo = false

    val ranges = getDataScannerWithYear(2, 2025, if (isDemo) arrayOf("demo") else emptyArray())
        .nextLine()
        .split(",")
        .map { it.split("-") }
        .map { Range(it[0].toLong(), it[1].toLong()) }

    val result = Day2Solver().solve(ranges)

    println(result)
}


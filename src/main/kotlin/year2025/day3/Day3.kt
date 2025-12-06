package year2025.day3

import utils.getDataLinesWithYear

const val batteriesToStart = 12

fun main() {
    val isDemo = false
    val lines = getDataLinesWithYear(3, 2025, if (isDemo) arrayOf("demo") else emptyArray())

    val result = lines.sumOf {
        var digits = ""
        var latestIndex: Int? = null

        for (i in 0 until batteriesToStart) {

            val maxIndex = findMaxValueIndex(it, batteriesToStart - i, latestIndex)
            digits += it[maxIndex]
            latestIndex = maxIndex

        }
        digits.toLong()
    }

    println(result)
}

fun findMaxValueIndex(line: String, missingBatteries: Int, initialIndex: Int? = null): Int {

    var maxValue = -1
    var maxValueIndex = -1
    for (i in (initialIndex
        ?: -1) + 1 until line.length - missingBatteries + 1) {
        if (line[i].digitToInt() > maxValue) {
            maxValue = line[i].digitToInt()
            maxValueIndex = i
        }
    }

    return maxValueIndex
}
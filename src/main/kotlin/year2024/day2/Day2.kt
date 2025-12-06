package year2024.day2

import utils.getDataLines

fun main() {
    val isDemo = false
    val lines = getDataLines(2, if (isDemo) arrayOf("demo") else emptyArray())

    println(solution(lines, true))
}

fun solution(lines: List<String>, part2: Boolean = false): Int {

    val validLines = lines.filter {
        val parts = it.split(" ").map { value -> value.toInt() }
        valid(parts, part2)
    }

    return validLines.size
}

fun valid(parts: List<Int>, dampenerEnabled: Boolean = false): Boolean {

    val line = if (parts[1] < parts[0]) parts.reversed() else parts

    for (i in 1 until line.size) {
        val diff = line[i] - line[i - 1]

        if (diff < 1 || diff > 3) {
            if (!dampenerEnabled) {
                return false
            } else {

                return valid(
                    line.filterIndexed { index, item ->
                        index != i - 1
                    },
                ) || valid(
                    line.filterIndexed { index, item ->
                        index != i
                    },
                ) || valid(
                    line.filterIndexed { index, item ->
                        index != 0
                    },
                ) || valid(
                    line.filterIndexed { index, item ->
                        index != line.size - 1
                    },
                )
            }
        }
    }


    return true
}
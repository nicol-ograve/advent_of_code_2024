package year2025.day1

import utils.getDataLinesWithYear
import kotlin.math.absoluteValue

fun main() {

    val isDemo = false
    val commands = getDataLinesWithYear(1, 2025, if (isDemo) arrayOf("demo") else emptyArray()).map(parseLine)

    var pointer = 50
    var result = 0

    commands.forEach { command ->

        val initialPointer = pointer

        val additionalRotations = (command / 100).absoluteValue
        result += additionalRotations

        val baseRotation = command % 100
        pointer += baseRotation

        if (initialPointer != 0 && (pointer !in 1 until 100)) {
            result++
        }

        pointer += 100
        pointer %= 100

    }

    println("_______________")
    println(result)
}

val parseLine = { line: String ->
    val sign = if (line.startsWith("L")) -1 else 1
    val value = line.substring(1).toInt()
    sign * value
}

package year2024.day8

import utils.getDataLines

fun main() {
    val isDemo = false
    val lines = getDataLines(8, if (isDemo) arrayOf("demo") else emptyArray())

    println(
        Day8Solution(
            Day8Input(lines)
        ).solve()
    )
}
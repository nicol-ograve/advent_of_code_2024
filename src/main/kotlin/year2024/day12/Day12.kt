package day12

import utils.getDataLines
import utils.getDataScanner
import java.util.Scanner

fun main() {
    val isDemo = false
    val lines = getDataLines(12, if (isDemo) arrayOf("demo") else emptyArray())

    val input = Day12Input(lines)
    val result = Day12Solution(input.matrix).solve()

    println(result)
}
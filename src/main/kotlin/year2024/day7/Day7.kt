package year2024.day7

import utils.getDataScanner

fun main() {
    val isDemo = false
    val scanner = getDataScanner(7, if (isDemo) arrayOf("demo") else emptyArray())

    println(
        Day7Solution(
            Day7Input(scanner), true
        ).solve()
    )


}
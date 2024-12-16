package day11

import utils.getDataScanner

fun main() {
    val isDemo = true
    val scanner = getDataScanner(11, if (isDemo) arrayOf("demo") else emptyArray())
    val stones = mutableListOf<Stone>()
    var count = 0



    while (scanner.hasNextInt()) {
        stones.add(Stone(scanner.nextLong()) {
            count++
        })
    }

    count = stones.size

    val solver = StoneBlinker(stones.toTypedArray())
    solver.blink(75)


    // 1: 218079

    val result = count

    println(result)
}
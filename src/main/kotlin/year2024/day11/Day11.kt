package year2024.day11

import utils.getDataScanner

fun main() {
    val isDemo = false
    val scanner = getDataScanner(11, if (isDemo) arrayOf("demo") else emptyArray())
    val stones = mutableListOf<Stone>()
    var count = 0


    while (scanner.hasNextInt()) {
        stones.add(Stone.create(scanner.nextLong()) {
            count++
        })
    }


    val solver = StoneBlinker(stones.toTypedArray())
    solver.blink(75)


}
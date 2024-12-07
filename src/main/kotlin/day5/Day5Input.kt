package day5

import java.util.Scanner

class Day5Input(private val scanner: Scanner) {

    val orderingRules: List<Pair<Int, Int>>
    val printUpdates: List<Array<Int>>

    init {
        val rules = mutableListOf<Pair<Int, Int>>()
        val updates = mutableListOf<Array<Int>>()

        do {
            val line = scanner.nextLine()

            if (line.isNotEmpty()) {
                val parts = line.split("|")
                rules.add(
                    Pair(parts[0].toInt(), parts[1].toInt())
                )
            }

        } while (line.isNotEmpty())


        while (scanner.hasNext()) {
            val line = scanner.nextLine()
            updates.add(
                line.split(",").map { it.toInt() }.toTypedArray()
            )
        }


        this.orderingRules = rules
        this.printUpdates = updates
    }
}
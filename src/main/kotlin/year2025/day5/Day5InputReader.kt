package year2025.day5

import shared.Range
import java.util.Scanner

fun readInput(scanner: Scanner): Day5Input {

    val ranges = mutableListOf<Range>()
    val ingredients = mutableListOf<Long>()

    do {
        val nextLine = scanner.nextLine()

        if (nextLine.isNotEmpty()) {
            ranges.add(nextLine.split("-").let {
                Range(it[0].toLong(), it[1].toLong())
            })
        }
    } while (nextLine.isNotEmpty())

    while (scanner.hasNextLine()) {
        ingredients.add(
            scanner.nextLong()
        )
    }


    return Day5Input(ranges, ingredients)

}

data class Day5Input(val ranges: List<Range>, val ingredients: List<Long>)
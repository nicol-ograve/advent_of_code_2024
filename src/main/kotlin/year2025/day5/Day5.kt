package year2025.day5

import shared.Range
import utils.getDataScannerWithYear

fun main() {
    val isDemo = false

    val (ranges, ingredients) = readInput(
        getDataScannerWithYear(5, 2025, if (isDemo) arrayOf("demo") else emptyArray())
    )
    val mergedRanges = mergeRanges(ranges)

    val part1Result = ingredients.count { isFresh(it, mergedRanges) }
    val part2Result = mergedRanges.sumOf { it.size }

    println(part1Result)
    println(part2Result)
}

fun mergeRanges(ranges: List<Range>): List<Range> {
    var mergeOccurred = false

    val newRanges = mutableListOf<Range>()
    val currentRanges = ranges.toMutableList()

    var currentIndex = 0

    while (currentIndex < currentRanges.size) {
        val indexesToRemove = mutableListOf<Int>()
        var currentRange = currentRanges[currentIndex]

        for (i in currentIndex + 1 until currentRanges.size) {
            val otherRange = currentRanges[i]
            if (currentRange.overlap(otherRange)) {
                currentRange = currentRange.mergeWith(otherRange)
                indexesToRemove.add(i)
                mergeOccurred = true
            }
        }

        indexesToRemove.reversed().forEach {
            currentRanges.removeAt(it)
        }

        assert(newRanges.size == currentIndex)
        currentIndex++
        newRanges.add(currentRange)
    }

    return if (mergeOccurred) mergeRanges(newRanges) else newRanges
}


fun isFresh(ingredient: Long, ranges: List<Range>): Boolean {
    return ranges.any {
        it.start <= ingredient && it.end >= ingredient
    }
}
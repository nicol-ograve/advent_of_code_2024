package year2024.day1

import utils.getDataScanner
import kotlin.math.abs


fun main() {
    val isDemo = false

    val left = mutableListOf<Int>()
    val right = mutableListOf<Int>()

    val scanner = getDataScanner(1, if (isDemo) arrayOf("demo") else emptyArray())

    while (scanner.hasNextInt()) {
        left.add(scanner.nextInt())
        right.add(scanner.nextInt())
    }

    println(day1part2(left, right))


}

fun day1part2(left: MutableList<Int>, right: MutableList<Int>): Int {


    val rightOccurrencesMap = HashMap<Int, Int>()

    right.forEach {
        if (rightOccurrencesMap.containsKey(it)) {
            rightOccurrencesMap[it] = rightOccurrencesMap[it]!! + 1
        } else {
            rightOccurrencesMap[it] = 1
        }
    }

    return left.sumOf {
        it * (if (rightOccurrencesMap.containsKey(it)) rightOccurrencesMap[it]!! else 0)
    }


}

fun day1part1(left: MutableList<Int>, right: MutableList<Int>): Int {

    left.sort()
    right.sort()

    return left.foldIndexed(0) { index: Int, acc: Int, value: Int ->
        acc + abs(value - right[index])
    }


}


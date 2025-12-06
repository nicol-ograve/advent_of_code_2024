package year2024.day5

import java.lang.RuntimeException

class Day5Solution(val input: Day5Input) {

    private val previousPages: HashMap<Int, HashSet<Int>>
    private val followingPages: HashMap<Int, HashSet<Int>>

    init {

        val previousPages = HashMap<Int, HashSet<Int>>()
        val followingPages = HashMap<Int, HashSet<Int>>()

        input.orderingRules.forEach {
            val (firstPage, secondPage) = it

            if (!previousPages.containsKey(secondPage)) {
                previousPages[secondPage] = HashSet()
            }
            if (!followingPages.containsKey(firstPage)) {
                followingPages[firstPage] = HashSet()
            }

            previousPages[secondPage]!!.add(firstPage)
            followingPages[firstPage]!!.add(secondPage)
        }

        this.previousPages = previousPages
        this.followingPages = followingPages
    }

    fun findSortedSum(): Int {
        return input.printUpdates.filter(this::isSorted).sumOf {
            val result = it[(it.size - 1) / 2]
            result
        }
    }


    fun sortIncorrectPrints(): Int {
        var result = 0

        val incorrectPrints = input.printUpdates.filter { !isSorted(it) }

        incorrectPrints.forEach {

            val items = findUnsortedPagesIndexes(it).toMutableList().sorted()
            val sorted = sortUnsortedPages(it, items)


            val copy = it.toList().toTypedArray()
            sorted.forEachIndexed { index, item ->
                copy[items[index]] = it[sorted[index]]
            }

            result += copy[(copy.size - 1) / 2]

        }


        return result
    }

    private fun sortUnsortedPages(prints: Array<Int>, pagesIndexes: List<Int>): List<Int> {
        val mutableIndexes = pagesIndexes.toMutableList()
        val result = mutableListOf<Int>()

        while (mutableIndexes.isNotEmpty()) {

            val itemToRemove = mutableIndexes.find { index ->
                val value = prints[index]

                val previousUnsortedPages = mutableIndexes.count {
                    it != index && previousPages[value]?.contains(prints[it]) == true
                }

                previousUnsortedPages == 0


            } ?: throw RuntimeException("No item to remove")

            mutableIndexes.remove(itemToRemove)
            result.add(itemToRemove)

        }

        return result

    }

    private fun findUnsortedPagesIndexes(print: Array<Int>): HashSet<Int> {
        val result = HashSet<Int>()

        for (i in 0 until print.size - 1) {
            val previousValue = print[i]
            for (j in i + 1 until print.size) {
                val followingValue = print[j]
                if (followingPages[followingValue]?.contains(previousValue) == true) {
                    result.add(i)
                    result.add(j)
                }
            }
        }
        return result
    }

    private fun findUnsortedPairs(print: Array<Int>): List<Pair<Int, Int>> {

        val result = mutableListOf<Pair<Int, Int>>()

        for (i in 0 until print.size - 1) {
            val previousValue = print[i]
            for (j in i + 1 until print.size) {
                val followingValue = print[j]
                if (followingPages[followingValue]?.contains(previousValue) == true) {
                    result.add(Pair(i, j))
                }
            }
        }
        return result
    }

    private fun isSorted(print: Array<Int>): Boolean {

        for (i in 0 until print.size - 1) {
            val previousValue = print[i]
            for (j in i + 1 until print.size) {
                val followingValue = print[j]
                if (followingPages[followingValue]?.contains(previousValue) == true) {
                    return false
                }
            }
        }
        return true
    }
}
package year2024.day9

import utils.getDataScanner

fun main() {
    val isDemo = false
    val scanner = getDataScanner(9, if (isDemo) arrayOf("demo") else emptyArray())
    val line = scanner.nextLine()

    val values = line.map { it.digitToInt() }
    val sum = values.sum()

    val arr = Array<Int?>(sum) {
        null
    }

    var value = -1
    var index = 0


    val emptyBlocks = mutableListOf<Block>()
    val fileBlocks = mutableListOf<Block>()

    for (i in values.indices) {
        val count = values[i]

        val list = if (i % 2 == 0) fileBlocks else emptyBlocks
        list.add(Block(index, count))

        if (i % 2 == 0) {
            value++
        }
        for (j in 0 until count) {


            if (i % 2 == 0) {
                arr[index] = value
            }
            index++

        }
    }

    val result = DiskDefragment(arr, fileBlocks, emptyBlocks).solvePart2()

    /*
    for (a in arr) {
        print(a ?: '.')
    }
    */

    println()
    println()
    println()

    // WRONG: 8582381894860 --> Too high
    // 8863714221574
    println(result)

}


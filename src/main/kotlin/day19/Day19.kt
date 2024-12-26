package day19

import utils.getDataScanner
import java.util.Scanner

fun main() {
    val isDemo = false
    val scanner = getDataScanner(19, if (isDemo) arrayOf("demo") else emptyArray())

    val patterns = scanner.nextLine().split(",").map { it.trim() }
    scanner.nextLine()

    println(patterns)

    val towels = mutableListOf<String>()
    while (scanner.hasNextLine()) {
        towels.add(scanner.nextLine())
    }

    val designer = TowelsDesigner(patterns, towels)

    val result = designer.createTowels()

    println(result)
}
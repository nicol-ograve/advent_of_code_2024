package day3

import utils.getDataScanner
import java.util.Scanner

fun main() {
    val isDemo = false
    val scanner = getDataScanner(3, if (isDemo) arrayOf("demo") else emptyArray())

    val solution = Solution(scanner)

    val result = solution.solve()

    println(result)
}


 package day6

import utils.getDataLines

 fun main() {
    val isDemo = false
    val scanner = getDataLines(6, if (isDemo) arrayOf("demo") else emptyArray())

    val input = Day6Input(scanner)
    
    val result = Day6Solution(input.matrix, input.initialGuardPosition).solve()
    
    println(result)
}
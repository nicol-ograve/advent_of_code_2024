package year2024.day15

import utils.getDataScanner

fun main() {
    val isDemo = false
    val scanner = getDataScanner(15, if (isDemo) arrayOf("demo") else emptyArray())

    val warehouse = Warehouse(scanner)



    while (warehouse.instructions.isNotEmpty()) {
        warehouse.executeNextInstruction()
        // warehouse.matrix.print()
        // println()
    }


    val result = warehouse.getBoxesCoordinatesValue()

    println(result)
}
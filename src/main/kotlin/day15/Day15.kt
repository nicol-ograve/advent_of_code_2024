package day15

import shared.print
import utils.getDataScanner
import java.util.Scanner

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
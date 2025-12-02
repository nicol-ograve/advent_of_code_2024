package day7

import java.util.Scanner


data class BridgeOperation(val result: Long, val values: List<Long>)
class Day7Input(private val scanner: Scanner) {
    val operations: List<BridgeOperation>

    init {
        val ops = mutableListOf<BridgeOperation>()

        while (scanner.hasNext()) {
            val line = scanner.nextLine()

            val parts = line.split(":")

            val result = parts[0].toLong()
            val values = parts[1].split(" ").filter { it.isNotEmpty() }.map { it.toLong() }

            ops.add(BridgeOperation(result, values))
        }


        operations = ops
    }
}
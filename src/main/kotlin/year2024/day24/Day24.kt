package year2024.day24

import utils.getDataScanner
import java.lang.RuntimeException

fun main() {
    val isDemo = false
    val scanner = getDataScanner(24, if (isDemo) arrayOf("demo") else emptyArray())

    val input = Day24Input(scanner)


    input.initialValues.keys.forEach {
        input.wires[it]!!.setValue(input.initialValues[it]!!)
    }


    val result = input.wires.keys.filter { it.startsWith("z") }.sorted().reversed().map {
        input.wires[it]!!
    }.map { if (it.value == null) throw RuntimeException("Wrong result") else if (it.value == true) 1 else 0 }
        .joinToString("").toLong(2)


    println(result)
}
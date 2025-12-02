package day24

import java.util.Scanner

class Day24Input(scanner: Scanner) {

    val gates: List<Gate>
    val wires: HashMap<String, Wire>
    val initialValues: HashMap<String, Boolean>

    init {
        val initialVals = hashMapOf<String, Boolean>()
        val wireRefs = hashMapOf<String, Wire>()

        do {
            val nextLine = scanner.nextLine()

            if (nextLine.isNotEmpty()) {
                nextLine.split(":").map { it.trim() }.let {
                    initialVals[it[0]] = it[1].toInt() == 1
                }
            }

        } while (nextLine.isNotEmpty())


        val gateLines = mutableListOf<Gate>()

        while (scanner.hasNextLine()) {
            val line = scanner.nextLine()

            val inOut = line.split("->").map { it.trim() }
            val output = inOut[1]

            if (!wireRefs.containsKey(output)) {
                wireRefs[output] = Wire(output)
            }

            inOut[0].split(" ").let {

                val first = it[0]
                val second = it[2]

                val gateType = GateType.fromString(it[1])

                if (!wireRefs.containsKey(first)) {
                    wireRefs[first] = Wire(first)
                }
                if (!wireRefs.containsKey(second)) {
                    wireRefs[second] = Wire(second)
                }

                gateLines.add(
                    Gate(
                        gateType,
                        Pair(wireRefs[first]!!, wireRefs[second]!!),
                        wireRefs[output]!!
                    )
                )
            }
        }

        gates = gateLines
        wires = wireRefs
        initialValues = initialVals


    }
}


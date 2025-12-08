package year2025.day8

import shared.Point3D
import utils.getDataLinesWithYear

fun main() {
    val isDemo = false
    val boxes = getDataLinesWithYear(8, 2025, if (isDemo) arrayOf("demo") else emptyArray())
        .map { line -> line.split(",").let { Point3D(it[0].toInt(), it[1].toInt(), it[2].toInt()) } }

    val connections = mutableListOf<Connection>()

    for (i in 0 until boxes.size - 1) {
        for (j in i + 1 until boxes.size) {
            connections.add(
                Connection(
                    boxes[i], boxes[j], boxes[i].distanceTo(boxes[j])
                )
            )
        }
    }
    val sortedConnections = connections.sortedByDescending { it.distance }.reversed()

    val circuitsMap = hashMapOf<Point3D, Circuit>()

    var i = 0
    while (true) {
        val nextConnection = sortedConnections[i]

        val (firstBox, secondBox) = nextConnection
        val firstCircuit = circuitsMap[firstBox]
        val secondCircuit = circuitsMap[secondBox]

        if (firstCircuit == null && secondCircuit == null) {
            val circuit = Circuit(mutableSetOf(firstBox, secondBox))
            circuitsMap[firstBox] = circuit
            circuitsMap[secondBox] = circuit
        } else if (firstCircuit == null) {
            secondCircuit!!.boxes.add(firstBox)
            circuitsMap[firstBox] = secondCircuit
        } else if (secondCircuit == null) {
            firstCircuit.boxes.add(secondBox)
            circuitsMap[secondBox] = firstCircuit
        } else if (firstCircuit != secondCircuit) {
            firstCircuit.boxes.addAll(secondCircuit.boxes)
            secondCircuit.boxes.forEach {
                circuitsMap[it] = firstCircuit
            }
        }

        if (circuitsMap.keys.size == boxes.size) {
            println(firstBox.x.toLong() * secondBox.x.toLong())
            break
        }
        i++
    }

}


data class Connection(val firstBox: Point3D, val secondBox: Point3D, val distance: Double)
data class Circuit(val boxes: MutableSet<Point3D>) {

}
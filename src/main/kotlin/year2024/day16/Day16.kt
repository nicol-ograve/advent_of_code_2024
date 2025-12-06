 package year2024.day16

import utils.getDataLines

fun main() {
    val isDemo = false
    val lines = getDataLines(16, if (isDemo) arrayOf("demo") else emptyArray())

    val race = ReindeerRace(lines)
    val result = race.findBestPath()

    val i: Int = 1



    println(result)
}
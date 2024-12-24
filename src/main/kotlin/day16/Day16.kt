 package day16

import utils.getDataLines

fun main() {
    val isDemo = false
    val lines = getDataLines(16, if (isDemo) arrayOf("demo") else emptyArray())

    val race = ReindeerRace(lines)
    val result = race.findBestPath()


    println(result)
}
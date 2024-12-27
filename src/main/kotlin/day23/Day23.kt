 package day23

import utils.getDataLines

fun main() {
    val isDemo = false
    val lines = getDataLines(23, if (isDemo) arrayOf("demo") else emptyArray())

    val input = LanPairsInput(lines)
    val lanParty = LanParty(input)

    val result = lanParty.findTriplets()
    
    println(result)
}
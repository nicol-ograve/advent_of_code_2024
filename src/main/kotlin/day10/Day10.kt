 package day10

import day6.TerrainTile
import shared.Point
import utils.getDataLines
import utils.getDataScanner
        import java.util.Scanner

fun main() {
    val isDemo = false
    val lines = getDataLines(10, if (isDemo) arrayOf("demo") else emptyArray())

    val matrix = Array(lines.size) { r ->
        Array(lines[r].length) { c ->
            lines[r][c].digitToInt()
        }
    }

    val result = TrailFinder(matrix, true).findTrails()
    
    println(result)
}
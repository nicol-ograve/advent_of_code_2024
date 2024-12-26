package day20

import shared.Point
import shared.TerrainTile
import utils.getDataLines
import utils.getDataScanner
import java.util.Scanner

fun main() {
    val isDemo = false
    val lines = getDataLines(20, if (isDemo) arrayOf("demo") else emptyArray())

    var initialPosition: Point? = null
    var finalPosition: Point? = null

    val mtx = Array(lines.size) { r ->
        Array(lines[r].length) { c ->
            if (lines[r][c] == 'S') {
                initialPosition = Point(c, r)
            } else if (lines[r][c] == 'E') {
                finalPosition = Point(c, r)
            }
            if (lines[r][c] == '#') TerrainTile.Obstruction else TerrainTile.Empty
        }
    }

    val run = CpuRun(mtx, initialPosition!!, finalPosition!!)

    // Too low: 1501
    val result = run.findCuts()

    println(result)
}
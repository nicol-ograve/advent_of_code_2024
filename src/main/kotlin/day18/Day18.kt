package day18

import shared.Matrix
import shared.Point
import shared.TerrainTile
import utils.getDataScanner
import shared.*

fun main() {
    val isDemo = false
    val scanner = getDataScanner(18, if (isDemo) arrayOf("demo") else emptyArray())

    val size = if (isDemo) 7 else 71
    val fallingBytes = if (isDemo) 12 else 1024

    val matrix: Matrix<TerrainTile> = Array(size) {
        Array(size) {
            TerrainTile.Empty
        }
    }

    for (i in 0 until fallingBytes) {
        matrix[readPoint(scanner.nextLine())] = TerrainTile.Obstruction
    }

    var found = false


    while (!found && scanner.hasNextLine()) {
        val nextPoint =readPoint(scanner.nextLine())
        matrix[nextPoint] = TerrainTile.Obstruction
        val memSpace = MemorySpace(matrix)
        val result = memSpace.findBestPath()
        if (result == null) {
            found = true
            println(nextPoint)
        }
    }

}

fun readPoint(string: String): Point {
    return string.split(",").let {
        Point(it[0].toInt(), it[1].toInt())
    }
}
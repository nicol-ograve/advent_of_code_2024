package year2025.day4

import shared.*
import utils.getDataLinesWithYear

fun main() {
    val isDemo = false
    val lines = getDataLinesWithYear(4, 2025, if (isDemo) arrayOf("demo") else emptyArray())

    val matrix = Array(lines.count()) { r -> Array(lines[0].count()) { c -> lines[r][c] == '@' } }
    val directions = arrayOf(Up, UpRight, Right, DownRight, Down, DownLeft, Left, UpLeft)

    var accessibleRolls = 0
    val accessiblePoints = mutableListOf<Point>()

    for (r in matrix.indices) {
        for (c in matrix[r].indices) {
            val currentPoint = Point(c, r)
            if (matrix[currentPoint]) { // Consider only cells with rolls
                val nearRolls = directions.count {
                    val nextPoint = it.moveFrom(currentPoint)
                    matrix.inRange(nextPoint) && matrix[nextPoint]
                }
                if (nearRolls <= 3) {
                    accessibleRolls++
                    accessiblePoints.add(currentPoint)
                }
            }
        }
    }

    println(accessibleRolls)
}
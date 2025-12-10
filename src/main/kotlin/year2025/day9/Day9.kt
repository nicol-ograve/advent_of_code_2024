package year2025.day9

import shared.Point
import utils.getDataLinesWithYear
import kotlin.math.abs

fun main() {
    val isDemo = false
    val points = getDataLinesWithYear(9, 2025, if (isDemo) arrayOf("demo") else emptyArray())
        .map { line -> line.split(",").let { Point(it[0].toInt(), it[1].toInt()) } }


    var maxArea = 0L

    for (i in 0 until points.size - 1) {
        for (j in i + 1 until points.size) {
            val xDelta = abs(points[i].x - points[j].x).toLong()
            val yDelta = abs(points[i].y - points[j].y).toLong()
            val area = (xDelta + 1) * (yDelta + 1)
            if (area > maxArea) {
                maxArea = area
            }
        }
    }

    println(maxArea)
}
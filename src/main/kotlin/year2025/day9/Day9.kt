package year2025.day9

import shared.*
import utils.getDataLinesWithYear
import kotlin.math.abs
import kotlin.math.max
import kotlin.math.min

fun main() {
    val isDemo = false
    val points = getDataLinesWithYear(9, 2025, if (isDemo) arrayOf("demo") else emptyArray())
        .map { line -> line.split(",").let { Point(it[0].toInt(), it[1].toInt()) } }

    val lines: List<Line> = points.mapIndexed { index, p1 ->
        val p2 = points[if (index == 0) points.size - 1 else index - 1]
        Line(p1, p2)
    }


    val r = Rectangle(Point(9, 5), Point(2, 3), 24)
    val l = Line(Point(2, 3), Point(2, 5))

    if (r.isCrossedBy(l)) {
        throw RuntimeException("Wrong implementation")
    }

    val rectangles = mutableListOf<Rectangle>()

    for (i in 0 until points.size - 1) {
        for (j in i + 1 until points.size) {

            val p1 = points[i]
            val p2 = points[j]

            val xDelta = abs(p1.x - p2.x).toLong()
            val yDelta = abs(p1.y - p2.y).toLong()
            val area = (xDelta + 1) * (yDelta + 1)

            rectangles.add(Rectangle(p1, p2, area))
        }
    }
    val sortedRectangles = rectangles.sortedByDescending { it.area }.toMutableList()

    var result: Rectangle? = null

    while (result == null && sortedRectangles.isNotEmpty()) {
        val rect = sortedRectangles.removeFirst()
        if (isValid(rect, lines)) {
            result = rect
        }
    }

    // It should probably also be checked if the rectangle is external or internal to the path
    println(result)

}


fun isValid(rectangle: Rectangle, lines: List<Line>): Boolean {

    for (line in lines) {
        if (rectangle.isCrossedBy(line)) {
            return false
        }
    }
    return true
}


data class Line(val p1: Point, val p2: Point) {
    fun isVertical(): Boolean {
        return p1.x == p2.x
    }

    fun isHorizontal(): Boolean {
        return p1.y == p2.y
    }
}

data class Rectangle(val p1: Point, val p2: Point, val area: Long) {
    val left = min(p1.x, p2.x)
    val right = max(p1.x, p2.x)
    val top = max(p1.y, p2.y)
    val bottom = min(p1.y, p2.y)

    fun isCrossedBy(line: Line): Boolean {
        if (line.isVertical()) {
            if (line.p1.x !in (left + 1) until right) {
                return false
            }
            val lineTopY = max(line.p1.y, line.p2.y)
            val lineBottomY = min(line.p1.y, line.p2.y)

            return (top in (lineBottomY + 1)..lineTopY) ||
                    (bottom in lineBottomY until lineTopY)
                    || lineTopY in bottom + 1..top || lineBottomY in bottom until top
        } else if (line.isHorizontal()) {

            if (line.p1.y !in (bottom + 1) until top) {
                return false
            }
            val lineLeftX = min(line.p1.x, line.p2.x)
            val lineRightX = max(line.p1.x, line.p2.x)

            return (left in lineLeftX until lineRightX) ||
                    (right in (lineLeftX + 1)..lineRightX)
                    || lineLeftX in left until right || lineRightX in left + 1..right
        } else {
            throw RuntimeException("No diagonal lines supported")
        }
    }
}

enum class TileColor { Red, Green, None }
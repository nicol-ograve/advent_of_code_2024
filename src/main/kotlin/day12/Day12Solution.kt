package day12

import shared.*

class Day12Solution(private val matrix: Matrix<Char>) {

    val checkedCells = Array(matrix.size) {
        Array(matrix[it].size) { false }
    }

    fun solve(): Long {
        var totalPrice = 0L

        for (r in matrix.indices) {
            for (c in 0 until matrix[r].size) {
                val cell = Point(c, r)
                if (!checkedCells[cell]) {
                    val price = findRegionPrice(cell)
                    totalPrice += price
                }
            }
        }


        return totalPrice
    }

    private fun findRegionPrice(startPoint: Point): Long {
        var perimeter = 0L
        var area = 1L


        val regionPoints = hashSetOf<Point>(startPoint)
        val pointsToCheck = mutableListOf(startPoint)

        val perimeterPoints = hashSetOf<Point>()
        val boundaries = hashSetOf<PerimeterBoundary>()

        val regionChar = matrix[startPoint]

        while (pointsToCheck.isNotEmpty()) {
            val point = pointsToCheck.removeFirst()
            checkedCells[point] = true

            directions.forEach { dir ->
                val nextPoint = dir.moveFrom(point)

                if (!matrix.inRange(nextPoint) || matrix[nextPoint] != regionChar) {
                    perimeter++
                    perimeterPoints.add(point)
                    boundaries.add(PerimeterBoundary(point, dir))
                } else if (!regionPoints.contains(nextPoint)) {
                    regionPoints.add(nextPoint)
                    pointsToCheck.add(nextPoint)
                    area++
                }

            }

        }

        val sides = calculatePerimeterSides(boundaries.toMutableList(), regionChar)

        return sides * area

    }
}

private fun calculatePerimeterSides(boundaries: MutableList<PerimeterBoundary>, char: Char): Int {
    var sides = 0

    while (boundaries.isNotEmpty()) {
        val item = boundaries.removeFirst()
        sides++

        val direction = item.direction
        val crossDirections = arrayOf(direction.turn90DegLeft(), direction.turn90DegRight())

        crossDirections.forEach { dir ->
            var currentPosition = item.point

            do {
                val newPosition = dir.moveFrom(currentPosition)
                val isSameSide = boundaries.removeIf { it.point == newPosition && it.direction == item.direction }

                if (isSameSide) {
                    currentPosition = newPosition
                }

            } while (isSameSide)

        }


    }

    return sides

}


val directions = arrayOf(Up, Right, Down, Left)

data class PerimeterBoundary(val point: Point, val direction: Direction)
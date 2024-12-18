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

        val regionChar = matrix[startPoint]

        while (pointsToCheck.isNotEmpty()) {
            val point = pointsToCheck.removeFirst()
            checkedCells[point] = true

            directions.forEach { dir ->
                val nextPoint = dir.moveFrom(point)

                if (!matrix.inRange(nextPoint) || matrix[nextPoint] != regionChar) {
                    perimeter++
                } else if (!regionPoints.contains(nextPoint)) {
                    regionPoints.add(nextPoint)
                    pointsToCheck.add(nextPoint)
                    area++
                }

            }

        }

        val price = perimeter * area
        // println("PointsToCheck: ${regionPoints.size}")
        // println("A region of $regionChar plants with price $area * $perimeter = $price.")

        return price

    }
}


val directions = arrayOf(Up, Right, Down, Left)
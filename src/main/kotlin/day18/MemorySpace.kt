package day18

import day16.ReindeerPath
import day16.Result
import shared.*

class MemorySpace(val matrix: Matrix<TerrainTile>) {

    val minCosts = hashMapOf<Point, Int>()
    fun findBestPath(): Int? {

        val position = Point(0, 0)
        val finalPosition = Point(matrix.size - 1, matrix.size - 1)


        val paths = mutableListOf<Path>()

        minCosts[position] = 0

        arrayOf(Right, Down).forEach {
            val nextPoint = it.moveFrom(position)
            if (matrix[nextPoint] == TerrainTile.Empty) {
                minCosts[nextPoint] = 1
                paths.add(Path(1, it, nextPoint))
            }
        }

        while (paths.isNotEmpty()) {
            val path = paths.removeFirst()
            val (cost, direction, point) = path

            nextDirections[direction]!!.forEach {
                var newCost = cost
                val nextPoint = it.moveFrom(point)
                val nextPointCost = minCosts[nextPoint]

                newCost++

                if (matrix.inRange(nextPoint) && matrix[nextPoint] == TerrainTile.Empty && (nextPointCost == null || nextPointCost > newCost)) {
                    minCosts[nextPoint] = newCost

                    val newPath = Path(newCost, it, nextPoint)
                    paths.add(newPath)

                }

            }
        }

        return minCosts[finalPosition]
    }
}

data class Path(
    val cost: Int, val direction: Direction, val position: Point
)
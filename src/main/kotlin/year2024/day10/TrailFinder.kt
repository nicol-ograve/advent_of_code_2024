package year2024.day10

import shared.*

class TrailFinder(val matrix: Matrix<Int>, val countDistinctPaths: Boolean) {

    fun findTrails(): Int {


        return findStartPoints()
            .sumOf {
                findTrailsFrom(it)
            }
    }

    private fun findStartPoints(): List<Point> {
        val startPoints = mutableListOf<Point>()
        for (r in 0 until matrix.rows()) {
            for (c in 0 until matrix.columns()) {
                val point = Point(c, r)
                if (matrix[point] == 0) {
                    startPoints.add(point)
                }
            }
        }
        return startPoints
    }

    private fun findTrailsFrom(startPoint: Point): Int {

        val points = hashSetOf<Point>()
        var count = 0

        val queue = mutableListOf(
            PathPosition(startPoint, arrayOf(Up, Right, Down, Left))
        )

        while (queue.isNotEmpty()) {
            val pathPosition = queue.removeFirst()
            val currentAltitude = matrix[pathPosition.point]


            pathPosition.directions.forEach { direction ->

                val nextPoint = direction.moveFrom(pathPosition.point)
                if (matrix.inRange(nextPoint)) {
                    val newAltitude = matrix[nextPoint]

                    if (newAltitude == currentAltitude + 1) {
                        if (newAltitude == 9) {
                            points.add(nextPoint)
                            count++
                        } else {
                            queue.add(
                                PathPosition(nextPoint, nextDirections[direction]!!)
                            )
                        }
                    }
                }
            }

        }

        return if(countDistinctPaths) count else points.size
    }

}

class PathPosition(val point: Point, val directions: Array<Direction>)

val nextDirections = hashMapOf<Direction, Array<Direction>>(
    Pair(Up, arrayOf(Right, Up, Left)),
    Pair(Right, arrayOf(Down, Right, Up)),
    Pair(Down, arrayOf(Left, Down, Right)),
    Pair(Left, arrayOf(Up, Left, Down)),
)
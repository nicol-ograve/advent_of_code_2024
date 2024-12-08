package day6

import shared.*

class Day6Solution(private val matrix: Matrix<TerrainTile>, private val initialGuardPosition: Point) {

    fun solvePart2(): Int {


        return getPath().count {
            checkPathLoop(it)
        }
    }

    private fun checkPathLoop(blockPosition: Point): Boolean {

        var guardPosition = initialGuardPosition
        var guardDirection: Direction = Up

        val points = HashMap<Point, HashSet<Direction>>()


        points[initialGuardPosition] = hashSetOf(guardDirection)

        while (matrix.inRange(guardPosition)) {
            val nextPoint = guardDirection.moveFrom(guardPosition)


            if (matrix.inRange(nextPoint) && (matrix[nextPoint] == TerrainTile.Obstruction || nextPoint == blockPosition)) {
                guardDirection = guardDirection.turn90DegRight()
            } else {
                guardPosition = nextPoint



                if (!points.containsKey(guardPosition)) {
                    points[guardPosition] = hashSetOf(guardDirection)
                } else {
                    if (points[guardPosition]!!.contains(guardDirection)) {
                        return true
                    }
                    points[guardPosition]!!.add(guardDirection)
                }
            }

        }




        return false

    }

    private fun getPath(): HashSet<Point> {

        var guardPosition = initialGuardPosition
        var guardDirection: Direction = Up

        val points = HashSet<Point>()


        points.add(initialGuardPosition)

        while (matrix.inRange(guardPosition)) {
            val nextPoint = guardDirection.moveFrom(guardPosition)


            if (matrix.inRange(nextPoint) && matrix[nextPoint] == TerrainTile.Obstruction) {
                guardDirection = guardDirection.turn90DegRight()
            } else {
                guardPosition = nextPoint
                points.add(nextPoint)
            }

        }


        // Remove last position since is out of matrix
        points.removeIf {
            !matrix.inRange(it)
        }

        return points

    }
}

package day6

import shared.*
import javax.swing.text.Position

class Day6Solution(private val matrix: Matrix<TerrainTile>, private val initialGuardPosition: Point) {
    fun solve(): Int {

        val movements = mutableListOf<Movement>()

        var guardPosition = initialGuardPosition
        var guardDirection: Direction = Up

        val loopBlockPoints = HashSet<Point>()

        movements.add(Movement(guardDirection, guardPosition, false))

        while (matrix.inRange(guardPosition)) {
            val nextPoint = guardDirection.moveFrom(guardPosition)


            if (matrix.inRange(nextPoint) && matrix[nextPoint] == TerrainTile.Obstruction) {
                movements.last().endPoint = guardPosition
                guardDirection = guardDirection.turn90DegRight()
                movements.add(Movement(guardDirection, guardPosition, false))
            } else {
                guardPosition = nextPoint

                if (findCircle(nextPoint, guardDirection, movements)) {
                    val blockPosition = guardDirection.moveFrom(nextPoint)

                    loopBlockPoints.add(blockPosition)
                }

            }

        }




        return loopBlockPoints.size // Remove last position since is out of matrix

    }


    private fun findCircle(
        currentPosition: Point,
        currentDirection: Direction,
        previousMovements: List<Movement>
    ): Boolean {
        var direction = currentDirection
        var position = currentPosition

        if (checkLoop(position, currentDirection, previousMovements)) {
            return true
        }

        val movements = mutableListOf<Point>()

        for (i in 0 until 1000) {
            direction = direction.turn90DegRight()
            val movement = nextMovement(position, direction)

            if (movement.finishIntoEdge) {
                return false
            }

            if (checkLoop(movement.endPoint!!, direction, previousMovements, currentPosition)) {
                return true
            }


            position = movement.endPoint!!
            movements.add(position)
        }



        return false
    }


    private fun checkLoop(
        currentPosition: Point,
        currentDirection: Direction,
        previousMovements: List<Movement>,
        loopStartPosition: Point? = null
    ): Boolean {
        val rightDirection = currentDirection.turn90DegRight()

        return previousMovements.any {

            if (it.endPoint == null && it.direction == rightDirection  && loopStartPosition != null && rightDirection.canTravelFrom(
                    currentPosition, loopStartPosition
                )
            ) {
                return@any true
            }

            return@any it.endPoint != null && it.direction == rightDirection && rightDirection.canTravelFrom(
                currentPosition,
                it.endPoint!!
            )
        }

    }

    private fun nextMovement(
        startPosition: Point,
        direction: Direction,
    ): Movement {

        var position = startPosition

        while (matrix.inRange(position)) {
            val nextPoint = direction.moveFrom(position)

            if (!matrix.inRange(nextPoint)) {
                return Movement(direction, startPosition, true, position)
            }

            if (matrix[nextPoint] == TerrainTile.Obstruction) {
                return Movement(direction, startPosition, false, position)
            } else {
                position = nextPoint
            }

        }
        return Movement(direction, startPosition, true, position)
    }


    fun solvePart1(): Int {

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




        return points.size - 1 // Remove last position since is out of matrix

    }
}


data class Movement(
    val direction: Direction,
    val startPoint: Point,
    val finishIntoEdge: Boolean,
    var endPoint: Point? = null
)
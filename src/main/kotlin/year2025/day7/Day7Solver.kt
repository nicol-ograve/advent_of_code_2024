package year2025.day7

import shared.Down
import shared.Left
import shared.Matrix
import shared.Point
import shared.Right
import shared.get
import shared.inRange

class Day7Solver(val matrix: Matrix<Tile>, val startPosition: Point) {

    val splitterTimelines = hashMapOf<Point, Long>()

    fun solvePart1(): Int {

        var splits = 0

        val crossedPositions = hashSetOf<Point>()

        val beams = mutableListOf<Beam>(
            Beam(startPosition)
        )

        while (beams.isNotEmpty()) {

            val indexesToRemove = mutableListOf<Int>()
            val beamsToAdd = mutableListOf<Beam>()

            beams.forEachIndexed { index, beam ->
                val nextPosition = Down.moveFrom(beam.position)
                if (!matrix.inRange(nextPosition)) {
                    indexesToRemove.add(index)
                } else if (crossedPositions.contains(nextPosition)) {
                    indexesToRemove.add(index)
                } else if (matrix[nextPosition] == Tile.Empty) {
                    beam.position = nextPosition
                    crossedPositions.add(nextPosition)
                } else {
                    beam.position = Left.moveFrom(nextPosition)
                    crossedPositions.add(nextPosition)

                    val rightBeamPosition = Right.moveFrom(nextPosition)
                    if (!crossedPositions.contains(rightBeamPosition)) {
                        beamsToAdd.add(Beam(rightBeamPosition))
                        crossedPositions.add(rightBeamPosition)
                    }
                    splits++
                }
            }

            indexesToRemove.reversed().forEach {
                beams.removeAt(it)
            }
            beams.addAll(beamsToAdd)

        }
        return splits
    }

    fun solvePart2(): Long {
        return timelinesFromPoint(startPosition)
    }

    private fun timelinesFromPoint(point: Point): Long {
        var currentPoint = point
        while (matrix.inRange(currentPoint)) {
            if (matrix[currentPoint] == Tile.Splitter) {
                return timelinesFromSplitter(currentPoint)
            }
            currentPoint = Down.moveFrom(currentPoint)
        }
        return 1
    }

    private fun timelinesFromSplitter(splitter: Point): Long {
        val cachedResult = splitterTimelines[splitter]
        if (cachedResult != null) {
            return cachedResult
        }

        val result = timelinesFromPoint(Left.moveFrom(splitter)) + timelinesFromPoint(Right.moveFrom(splitter))

        splitterTimelines[splitter] = result
        return result
    }
}


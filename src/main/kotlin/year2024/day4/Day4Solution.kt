package day4

import shared.*
import shared.checkers.ExactStringChecker
import shared.checkers.StringCharCheckResult.*

class Day4Solution(val matrix: Matrix<Char>) {

    val directions = arrayOf(
        Up, UpRight, Right, DownRight, Down, DownLeft, Left, UpLeft
    )


    fun findXmas(): Int {

        var result = 0

        findXs().forEach { x ->
            directions.forEach { dir ->
                if (checkDirection(x, dir)) {
                    result++
                }
            }
        }

        return result
    }

    fun findMasCross(): Int {
        var result = 0

        findAs().forEach { a ->

            fun checkPair(pair: Pair<Point, Point>): Boolean {
                return matrix.inRange(pair.first) && matrix.inRange(pair.second)
                        && (matrix[pair.first] == 'M' && matrix[pair.second] == 'S'
                        || matrix[pair.first] == 'S' && matrix[pair.second] == 'M')
            }

            val pair1 = Pair(UpLeft.moveFrom(a), DownRight.moveFrom(a))
            val pair2 = Pair(DownLeft.moveFrom(a), UpRight.moveFrom(a))

            if (checkPair(pair1) && checkPair(pair2)) {
                result++
            }

        }

        return result
    }

    private fun checkDirection(start: Point, direction: Direction): Boolean {

        val checker = ExactStringChecker("MAS")

        var currentPoint = start
        while (true) {

            currentPoint = direction.moveFrom(currentPoint)

            if (!matrix.inRange(currentPoint)) {
                return false
            }

            when (checker.checkNextChar(matrix[currentPoint])) {
                Failed -> return false
                CharValid -> {}
                is PartCompleted -> return true
            }
        }
    }

    private fun findXs(): List<Point> {
        val xs = mutableListOf<Point>()

        matrix.forEachIndexed { r, chars ->
            chars.forEachIndexed { c, char ->
                if (char == 'X') {
                    xs.add(Point(c, r))
                }
            }
        }
        return xs
    }

    private fun findAs(): List<Point> {
        val aPoints = mutableListOf<Point>()

        matrix.forEachIndexed { r, chars ->
            chars.forEachIndexed { c, char ->
                if (char == 'A') {
                    aPoints.add(Point(c, r))
                }
            }
        }
        return aPoints
    }
}
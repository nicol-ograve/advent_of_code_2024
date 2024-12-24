package day15

import shared.*
import java.lang.Exception
import java.lang.RuntimeException
import java.util.Scanner


class Warehouse(scanner: Scanner) {
    val matrix: Matrix<WarehouseTile>
    val instructions: MutableList<Direction>

    lateinit var robotPosition: Point

    init {
        val warehouseLines = mutableListOf<String>()
        val instructionsLines = mutableListOf<String>()

        do {
            val line = scanner.nextLine()

            if (line.isNotEmpty()) {
                warehouseLines.add(line)
            }
        } while (line.isNotEmpty())

        while (scanner.hasNextLine()) {
            instructionsLines.add(scanner.nextLine())
        }

        matrix = Array(warehouseLines.size) { r ->
            Array(warehouseLines[r].length * 2) { c ->
                when (warehouseLines[r][c / 2]) {
                    '#' -> WarehouseTile.Wall()
                    '@' -> {
                        robotPosition = Point(c - 1, r)
                        WarehouseTile.Empty
                    }

                    'O' -> WarehouseTile.Box(if (c % 2 == 0) BoxSide.Left else BoxSide.Right)
                    '.' -> WarehouseTile.Empty
                    else -> throw RuntimeException("Invalid tile")
                }
            }
        }

        val instr = mutableListOf<Direction>()

        instructionsLines.forEach {
            it.forEach { char ->

                instr.add(
                    when (char) {
                        '<' -> Left
                        '^' -> Up
                        '>' -> Right
                        'v' -> Down
                        else -> throw RuntimeException("Invalid instruction")
                    }
                )
            }

        }

        instructions = instr

    }

    fun executeNextInstruction() {
        val direction = instructions.removeFirst()

        val nextPoint = direction.moveFrom(robotPosition)
        when (matrix[nextPoint]) {
            is WarehouseTile.Wall -> {}
            WarehouseTile.Empty -> {
                robotPosition = nextPoint
            }

            is WarehouseTile.Box -> {
                moveBox(direction, nextPoint)
            }

        }
    }

    private fun moveBox(direction: Direction, point: Point) {

        if (direction.isHorizontal()) {
            moveBoxHorizontally(direction, point)
        } else {
            moveBoxVertically(direction, point)
        }
    }

    private fun moveBoxHorizontally(direction: Direction, point: Point) {

        var boxOffset = point
        while (matrix.inRange(boxOffset) && matrix[boxOffset] is WarehouseTile.Box) {
            boxOffset = direction.moveFrom(boxOffset)
        }

        if (matrix.inRange(boxOffset) && matrix[boxOffset] == WarehouseTile.Empty) {
            val reversedDirection = direction.reversed
            while (boxOffset != point) {
                val nextPoint = reversedDirection.moveFrom(boxOffset)
                matrix[boxOffset] = matrix[nextPoint]
                boxOffset = nextPoint
            }
            matrix[point] = WarehouseTile.Empty
            robotPosition = point
        }
    }

    private fun moveBoxVertically(direction: Direction, point: Point) {
        try {
            val boxesPoints = boxesThatCanBeMovedVertically(direction, point)

            val tempMap = hashMapOf<Point, WarehouseTile>()
            boxesPoints.forEach {
                val nextPoint = direction.moveFrom(it)
                tempMap[nextPoint] = matrix[it]
                matrix[it] = WarehouseTile.Empty
            }
            tempMap.keys.forEach {
                matrix[it] = tempMap[it]!!
            }
            robotPosition = point
        } catch (e: Exception) {
            // Nothing to be done
        }

    }

    private fun boxesThatCanBeMovedVertically(direction: Direction, point: Point): HashSet<Point> {
        val box = matrix[point]
        if (box !is WarehouseTile.Box) {
            throw RuntimeException("Error")
        }

        val otherPoint = Point(if (box.side == BoxSide.Left) point.x + 1 else point.x - 1, point.y)
        val nextPoints = arrayOf(direction.moveFrom(point), direction.moveFrom(otherPoint))

        if (nextPoints.any { matrix[it] is WarehouseTile.Wall }) {
            throw RuntimeException("Cannot be moved")
        }

        if (nextPoints.all { matrix[it] is WarehouseTile.Empty }) {
            return hashSetOf(point, otherPoint)
        }

        val result = hashSetOf(point, otherPoint)
        if (matrix[nextPoints[0]] is WarehouseTile.Box) {
            result.addAll(boxesThatCanBeMovedVertically(direction, nextPoints[0]))
        }
        if (matrix[nextPoints[1]] is WarehouseTile.Box) {
            result.addAll(boxesThatCanBeMovedVertically(direction, nextPoints[1]))
        }

        return result
    }

    fun getBoxesCoordinatesValue(): Int {
        var sum = 0

        val rows = matrix.rows()
        val columns = matrix.columns()

        for (r in 0 until rows) {
            for (c in 0 until columns) {
                val point = Point(c, r)
                val tile = matrix[point]
                if (tile is WarehouseTile.Box && tile.side == BoxSide.Left) {
                    sum += getCoordinatesValue(point)
                }
            }

        }
        return sum
    }

    private fun getCoordinatesValue(point: Point): Int {
        return point.y * 100 + point.x
    }

}
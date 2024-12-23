package day15

import day6.TerrainTile
import shared.*
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
            Array(warehouseLines[r].length) { c ->
                when (warehouseLines[r][c]) {
                    '#' -> WarehouseTile.Wall()
                    '@' -> {
                        robotPosition = Point(c, r)
                        WarehouseTile.Empty
                    }

                    'O' -> WarehouseTile.Box(BoxSide.Left)
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
                var boxOffset = nextPoint
                while (matrix.inRange(boxOffset) && matrix[boxOffset] is WarehouseTile.Box) {
                    boxOffset = direction.moveFrom(boxOffset)
                }

                if (matrix.inRange(boxOffset) && matrix[boxOffset] == WarehouseTile.Empty) {
                    matrix[boxOffset] = WarehouseTile.Box(BoxSide.Left)
                    matrix[nextPoint] = WarehouseTile.Empty
                    robotPosition = nextPoint
                }


            }

        }
    }

    fun getBoxesCoordinatesValue(): Int {
        var sum = 0

        val rows = matrix.rows()
        val columns = matrix.columns()

        for (r in 0 until rows) {
            for (c in 0 until columns) {
                val point = Point(c, r)
                if (matrix[point] is WarehouseTile.Box) {
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
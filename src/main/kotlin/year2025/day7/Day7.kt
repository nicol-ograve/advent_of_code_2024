package year2025.day7

import utils.getDataLinesWithYear
import shared.*

fun main() {
    val isDemo = false
    val lines = getDataLinesWithYear(7, 2025, if (isDemo) arrayOf("demo") else emptyArray())

    lateinit var startingPoint: Point

    val matrix = Array(lines.size) { r ->
        Array(lines[r].count()) { c ->
            when (lines[r][c]) {
                '.' -> Tile.Empty
                '^' -> Tile.Splitter
                'S' -> {
                    startingPoint = Point(c, r)
                    Tile.Empty
                }

                else -> throw RuntimeException("Invalid char")
            }
        }
    }

    val solver = Day7Solver(matrix, startingPoint)
    val result = solver.solvePart2()

    println(result)
}


data class Beam(var position: Point)

enum class Tile {
    Empty, Splitter;

    override fun toString(): String {
        return if (this == Tile.Empty) "." else "^"
    }
}
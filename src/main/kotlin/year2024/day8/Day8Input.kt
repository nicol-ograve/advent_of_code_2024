package year2024.day8

import shared.Matrix
import shared.Point

sealed class AntennaTile {
    object Empty : AntennaTile()
    class BunnyAntenna(val symbol: Char) : AntennaTile()
}

class Day8Input(private val lines: List<String>) {

    val matrix: Matrix<AntennaTile>
    val symbolsPositions = HashMap<Char, MutableList<Point>>()

    init {
        val mtx = Array(lines.size) { r ->
            Array(lines[r].length) { c ->
                if (lines[r][c] == '.') {
                    AntennaTile.Empty
                } else {
                    registerSymbolLocation(lines[r][c], r, c)
                    AntennaTile.BunnyAntenna(lines[r][c])
                }
            }
        }

        matrix = mtx
    }

    private fun registerSymbolLocation(symbol: Char, row: Int, column: Int) {
        if (!symbolsPositions.containsKey(symbol)) {
            symbolsPositions[symbol] = mutableListOf()
        }

        symbolsPositions[symbol]!!.add(Point(column, row))
    }
}
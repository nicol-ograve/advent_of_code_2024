package day12

import shared.Matrix
import shared.Point

class Day12Input(lines: List<String>) {

    val matrix: Matrix<Char>

    init {

        val mtx = Array(lines.size) { r ->
            Array(lines[r].length) { c ->
                lines[r][c]
            }
        }

        matrix = mtx

    }

}
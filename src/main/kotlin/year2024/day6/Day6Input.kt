package year2024.day6

import shared.Matrix
import shared.Point
import shared.TerrainTile


class Day6Input(lines: List<String>) {

    val matrix: Matrix<TerrainTile>
    lateinit var initialGuardPosition: Point

    init {

        val mtx = Array(lines.size) { r ->
            Array(lines[r].length) { c ->
                if(lines[r][c] == '^'){
                    initialGuardPosition = Point(c, r)
                }
                if (lines[r][c] == '#') TerrainTile.Obstruction else TerrainTile.Empty
            }
        }

        matrix = mtx

    }

}
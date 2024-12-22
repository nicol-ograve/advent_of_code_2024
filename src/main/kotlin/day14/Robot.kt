package day14

import shared.Point

data class Robot(var position: Point, val velocity: Point, val boardSize: Point) {
    fun step() {
        position = Point(
            correctPosition(position.x, velocity.x, boardSize.x),
            correctPosition(position.y, velocity.y, boardSize.y)
        )
    }

    private fun correctPosition(current: Int, velocity: Int, size: Int): Int {
        var new = current + velocity
        if (new < 0) {
            new += size
        } else if (new >= size) {
            new -= size
        }


        return new
    }
}

fun robotFromInput(input: String, boardSize: Point): Robot {
    return input.split(" ").map { parts ->
        parts.substring(2).split(",").map { it.toInt() }.let {
            Point(it[0], it[1])
        }
    }.let {
        Robot(it[0], it[1], boardSize)
    }
}


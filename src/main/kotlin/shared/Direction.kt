package shared


sealed interface Direction {
    fun moveFrom(startPoint: Point, times: Int = 1): Point
    fun canTravelFrom(currentPosition: Point, startPoint: Point): Boolean

    val reversed: Direction
}

object Up : Direction {
    override fun moveFrom(startPoint: Point, times: Int): Point {
        return startPoint.copy(y = startPoint.y - times)
    }

    override fun canTravelFrom(startPoint: Point, endPoint: Point): Boolean {
        return startPoint.x == endPoint.x && startPoint.y >= endPoint.y
    }

    override val reversed: Direction
        get() = Down


    override fun toString(): String {
        return "^"
    }
}

object Down : Direction {
    override fun moveFrom(startPoint: Point, times: Int): Point {
        return startPoint.copy(y = startPoint.y + times)
    }


    override fun canTravelFrom(startPoint: Point, endPoint: Point): Boolean {
        return startPoint.x == endPoint.x && startPoint.y <= endPoint.y
    }

    override val reversed: Direction
        get() = Up

    override fun toString(): String {
        return "v"
    }
}

object Left : Direction {
    override fun moveFrom(startPoint: Point, times: Int): Point {
        return startPoint.copy(x = startPoint.x - times)
    }

    override fun canTravelFrom(startPoint: Point, endPoint: Point): Boolean {
        return startPoint.y == endPoint.y && startPoint.x >= endPoint.x
    }

    override val reversed: Direction
        get() = Right

    override fun toString(): String {
        return "<"
    }
}

object Right : Direction {
    override fun moveFrom(startPoint: Point, times: Int): Point {
        return startPoint.copy(x = startPoint.x + times)
    }

    override fun canTravelFrom(startPoint: Point, endPoint: Point): Boolean {
        return startPoint.y == endPoint.y && startPoint.x <= endPoint.x
    }

    override val reversed: Direction
        get() = Left

    override fun toString(): String {
        return ">"
    }
}


object DownRight : Direction {
    override fun moveFrom(startPoint: Point, times: Int): Point {
        return startPoint.copy(x = startPoint.x + times, y = startPoint.y + times)
    }

    override fun canTravelFrom(currentPosition: Point, startPoint: Point): Boolean {
        TODO("Not yet implemented")
    }

    override val reversed: Direction
        get() = UpLeft
}


object DownLeft : Direction {
    override fun moveFrom(startPoint: Point, times: Int): Point {
        return startPoint.copy(x = startPoint.x - times, y = startPoint.y + times)
    }

    override fun canTravelFrom(currentPosition: Point, startPoint: Point): Boolean {
        TODO("Not yet implemented")
    }

    override val reversed: Direction
        get() = UpRight
}

object UpLeft : Direction {
    override fun moveFrom(startPoint: Point, times: Int): Point {
        return startPoint.copy(x = startPoint.x - times, y = startPoint.y - times)
    }

    override fun canTravelFrom(currentPosition: Point, startPoint: Point): Boolean {
        TODO("Not yet implemented")
    }

    override val reversed: Direction
        get() = DownRight
}

object UpRight : Direction {
    override fun moveFrom(startPoint: Point, times: Int): Point {
        return startPoint.copy(x = startPoint.x + times, y = startPoint.y - times)
    }

    override fun canTravelFrom(currentPosition: Point, startPoint: Point): Boolean {
        TODO("Not yet implemented")
    }

    override val reversed: Direction
        get() = DownLeft
}

object StandDirection :Direction{
    override fun moveFrom(startPoint: Point, times: Int): Point {
        return startPoint
    }

    override fun canTravelFrom(currentPosition: Point, startPoint: Point): Boolean {
       return false
    }

    override val reversed: Direction
        get() = this

    override fun toString(): String {
        return "A"
    }
}


fun Direction.turn90DegRight(): Direction {
    return when (this) {
        Up -> Right
        UpRight -> DownRight
        Right -> Down
        DownRight -> DownLeft
        Down -> Left
        DownLeft -> UpLeft
        Left -> Up
        UpLeft -> UpRight
        StandDirection -> StandDirection
    }
}

fun Direction.turn90DegLeft(): Direction {
    return this.reversed.turn90DegRight()
}

fun Direction.isHorizontal(): Boolean {
    return this is Right || this is Left
}

fun Direction.isVertical(): Boolean {
    return this is Up || this is Down
}


val allDirections = arrayOf(Down, Right, Up, Left)

// Micro-optimization
val nextDirections = hashMapOf<Direction, Array<Direction>>(
    Pair(Up, arrayOf(Right, Up, Left)),
    Pair(Right, arrayOf(Down, Right, Up)),
    Pair(Down, arrayOf(Left, Down, Right)),
    Pair(Left, arrayOf(Up, Left, Down)),
)

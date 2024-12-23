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
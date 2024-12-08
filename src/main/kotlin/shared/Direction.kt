package shared


sealed interface Direction {
    fun moveFrom(startPoint: Point, times: Int = 1): Point
     fun canTravelFrom(currentPosition: Point, startPoint: Point): Boolean

    val reverse: Direction
}

object Up : Direction {
    override fun moveFrom(startPoint: Point, times: Int): Point {
        return startPoint.copy(y = startPoint.y - times)
    }

    override fun canTravelFrom(startPoint:  Point, endPoint: Point): Boolean {
        return startPoint.x == endPoint.x && startPoint.y >= endPoint.y
    }

    override val reverse: Direction
        get() = Down
}

object Down : Direction {
    override fun moveFrom(startPoint: Point, times: Int): Point {
        return startPoint.copy(y = startPoint.y + times)
    }


    override fun canTravelFrom(startPoint:  Point, endPoint: Point): Boolean {
        return startPoint.x == endPoint.x && startPoint.y <= endPoint.y
    }

    override val reverse: Direction
        get() = Up
}

object Left : Direction {
    override fun moveFrom(startPoint: Point, times: Int): Point {
        return startPoint.copy(x = startPoint.x - times)
    }

    override fun canTravelFrom(startPoint:  Point, endPoint: Point): Boolean {
        return startPoint.y == endPoint.y && startPoint.x >= endPoint.x
    }

    override val reverse: Direction
        get() = Right
}

object Right : Direction {
    override fun moveFrom(startPoint: Point, times: Int): Point {
        return startPoint.copy(x = startPoint.x + times)
    }

    override fun canTravelFrom(startPoint:  Point, endPoint: Point): Boolean {
        return startPoint.y == endPoint.y && startPoint.x <= endPoint.x
    }
    override val reverse: Direction
        get() = Left
}


object DownRight : Direction {
    override fun moveFrom(startPoint: Point, times: Int): Point {
        return startPoint.copy(x = startPoint.x + times, y = startPoint.y + times)
    }

    override fun canTravelFrom(currentPosition: Point, startPoint: Point): Boolean {
        TODO("Not yet implemented")
    }

    override val reverse: Direction
        get() = UpLeft
}


object DownLeft : Direction {
    override fun moveFrom(startPoint: Point, times: Int): Point {
        return startPoint.copy(x = startPoint.x - times, y = startPoint.y + times)
    }

    override fun canTravelFrom(currentPosition: Point, startPoint: Point): Boolean {
        TODO("Not yet implemented")
    }

    override val reverse: Direction
        get() = UpRight
}

object UpLeft : Direction {
    override fun moveFrom(startPoint: Point, times: Int): Point {
        return startPoint.copy(x = startPoint.x - times, y = startPoint.y - times)
    }

    override fun canTravelFrom(currentPosition: Point, startPoint: Point): Boolean {
        TODO("Not yet implemented")
    }

    override val reverse: Direction
        get() = DownRight
}

object UpRight : Direction {
    override fun moveFrom(startPoint: Point, times: Int): Point {
        return startPoint.copy(x = startPoint.x + times, y = startPoint.y - times)
    }

    override fun canTravelFrom(currentPosition: Point, startPoint: Point): Boolean {
        TODO("Not yet implemented")
    }

    override val reverse: Direction
        get() = DownLeft
}


fun Direction.turn90DegRight(): Direction {
    return when (this) {

        Down -> Left
        DownLeft -> UpLeft
        DownRight -> DownLeft
        Left -> Up
        Right -> Down
        Up -> Right
        UpLeft -> UpRight
        UpRight -> DownRight
    }
}

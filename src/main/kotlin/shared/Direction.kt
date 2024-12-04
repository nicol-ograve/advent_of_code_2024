package shared


sealed interface Direction {
    fun moveFrom(startPoint: Point, times: Int = 1): Point
    val reverse: Direction
}

object Up : Direction {
    override fun moveFrom(startPoint: Point, times: Int): Point {
        return startPoint.copy(y = startPoint.y - times)
    }

    override val reverse: Direction
        get() = Down
}

object Down : Direction {
    override fun moveFrom(startPoint: Point, times: Int): Point {
        return startPoint.copy(y = startPoint.y + times)
    }

    override val reverse: Direction
        get() = Up
}

object Left : Direction {
    override fun moveFrom(startPoint: Point, times: Int): Point {
        return startPoint.copy(x = startPoint.x - times)
    }

    override val reverse: Direction
        get() = Right
}

object Right : Direction {
    override fun moveFrom(startPoint: Point, times: Int): Point {
        return startPoint.copy(x = startPoint.x + times)
    }

    override val reverse: Direction
        get() = Left
}


object DownRight : Direction {
    override fun moveFrom(startPoint: Point, times: Int): Point {
        return startPoint.copy(x = startPoint.x + times, y = startPoint.y + times)
    }

    override val reverse: Direction
        get() = UpLeft
}


object DownLeft : Direction {
    override fun moveFrom(startPoint: Point, times: Int): Point {
        return startPoint.copy(x = startPoint.x - times, y = startPoint.y + times)
    }

    override val reverse: Direction
        get() = UpRight
}

object UpLeft : Direction {
    override fun moveFrom(startPoint: Point, times: Int): Point {
        return startPoint.copy(x = startPoint.x - times, y = startPoint.y - times)
    }

    override val reverse: Direction
        get() = DownRight
}

object UpRight : Direction {
    override fun moveFrom(startPoint: Point, times: Int): Point {
        return startPoint.copy(x = startPoint.x + times, y = startPoint.y - times)
    }

    override val reverse: Direction
        get() = DownLeft
}


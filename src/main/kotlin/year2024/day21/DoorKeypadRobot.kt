package year2024.day21

import shared.Direction
import shared.Point
import shared.StandDirection

class DoorKeypadRobot {
    private val charKeysMap = arrayOf(
        Pair('7', Point(0, 0)),
        Pair('8', Point(1, 0)),
        Pair('9', Point(2, 0)),
        Pair('4', Point(0, 1)),
        Pair('5', Point(1, 1)),
        Pair('6', Point(2, 1)),
        Pair('1', Point(0, 2)),
        Pair('2', Point(1, 2)),
        Pair('3', Point(2, 2)),
        Pair('0', Point(1, 3)),
        Pair('A', Point(2, 3)),
    )

    private val keyPosition: HashMap<Char, Point> = hashMapOf()

    private var currentPosition: Point

    init {
        charKeysMap.forEach {
            keyPosition[it.first] = it.second
        }

        currentPosition = keyPosition['A']!!
    }

    fun pressKey(char: Char): DoorKeyCommandsResult {
        val position = keyPosition[char]!!

        val result = DoorKeyCommandsResult(
            horizontalCommands = DoorKeyCommand.horizontalShiftCommand(position.x - currentPosition.x),
            verticalCommands = DoorKeyCommand.verticalShiftCommand(position.y - currentPosition.y),
            initialAxis = CommandAxis.Horizontal // if (currentPosition.x == 0) CommandAxis.Horizontal else if (currentPosition.y == 3) CommandAxis.Vertical else CommandAxis.Horizontal
        )

        currentPosition = position

        return result

    }


    fun allCombosTo(char: Char): List<List<Direction>> {
        val charPosition = keyPosition[char]!!
        val paths = mutableListOf<List<Direction>>()

        val dirs = arrayOf(
            DoorKeyCommand.horizontalDirection(charPosition.x - currentPosition.x),
            DoorKeyCommand.verticalDirection(charPosition.y - currentPosition.y)
        )

        val queue = mutableListOf<KeypadPath>(
            KeypadPath(emptyList(), currentPosition)
        )

        while (queue.isNotEmpty()) {
            val path = queue.removeFirst()
            dirs.forEach {
                val nextPosition = it.moveFrom(path.position)
                if (nextPosition == charPosition) {
                    paths.add(path.previousDirections + it + StandDirection)
                } else if (isValidPosition(nextPosition)) {
                    queue.add(KeypadPath(path.previousDirections + it, nextPosition))
                }
            }
        }

        currentPosition = charPosition
        return paths
    }

    private fun isValidPosition(position: Point): Boolean {
        return !(position.x < 0 || position.x > 2 || position.y < 0 || position.y > 3 || (position.x == 0 && position.y == 3))
    }
}

data class KeypadPath(val previousDirections: List<Direction>, val position: Point)

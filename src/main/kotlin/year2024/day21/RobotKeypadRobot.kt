package year2024.day21

import shared.*

class RobotKeypadRobot {
    private val charKeysMap = arrayOf(
        Pair(DoorKeyCommand.DoorKeyUp, Point(1, 0)),
        Pair(DoorKeyCommand.A, Point(2, 0)),
        Pair(DoorKeyCommand.DoorKeyLeft, Point(0, 1)),
        Pair(DoorKeyCommand.DoorKeyDown, Point(1, 1)),
        Pair(DoorKeyCommand.DoorKeyRight, Point(2, 1)),
    )
    private val directionButtonsMap = arrayOf(
        Pair(Up, Point(1, 0)),
        Pair(StandDirection, Point(2, 0)),
        Pair(Left, Point(0, 1)),
        Pair(Down, Point(1, 1)),
        Pair(Right, Point(2, 1)),
    )

    private val keyPosition: HashMap<DoorKeyCommand, Point> = hashMapOf()
    private val directionPosition: HashMap<Direction, Point> = hashMapOf()

    var currentPosition: Point

    init {
        charKeysMap.forEach {
            keyPosition[it.first] = it.second
        }

        directionButtonsMap.forEach {
            directionPosition[it.first] = it.second
        }

        currentPosition = keyPosition[DoorKeyCommand.A]!!
    }


    // If direction is null, button is A
    fun allCombosTo(button: Direction): List<List<Direction>> {
        val charPosition = directionPosition[button]!!
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
        return !(position.x < 0 || position.x > 2 || position.y < 0 || position.y > 1 || (position.x == 0 && position.y == 0))
    }


    fun pressKeys(commands: List<DoorKeyCommandsResult>): List<DoorKeyCommandsResult> {

        val results = mutableListOf<DoorKeyCommandsResult>()


        commands.map { command ->
            val (horizontalCommands, verticalCommands, initialAxis) = command
            sortCommands(command) + DoorKeyCommand.A

        }.forEach { list ->
            list.forEach { command ->
                val position = keyPosition[command]!!
                val result = DoorKeyCommandsResult(
                    horizontalCommands = DoorKeyCommand.horizontalShiftCommand(position.x - currentPosition.x),
                    verticalCommands = DoorKeyCommand.verticalShiftCommand(position.y - currentPosition.y),
                    initialAxis = if (currentPosition.x == 0) CommandAxis.Horizontal else if (currentPosition.y == 3) CommandAxis.Vertical else CommandAxis.Horizontal
                )

                currentPosition = position

                results.add(result)
            }
        }
        return results
    }

    private fun sortCommands(command: DoorKeyCommandsResult): List<DoorKeyCommand> {
        val (horizontalCommands, verticalCommands, initialAxis) = command
        //return horizontalCommands + verticalCommands
        return when (initialAxis) {
            CommandAxis.Horizontal -> horizontalCommands + verticalCommands
            CommandAxis.Vertical -> verticalCommands + horizontalCommands
            null -> {
                val hCommandDistance =
                    if (horizontalCommands.isNotEmpty()) commandDistance(horizontalCommands.first()) else -1
                val vCommandDistance =
                    if (verticalCommands.isNotEmpty()) commandDistance(verticalCommands.first()) else -1


                if (hCommandDistance < vCommandDistance)
                    horizontalCommands + verticalCommands
                else
                    verticalCommands + horizontalCommands
            }
        }
    }

    private fun commandDistance(command: DoorKeyCommand): Int {

        val point = keyPosition[command]!!
        return (point.x - currentPosition.x) + (point.y - currentPosition.y)
    }
}
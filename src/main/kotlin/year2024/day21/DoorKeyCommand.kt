package year2024.day21

import shared.*
import kotlin.math.abs

enum class DoorKeyCommand {
    DoorKeyUp, DoorKeyRight, DoorKeyDown, DoorKeyLeft, A;

    override fun toString(): String {
        return when (this) {
            DoorKeyUp -> "^"
            DoorKeyRight -> ">"
            DoorKeyDown -> "v"
            DoorKeyLeft -> "<"
            A -> "A"
        }
    }

    companion object {

        fun horizontalDirection(diff: Int): Direction {
            return if (diff > 0) Right else Left
        }

        fun verticalDirection(diff: Int): Direction {
            return if (diff > 0) Down else Up
        }

        fun horizontalShiftCommand(diff: Int): List<DoorKeyCommand> {
            val command = if (diff > 0) DoorKeyRight else DoorKeyLeft
            return List(abs(diff)) { command }
        }

        fun verticalShiftCommand(diff: Int): List<DoorKeyCommand> {
            val command = if (diff > 0) DoorKeyDown else DoorKeyUp
            return List(abs(diff)) { command }
        }

    }
}

enum class CommandAxis { Horizontal, Vertical }
data class DoorKeyCommandsResult(
    val horizontalCommands: List<DoorKeyCommand>, val verticalCommands: List<DoorKeyCommand>,
    val initialAxis: CommandAxis?
)
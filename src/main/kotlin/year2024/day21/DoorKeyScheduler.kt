package year2024.day21

import shared.Direction
import shared.StandDirection
import java.lang.StringBuilder

class DoorKeyScheduler {

    var totalComplexity = 0L

    private val doorKeyRobot = DoorKeypadRobot()
    private val intermediateRobot = RobotKeypadRobot()
    private val firstRobot = RobotKeypadRobot()


    fun unlockDoor(key: String) {
        val dirs = mutableListOf<Direction>()
        key.forEach {
            val doorCombos = doorKeyRobot.allCombosTo(it)

            val min = doorCombos.map { dirs ->
                bestIntermediateCombo(dirs)
            }.minBy { it.size }
            dirs.addAll(min)
        }

        val numericPart = key.replace("A", "").toInt()

        println()
        println(dirs.joinToString(""))
        println()

        println("${dirs.size} * $numericPart")
        val result = dirs.size * numericPart
        totalComplexity += result
    }

    private fun bestIntermediateCombo(commands: List<Direction>): List<Direction> {
        val result = mutableListOf<Direction>()
        commands.forEach { cmd ->

            val allCombos = intermediateRobot.allCombosTo(cmd)
            if (allCombos.isEmpty()) {
                result.add(StandDirection)
            } else {
                val min = allCombos.map {
                    bestHumanCombo(it)
                }.minBy { it.size }
                result.addAll(min)
            }
        }

        return result
    }

    private fun bestHumanCombo(commands: List<Direction>): List<Direction> {
        val result = mutableListOf<Direction>()
        commands.forEach {

            val allCombos = firstRobot.allCombosTo(it)
            if (allCombos.isEmpty()) {
                result.add(StandDirection)
            } else {
                val min = allCombos.minBy {
                    it.size
                }

                result.addAll(min)
            }

        }

        return result
    }

    fun unlockDoorHeuristic(key: String) {

        val doorCommands = key.map {
            val doorBotResult = doorKeyRobot.pressKey(it)
            doorBotResult
        }
        println()


        val imCommands = intermediateRobot.pressKeys(doorCommands)
        imCommands.forEach {
            print(toSequence(it))
        }
        println()

        val humanCommands = firstRobot.pressKeys(imCommands)
        val totalLength = humanCommands.sumOf { it.horizontalCommands.size + it.verticalCommands.size + 1 }
        humanCommands.forEach {
            print(toSequence(it))
        }

        val numericPart = key.replace("A", "").toInt()

        println()

        println("$totalLength * $numericPart")
        val result = totalLength * numericPart
        totalComplexity += result


    }
    //<A^A>^^AvvvA
    //<A^A^^>AvvvA
    //v<<A>>^A<A>AvA<^AA>A<vAAA>^A
    //v<<A>>^A<A>A<AAv>A^Av<AAA^>A
    //v<<AA>A^>AvAA^<A>Av<<A>>^AvA^Av<<A>>^AAv<A>A^A<A>Av<<A>A^>AAA<Av>A^A
    //<vA<AA>>^AvAA<^A>A<v<A>>^AvA^A<vA>^A<v<A>^A>AAvA^A<v<A>A>^AAAvA<^A>A


    private fun toSequence(commandsResult: DoorKeyCommandsResult): String {
        val builder = StringBuilder()
        val firstCommands =
            if (commandsResult.initialAxis == CommandAxis.Horizontal) commandsResult.horizontalCommands else commandsResult.verticalCommands
        val secondCommands =
            if (commandsResult.initialAxis != CommandAxis.Horizontal) commandsResult.horizontalCommands else commandsResult.verticalCommands
        builder.append(firstCommands.joinToString(""))
        builder.append(secondCommands.joinToString(""))
        builder.append("A")
        return builder.toString()
    }
}
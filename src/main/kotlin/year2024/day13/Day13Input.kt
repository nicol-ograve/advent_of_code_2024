package year2024.day13

import shared.Point
import java.util.Scanner

class Day13Input(private val scanner: Scanner) {
    val configurations: List<ClawConfiguration>

    init {
        val configs = mutableListOf<ClawConfiguration>()
        while (scanner.hasNext()) {
            val buttonALine = scanner.nextLine()
            val buttonBLine = scanner.nextLine()
            val prizeLine = scanner.nextLine()

            if (scanner.hasNext()) {
                scanner.nextLine()
            }

            val buttonA = buttonALine.replace("Button A: X+", "").replace(" Y+", "")
                .split(",")
                .let { ClawButton(it[0].toInt(), it[1].toInt()) }

            val buttonB = buttonBLine.replace("Button B: X+", "").replace(" Y+", "")
                .split(",")
                .let { ClawButton(it[0].toInt(), it[1].toInt()) }

            val prize = prizeLine.replace("Prize: X=", "").replace(" Y=", "")
                .split(",")
                .let { Point(it[0].toInt(), it[1].toInt()) }

            configs.add(ClawConfiguration(buttonA, buttonB, prize))

        }
        configurations = configs.toList()
    }
}

data class ClawButton(val increaseX: Int, val increaseY: Int)

data class ClawConfiguration(val buttonA: ClawButton, val buttonB: ClawButton, val prizePosition: Point)
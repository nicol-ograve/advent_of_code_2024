package day13

import shared.Point

class Day13Solution(val input: Day13Input) {

    fun solve(): Long {

        return input.configurations.mapNotNull { solveConfiguration(it) }.sum()


    }

    private fun solveConfiguration(configuration: ClawConfiguration): Long? {

        val solutions = mutableListOf<ClawSolution>()

        val (buttonA, buttonB, prize) = configuration

        val prizeX = prize.x.toLong() //+ 10000000000000L
        val prizeY = prize.y.toLong()// + 10000000000000L

        val maxAX = prizeX / buttonA.increaseX

        for (a in maxAX downTo 0) {
            val rest = prizeX - a * buttonA.increaseX
            if (rest % buttonB.increaseX == 0L) {
                val b = rest / buttonB.increaseX
                if (a * buttonA.increaseY + b * buttonB.increaseY == prizeY) {
                    solutions.add(ClawSolution(a, b))
                }


            }

        }

        var currentPrize = Point(prize.x, prize.y)
        var aCount = 0
        while (currentPrize.x % buttonA.increaseX == 0 && currentPrize.y % buttonA.increaseY == 0) {
            aCount++
            currentPrize = Point(currentPrize.x - buttonA.increaseX, currentPrize.y - buttonA.increaseY)
        }

        println(aCount)


        return solutions.minOfOrNull {
            it.a * 3 + it.b
        }
    }
}

data class ClawSolution(val a: Long, val b: Long)
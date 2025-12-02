package day13

import shared.Point
import shared.prime.factorize

class Day13Solution(val input: Day13Input) {

    fun solve(): Long {

        return input.configurations.mapIndexedNotNull { index, it ->

            solveEquation(it)

        }.sum()
        //solveConfiguration(it) }.sum()


    }

    private fun solveEquation(configuration: ClawConfiguration): Long? {


        val (buttonA, buttonB, prize) = configuration


        val ay = buttonA.increaseY
        val by = buttonB.increaseY
        val ax = buttonA.increaseX
        val bx = buttonB.increaseX
        val py = prize.y + 10000000000000L
        val px = prize.x + 10000000000000L

        val b = (ay * px - ax * py) / (ay * bx - ax * by)

        if ((px - b * bx) % ax != 0L) {
            return null
        }

        val a = (px - b * bx) / ax

        if (ay * a + by * b == py) {
            return 3L * a + b
        }

        return null;
    }

    private fun solveConfiguration(configuration: ClawConfiguration): Long? {

        val solutions = mutableListOf<ClawSolution>()

        val (buttonA, buttonB, prize) = configuration

        val prizeX = prize.x.toLong() // + 10000000000000L
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

        /*
        var currentPrize = Point(prize.x, prize.y)
        var aCount = 0
        while (currentPrize.x % buttonA.increaseX == 0 && currentPrize.y % buttonA.increaseY == 0) {
            aCount++
            currentPrize = Point(currentPrize.x - buttonA.increaseX, currentPrize.y - buttonA.increaseY)
        }
        */



        return solutions.minOfOrNull {
            it.a * 3 + it.b
        }
    }
}

data class ClawSolution(val a: Long, val b: Long)
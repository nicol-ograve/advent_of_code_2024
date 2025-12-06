package year2024.day8

import shared.Point
import shared.inRange

class Day8Solution(val input: Day8Input) {

    val result = hashSetOf<Point>()


    fun solve(): Int {
        result.clear()
        input.symbolsPositions.keys.forEach {
            findAntinodes(it, input.symbolsPositions[it]!!)
        }
        return result.size
    }

    fun findAntinodes(symbol: Char, antennas: List<Point>) {


        for (i in 0 until antennas.size - 1) {
            for (j in i + 1 until antennas.size) {

                val first = antennas[i]
                val second = antennas[j]

                result.add(first)
                result.add(second)

                var delta = Point(first.x - second.x, first.y - second.y)


                var antinode1 = Point(first.x + delta.x, first.y + delta.y)

                while (input.matrix.inRange(antinode1)) {
                    result.add(antinode1)
                    antinode1 = Point(antinode1.x + delta.x, antinode1.y + delta.y)
                }


                var antinode2 = Point(second.x - delta.x, second.y - delta.y)

                while (input.matrix.inRange(antinode2)) {
                    result.add(antinode2)
                    antinode2 = Point(antinode2.x - delta.x, antinode2.y - delta.y)
                }


            }
        }


    }

    private fun findAntinodesWithoutRepetition(symbol: Char, antennas: List<Point>) {


        for (i in 0 until antennas.size - 1) {
            for (j in i + 1 until antennas.size) {

                val first = antennas[i]
                val second = antennas[j]

                var delta = Point(first.x - second.x, first.y - second.y)

                val antinode1 = Point(first.x + delta.x, first.y + delta.y)
                val antinode2 = Point(second.x - delta.x, second.y - delta.y)

                if (input.matrix.inRange(antinode1)) {
                    result.add(antinode1)
                }

                if (input.matrix.inRange(antinode2)) {
                    result.add(antinode2)
                }


            }
        }


    }
}
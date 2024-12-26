package day20

import shared.*

class CpuRun(val matrix: Matrix<TerrainTile>, val initialPosition: Point, val finalPosition: Point) {

    val costs = hashMapOf<Point, Int>()

    val directions = hashMapOf<Point, Direction>()

    fun findCuts() {
        val points = findStandardPath()
        val maxCost = points.size

        val gains = hashMapOf<Int, Int>()
        var highGains = 0


        points.forEach { point ->
            val pointDirection = directions[point]!!
            val crossDirections =
                arrayOf(
                    pointDirection.turn90DegLeft(),
                    pointDirection.turn90DegRight(),
                    pointDirection.reversed,
                    pointDirection
                )

            val gainingPoints = crossDirections.mapNotNull { dir ->
                val oneStepOver = dir.moveFrom(point, 1)
                val twoStepsOver = dir.moveFrom(point, 2)
                if (matrix.inRange(twoStepsOver) && matrix[oneStepOver] == TerrainTile.Obstruction && matrix[twoStepsOver] == TerrainTile.Empty) twoStepsOver else null
            }.forEach { gainPoint ->
                // println(gainPoint)
                val gain = costs[gainPoint]!! - costs[point]!!
                if (gain < 0) {
                    val actualGain = gain * -1 - 2
                    if (actualGain > 100) {
                        //highGains++
                    }

                    if (!gains.containsKey(actualGain)) {
                        gains[actualGain] = 1
                    } else {
                        gains[actualGain] = gains[actualGain]!! + 1
                    }
                }
            }


        }


        gains.keys.sorted().forEach {
            println("$it: ${gains[it]!!}")
            if (it >= 100) {
                highGains += gains[it]!!
            }
        }


        println(highGains)

    }

    fun findStandardPath(): List<Point> {

        val list = mutableListOf<Point>()

        var steps = 0

        val finalDirection = allDirections.find {
            val nextPoint = it.moveFrom(finalPosition)
            matrix[nextPoint] == TerrainTile.Empty
        }!!
        directions[finalPosition] = finalDirection.reversed
        costs[finalPosition] = 0

        var position = finalDirection.moveFrom(finalPosition)
        var direction = finalDirection

        directions[position] = finalDirection.reversed
        list.add(position)

        steps++
        costs[position] = steps

        do {
            direction = nextDirections[direction]!!.find {
                val nextPoint = it.moveFrom(position)
                matrix[nextPoint] == TerrainTile.Empty
            }!!
            position = direction.moveFrom(position)

            directions[position] = direction.reversed

            steps++
            costs[position] = steps
            list.add(position)
        } while (position != initialPosition)

        println("OK")

        return list.reversed()

    }


}

data class Path(
    val cost: Int, val direction: Direction, val position: Point
)

val allDirections = arrayOf(Right, Down, Left, Up)
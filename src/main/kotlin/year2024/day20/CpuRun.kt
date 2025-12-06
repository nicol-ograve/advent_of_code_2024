package year2024.day20

import shared.*

class CpuRun(val matrix: Matrix<TerrainTile>, val initialPosition: Point, val finalPosition: Point) {

    val costs = hashMapOf<Point, Int>()

    val directions = hashMapOf<Point, Direction>()

    fun findCuts(): Int {
        val points = findStandardPath().toMutableList()

        points.forEach {
            costs[it] = points.size - (costs[it] ?: 0)
        }
        costs[finalPosition] = points.size
        points.add(finalPosition)

        val gains = hashMapOf<Int, Int>()

        points.forEach { point ->
            //var point = initialPosition
            val reachablePoints = findPathsCosts(point)

            reachablePoints.forEach { target ->
                val (targetPoint, pathCost) = target

                val gain = costs[targetPoint]!! - costs[point]!! - pathCost
                if (gain >= 50) {
                    if (!gains.containsKey(gain)) {
                        gains[gain] = 1
                    } else {
                        gains[gain] = gains[gain]!! + 1
                    }
                }

            }

        }

        var highGains = 0
        gains.keys.sorted().forEach {
            if (it >= 100) {
                highGains += gains[it]!!
            }

        }


        return highGains
    }

    private fun findStandardPath(): List<Point> {
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

        return list.reversed()

    }


    fun findPathsCosts(initialPosition: Point): HashMap<Point, Int> {

        val minCosts = hashMapOf<Point, Int>()

        val position = initialPosition

        val paths = mutableListOf<Path>()

        val targets = hashSetOf<Point>()

        minCosts[position] = 0

        allDirections.forEach {
            val nextPoint = it.moveFrom(position)
            //if (matrix[nextPoint] == TerrainTile.Obstruction) {
            minCosts[nextPoint] = 1
            paths.add(Path(1, it, nextPoint))
            //}
        }

        while (paths.isNotEmpty()) {
            val path = paths.removeFirst()
            val (cost, direction, point) = path

            if (cost < 20) {
                nextDirections[direction]!!.forEach {
                    val newCost = cost + 1
                    val nextPoint = it.moveFrom(point)
                    val nextPointCurrentCost = minCosts[nextPoint]

                    if (matrix.inRange(nextPoint) && (nextPointCurrentCost == null || newCost < nextPointCurrentCost)) {
                        minCosts[nextPoint] = newCost

                        val isReverseCrossing = costs[point] != null && costs[nextPoint] != null
                                && costs[nextPoint]!! < costs[point]!!
                        if (matrix[nextPoint] == TerrainTile.Empty) {
                            targets.add(nextPoint)
                        }

                        //if (matrix[nextPoint] == TerrainTile.Obstruction) {
                        val newPath = Path(newCost, it, nextPoint)
                        paths.add(newPath)

                    }

                }
            }
        }

        val pointCosts = hashMapOf<Point, Int>()
        targets.forEach {
            pointCosts[it] = minCosts[it]!!
        }
        return pointCosts
    }

    private fun find2PicoSecondsCuts(points: List<Point>) {

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

}

data class Path(
    val cost: Int, val direction: Direction, val position: Point
)

val allDirections = arrayOf(Right, Down, Left, Up)
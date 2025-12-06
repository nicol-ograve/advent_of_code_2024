package year2024.day16

import shared.*


class ReindeerRace(lines: List<String>) {


    val matrix: Matrix<TerrainTile>
    private lateinit var initialReindeerPosition: Point
    private lateinit var finalPosition: Point

    private var reindeerDirection: Direction = Right

    private val minCosts = hashMapOf<Point, Long>()

    init {

        val mtx = Array(lines.size) { r ->
            Array(lines[r].length) { c ->
                if (lines[r][c] == 'S') {
                    initialReindeerPosition = Point(c, r)
                } else if (lines[r][c] == 'E') {
                    finalPosition = Point(c, r)
                }
                if (lines[r][c] == '#') TerrainTile.Obstruction else TerrainTile.Empty
            }
        }


        matrix = mtx

    }

    fun findBestPath(): Int {

        val position = initialReindeerPosition
        val direction = reindeerDirection

        val results = mutableListOf<Result>()

        val paths = mutableListOf<ReindeerPath>()

        minCosts[position] = 0

        allDirections.forEach {
            var cost = 1
            val nextPoint = it.moveFrom(position)
            if (matrix[nextPoint] == TerrainTile.Empty) {
                if (it == direction.reversed) {
                    cost += 2000
                } else if (it != direction) {
                    cost += 1000
                }

                minCosts[nextPoint] = cost.toLong()
                paths.add(ReindeerPath(cost, it, nextPoint, mutableListOf(nextPoint)))
            }
        }

        while (paths.isNotEmpty()) {
            val path = paths.removeFirst()
            val (cost, direction, point, points) = path

            nextDirections[direction]!!.forEach {
                var newCost = cost
                val nextPoint = it.moveFrom(point)
                val nextPointCost = minCosts[nextPoint]

                newCost += if (it != direction) 1001 else 1


                if (matrix[nextPoint] == TerrainTile.Empty && (nextPointCost == null || nextPointCost + 1000 >= newCost)) {
                    minCosts[nextPoint] = newCost.toLong()
                    val newList = mutableListOf<Point>()
                    newList.addAll(points)
                    newList.add(nextPoint)

                    val newPath = ReindeerPath(newCost, it, nextPoint, newList)
                    paths.add(newPath)

                    if (nextPoint == finalPosition) {

                        results.add(Result(newCost, newList))
                    }
                }

            }


        }

        return countBestPathsPoints(results)

        //return minCosts[finalPosition]!!
    }

    private fun countBestPathsPoints(results: List<Result>): Int {
        val minCost = results.minOf { it.cost }
        val bestPaths = results.filter { it.cost == minCost }

        val points = hashSetOf<Point>()

        bestPaths.forEach {
            points.addAll(it.points)
        }

        return points.size + 1

    }


}

data class ReindeerPath(
    val cost: Int, val direction: Direction, val position: Point, val points: MutableList<Point>
)

data class Result(val cost: Int, val points: MutableList<Point>)

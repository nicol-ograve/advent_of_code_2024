package day14

import shared.*
import utils.getDataScanner

fun main() {
    val isDemo = false
    val scanner = getDataScanner(14, if (isDemo) arrayOf("demo") else emptyArray())

    val size = if (isDemo) Point(11, 7) else Point(101, 103)

    val matrix: Matrix<Int> = Array(size.y) {
        Array(size.x) { 0 }
    }

    val robots = mutableListOf<Robot>()

    while (scanner.hasNextLine()) {
        robots.add(robotFromInput(scanner.nextLine(), size))
    }

    robots.forEach {
        matrix[it.position]++
    }


    /*
    val robot = Robot(Point(2, 4), Point(2, -3), size)

    robot.step()
    assert(robot.position == Point(4, 1))
    robot.step()
    assert(robot.position == Point(6, 5))
     */

    for (i in 0 until 100000) {
        robots.forEach {
            matrix[it.position]--
            it.step()
            matrix[it.position]++
        }

        if (checkEasterEgg(matrix)) {
            println("Easter egg: $i")
            matrix.print()
            println()
        }
    }


    val quadrants = arrayOf(0, 0, 0, 0)
    val midlines = Point((size.x - 1) / 2, (size.y - 1) / 2)

    robots.forEach {
        if (it.position.x < midlines.x && it.position.y < midlines.y) {
            quadrants[0]++
        } else if (it.position.x < midlines.x && it.position.y > midlines.y) {
            quadrants[1]++
        } else if (it.position.x > midlines.x && it.position.y < midlines.y) {
            quadrants[2]++
        } else if (it.position.x > midlines.x && it.position.y > midlines.y) {
            quadrants[3]++
        }
    }


    val result = quadrants.fold(1) { acc, i -> acc * i }

    println(result)
}

fun checkEasterEgg(matrix: Matrix<Int>): Boolean {


    val rows = matrix.rows()
    val columns = matrix.columns()


    for (r in 0 until rows - 3) {
        for (c in 3 until columns - 3) {
            val point = Point(c, r)
            val pointL = Point(c - 1, r + 1)
            val pointR = Point(c + 1, r + 1)
            val pointLL = Point(c - 2, r + 2)
            val pointRR = Point(c + 2, r + 2)

            val points = arrayOf(point, pointL, pointR, pointLL, pointRR, Point(c - 3, r + 3))
            if (points.all { matrix[it] == 1 }) {
                return true
            }


        }
    }
    return false

    for (r in rows - 1 downTo 0) {
        val gap = rows - r - 1
        for (c in gap until columns - gap) {
            val point = Point(c, r)
            if (matrix[point] != 1) {
                return false
            }
        }

    }
    return true
}
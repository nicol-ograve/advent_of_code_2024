package day21

import utils.getDataScanner

fun main() {
    val isDemo = false
    val scanner = getDataScanner(21, if (isDemo) arrayOf("demo") else emptyArray())

    val scheduler = DoorKeyScheduler()
    while (scanner.hasNextLine()) {
        scheduler.unlockDoor(scanner.nextLine())
    }


    // Too high: 293472, 284652

    val result = scheduler.totalComplexity

    println(result)
}
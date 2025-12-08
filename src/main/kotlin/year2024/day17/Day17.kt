package year2024.day17

import utils.getDataScannerWithYear

fun main() {
    val isDemo = false
    val scanner = getDataScannerWithYear(17, 2024, if (isDemo) arrayOf("demo") else emptyArray())

    val registerInitialValues = hashMapOf(
        Pair(ThreeBitRegister.A, readRegisterValue(scanner.nextLine(), "A")),
        Pair(ThreeBitRegister.B, readRegisterValue(scanner.nextLine(), "B")),
        Pair(ThreeBitRegister.C, readRegisterValue(scanner.nextLine(), "C"))
    )

    scanner.nextLine()
    val program = scanner.nextLine().replace("Program: ", "").split(",").map { it.toLong() }

    val computer = ThreeBitComputer(
        registerInitialValues,
        program
    )

    // Wrong: 5,4,7,6,4,7,5,0,4
    val result = computer.executeProgram()
    println(result)
    println("END")

}

private fun readRegisterValue(string: String, registerName: String): Long {
    return string.replace("Register $registerName: ", "").toLong()
}
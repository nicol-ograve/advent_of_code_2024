package day17

import utils.getDataScanner
import java.util.Scanner

fun main() {
    val isDemo = false
    val scanner = getDataScanner(17, if (isDemo) arrayOf("demo") else emptyArray())

    val registerInitialValues = hashMapOf(
        Pair(ThreeBitRegister.A, readRegisterValue(scanner.nextLine(), "A")),
        Pair(ThreeBitRegister.B, readRegisterValue(scanner.nextLine(), "B")),
        Pair(ThreeBitRegister.C, readRegisterValue(scanner.nextLine(), "C"))
    )

    scanner.nextLine()
    val program = scanner.nextLine().replace("Program: ", "").split(",").map { it.toInt() }

    val computer = ThreeBitComputer(
        registerInitialValues,
        program
    )

    // Wrong: 5,4,7,6,4,7,5,0,4
    val result = computer.executeProgram()
    println(result)
    println("END")

}

private fun readRegisterValue(string: String, registerName: String): Int {
    return string.replace("Register $registerName: ", "").toInt()
}
package day3

import day3.checkers.ExactStringChecker
import day3.checkers.NumberPartChecker
import day3.checkers.StringCharCheckResult.*
import day3.checkers.StringPartChecker
import java.util.Scanner

class Solution(val scanner: Scanner) {
    private val checkers: Array<StringPartChecker<*>> = arrayOf(
        ExactStringChecker("mul("),
        NumberPartChecker(','),
        NumberPartChecker(')'),
    )

    private var currentCheckerIndex = 0
    private var currentNumbers = mutableListOf<Int>()

    private var finalResult = 0L

    private val enabler = ExactStringChecker("do()")
    private val disabler = ExactStringChecker("don't()")

    fun solve(): Long {

        var enabled = true
        var line = ""
        while (scanner.hasNext()) {
            line += scanner.next()
        }

        while (line.isNotEmpty()) {
            val char = line[0]

            if (enabled) {
                val checker = checkers[currentCheckerIndex]

                when (val result = checker.checkNextChar(char)) {
                    CharValid -> {}
                    Failed -> resetAll()
                    is PartCompleted -> {
                        if (result.result is Int) {
                            currentNumbers.add(result.result)
                        }
                        if (currentCheckerIndex == checkers.size - 1) {

                            println("mul(" + currentNumbers.toString() + ")")
                            finalResult += currentNumbers.fold(1) { acc, value -> acc * value }
                            resetAll()
                        } else {
                            currentCheckerIndex++
                        }
                    }
                }
            }

            enabled = checkEnabled(char, enabled)



            line = line.substring(1)
        }
        return finalResult
    }

    private fun checkEnabled(char: Char, enabled: Boolean): Boolean {
        var result = enabled
        if (enabled) {
            when (disabler.checkNextChar(char)) {
                CharValid -> {}
                Failed -> disabler.reset()
                is PartCompleted -> {
                    disabler.reset()
                    result = false
                }
            }
        } else {
            when (enabler.checkNextChar(char)) {
                CharValid -> {}
                Failed -> enabler.reset()
                is PartCompleted -> {
                    enabler.reset()
                    result = true
                }
            }
        }

        return result
    }

    private fun resetAll() {
        currentCheckerIndex = 0
        currentNumbers = mutableListOf()

        checkers.forEach { it.reset() }
    }
}
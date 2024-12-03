package day3.checkers

import day3.checkers.StringCharCheckResult.*

class NumberPartChecker(private val termination: Char) : StringPartChecker<Int> {
    private var currentValue = ""

    override fun checkNextChar(nextChar: Char): StringCharCheckResult<Int> {

        if (nextChar == termination && currentValue.isNotEmpty()) {
            return PartCompleted(currentValue.toInt())
        }

        if (nextChar.isDigit()) {
            currentValue += nextChar
            return CharValid
        }

        return Failed

    }

    override fun reset() {
        currentValue = ""
    }
}
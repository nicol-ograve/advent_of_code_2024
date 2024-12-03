package day3.checkers

import day3.checkers.StringCharCheckResult.PartCompleted

class ExactStringChecker(private val stringToCheck: String) : StringPartChecker<Nothing?> {

    private var currentCheckIndex = -1

    override fun checkNextChar(char: Char): StringCharCheckResult<Nothing?> {
        currentCheckIndex++
        return if (char == stringToCheck[currentCheckIndex]) {
            if (currentCheckIndex == stringToCheck.length - 1) {
                PartCompleted(null)
            } else {
                StringCharCheckResult.CharValid
            }
        } else {
            StringCharCheckResult.Failed
        }
    }

    override fun reset() {
        currentCheckIndex = -1
    }
}
package day3.checkers

sealed class StringCharCheckResult<out T> {
    object Failed : StringCharCheckResult<Nothing>()
    class PartCompleted<T>(val result: T) : StringCharCheckResult<T>()

    object CharValid : StringCharCheckResult<Nothing>()
}

interface StringPartChecker<out T> {

    fun checkNextChar(char: Char): StringCharCheckResult<T>

    fun reset()

}



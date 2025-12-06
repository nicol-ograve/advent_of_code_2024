package year2024.day7

class Day7Solution(private val input: Day7Input, private val withConcatOperator: Boolean) {


    fun solve(): Long {
        return input.operations.filter {
            isValid(it)
        }.sumOf {
            it.result
        }

    }

    private fun isValid(operation: BridgeOperation): Boolean {

        val queue = ArrayDeque<OperationState>()
        val initialValues = operation.values.toMutableList()

        val firstValue = initialValues.removeFirst()
        val secondValue = initialValues.removeFirst()

        queue.add(OperationState(initialValues.toMutableList(), firstValue + secondValue))
        queue.add(OperationState(initialValues.toMutableList(), firstValue * secondValue))
        if (withConcatOperator) {
            queue.add(OperationState(initialValues.toMutableList(), firstValue.concat(secondValue)))
        }

        while (queue.isNotEmpty()) {
            val attempt = queue.removeFirst()

            if (attempt.acc == operation.result && attempt.values.isEmpty()) {
                return true
            }

            if (attempt.acc <= operation.result && attempt.values.isNotEmpty()) {

                val values = attempt.values

                val nextValue = values.removeFirst()

                queue.add(OperationState(values.toMutableList(), attempt.acc + nextValue))
                queue.add(OperationState(values.toMutableList(), attempt.acc * nextValue))
                if (withConcatOperator) {
                    queue.add(OperationState(values.toMutableList(), attempt.acc.concat(nextValue)))
                }
            }

        }

        return false

    }
}

data class OperationState(val values: MutableList<Long>, val acc: Long)

fun Long.concat(other: Long): Long {
    return (this.toString() + other.toString()).toLong()
}

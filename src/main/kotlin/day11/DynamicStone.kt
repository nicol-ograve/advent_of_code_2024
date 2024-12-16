package day11
/*
class DynamicStone(val initialValue: Long, val onSplit: () -> Unit) {
    var value: Long?
    val children = mutableListOf<DynamicStone>()

    var blinks = 0

    init {
        value = initialValue
    }

    fun blink() {
        blinks++

        if (cache[initialValue]!![blinks] != null) {
            return
        }

        val stringValue = value.toString()
        if (value != null) {
            if (value == 0L) {
                value = 1
            } else if (stringValue.length % 2 == 0) {
                if (stringValue.contains("-")) {
                    println("???")
                }
                onSplit()
                children.add(
                    DynamicStone(stringValue.substring(0, stringValue.length / 2).toLong(), onSplit)
                )
                children.add(
                    DynamicStone(stringValue.substring(stringValue.length / 2).toLong(), onSplit)
                )
                value = null

            } else {
                value = value!! * 2024
            }
        } else {
            children.forEach { it.blink() }
        }
    }

    companion object {
        val cache = HashMap<Long, HashMap<Int, List<DynamicStoneSnapshot>>>()


        fun blink(value: Long, blinks: Int) {

            if (cache[value]!![blinks] != null) {
                return
            } else {
                if (blinks == 0) {
                    cache[value] = hashMapOf(
                        Pair(0, listOf(DynamicStoneSnapshot(value, 0)))
                    )
                } else {
                    blink(value, blinks - 1)
                    cache[value]!![blinks - 1]!!.forEach { childValue ->
                        val results = resultsFrom(childValue.value)
                    }
                }

            }
        }


        fun resultsFrom(value: Long): List<Long> {

        }
    }
}

data class DynamicStoneSnapshot(val value: Long, val blinksCount: Int)
*/
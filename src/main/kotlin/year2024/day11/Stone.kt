package day11

import java.lang.RuntimeException

class Stone private constructor(initialValue: Long, val onSplit: () -> Unit) {

    companion object {

        private val cache = hashMapOf<Long, Stone>()


        fun create(initialValue: Long, onSplit: () -> Unit): Stone {
            if (!cache.containsKey(initialValue)) {
                cache[initialValue] = Stone(initialValue, onSplit)
            }
            return cache[initialValue]!!
        }
    }


    private var value: Long?
    private val children = mutableListOf<StoneChild>()

    private val childrenCountMap = hashMapOf<Int, Long>()

    init {
        value = initialValue
    }

    var totalChildren = 1L


    fun blink(blinksCount: Int): Long {
        if (childrenCountMap.containsKey(blinksCount)) {
            return childrenCountMap[blinksCount]!!
        }

        if (blinksCount > 1 && !childrenCountMap.containsKey(blinksCount - 1)) {
            throw RuntimeException("Nonsense")
        }

        var result: Long
        val stringValue = value.toString()
        if (value != null) {
            if (value == 0L) {
                value = 1
                result = 1
            } else if (stringValue.length % 2 == 0) {

                totalChildren++
                onSplit()

                val onChildrenSplit: () -> Unit = {
                    totalChildren++
                    onSplit()
                }

                children.add(
                    StoneChild(create(stringValue.substring(0, stringValue.length / 2).toLong(), onChildrenSplit))
                )
                children.add(
                    StoneChild(create(stringValue.substring(stringValue.length / 2).toLong(), onChildrenSplit))
                )
                value = null
                result = 2

            } else {
                value = value!! * 2024
                result = 1
            }
        } else {
            result = children.sumOf {
                val result = it.stone.blink(it.blinksCount - 1)
                it.blinksCount++

                result
            }
        }

        childrenCountMap[blinksCount] = result
        return result
    }

    fun printTree() {
        if (value !== null) {
            println(value)
        } else {
            println("Children:")
            children.forEach { it.stone.printTree() }
        }
    }

}

data class StoneChild(val stone: Stone, var blinksCount: Int = 1)

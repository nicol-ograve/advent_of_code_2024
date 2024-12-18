package day11

class StoneBlinker(initialStones: Array<Stone>) {
    private val stones: Array<Stone>
    private var totalStones: Int

    init {
        stones = initialStones.map { it }.toTypedArray()
        totalStones = stones.size
    }


    fun blink(times: Int) {
        for (i in 0 until times) {
            var sum = 0L
            stones.forEach {

                val count = it.blink(i)
                if (i <= times - 1) {
                    sum += count
                }
            }
            println(sum)
        }


    }

}
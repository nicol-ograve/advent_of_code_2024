package day11

class StoneBlinker(initialStones: Array<Stone>) {
    val stones: Array<Stone>
    var totalStones: Int

    init {
        stones = initialStones.map { it }.toTypedArray()
        totalStones = stones.size
    }


    fun blink(times: Int) {
        for (i in 0 until times) {
            stones.forEach {
                it.blink()
            }
        }


    }

}
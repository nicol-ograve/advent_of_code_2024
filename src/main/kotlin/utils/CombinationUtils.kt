package utils


fun generateAllCombinations(size: Int): List<Array<Int>> {

    val combo = arrayOf(0, 1, 2, 3, 4, 5, 6, 7)

    val combos = mutableListOf<Array<Int>>()

    fun storeCurrentCombo() {
        combos.add(combo.copyOf())
    }


    while (true) {

        storeCurrentCombo()

        while (combo[7] < size - 1) {
            combo[7]++
            storeCurrentCombo()
        }

        var currentIndex = 6
        while (currentIndex >= 0 && combo[currentIndex] == size - (8 - currentIndex)) {
            currentIndex--
        }
        if (currentIndex == -1) {
            return combos
        }

        combo[currentIndex]++
        for (i in currentIndex + 1..7) {
            combo[i] = combo[i - 1] + 1
        }

    }
}
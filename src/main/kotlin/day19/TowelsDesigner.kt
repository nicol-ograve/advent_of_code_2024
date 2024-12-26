package day19

class TowelsDesigner(patterns: List<String>, val towels: List<String>) {

    val rootPatternStep = PatternStep("")

    init {
        for (pattern in patterns) {
            rootPatternStep.addPattern(pattern)
        }
    }

    fun createTowels(): Int {

        return towels.count {
            canCreateTowel(it)
        }
    }

    private fun canCreateTowel(towel: String): Boolean {

        var currentIndex = 0
        var runningPatterns = mutableListOf<PatternStep>(rootPatternStep)

        while (currentIndex < towel.length) {
            val hasFinalPattern = runningPatterns.any { it.isFinal }
            if (hasFinalPattern) {
                runningPatterns.add(rootPatternStep)
            }

            runningPatterns = runningPatterns.mapNotNull { it.nextPattern(towel[currentIndex]) }.toMutableList()


            currentIndex++
        }

        val available = runningPatterns.any { it.isFinal }

        return available
    }
}


class PatternStep(val prefix: String) {

    val children: HashMap<Char, PatternStep> = hashMapOf()
    var isFinal = false

    fun addPattern(pattern: String) {
        if (pattern.isEmpty()) {
            isFinal = true
        } else {
            val firstChar = pattern.first()
            val rest = pattern.substring(1)

            if (!children.containsKey(firstChar)) {
                children[firstChar] = PatternStep(prefix + firstChar)
            }

            children[firstChar]!!.addPattern(rest)
        }

    }

    fun nextPattern(char: Char): PatternStep? {
        return children[char]
    }
}
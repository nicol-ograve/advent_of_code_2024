package year2024.day19

class TowelsDesigner(patterns: List<String>, private val towels: List<String>) {

    private val rootPatternStep = PatternStep("")

    init {
        for (pattern in patterns) {
            rootPatternStep.addPattern(pattern)
        }
    }

    fun createTowels(): Long {

        return towels.sumOf {
            createTowel(it)
        }
    }

    private fun createTowel(towel: String): Long {

        var currentIndex = 0
        var runningPatterns = mutableListOf<PatternStepRef>(
            PatternStepRef(rootPatternStep)
        )

        while (currentIndex < towel.length) {
            val finalPatterns = runningPatterns.sumOf { if (it.ref?.isFinal == true) it.refsCount else 0 }
            if (finalPatterns > 0) {
                runningPatterns.add(PatternStepRef(rootPatternStep, finalPatterns))
            }

            val nextChar = towel[currentIndex]

            runningPatterns.forEach { it.nextPattern(nextChar) }
            runningPatterns = runningPatterns.filter { it.ref != null }.toMutableList()


            currentIndex++
        }

        // Part 1: return if(runningPatterns.size > 0) 1 else 0
        return runningPatterns.sumOf {
           if(it.ref?.isFinal == true) it.refsCount  else 0
        }
    }
}

class PatternStepRef(var ref: PatternStep?, var refsCount: Long = 1L) {
    fun nextPattern(char: Char): PatternStep? {
        ref = ref?.children?.get(char)
        return ref
    }
}


class PatternStep(private val prefix: String) {

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
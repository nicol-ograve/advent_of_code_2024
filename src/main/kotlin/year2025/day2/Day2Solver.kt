package year2025.day2

import kotlin.math.log10
import kotlin.math.sqrt

class Day2Solver {

    val invalidIds = mutableSetOf<Long>()

    fun solve(ranges: List<Range>): Long {
        ranges.forEach {
            checkRange(it)
        }
        return invalidIds.sum()
    }

    fun checkRange(range: Range) {

        var digits: Int = range.start.digitsCount()
        var sections = getSections(digits)

        for (i in range.start..range.end) {

            if(i.digitsCount() > 1) {

                if (i.digitsCount() != digits) {
                    digits = i.digitsCount()
                    sections = getSections(digits)
                }

                if (isInvalid(i, sections)) {
                    invalidIds.add(i)
                }
            }
        }
    }

    private fun isInvalid(value: Long, sections: List<Section>): Boolean{
        return sections.any { isInvalid(value, it) }
    }

    private fun isInvalid(value:Long, section: Section): Boolean{
        val stringValue = value.toString()

        for(i in 1 until section.count){
            for(j in 0 until section.size) {
                if (stringValue[i * section.size + j] != stringValue[(i - 1) * section.size + j]) {
                    return false
                }
            }
        }
        return true
    }


    private fun getSections(digits: Int): List<Section> {

        val sections = mutableListOf<Section>()
        for(i in 1..sqrt(digits.toDouble()).toInt() ){
            if(digits % i == 0){
                sections.add(Section(i, digits / i))
                if(i != 1) {
                    sections.add(Section(digits / i, i))
                }
            }
        }
        return sections

    }

}


private data class Section(val size: Int, val count: Int)

fun Long.digitsCount(): Int {
    return log10(toDouble()).toInt() + 1
}


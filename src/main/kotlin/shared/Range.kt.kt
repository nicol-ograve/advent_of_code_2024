package shared

import kotlin.math.max
import kotlin.math.min

data class Range(val start: Long, val end: Long) {

    val size: Long
        get() = end - start + 1 // Inclusive range

    fun contains(value: Long): Boolean {
        return value in start..end
    }


    override fun toString(): String {
        return "$start - $end"
    }

    fun overlap(otherRange: Range): Boolean {
        return (start in otherRange.start..otherRange.end) ||
                (otherRange.start in start..end)
    }


    fun mergeWith(otherRange: Range): Range {
        return Range(min(start, otherRange.start), max(end, otherRange.end))
    }
}


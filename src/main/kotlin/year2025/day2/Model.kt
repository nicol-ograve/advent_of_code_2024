package year2025.day2

data class Range(val start: Long, val end: Long) {
    fun contains(value: Long): Boolean {
        return value in start..end
    }
}
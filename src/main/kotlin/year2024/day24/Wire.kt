package year2024.day24


class Wire(val code: String) {

    var listeners = mutableListOf<WireChangeListener>()
    var value: Boolean? = null

    fun setValue(value: Boolean) {
        this.value = value
        listeners.forEach { it(value) }
    }

    override fun toString(): String {
        return "$code: ${value ?: '-'}"
    }

}

typealias WireChangeListener = (Boolean) -> Unit
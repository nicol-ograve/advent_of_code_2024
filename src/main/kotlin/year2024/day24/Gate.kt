package day24

import java.lang.RuntimeException
import java.util.stream.Stream

enum class GateType {
    AND, OR, XOR;

    companion object {
        fun fromString(s: String): GateType {
            return when (s) {
                "AND" -> AND
                "OR" -> OR
                "XOR" -> XOR
                else -> throw RuntimeException("Unknown Gate type")
            }
        }
    }
}

class Gate(val type: GateType, val inputs: Pair<Wire, Wire>, val output: Wire) {

    init {
        inputs.first.listeners.add { onInputChanged() }
        inputs.second.listeners.add { onInputChanged() }
    }

    private fun onInputChanged() {
        val (first, second) = inputs
        if (first.value != null && second.value != null) {
            when (type) {
                GateType.AND -> output.setValue(first.value!! && second.value!!)
                GateType.OR -> output.setValue(first.value!! || second.value!!)
                GateType.XOR -> output.setValue(first.value!! xor second.value!!)
            }
        }
    }


}
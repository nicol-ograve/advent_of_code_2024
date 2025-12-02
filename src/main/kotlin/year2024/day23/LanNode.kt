package day23

class LanNode(val code: String) {

    val neighbors = hashSetOf<LanNode>()
    fun addNeighbor(neighbor: LanNode) {
        neighbors.add(neighbor)
    }

    override fun toString(): String {
        return code
    }
}
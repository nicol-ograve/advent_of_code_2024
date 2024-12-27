package day23

class LanParty(val input: LanPairsInput) {

    fun findTriplets(): Int {

        val tNodes = input.nodes.keys.filter { it.startsWith("t") }.map { input.nodes[it]!! }

        val triplets = hashSetOf<Triple<LanNode, LanNode, LanNode>>()
        tNodes.forEach { node ->
            val neighbors = node.neighbors.filter { n -> !n.code.startsWith("t") || n.code > node.code }.toHashSet()
            neighbors.forEach { neighbor ->
                neighbor.neighbors.filter { nn -> nn.code > neighbor.code && neighbors.any { it.code == nn.code } }.forEach { third ->
                    triplets.add(Triple(node, neighbor, third))
                }
            }
        }

        return triplets.size 
    }
}
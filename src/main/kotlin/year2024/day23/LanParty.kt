package year2024.day23

class LanParty(val input: LanPairsInput) {

    fun findTriplets(): Int {

        val tNodes = input.nodes.keys.filter { it.startsWith("t") }.map { input.nodes[it]!! }

        val triplets = hashSetOf<Triple<LanNode, LanNode, LanNode>>()
        tNodes.forEach { node ->
            val neighbors = node.neighbors.filter { n -> !n.code.startsWith("t") || n.code > node.code }.toHashSet()
            neighbors.forEach { neighbor ->
                neighbor.neighbors.filter { nn -> nn.code > neighbor.code && neighbors.any { it.code == nn.code } }
                    .forEach { third ->
                        triplets.add(Triple(node, neighbor, third))
                    }
            }
        }

        return triplets.size
    }

    fun findBiggestGroup(): String {
        val nodes = input.nodes.values.toHashSet().sortedBy { it.code }



        nodes.forEach { findGroups(it) }



        return biggestGroup.map { it.code }.sortedBy { it }.joinToString(",")
    }

    var maxLength = 0
    var biggestGroup: List<LanNode> = listOf()

    fun findGroups(node: LanNode) {
        val neighbors = node.neighbors.filter { it.code > node.code }.sortedBy { it.code }

        val lists = mutableListOf<List<LanNode>>()

        neighbors.forEach {
            lists.add(listOf(node, it))
        }

        while (lists.isNotEmpty()) {
            val nodes = lists.removeFirst()

            val candidate =
                neighbors.find { it.code > nodes.last().code && nodes.all { n -> n.neighbors.contains(it) } }

            if (candidate != null) {
                val newList = (nodes + candidate)
                lists.add(newList)

                if (newList.size > maxLength) {
                    maxLength = newList.size
                    biggestGroup = newList

                }

            }


        }

    }

    private fun findBiggestGroupInNodes(initialNodes: HashSet<LanNode>, currentNodes: Set<LanNode>): Int {
        val nodes = initialNodes.sortedBy { it.code }

        if (currentNodes.size > 12) {
            println(
                currentNodes.sortedBy { it.code }.joinToString(
                    ","
                ) { it.code })
        }
        if (nodes.isEmpty()) {
            return 0
        }

        val max = nodes.maxOf { node ->
            val neighbors = node.neighbors.filter { initialNodes.contains(node) && it.code > node.code }

            if (neighbors.isEmpty()) {
                0
            } else {
                val neighborsMax = neighbors.maxOf { n ->
                    findBiggestGroupInNodes(n.neighbors.filter { it.code > n.code && neighbors.contains(it) }
                        .toHashSet(),
                        currentNodes + hashSetOf(node, n))

                }


                1 + neighborsMax
            }

        }


        return max + 1
    }
}
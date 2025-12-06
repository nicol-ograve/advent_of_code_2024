package year2024.day23

class LanPairsInput(pairs: List<String>) {

    val nodes = hashMapOf<String, LanNode>()

    init {
        val stringNodes = hashSetOf<String>()

        val pairNodes = pairs.map {
            it.split("-").let { nodesSplitted ->
                stringNodes.add(nodesSplitted[0])
                stringNodes.add(nodesSplitted[1])
                Pair(nodesSplitted[0], nodesSplitted[1])
            }
        }

        stringNodes.forEach {
            nodes[it] = LanNode(it)
        }

        pairNodes.forEach {
            nodes[it.first]!!.addNeighbor(nodes[it.second]!!)
            nodes[it.second]!!.addNeighbor(nodes[it.first]!!)
        }




    }

}


package de.fluxparticle.animation.graph

import de.fluxparticle.utils.chain.Chain
import de.fluxparticle.utils.chain.chainOf
import de.fluxparticle.utils.chain.cons

class GraphWrapper(graph: Graph) : Graph {

    private val nodeMap = HashMap<Int, GraphNode>()

    private val edges = HashMap<Int, Set<Int>>()

    override val nodes: Collection<GraphNode>
        get() = nodeMap.values

    init {
        graph.nodes.forEach { node ->
            depthFirst(graph, node, chainOf())
        }
    }

    private fun depthFirst(graph: Graph, node: GraphNode, chain: Chain<GraphNode>) {
        if (nodeMap.containsKey(node.id)) {
            return
        }

        val id = node.id
        nodeMap.put(id, node)

        val edgeSet = HashSet<Int>()
        for (next in graph.getNeighbours(node)) {
            if (chain.none { next == it }) {
                edgeSet.add(next.id)
                depthFirst(graph, next, cons(next, chain))
            }
        }

        edges.put(id, edgeSet)
    }

    override fun getNeighbours(node: GraphNode): Collection<GraphNode> {
        return edges[node.id]!!.map { neighbourId -> nodeMap[neighbourId]!! }
    }

}

package de.fluxparticle.animation.graph

interface Graph {

    val nodes: Collection<GraphNode>

    fun getNeighbours(node: GraphNode): Collection<GraphNode>

}

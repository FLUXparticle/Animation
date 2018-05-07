package de.fluxparticle.animation.example

import de.fluxparticle.animation.AnimationQueue
import de.fluxparticle.animation.graph.Graph
import de.fluxparticle.animation.graph.GraphNode

/**
 * Created by sreinck on 30.04.18.
 */
fun linkedListAlgorithm(animationQueue: AnimationQueue) = with(LinkedListBox(animationQueue)) {

}

private class LinkedListBox(private val animationQueue: AnimationQueue) {

    private val heap = HeadSpace()

}

private class HeadSpace : Graph {

    override val nodes: Collection<GraphNode>
        get() = emptyList()

    override fun getNeighbours(node: GraphNode): Collection<GraphNode> {
        TODO("not implemented")
    }

}

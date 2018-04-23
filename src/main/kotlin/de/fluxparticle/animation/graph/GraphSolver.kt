package de.fluxparticle.animation.graph

import java.util.*

class GraphSolver(graph: Graph) {

    private val graph: Graph

    init {
        this.graph = GraphWrapper(graph)
    }

    fun solve() {
        val white: MutableSet<GraphNode> = run {
            val nodes = graph.nodes
            nodes.forEach { it.setBlack() }
            nodes
                    .filter { it.isReferenced }
                    .toMutableSet()
        }

        var levelIndex = 0
        while (!white.isEmpty()) {
            val level = nextLevel(white)
            if (level.isEmpty()) {
                break
            }
            white.removeAll(level)

            for (graphNode in level) {
                graphNode.setLevel(levelIndex)
                graphNode.setWhite()
            }
            levelIndex++

            val nodes = level.toTypedArray<GraphNode>()

            GraphLevelSolver(nodes).solve()
        }
    }

    private fun nextLevel(white: Set<GraphNode>): Collection<GraphNode> {
        return white
                .filter { b ->
                    white.stream()
                            .filter { a -> a !== b }
                            .noneMatch { a -> hasPath(a, b) }
                }
                .toList()
    }

    private fun hasPath(a: GraphNode, b: GraphNode): Boolean {
        val visited = HashSet<GraphNode>()

        val queue = LinkedList<GraphNode>()
        queue.add(a)
        visited.add(a)

        while (!queue.isEmpty()) {
            val cur = queue.poll()

            val neighbours = graph.getNeighbours(cur)
            for (neighbour in neighbours) {
                if (!visited.contains(neighbour)) {
                    if (neighbour == b) {
                        return true
                    }
                    queue.add(neighbour)
                    visited.add(neighbour)
                }
            }
        }

        return false
    }

}

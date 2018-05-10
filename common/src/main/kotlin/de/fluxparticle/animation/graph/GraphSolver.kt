package de.fluxparticle.animation.graph

class GraphSolver(graph: Graph) {

    private val graph: Graph

    init {
        this.graph = GraphWrapper(graph)
    }

    fun solve() {
        val white: MutableSet<GraphNode> = run {
            val nodes = graph.nodes
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
            }
            levelIndex++

            val nodes = level.toTypedArray()

            GraphLevelSolver(nodes).solve()
        }
    }

    private fun nextLevel(white: Set<GraphNode>): Collection<GraphNode> {
        return white
                .filter { b ->
                    white
                            .filter { a -> a !== b }
                            .none { a -> hasPath(a, b) }
                }
                .toList()
    }

    private fun hasPath(a: GraphNode, b: GraphNode): Boolean {
        val visited = HashSet<GraphNode>()

        val queue = mutableListOf<GraphNode>()
        queue.add(a)
        visited.add(a)

        while (!queue.isEmpty()) {
            val cur = queue.removeAt(0)

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

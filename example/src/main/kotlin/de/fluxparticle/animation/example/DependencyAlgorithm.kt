package de.fluxparticle.animation.example

import de.fluxparticle.animation.AnimationQueue
import de.fluxparticle.animation.Box
import de.fluxparticle.animation.elementobject.ElementNode
import de.fluxparticle.animation.elementobject.ElementObject
import de.fluxparticle.animation.elementobject.ElementPath
import de.fluxparticle.animation.graph.Graph
import de.fluxparticle.animation.graph.GraphNode
import de.fluxparticle.animation.graph.GraphSolver
import de.fluxparticle.animation.point.DynamicPoint2D
import de.fluxparticle.animation.util.Bounds

/**
 * Created by sreinck on 30.04.18.
 */
fun dependencyAlgorithm(animationQueue: AnimationQueue): Box = DependencyBox(animationQueue).apply {

}

private class DependencyBox(private val animationQueue: AnimationQueue): Box {

    private val artifacts = listOf(Artifact("A", 0), Artifact("B", 1))
    
    private val dependencyGraph = DependencyGraph(artifacts, mapOf(0 to listOf(artifacts[1]), 1 to listOf()))

    override val bounds: Bounds

    init {
        GraphSolver(dependencyGraph).solve()

        val centers = dependencyGraph.nodes
                .associateBy(
                        keySelector = { it },
                        valueTransform = { artifact ->
                            val x = (artifact.getLevel() * ElementObject.OPERAND_WIDTH * 2).toDouble()
                            val y = artifact.getPos().toDouble()
                            Pair(x, y)
                        }
                )

        bounds = centers.values
                .map { center -> Bounds(center.first - ElementObject.OPERAND_WIDTH / 2, center.second - ElementObject.HEIGHT / 2, ElementObject.OPERAND_WIDTH.toDouble(), ElementObject.HEIGHT.toDouble()) }
                .reduce(Bounds::union)

        val elementObjects = centers.mapValues { (artifact, center) ->
            val lines = artifact.toString().split("(?=\\p{Upper})".toRegex())
                    .dropLastWhile { it.isEmpty() }
                    .toTypedArray()
            val elementObject = ElementObject(animationQueue.time, true, true, ElementObject.OBJECT_WIDTH, *lines)
            val translate = DynamicPoint2D(center.first - bounds.minX, center.second - bounds.minY)
            elementObject.setTranslate(translate)
            elementObject
        }

        val elementNodes = mutableListOf<ElementNode>()
        elementNodes.addAll(elementObjects.values)

        elementObjects.forEach { (artifact, fromObject) ->
            val fromCenter = fromObject.translate.lastValue()
            val from = DynamicPoint2D(fromCenter.x.value + ElementObject.OPERAND_WIDTH / 2, fromCenter.y.value)
            val dependencies = dependencyGraph.getNeighbours(artifact)
            for (dependency in dependencies) {
                val toObject = elementObjects[dependency]!!
                val toCenter = toObject.translate.lastValue()
                val to = DynamicPoint2D(toCenter.x.value - ElementObject.OPERAND_WIDTH / 2, toCenter.y.value)
                val path = ElementPath(animationQueue.time, from, to)
                elementNodes.add(path)
            }
        }

        animationQueue.with { queue, doubleExpression ->
            for (elementNode in elementNodes) {
                animationQueue.fadeIn(0, elementNode)
            }
        }

    }
    
}

private class DependencyGraph(private val artifacts: Collection<Artifact>, private val dependencies: Map<Int, List<Artifact>>) : Graph {

    override val nodes: Collection<Artifact>
        get() = artifacts

    override fun getNeighbours(node: GraphNode): Collection<Artifact> {
        return dependencies[node.id]!!
    }

}

private class Artifact(private val name: String, override val id: Int) : GraphNode {

    private var level: Int = 0

    private var pos: Int = 0

    private var white: Boolean = false

    override val preferredPos: Int
        get() = 0

    override val size: Int
        get() = ElementObject.HEIGHT + ElementObject.MARGIN

    override val isReferenced: Boolean
        get() = true

    init {
        level = 0
        pos = 0
        white = true
    }

    fun getPos(): Int {
        return pos
    }

    override fun setPos(pos: Int) {
        this.pos = pos
    }

    fun getLevel(): Int {
        return level
    }

    override fun setLevel(level: Int) {
        this.level = level
    }

    override fun setWhite() {
        white = true
    }

    override fun setBlack() {
        white = false
    }

    override fun toString(): String {
        return name
    }

}


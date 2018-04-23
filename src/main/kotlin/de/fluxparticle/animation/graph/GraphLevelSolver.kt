package de.fluxparticle.animation.graph

import java.lang.Integer.compare
import java.util.*

class GraphLevelSolver(private val nodes: Array<GraphNode>) {

    private val maxK: Int

    private val min: Int

    private val max: Int

    private class Range(private var min: Int, private var max: Int) {

        val isEmpty: Boolean
            get() = min > max

        val center: Int
            get() = (min + max) / 2

        fun getMin(): Int {
            return min
        }

        fun setMin(min: Int) {
            if (min > this.min) {
                this.min = min
            }
        }

        fun getMax(): Int {
            return max
        }

        fun setMax(max: Int) {
            if (max < this.max) {
                this.max = max
            }
        }

    }

    init {
        Arrays.sort(nodes, COMPARATOR)

        var maxK = 0
        for (graphNode in nodes) {
            maxK += graphNode.size
        }
        this.maxK = maxK

        min = nodes[0].preferredPos - maxK
        max = nodes[nodes.size - 1].preferredPos + maxK
    }

    fun solve() {
        val k = findK()

        val ranges = mkRanges(k)

        for (i in ranges.indices) {
            nodes[i].setPos(ranges[i].center)
        }
    }

    private fun findK(): Int {
        var lo = 0
        var hi = maxK

        while (lo < hi) {
            val k = (lo + hi) / 2
            if (check(k)) {
                hi = k
            } else {
                lo = k + 1
            }
        }

        return hi
    }

    private fun check(k: Int): Boolean {
        val ranges = mkRanges(k)

        for (i in ranges.indices) {
            if (ranges[i].isEmpty) {
                return false
            }
        }

        return true
    }

    private fun mkRanges(k: Int): Array<Range> {
        val ranges = Array(nodes.size) {
            Range(min, max)
        }

        for (i in ranges.indices) {
            ranges[i].setMin(nodes[i].preferredPos - k)
            ranges[i].setMax(nodes[i].preferredPos + k)
        }

        for (i in 1 until ranges.size) {
            ranges[i].setMin(ranges[i - 1].getMin() + nodes[i - 1].size)
        }

        for (i in ranges.size - 2 downTo 0) {
            ranges[i].setMax(ranges[i + 1].getMax() - nodes[i].size)
        }

        return ranges
    }

    companion object {

        private val COMPARATOR = { o1: GraphNode, o2: GraphNode -> compare(o1.preferredPos, o2.preferredPos) }

    }

}

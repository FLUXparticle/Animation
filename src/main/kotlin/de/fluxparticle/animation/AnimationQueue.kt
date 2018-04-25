package de.fluxparticle.animation

import de.fluxparticle.animation.elementobject.ElementNode
import de.fluxparticle.animation.elementobject.ElementNodeVisitor
import de.fluxparticle.animation.elementobject.ElementObject
import de.fluxparticle.animation.elementobject.ElementRectangle
import de.fluxparticle.animation.output.Log
import de.fluxparticle.animation.point.DynamicPoint2D
import de.fluxparticle.animation.util.Bounds
import de.fluxparticle.animation.util.Color
import de.fluxparticle.animation.value.Value

class AnimationQueue(val clip: Clip) {

    private val group = mutableListOf<ElementNode>()

    protected lateinit var transitions: AnimationCollection

    val time: Value<Double>
        get() = clip.timeProperty()

    fun with(consumer: (AnimationQueue, Value<Double>) -> Unit) {
        begin()
        consumer(this, this.time)
        end()
    }

    fun begin() {
        LOG1.info("{")
        transitions = AnimationCollection()
    }

    fun add(priority: Int, obj: ElementNode) {
        LOG2.info("ADD " + obj)
        group.add(obj)
        transitions.add(priority, show(obj))
    }

    fun fadeIn(priority: Int, obj: ElementNode) {
        LOG2.info("FADE_IN " + obj)
        LOG3.info("ADD " + obj)
        transitions.add(priority, fadeIn(obj))
        if (obj is ElementRectangle) {
            group.add(0, obj)
        } else {
            group.add(obj)
        }
    }

    fun changeColor(priority: Int, area: ElementRectangle, toColor: Color) {
        LOG2.info("CHANGE_COLOR " + area)
        transitions.add(priority, changeColor(area, toColor))
    }

    fun adjustAreaToBounds(priority: Int, area: ElementRectangle, bounds: Bounds): Boolean {
        LOG2.info("CHANGE_AREA " + area)
        val timeframe = adjustAreaToBounds(area, bounds)
        if (timeframe != null) {
            transitions.add(priority, timeframe)
            val lastY = area.y.lastValue()
            return lastY != bounds.minY
        }

        return false
    }

    fun move(priority: Int, obj: ElementObject, dest: DynamicPoint2D, copy: Boolean, oldObject: ElementObject?, simple: Boolean) {
        LOG2.info("MOVE " + obj)
        if (copy) {
            LOG3.info("ADD (COPY) " + obj)
            group.add(obj)
            transitions.add(priority, show(obj))
        }

        transitions.add(priority, move(obj, dest, simple))
        if (oldObject != null) {
            transitions.add(priority + 1, hide(oldObject))
        }
    }

    fun fadeOut(priority: Int, obj: ElementNode) {
        LOG2.info("FADE_OUT " + obj)
        transitions.add(priority, fadeOut(obj))
    }

    fun update(priority: Int, obj: ElementObject, newContent: String) {
        transitions.add(priority, update(obj, newContent))
    }

    fun end() {
        transitions.appendToClip(clip)
        //        LOG2.info("CLIP " + clip.getCntSequences());
        LOG1.info("}")
    }

    fun endRaw() {
        transitions.appendToClipRaw(clip)
        //        LOG2.info("CLIP " + clip.getCntSequences());
        LOG1.info("}")
    }

    fun getGroup(): List<ElementNode> {
        return group.toList()
    }

    fun <T> getVisitedGroup(visitor: ElementNodeVisitor<T>): List<T> {
        val result = ArrayList<T>(group.size)

        for (elementNode in group) {
            val node = elementNode.accept(visitor)
            result.add(node)
        }

        return result

    }

    companion object {

        private val LOG1 = Log.getLog(1)

        private val LOG2 = Log.getLog(2)

        private val LOG3 = Log.getLog(3)
    }

}

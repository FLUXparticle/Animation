package de.fluxparticle.animation

import de.fluxparticle.animation.`object`.ElementNode
import de.fluxparticle.animation.`object`.ElementNodeVisitor
import de.fluxparticle.animation.`object`.ElementObject
import de.fluxparticle.animation.`object`.ElementRectangle
import de.fluxparticle.animation.output.Log
import de.fluxparticle.animation.point.DynamicPoint2D
import javafx.beans.binding.DoubleExpression
import javafx.geometry.Bounds
import javafx.scene.paint.Color
import java.util.*
import java.util.function.BiConsumer

class AnimationQueue {

    val clip = Clip()

    private val group = LinkedList<ElementNode>()

    protected lateinit var transitions: AnimationCollection

    val time: DoubleExpression
        get() = clip.timeProperty()

    fun with(consumer: BiConsumer<AnimationQueue, DoubleExpression>) {
        begin()
        consumer.accept(this, this.time)
        end()
    }

    fun begin() {
        LOG1.info("{")
        transitions = AnimationCollection()
    }

    fun add(priority: Int, obj: ElementNode) {
        LOG2.info("ADD " + obj)
        group.add(obj)
        transitions.add(priority, AnimationMaker.show(obj))
    }

    fun fadeIn(priority: Int, obj: ElementNode) {
        LOG2.info("FADE_IN " + obj)
        LOG3.info("ADD " + obj)
        transitions.add(priority, AnimationMaker.fadeIn(obj))
        if (obj is ElementRectangle) {
            group.add(0, obj)
        } else {
            group.add(obj)
        }
    }

    fun changeColor(priority: Int, area: ElementRectangle, toColor: Color) {
        LOG2.info("CHANGE_COLOR " + area)
        transitions.add(priority, AnimationMaker.changeColor(area, toColor))
    }

    fun adjustAreaToBounds(priority: Int, area: ElementRectangle, bounds: Bounds): Boolean {
        LOG2.info("CHANGE_AREA " + area)
        val timeframe = AnimationMaker.adjustAreaToBounds(area, bounds)
        if (timeframe != null) {
            transitions.add(priority, timeframe)
            val lastY = area.ySignal().lastValue()
            return lastY != bounds.minY
        }

        return false
    }

    fun move(priority: Int, obj: ElementObject, dest: DynamicPoint2D, copy: Boolean, oldObject: ElementObject?, simple: Boolean) {
        LOG2.info("MOVE " + obj)
        if (copy) {
            LOG3.info("ADD (COPY) " + obj)
            group.add(obj)
            transitions.add(priority, AnimationMaker.show(obj))
        }

        transitions.add(priority, AnimationMaker.move(obj, dest, simple))
        if (oldObject != null) {
            transitions.add(priority + 1, AnimationMaker.hide(oldObject))
        }
    }

    fun fadeOut(priority: Int, obj: ElementNode) {
        LOG2.info("FADE_OUT " + obj)
        transitions.add(priority, AnimationMaker.fadeOut(obj))
    }

    fun update(priority: Int, obj: ElementObject, newContent: String) {
        transitions.add(priority, AnimationMaker.update(obj, newContent))
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
        return Collections.unmodifiableList(group)
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

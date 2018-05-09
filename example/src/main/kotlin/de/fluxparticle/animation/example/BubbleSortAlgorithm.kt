package de.fluxparticle.animation.example

import de.fluxparticle.animation.AnimationQueue
import de.fluxparticle.animation.Box
import de.fluxparticle.animation.elementobject.ElementObject
import de.fluxparticle.animation.elementobject.ElementRectangle
import de.fluxparticle.animation.point.DynamicPoint2D
import de.fluxparticle.animation.signal.Signal
import de.fluxparticle.animation.util.Bounds
import de.fluxparticle.animation.util.Color
import de.fluxparticle.animation.value.Value

/**
 * Created by sreinck on 09.02.16.
 */
fun bubbleSortAlgorithm(animationQueue: AnimationQueue): Box = BubbleSortBox(animationQueue).apply {
    for (top in size() - 1 downTo 0) {
        for (i in 0 until top) {
            if (!inOrder(i)) {
                swap(i)
            }
        }
        ready(top)
    }
}

private class Wrapper<T> {
    var data: T? = null
}

private class BubbleSortBox(private val animationQueue: AnimationQueue): Box {

    private val array = intArrayOf(13, 4, 3, 21, 1)

    private val elementObjects = Array<ElementObject>(array.size) { i ->
        ElementObject(animationQueue.time, true, true, WIDTH, array[i].toString()).apply {
            this.translate = Signal(animationQueue.time, center(i))
        }
    }

    private var rectangleCompare = Wrapper<ElementRectangle>()

    private var rectangleReady = Wrapper<ElementRectangle>()

    override val bounds: Bounds
        get() = Bounds(0.0, 0.0, (SPACING + array.size * (WIDTH + SPACING)).toDouble(), (2 * SPACING + HEIGHT).toDouble())

    init {
        animationQueue.with { aq, _ ->
            for (i in array.indices) {
                aq.fadeIn(0, elementObjects[i])
            }
        }
    }

    fun size(): Int {
        return array.size
    }

    fun inOrder(index: Int): Boolean {
        val result = array[index] < array[index + 1]

        animationQueue.with { aq, t ->

            val bs = surround(index, 2)

            val colorCompare = Color.BLUE
            val rectangle = rectangleCompare.createOrMove(aq, t, bs, colorCompare)

            val c = if (result) Color.GREEN else Color.RED
            aq.changeColor(1, rectangle, c)
        }

        return result
    }

    fun swap(index: Int) {
        val next = index + 1
        run {
            val tmp = array[index]
            array[index] = array[next]
            array[next] = tmp
        }

        animationQueue.with { aq, _ ->
            aq.move(0, elementObjects[index], center(next), false, null, true)
            aq.move(0, elementObjects[next], center(index), false, null, true)

            val tmp = elementObjects[index]
            elementObjects[index] = elementObjects[next]
            elementObjects[next] = tmp
        }
    }

    fun ready(index: Int) {
        val range = array.size - index

        animationQueue.with { aq, t ->
            val rectangle = rectangleCompare.data
            if (rectangle != null) {
                aq.fadeOut(-1, rectangle)
                rectangleCompare.data = null
            }

            val bs = surround(index, range)

            val colorReady = Color.GREEN
            rectangleReady.createOrMove(aq, t, bs, colorReady)
        }
    }

    companion object {

        private const val WIDTH = ElementObject.OPERAND_WIDTH

        private const val HEIGHT = ElementObject.HEIGHT

        private const val SPACING = 10

        private fun center(index: Int): DynamicPoint2D {
            return DynamicPoint2D((SPACING + WIDTH / 2 + index * (WIDTH + SPACING)).toDouble(), (SPACING + HEIGHT / 2).toDouble())
        }

        private fun surround(index: Int, range: Int): Bounds {
            return Bounds((SPACING / 2 + index * (WIDTH + SPACING)).toDouble(), (SPACING / 2).toDouble(), (range * (WIDTH + SPACING)).toDouble(), (HEIGHT + SPACING).toDouble())
        }
    }

}

private fun Wrapper<ElementRectangle>.createOrMove(aq: AnimationQueue, t: Value<Double>, bs: Bounds, colorReady: Color): ElementRectangle {
    val rectangle = this.data
    if (rectangle == null) {
        val newRectangle = ElementRectangle(t, bs, colorReady)
        aq.fadeIn(0, newRectangle)
        this.data = newRectangle
        return newRectangle
    } else {
        aq.adjustAreaToBounds(0, rectangle, bs)
        aq.changeColor(0, rectangle, colorReady)
        return rectangle
    }
}

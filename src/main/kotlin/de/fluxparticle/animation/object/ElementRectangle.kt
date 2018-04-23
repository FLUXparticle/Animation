package de.fluxparticle.animation.`object`

import de.fluxparticle.animation.signal.Signal
import javafx.beans.binding.DoubleExpression
import javafx.geometry.BoundingBox
import javafx.geometry.Bounds
import javafx.scene.paint.Color

class ElementRectangle(time: DoubleExpression, bs: Bounds, fill: Color) : ElementNode(time) {

    private val x: Signal<Double>

    private val y: Signal<Double>

    private val width: Signal<Double>

    private val height: Signal<Double>

    private val fill: Signal<Color>

    val boundsInParent: Bounds
        get() = BoundingBox(x.lastValue(), y.lastValue(), width.lastValue(), height.lastValue())

    fun xSignal(): Signal<Double> {
        return x
    }

    fun ySignal(): Signal<Double> {
        return y
    }

    fun widthSignal(): Signal<Double> {
        return width
    }

    fun heightSignal(): Signal<Double> {
        return height
    }

    fun fillSignal(): Signal<Color> {
        return fill
    }

    init {
        this.x = Signal(time, this, "x", bs.minX)
        this.y = Signal(time, this, "y", bs.minY)
        this.width = Signal(time, this, "w", bs.width)
        this.height = Signal(time, this, "h", bs.height)
        this.fill = Signal(time, this, "fill", fill)
    }

    override fun <R> accept(visitor: ElementNodeVisitor<R>): R {
        return visitor.visitRectangle(this)
    }

}

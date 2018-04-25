package de.fluxparticle.animation.elementobject

import de.fluxparticle.animation.signal.Signal
import de.fluxparticle.animation.util.Bounds
import de.fluxparticle.animation.util.Color
import de.fluxparticle.animation.value.Value

class ElementRectangle(time: Value<Double>, bs: Bounds, fill: Color) : ElementNode(time) {

    val x: Signal<Double> = Signal(time, bs.minX)

    val y: Signal<Double> = Signal(time, bs.minY)

    val width: Signal<Double> = Signal(time, bs.width)

    val height: Signal<Double> = Signal(time, bs.height)

    val fill: Signal<Color> = Signal(time, fill)

    val boundsInParent: Bounds
        get() = Bounds(x.lastValue(), y.lastValue(), width.lastValue(), height.lastValue())

    override fun <R> accept(visitor: ElementNodeVisitor<R>): R {
        return visitor.visitRectangle(this)
    }

}

package de.fluxparticle.animation.`object`

import de.fluxparticle.animation.signal.Signal
import javafx.beans.binding.DoubleExpression

abstract class ElementNode(protected val time: DoubleExpression) {

    private val opacity: Signal<Double>
    fun opacitySignal(): Signal<Double> {
        return opacity
    }

    abstract fun <R> accept(visitor: ElementNodeVisitor<R>): R

    init {
        this.opacity = Signal(time, this, "opacity", 0.0)
    }

}

package de.fluxparticle.animation.elementobject

import de.fluxparticle.animation.signal.Signal
import de.fluxparticle.animation.value.Value

abstract class ElementNode(time: Value<Double>) {

    val opacity: Signal<Double> = Signal(time, 0.0)

    abstract fun <R> accept(visitor: ElementNodeVisitor<R>): R

}

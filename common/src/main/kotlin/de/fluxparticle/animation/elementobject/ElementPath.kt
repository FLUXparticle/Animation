package de.fluxparticle.animation.elementobject

import de.fluxparticle.animation.point.DynamicPoint2D
import de.fluxparticle.animation.value.Value

class ElementPath(time: Value<Double>, val origin: DynamicPoint2D, val dest: DynamicPoint2D) : ElementNode(time) {

    override fun <R> accept(visitor: ElementNodeVisitor<R>): R {
        return visitor.visitPath(this)
    }

}

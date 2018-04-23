package de.fluxparticle.animation.`object`

import de.fluxparticle.animation.point.DynamicPoint2D
import javafx.beans.binding.DoubleExpression

class ElementPath(time: DoubleExpression, val origin: DynamicPoint2D, val dest: DynamicPoint2D) : ElementNode(time) {

    override fun <R> accept(visitor: ElementNodeVisitor<R>): R {
        return visitor.visitPath(this)
    }

}

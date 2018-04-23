package de.fluxparticle.animation.point

import javafx.animation.Interpolatable
import javafx.beans.binding.DoubleExpression
import javafx.beans.property.SimpleDoubleProperty

class DynamicPoint2D : Interpolatable<DynamicPoint2D> {

    private val x: DoubleExpression

    private val y: DoubleExpression

    constructor(x: DoubleExpression, y: DoubleExpression) {
        this.x = x
        this.y = y
    }

    constructor(x: Double, y: Double) {
        this.x = SimpleDoubleProperty(x)
        this.y = SimpleDoubleProperty(y)
    }

    override fun interpolate(endValue: DynamicPoint2D, t: Double): DynamicPoint2D {
        val x = this.xProperty().multiply(1.0 - t).add(endValue.xProperty().multiply(t))
        val y = this.yProperty().multiply(1.0 - t).add(endValue.yProperty().multiply(t))
        return DynamicPoint2D(x, y)
    }

    fun getX(): Double {
        return x.get()
    }

    fun xProperty(): DoubleExpression {
        return x
    }

    fun getY(): Double {
        return y.get()
    }

    fun yProperty(): DoubleExpression {
        return y
    }
}

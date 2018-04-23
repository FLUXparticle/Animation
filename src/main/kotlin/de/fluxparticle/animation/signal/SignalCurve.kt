package de.fluxparticle.animation.signal

import de.fluxparticle.animation.binding.BindingBiFunction
import de.fluxparticle.animation.binding.BindingDoubleBinaryOperator
import de.fluxparticle.animation.point.DynamicPoint2D
import javafx.beans.binding.DoubleBinding
import javafx.beans.binding.DoubleExpression
import javafx.geometry.Point2D

class SignalCurve(start: DynamicPoint2D, end: DynamicPoint2D, private val control1: DynamicPoint2D, private val control2: DynamicPoint2D) : SignalPath<DynamicPoint2D>(start, end) {

    class Builder : SignalPath.Builder<DynamicPoint2D>() {

        override fun build(): SignalPath<DynamicPoint2D> {
            val pointStart = BindingBiFunction(start.xProperty(), start.yProperty()) { x, y -> Point2D(x.toDouble(), y.toDouble()) }
            val pointEnd = BindingBiFunction(end.xProperty(), end.yProperty()) { x, y -> Point2D(x.toDouble(), y.toDouble()) }
            val valueV = BindingBiFunction(pointStart, pointEnd) { start, end -> 3.0 / 2.0 * start.distance(end) }

            val control1X = BindingDoubleBinaryOperator(start.xProperty(), valueV) { x, v -> x + v / 3.0 }
            val control1 = DynamicPoint2D(control1X, start.yProperty())

            val control2X = BindingDoubleBinaryOperator(end.xProperty(), valueV) { x, v -> x + v / 3.0 }
            val control2 = DynamicPoint2D(control2X, end.yProperty())

            return SignalCurve(start, end, control1, control2)
        }

    }

    override fun apply(t: Double): DynamicPoint2D {
        val x = BezierBinding(start.xProperty(), control1.xProperty(), control2.xProperty(), end.xProperty(), t)
        val y = BezierBinding(start.yProperty(), control1.yProperty(), control2.yProperty(), end.yProperty(), t)

        return DynamicPoint2D(x, y)
    }

    private class BezierBinding(private val g0: DoubleExpression, private val g1: DoubleExpression, private val g2: DoubleExpression, private val g3: DoubleExpression, private val t: Double) : DoubleBinding() {

        init {

            bind(g0, g1, g2, g3)
        }

        override fun computeValue(): Double {
            return bezier(g0.get(), g1.get(), g2.get(), g3.get(), t)
        }

    }

    companion object {

        private fun bezier(g0: Double, g1: Double, g2: Double, g3: Double, t: Double): Double {
            val a3 = -g0 + 3 * g1 - 3 * g2 + g3
            val a2 = 3 * g0 - 6 * g1 + 3 * g2
            val a1 = -3 * g0 + 3 * g1

            return a3 * t * t * t + a2 * t * t + a1 * t + g0
        }
    }

}

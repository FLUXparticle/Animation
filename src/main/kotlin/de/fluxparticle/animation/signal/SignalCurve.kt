package de.fluxparticle.animation.signal

import de.fluxparticle.animation.point.DynamicPoint2D
import de.fluxparticle.animation.value.Value
import kotlin.math.sqrt

class SignalCurve(start: DynamicPoint2D, end: DynamicPoint2D, private val control1: DynamicPoint2D, private val control2: DynamicPoint2D) : SignalPath<DynamicPoint2D>(start, end) {

    class Builder : SignalPath.Builder<DynamicPoint2D>() {

        override fun build(): SignalPath<DynamicPoint2D> {
            val pointStart = start.x.combine(start.y, ::Pair)
            val pointEnd = end.x.combine(end.y, ::Pair)
            val valueV = pointStart.combine(pointEnd) { start, end -> 3.0 / 2.0 * start.distance(end) }

            val control1X = start.x.combine(valueV) { x, v -> x + v / 3.0 }
            val control1 = DynamicPoint2D(control1X, start.y)

            val control2X = end.x.combine(valueV) { x, v -> x + v / 3.0 }
            val control2 = DynamicPoint2D(control2X, end.y)

            return SignalCurve(start, end, control1, control2)
        }

    }

    override fun apply(t: Double): DynamicPoint2D {
        val x = BezierBinding(start.x, control1.x, control2.x, end.x, t)
        val y = BezierBinding(start.y, control1.y, control2.y, end.y, t)
        return DynamicPoint2D(x, y)
    }

}

private fun BezierBinding(g0: Value<Double>, g1: Value<Double>, g2: Value<Double>, g3: Value<Double>, t: Double) : Value<Double> {
    return g0.combine(g1, ::Pair).combine(g2.combine(g3, ::Pair)) { g0g1, g2g3-> bezier(g0g1.first, g0g1.second, g2g3.first, g2g3.second, t) }
}


private fun Pair<Double, Double>.distance(other: Pair<Double, Double>): Double {
    val dx = this.first - other.first
    val dy = this.second - other.second
    return sqrt(dx * dx + dy * dy)
}

private fun bezier(g0: Double, g1: Double, g2: Double, g3: Double, t: Double): Double {
    val a3 = -g0 + 3 * g1 - 3 * g2 + g3
    val a2 = 3 * g0 - 6 * g1 + 3 * g2
    val a1 = -3 * g0 + 3 * g1

    return a3 * t * t * t + a2 * t * t + a1 * t + g0
}

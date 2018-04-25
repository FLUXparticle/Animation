package de.fluxparticle.animation.point

import de.fluxparticle.animation.util.Interpolatable
import de.fluxparticle.animation.value.SimpleValue
import de.fluxparticle.animation.value.Value

class DynamicPoint2D : Interpolatable<DynamicPoint2D> {

    val x: Value<Double>

    val y: Value<Double>

    constructor(x: Value<Double>, y: Value<Double>) {
        this.x = x
        this.y = y
    }

    constructor(x: Double, y: Double) {
        this.x = SimpleValue(x)
        this.y = SimpleValue(y)
    }

    override fun interpolate(endValue: DynamicPoint2D, t: Double): DynamicPoint2D {
        val x = this.x.combine(endValue.x) { s, e -> s * (1 - t) + e * t }
        val y = this.y.combine(endValue.y) { s, e -> s * (1 - t) + e * t }
        return DynamicPoint2D(x, y)
    }

}

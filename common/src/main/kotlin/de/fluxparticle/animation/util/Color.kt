package de.fluxparticle.animation.util

/**
 * Created by sreinck on 23.04.18.
 */
data class Color(val red: Double, val green: Double, val blue: Double) : Interpolatable<Color> {

    override fun interpolate(endValue: Color, t: Double): Color {
        if (t <= 0.0) return this
        if (t >= 1.0) return endValue
        val ft = t.toFloat()
        return Color(
                (red + (endValue.red - red) * ft),
                (green + (endValue.green - green) * ft),
                (blue + (endValue.blue - blue) * ft)
        )
    }

    override fun toString(): String {
        return "rgb(${byte(red)},${byte(green)},${byte(blue)})"
    }

    companion object {

        val BLACK = Color(0.0, 0.0, 0.0)

        val BLUE = Color(0.0, 0.0, 1.0)

        val RED = Color(1.0, 0.0, 0.0)

        val GREEN = Color(0.0, 0.5019608, 0.0)

        val WHITE = Color(1.0, 1.0, 1.0)

    }
}

private fun byte(value: Double): Int = (value * 255).toInt()

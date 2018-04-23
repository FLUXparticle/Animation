package de.fluxparticle.animation.signal

class SignalDoubleLine(start: Double, end: Double) : SignalPath<Double>(start, end) {

    class Builder : SignalPath.Builder<Double>() {

        override fun build(): SignalPath<Double> {
            return SignalDoubleLine(start, end)
        }

    }

    override fun apply(t: Double): Double {
        return start * (1.0 - t) + end * t
    }

}

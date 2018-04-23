package de.fluxparticle.animation.signal

import javafx.animation.Interpolatable

class SignalInterpolatable<T : Interpolatable<T>>(start: T, end: T) : SignalPath<T>(start, end) {

    class Builder<T : Interpolatable<T>> : SignalPath.Builder<T>() {

        override fun build(): SignalPath<T> {
            return SignalInterpolatable(start, end)
        }

    }

    override fun apply(t: Double): T {
        return start.interpolate(end, t)
    }

}

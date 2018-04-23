package de.fluxparticle.animation.signal

class SignalJump<T : Any> private constructor(start: T, end: T, private val jumpTime: Double) : SignalPath<T>(start, end) {

    class Builder<T : Any> @JvmOverloads constructor(private val jumpTime: Double = 0.5) : SignalPath.Builder<T>() {

        override fun build(): SignalPath<T> {
            return SignalJump(start, end, jumpTime)
        }

    }

    override fun apply(t: Double): T {
        return if (t < jumpTime) start else end
    }

}

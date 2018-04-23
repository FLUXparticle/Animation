package de.fluxparticle.animation.signal

class SignalTransform<T>(private val timeStart: Double, private val timeDuration: Double, private val path: SignalPath<T>) : SignalFunction<T>() {

    override fun apply(t: Double): T {
        return path.apply((t - timeStart) / timeDuration)
    }

}

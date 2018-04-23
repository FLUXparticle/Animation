package de.fluxparticle.animation.signal

class SignalConstant<T>(private val value: T) : SignalFunction<T>() {

    override fun apply(t: Double): T {
        return value
    }

}

package de.fluxparticle.animation.signal

abstract class SignalFunction<T> {

    abstract fun apply(t: Double): T

}

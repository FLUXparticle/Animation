package de.fluxparticle.animation.signal

import java.util.function.Function

abstract class SignalPath<T>(protected val start: T, protected val end: T) : Function<Double, T> {

    abstract class Builder<T : Any> {

        protected lateinit var start: T

        protected lateinit var end: T

        fun start(start: T): Builder<T> {
            this.start = start
            return this
        }

        fun end(end: T): Builder<T> {
            this.end = end
            return this
        }

        abstract fun build(): SignalPath<T>

    }

}

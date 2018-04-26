package de.fluxparticle.animation.signal

abstract class SignalPath<T>(protected val start: T, protected val end: T) {

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

    abstract fun apply(t: Double): T

}

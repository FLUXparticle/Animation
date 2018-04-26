package de.fluxparticle.animation.value

/**
 * Created by sreinck on 24.04.18.
 */
sealed class Value<T : Any>(private var v: T) {

    open var value: T
        get() = v
        protected set(newValue) {
            v = newValue
            observers.forEach {
                it(newValue)
            }
        }

    private val observers: MutableList<(T) -> Unit> = mutableListOf()

    fun addObserver(observer: (T) -> Unit) = observers.add(observer)

    fun <R : Any> map(mapper: (T) -> R): Value<R> = MappedValue(this, mapper)

    fun <R : Any, S : Any> combine(other: Value<S>, combiner: (T, S) -> R): Value<R> = CombinedValue(this, other, combiner)

}

class SimpleValue<T : Any>(initValue: T): Value<T>(initValue) {

    override var value: T
        get() = super.value
        public set(newValue) {
            super.value = newValue
        }

}

private class MappedValue<T : Any, R : Any>(private val observable: Value<T>, private val mapper: (T) -> R): Value<R>(mapper(observable.value)) {

    init {
        observable.addObserver { newValue -> value = mapper(newValue) }
    }

}

private class CombinedValue<T : Any, S : Any, R : Any>(private val observable1: Value<T>, private val observable2: Value<S>, private val combiner: (T, S) -> R): Value<R>(combiner(observable1.value, observable2.value)) {

    init {
        observable1.addObserver { _ -> update() }
        observable2.addObserver { _ -> update() }
    }

    private fun update() {
        value = combiner(observable1.value, observable2.value)
    }

}

interface Observer<T> {

    fun notify(newValue: T)

}

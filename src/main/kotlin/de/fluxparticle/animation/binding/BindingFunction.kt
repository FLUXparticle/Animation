package de.fluxparticle.animation.binding

import javafx.beans.binding.ObjectBinding
import javafx.beans.value.ObservableValue

class BindingFunction<T, R>(private val value: ObservableValue<T>, private val func: (T) -> R) : ObjectBinding<R>() {

    init {

        bind(value)
    }

    override fun computeValue(): R {
        return func(value.value)
    }

}

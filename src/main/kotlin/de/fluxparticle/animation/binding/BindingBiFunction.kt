package de.fluxparticle.animation.binding

import javafx.beans.binding.ObjectBinding
import javafx.beans.value.ObservableValue

class BindingBiFunction<T, U, R>(private val valueT: ObservableValue<T>, private val valueU: ObservableValue<U>, private val func: (T, U) -> R) : ObjectBinding<R>() {

    init {

        bind(valueT, valueU)
    }

    override fun computeValue(): R {
        return func(valueT.value, valueU.value)
    }

}

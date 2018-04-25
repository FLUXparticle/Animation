package de.fluxparticle.animation.binding

/*
class BindingObservableFunction<T, R : ObservableValue<V>, V>(observable: ReadOnlyObjectProperty<T>, func: (T) -> R, name: String) : ReadOnlyObjectProperty<V>() {

    private val binding: ObservableValue<R>

    private val value: ObjectProperty<V>

    init {
        this.binding = BindingFunction(observable, func)

        value = SimpleObjectProperty(observable, observable.name + '.' + name)
        value.bind(binding.value)
        binding.addListener { o, oldBinding, newBinding -> value.bind(newBinding) }
    }

    override fun addListener(listener: InvalidationListener) {
        value.addListener(listener)
    }

    override fun removeListener(listener: InvalidationListener) {
        value.removeListener(listener)
    }

    override fun addListener(listener: ChangeListener<in V>) {
        value.addListener(listener)
    }

    override fun removeListener(listener: ChangeListener<in V>) {
        value.removeListener(listener)
    }

    override fun get(): V {
        return value.get()
    }

    override fun getBean(): Any {
        return value.bean
    }

    override fun getName(): String {
        return value.name
    }

}
*/

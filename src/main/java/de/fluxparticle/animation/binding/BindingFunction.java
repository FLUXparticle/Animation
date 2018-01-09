package de.fluxparticle.animation.binding;

import javafx.beans.binding.ObjectBinding;
import javafx.beans.value.ObservableValue;

import java.util.function.Function;

public class BindingFunction<T, R> extends ObjectBinding<R> {

    private final ObservableValue<T> value;

    private final Function<T, R> func;

    public BindingFunction(ObservableValue<T> value, Function<T, R> func) {
        this.value = value;
        this.func = func;

        bind(value);
    }

    @Override
    protected R computeValue() {
        return func.apply(value.getValue());
    }

}

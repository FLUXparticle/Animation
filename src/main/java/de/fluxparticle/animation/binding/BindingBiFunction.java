package de.fluxparticle.animation.binding;

import javafx.beans.binding.ObjectBinding;
import javafx.beans.value.ObservableValue;

import java.util.function.BiFunction;

public class BindingBiFunction<T, U, R> extends ObjectBinding<R> {

    private final ObservableValue<T> valueT;

    private final ObservableValue<U> valueU;

    private final BiFunction<T, U, R> func;

    public BindingBiFunction(ObservableValue<T> valueT, ObservableValue<U> valueU, BiFunction<T, U, R> func) {
        this.valueT = valueT;
        this.valueU = valueU;
        this.func = func;

        bind(valueT, valueU);
    }

    @Override
    protected R computeValue() {
        return func.apply(valueT.getValue(), valueU.getValue());
    }

}

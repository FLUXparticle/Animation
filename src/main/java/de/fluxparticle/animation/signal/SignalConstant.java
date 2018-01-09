package de.fluxparticle.animation.signal;

public class SignalConstant<T> extends SignalFunction<T> {

    private final T value;

    public SignalConstant(T value) {
        this.value = value;
    }

    @Override
    public T apply(Double t) {
        return value;
    }

}

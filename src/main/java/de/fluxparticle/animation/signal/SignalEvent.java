package de.fluxparticle.animation.signal;

public class SignalEvent<T> {

    private final Signal<T> signal;

    private final SignalPath.Builder<T> pathBuilder;

    private final T value;

    public SignalEvent(Signal<T> signal, T value, SignalPath.Builder<T> pathBuilder) {
        this.signal = signal;
        this.value = value;
		this.pathBuilder = pathBuilder;
	}

    public Signal<T> getSignal() {
        return signal;
    }

    public SignalPath.Builder<T> getPathBuilder() {
        return pathBuilder;
    }

    public T getValue() {
        return value;
    }

}

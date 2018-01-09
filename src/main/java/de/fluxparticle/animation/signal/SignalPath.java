package de.fluxparticle.animation.signal;

import java.util.function.Function;

public abstract class SignalPath<T> implements Function<Double, T> {

    public static abstract class Builder<T> {

        protected T start;

        protected T end;

        public Builder<T> start(T start) {
            this.start = start;
            return this;
        }

        public Builder<T> end(T end) {
            this.end = end;
            return this;
        }

        public abstract SignalPath<T> build();

    }

    protected final T start;

    protected final T end;

	public SignalPath(T start, T end) {
		this.start = start;
		this.end = end;
	}

}

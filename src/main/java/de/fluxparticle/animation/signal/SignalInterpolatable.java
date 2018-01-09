package de.fluxparticle.animation.signal;

import javafx.animation.Interpolatable;

public class SignalInterpolatable<T extends Interpolatable<T>> extends SignalPath<T> {

    public static class Builder<T extends Interpolatable<T>> extends SignalPath.Builder<T> {

        @Override
        public SignalPath<T> build() {
            return new SignalInterpolatable<>(start, end);
        }

    }

	public SignalInterpolatable(T start, T end) {
		super(start, end);
	}

	@Override
	public T apply(Double t) {
		return start.interpolate(end, t);
	}

}

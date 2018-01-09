package de.fluxparticle.animation.signal;

public class SignalJump<T> extends SignalPath<T> {

    public static class Builder<T> extends SignalPath.Builder<T> {

        private final double jumpTime;

        public Builder() {
            this(0.5);
        }

        public Builder(double jumpTime) {
            this.jumpTime = jumpTime;
        }

        @Override
        public SignalPath<T> build() {
            return new SignalJump<>(start, end, jumpTime);
        }

    }

    private final double jumpTime;

    private SignalJump(T start, T end, double jumpTime) {
		super(start, end);
        this.jumpTime = jumpTime;
    }

	@Override
	public T apply(Double t) {
		return t < jumpTime ? start : end;
	}

}

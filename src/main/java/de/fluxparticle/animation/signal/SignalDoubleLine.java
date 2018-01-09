package de.fluxparticle.animation.signal;

public class SignalDoubleLine extends SignalPath<Double> {

    public static class Builder extends SignalPath.Builder<Double> {

        @Override
        public SignalPath<Double> build() {
            return new SignalDoubleLine(start, end);
        }

    }

	public SignalDoubleLine(Double start, Double end) {
		super(start, end);
	}

	@Override
	public Double apply(Double t) {
		return start * (1.0 - t) + end * t;
	}

}

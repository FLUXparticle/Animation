package de.fluxparticle.animation.signal;

public class SignalTransform<T> extends SignalFunction<T> {

	private final double timeStart;

	private final double timeDuration;

	private final SignalPath<T> path;

	public SignalTransform(double timeStart, double timeDuration, SignalPath<T> path) {
		this.timeStart = timeStart;
		this.timeDuration = timeDuration;
		this.path = path;
	}

	@Override
	public T apply(Double t) {
		return path.apply((t - timeStart) / timeDuration);
	}
}

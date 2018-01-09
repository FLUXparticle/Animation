package de.fluxparticle.animation.signal;

import java.util.function.Function;

public class SignalFollow<R> extends SignalFunction<R> {

	private final Signal<R> signal;

	private final Function<R, R> func;

	public SignalFollow(Signal<R> signal, Function<R, R> func) {
		this.signal = signal;
		this.func = func;
	}

	@Override
	public R apply(Double t) {
		return func.compose(signal).apply(t);
	}

}

package de.fluxparticle.animation;

import de.fluxparticle.animation.signal.SignalEvent;
import javafx.util.Duration;

import java.util.Arrays;
import java.util.Collection;

public class Timeframe {

	private final Duration duration;

	private final Collection<SignalEvent<?>> signalEvents;

	public Timeframe(Duration duration, SignalEvent<?>... signalEvents) {
		this.duration = duration;
		this.signalEvents = Arrays.asList(signalEvents);
	}

	public Duration getDuration() {
		return duration;
	}

	public Collection<SignalEvent<?>> getSignalEvents() {
		return signalEvents;
	}

}

package de.fluxparticle.animation;

import de.fluxparticle.animation.signal.Signal;
import de.fluxparticle.animation.signal.SignalEvent;
import com.sun.javafx.animation.TickCalculation;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.beans.property.ReadOnlyDoubleProperty;
import javafx.beans.property.ReadOnlyDoubleWrapper;
import javafx.util.Duration;

import java.util.*;
import java.util.function.Predicate;

public class Clip {

    private final ReadOnlyDoubleWrapper time = new ReadOnlyDoubleWrapper(this, "time");
    public ReadOnlyDoubleProperty timeProperty() {
        return time.getReadOnlyProperty();
    }

    private long clipTicks = 0;

    private int curSequence;

    private long ticksBegin;

    private Timeline timeline = null;

	private final List<Long> keyframes = new ArrayList<>();

    private final Map<String, Signal<?>> signals = new HashMap<>();

    public Clip() {
        keyframes.add(0L);
    }

    public synchronized void invoke(Runnable runnable, boolean resume) {
        ticksBegin = clipTicks;
        runnable.run();
        if (resume) {
            resume();
        }
		keyframes.add(clipTicks);
    }

	public void addTimeframe(Timeframe timeframe) {
		if (!Thread.holdsLock(this)) {
			throw new IllegalMonitorStateException();
		}

		Duration duration = timeframe.getDuration();
		for (SignalEvent signalEvent : timeframe.getSignalEvents()) {
			addSignalEvents(duration, signalEvent);
		}
	}

    private <T> void addSignalEvents(Duration duration, SignalEvent<T> signalEvent) {
        long ticksStart = ticksBegin;
        long ticksDuration = TickCalculation.fromDuration(duration);
        long ticksEnd = TickCalculation.add(ticksStart, ticksDuration);

        Signal<T> signal = signalEvent.getSignal();
        String name = signal.getName();

        signals.put(name, signal);

        signal.changeValue(signalEvent.getPathBuilder(), signalEvent.getValue(), ticksStart, ticksDuration);

        if (ticksEnd > clipTicks) {
            clipTicks = ticksEnd;
        }
    }

    public void play() {
        play(0.0, TickCalculation.toDuration(clipTicks).toSeconds());
    }

    public void resume() {
        double timeStart = time.get();
        double timeEnd = TickCalculation.toDuration(clipTicks).toSeconds();
        play(timeStart, timeEnd);
    }

    public boolean skipSequence() {
        return goToSequence(curSequence + 1);
    }

    public void gotoPrevSequence() {
        goToSequence(curSequence - 1);
    }

    public boolean goToSequence(int nr) {
        if (nr >= keyframes.size()) {
            return false;
        }

        long ticks = keyframes.get(nr);
        double t = TickCalculation.toDuration(ticks).toSeconds();
        time.setValue(t);
        curSequence = nr;

        return true;
    }

	public Timeline playNextSequence() {
        if (curSequence + 1 >= keyframes.size()) {
            return null;
        }

        long start = keyframes.get(curSequence);
        long end = keyframes.get(curSequence + 1);
        Timeline timeline = playKeyframes(start, end);
        curSequence++;

        return timeline;
    }

    public <T> Timeline play(Signal<T> signal, Predicate<T> predicate) {
        int startSequence = curSequence;
        fastFwd(signal, predicate);
        int endSequence = curSequence;

        long start = keyframes.get(startSequence);
        long end = keyframes.get(endSequence);
        Timeline timeline = playKeyframes(start, end);

        curSequence = endSequence;

        return timeline;
    }

    private Timeline playKeyframes(long start, long end) {
        double timeStart = TickCalculation.toDuration(start).toSeconds();
        double timeEnd = TickCalculation.toDuration(end).toSeconds();
        return play(timeStart, timeEnd);
    }

    private Timeline play(double timeStart, double timeEnd) {
        Duration durationLength = Duration.seconds(timeEnd - timeStart);

        KeyFrame start = new KeyFrame(Duration.ZERO, new KeyValue(time, timeStart));
        KeyFrame end = new KeyFrame(durationLength, new KeyValue(time, timeEnd));

        Timeline timeline = new Timeline(start, end);
        playTimeline(timeline);
        return timeline;
    }

    private void playTimeline(Timeline newTimeline) {
        if (timeline != null) {
            timeline.stop();
        }
        timeline = newTimeline;
        timeline.play();
    }

	public List<Long> getKeyframes() {
		return Collections.unmodifiableList(keyframes);
	}

	public int getCntSequences() {
        return keyframes.size();
    }

	private static final String APPLE_PRO_RES_422 = "Apple ProRes 422";

    public <T> void fastFwd(Signal<T> signal, Predicate<T> predicate) {
        if (predicate.test(signal.getValue())) {
            while (skipSequence() && predicate.test(signal.getValue())) {
                // empty
            }
        }

        while (skipSequence() && !predicate.test(signal.getValue())) {
            // empty
        }
    }

}

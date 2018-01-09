package de.fluxparticle.animation.signal;

import de.fluxparticle.animation.binding.BindingFunction;
import com.sun.javafx.animation.TickCalculation;
import javafx.beans.InvalidationListener;
import javafx.beans.binding.DoubleExpression;
import javafx.beans.binding.ObjectBinding;
import javafx.beans.property.ReadOnlyProperty;
import javafx.beans.value.ChangeListener;
import javafx.util.Duration;

import java.util.Map.Entry;
import java.util.TreeMap;
import java.util.function.Function;

public final class Signal<R> implements ReadOnlyProperty<R>, Function<Number, R> {

	private final Object bean;

    private final String name;

    private final TreeMap<Long, SignalFunction<R>> keyPoints;

    private final ObjectBinding<R> value;

	private final Class<?> type;

    public Signal(DoubleExpression time, Object bean, String name, R initValue) {
		this.bean = bean;
		this.name = name;

        keyPoints = new TreeMap<>();
        keyPoints.put(0L, new SignalConstant<>(initValue));

		value = new BindingFunction<>(time, this);

        type = initValue.getClass();
    }

	public R lastValue() {
		Entry<Long, SignalFunction<R>> lastEntry = keyPoints.lastEntry();
		double t = TickCalculation.toDuration(lastEntry.getKey()).toSeconds();
		return lastEntry.getValue().apply(t);
	}

	@Override
	public R apply(Number t) {
		long ticks = TickCalculation.fromDuration(Duration.seconds(t.doubleValue()));
		Entry<Long, SignalFunction<R>> floor = keyPoints.floorEntry(ticks);

		return floor.getValue().apply((Double) t);
	}

	public void changeValue(SignalPath.Builder<R> pathBuilder, R newValue, long ticksStart, long ticksDuration) {
        Entry<Long, SignalFunction<R>> lastEntry = keyPoints.lastEntry();

        long ticksLast = lastEntry.getKey();
		R oldValue = lastEntry.getValue().apply(0.0);

        if (ticksStart < ticksLast) {
            throw new IllegalArgumentException(ticksStart + " < " + ticksLast);
        }

        if (newValue != oldValue) {
            double timeStart = TickCalculation.toDuration(ticksStart).toSeconds();
            double timeDuration = TickCalculation.toDuration(ticksDuration).toSeconds();

            SignalPath<R> path = pathBuilder.start(oldValue).end(newValue).build();

            keyPoints.put(ticksStart, new SignalTransform<>(timeStart, timeDuration, path));
        }

        long ticksEnd = ticksStart + ticksDuration;
        keyPoints.put(ticksEnd, new SignalConstant<>(newValue));
    }

    @SuppressWarnings("unchecked")
    public <T> Signal<T> asTypedSignal(Class<? extends T> newType) {
        if (newType.isAssignableFrom(type)) {
            return (Signal<T>) this;
        } else {
            throw new ClassCastException("Signal of type " + type + " cannot be casted to " + newType);
        }
    }

	@Override
	public R getValue() {
		return value.getValue();
	}

	@Override
	public void addListener(InvalidationListener listener) {
		value.addListener(listener);
	}

	@Override
	public void removeListener(InvalidationListener listener) {
		value.removeListener(listener);
	}

	@Override
	public void addListener(ChangeListener<? super R> listener) {
		value.addListener(listener);
	}

	@Override
	public void removeListener(ChangeListener<? super R> listener) {
		value.removeListener(listener);
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public Object getBean() {
		return bean;
	}
	
}

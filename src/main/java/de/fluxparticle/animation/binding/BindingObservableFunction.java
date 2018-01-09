package de.fluxparticle.animation.binding;

import javafx.beans.InvalidationListener;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.ReadOnlyObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;

import java.util.function.Function;

public class BindingObservableFunction<T, R extends ObservableValue<V>, V> extends ReadOnlyObjectProperty<V> {

	private final ObservableValue<R> binding;

	private final ObjectProperty<V> value;

	public BindingObservableFunction(ReadOnlyObjectProperty<T> observable, Function<T, R> func, String name) {
		this.binding = new BindingFunction<>(observable, func);

		value = new SimpleObjectProperty<>(observable, observable.getName() + '.' + name);
		value.bind(binding.getValue());
		binding.addListener((o, oldBinding, newBinding) -> {
			value.bind(newBinding);
		});
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
	public void addListener(ChangeListener<? super V> listener) {
		value.addListener(listener);
	}

	@Override
	public void removeListener(ChangeListener<? super V> listener) {
		value.removeListener(listener);
	}

	@Override
	public V get() {
		return value.get();
	}

	@Override
	public Object getBean() {
		return value.getBean();
	}

	@Override
	public String getName() {
		return value.getName();
	}

}

package de.fluxparticle.animation.binding;

import javafx.beans.binding.DoubleBinding;
import javafx.beans.value.ObservableValue;

import java.util.function.DoubleBinaryOperator;

public class BindingDoubleBinaryOperator extends DoubleBinding {

	private final ObservableValue<? extends Number> t;

	private final ObservableValue<? extends Number> u;

	private final DoubleBinaryOperator op;

	public BindingDoubleBinaryOperator(ObservableValue<? extends Number> t, ObservableValue<? extends Number> u, DoubleBinaryOperator op) {
		this.t = t;
		this.u = u;
		this.op = op;

		bind(t, u);
	}

	@Override
	protected double computeValue() {
		return op.applyAsDouble(t.getValue().doubleValue(), u.getValue().doubleValue());
	}

}

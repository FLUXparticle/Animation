package de.fluxparticle.animation.object;

import de.fluxparticle.animation.signal.Signal;
import javafx.beans.binding.DoubleExpression;

public abstract class ElementNode {

	protected final DoubleExpression time;

	private final Signal<Double> opacity;
	public Signal<Double> opacitySignal() {
		return opacity;
	}

	public abstract <R> R accept(ElementNodeVisitor<R> visitor);

	public ElementNode(DoubleExpression time) {
		this.time = time;
		this.opacity = new Signal<>(time, this, "opacity", 0.0);
	}

}

package de.fluxparticle.animation.object;

import de.fluxparticle.animation.signal.Signal;
import javafx.beans.binding.DoubleExpression;
import javafx.geometry.BoundingBox;
import javafx.geometry.Bounds;
import javafx.scene.paint.Color;

public class ElementRectangle extends ElementNode {

	private final Signal<Double> x;
	public Signal<Double> xSignal() {
		return x;
	}

	private final Signal<Double> y;
	public Signal<Double> ySignal() {
		return y;
	}

	private final Signal<Double> width;
	public Signal<Double> widthSignal() {
		return width;
	}

	private final Signal<Double> height;
	public Signal<Double> heightSignal() {
		return height;
	}

	private final Signal<Color> fill;
	public Signal<Color> fillSignal() {
		return fill;
	}

	public ElementRectangle(DoubleExpression time, Bounds bs, Color fill) {
		super(time);
		this.x = new Signal<>(time, this, "x", bs.getMinX());
		this.y = new Signal<>(time, this, "y", bs.getMinY());
		this.width = new Signal<>(time, this, "w", bs.getWidth());
		this.height = new Signal<>(time, this, "h", bs.getHeight());
		this.fill = new Signal<>(time, this, "fill", fill);
	}

	@Override
	public <R> R accept(ElementNodeVisitor<R> visitor) {
		return visitor.visitRectangle(this);
	}

	public Bounds getBoundsInParent() {
		return new BoundingBox(x.lastValue(), y.lastValue(), width.lastValue(), height.lastValue());
	}

}

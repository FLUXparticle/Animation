package de.fluxparticle.animation.point;

import javafx.animation.Interpolatable;
import javafx.beans.binding.DoubleExpression;
import javafx.beans.property.SimpleDoubleProperty;

public class DynamicPoint2D implements Interpolatable<DynamicPoint2D> {

	private final DoubleExpression x;

	private final DoubleExpression y;

	public DynamicPoint2D(DoubleExpression x, DoubleExpression y) {
		this.x = x;
		this.y = y;
	}

	public DynamicPoint2D(double x, double y) {
		this.x = new SimpleDoubleProperty(x);
		this.y = new SimpleDoubleProperty(y);
	}

	@Override
	public DynamicPoint2D interpolate(DynamicPoint2D endValue, double t) {
        DoubleExpression x = this.xProperty().multiply(1.0 - t).add(endValue.xProperty().multiply(t));
        DoubleExpression y = this.yProperty().multiply(1.0 - t).add(endValue.yProperty().multiply(t));
		return new DynamicPoint2D(x, y);
	}

	public double getX() {
		return x.get();
	}

	public DoubleExpression xProperty() {
		return x;
	}

	public double getY() {
		return y.get();
	}

	public DoubleExpression yProperty() {
		return y;
	}
}

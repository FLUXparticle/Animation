package de.fluxparticle.animation.object;

import de.fluxparticle.animation.point.DynamicPoint2D;
import javafx.beans.binding.DoubleExpression;

public class ElementPath extends ElementNode {

	private final DynamicPoint2D origin;

	private final DynamicPoint2D dest;

	public ElementPath(DoubleExpression time, DynamicPoint2D origin, DynamicPoint2D dest) {
		super(time);
		this.origin = origin;
		this.dest = dest;
	}

	public DynamicPoint2D getOrigin() {
		return origin;
	}

	public DynamicPoint2D getDest() {
		return dest;
	}

	@Override
	public <R> R accept(ElementNodeVisitor<R> visitor) {
		return visitor.visitPath(this);
	}

}

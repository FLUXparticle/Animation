package de.fluxparticle.animation.signal;

import de.fluxparticle.animation.binding.BindingBiFunction;
import de.fluxparticle.animation.binding.BindingDoubleBinaryOperator;
import de.fluxparticle.animation.point.DynamicPoint2D;
import javafx.beans.binding.DoubleBinding;
import javafx.beans.binding.DoubleExpression;
import javafx.beans.binding.ObjectBinding;
import javafx.geometry.Point2D;

public class SignalCurve extends SignalPath<DynamicPoint2D> {

    public static class Builder extends SignalPath.Builder<DynamicPoint2D> {

        @Override
        public SignalPath<DynamicPoint2D> build() {
			ObjectBinding<Point2D> pointStart = new BindingBiFunction<>(start.xProperty(), start.yProperty(), (x, y) -> new Point2D(x.doubleValue(), y.doubleValue()));
			ObjectBinding<Point2D> pointEnd = new BindingBiFunction<>(end.xProperty(), end.yProperty(), (x, y) -> new Point2D(x.doubleValue(), y.doubleValue()));
			ObjectBinding<Double> valueV = new BindingBiFunction<>(pointStart, pointEnd, (start, end) -> 3.0/2.0 * start.distance(end));

			DoubleBinding control1X = new BindingDoubleBinaryOperator(start.xProperty(), valueV, (x, v) -> x + v / 3.0);
			DynamicPoint2D control1 = new DynamicPoint2D(control1X, start.yProperty());

			DoubleBinding control2X = new BindingDoubleBinaryOperator(end.xProperty(), valueV, (x, v) -> x + v / 3.0);
            DynamicPoint2D control2 = new DynamicPoint2D(control2X, end.yProperty());

            return new SignalCurve(start, end, control1, control2);
        }

    }

    private final DynamicPoint2D control1;

    private final DynamicPoint2D control2;

    public SignalCurve(DynamicPoint2D start, DynamicPoint2D end, DynamicPoint2D control1, DynamicPoint2D control2) {
        super(start, end);
        this.control1 = control1;
        this.control2 = control2;
    }

    @Override
    public DynamicPoint2D apply(Double t) {
		DoubleBinding x = new BezierBinding(start.xProperty(), control1.xProperty(), control2.xProperty(), end.xProperty(), t);
		DoubleBinding y = new BezierBinding(start.yProperty(), control1.yProperty(), control2.yProperty(), end.yProperty(), t);

        return new DynamicPoint2D(x, y);
    }

	private static class BezierBinding extends DoubleBinding {

		private final DoubleExpression g0;
		private final DoubleExpression g1;
		private final DoubleExpression g2;
		private final DoubleExpression g3;
		private final double t;

		public BezierBinding(DoubleExpression g0, DoubleExpression g1, DoubleExpression g2, DoubleExpression g3, double t) {
			this.g0 = g0;
			this.g1 = g1;
			this.g2 = g2;
			this.g3 = g3;
			this.t = t;

			bind(g0, g1, g2, g3);
		}

		@Override
		protected double computeValue() {
			return bezier(g0.get(), g1.get(), g2.get(), g3.get(), t);
		}

	}

    private static double bezier(double g0, double g1, double g2, double g3, double t) {
        double a3 = -g0 + 3 * g1 - 3 * g2 + g3;
        double a2 = 3 * g0 - 6 * g1  + 3 * g2;
        double a1 = -3 * g0 + 3 * g1;
        double a0 = g0;

        return a3 * t * t * t + a2 * t * t + a1 * t + a0;
    }

}

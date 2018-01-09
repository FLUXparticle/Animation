package de.fluxparticle.animation.path;

import de.fluxparticle.animation.point.DynamicPoint2D;
import javafx.beans.binding.DoubleBinding;
import javafx.beans.binding.DoubleExpression;
import javafx.collections.ObservableList;
import javafx.geometry.Point2D;
import javafx.scene.shape.*;

public class PathMaker {

	public static final int ARROW_SIZE = 5;

	public static Path mkCurveEastWest(Point2D origin, Point2D dest) {
		double dx = dest.getX() - origin.getX();
		double dy = dest.getY() - origin.getY();
		double v = 3.0/2.0 * Math.sqrt(dx*dx + dy*dy);

		double controlX1 = origin.getX() + v / 3.0;
		double controlY1 = origin.getY();
		double controlX2 = dest.getX() + v / 3.0;
		double controlY2 = dest.getY();

		return mkCurve(origin, dest, new Point2D(controlX1, controlY1), new Point2D(controlX2, controlY2));
	}

	public static Path mkCurveWestEast(Point2D origin, Point2D dest) {
		double dx = dest.getX() - origin.getX();
		double dy = dest.getY() - origin.getY();
		double v = 3.0/2.0 * Math.sqrt(dx*dx + dy*dy);

		double controlX1 = origin.getX() - v / 3.0;
		double controlY1 = origin.getY();
		double controlX2 = dest.getX() - v / 3.0;
		double controlY2 = dest.getY();

		return mkCurve(origin, dest, new Point2D(controlX1, controlY1), new Point2D(controlX2, controlY2));
	}

	public static Path mkDynamicCurveEastEast(final DynamicPoint2D origin, final DynamicPoint2D dest) {
		DoubleBinding v = new DoubleBinding() {
			{
				super.bind(origin.xProperty(), origin.yProperty(), dest.xProperty(), dest.yProperty());
			}

			@Override
			protected double computeValue() {
				double dx = dest.getX() - origin.getX();
				double dy = dest.getY() - origin.getY();
				return Math.sqrt(4*dx*dx + 3*dy*dy) - dx;
			}
		};

		DoubleBinding v3 = v.divide(3.0);

		DoubleExpression controlX1 = origin.xProperty().add(v3);
		DoubleExpression controlY1 = origin.yProperty();
		DoubleExpression controlX2 = dest.xProperty().subtract(v3);
		DoubleExpression controlY2 = dest.yProperty();

		return mkDynamicCurve(origin, dest, new DynamicPoint2D(controlX1, controlY1), new DynamicPoint2D(controlX2, controlY2));
	}

	private static Path mkCurve(Point2D origin, Point2D dest, Point2D control1, Point2D control2) {
		Path path = new Path();

		MoveTo moveTo = new MoveTo(origin.getX(), origin.getY());
		path.getElements().add(moveTo);

		CubicCurveTo curve = new CubicCurveTo();

		curve.setControlX1(control1.getX());
		curve.setControlY1(control1.getY());

		curve.setControlX2(control2.getX());
		curve.setControlY2(control2.getY());

		curve.setX(dest.getX());
		curve.setY(dest.getY());

		path.getElements().add(curve);

		return path;
	}

	private static Path mkDynamicCurve(DynamicPoint2D origin, DynamicPoint2D dest, DynamicPoint2D control1, DynamicPoint2D control2) {
		Path path = new Path();

		MoveTo moveTo = new MoveTo();
		moveTo.xProperty().bind(origin.xProperty());
		moveTo.yProperty().bind(origin.yProperty());
		path.getElements().add(moveTo);

		CubicCurveTo curve = new CubicCurveTo();

		curve.controlX1Property().bind(control1.xProperty());
		curve.controlY1Property().bind(control1.yProperty());

		curve.controlX2Property().bind(control2.xProperty());
		curve.controlY2Property().bind(control2.yProperty());

		curve.xProperty().bind(dest.xProperty());
		curve.yProperty().bind(dest.yProperty());

		path.getElements().add(curve);

		return path;
	}

	public static void addArrow(Path path, Point2D end) {
		ObservableList<PathElement> elements = path.getElements();

		elements.add(new MoveTo(end.getX(), end.getY() - ARROW_SIZE));
		elements.add(new LineTo(end.getX() + ARROW_SIZE, end.getY()));
		elements.add(new LineTo(end.getX(), end.getY() + ARROW_SIZE));
	}

	public static void addDynamicArrow(Path path, DynamicPoint2D end) {
		MoveTo e1 = new MoveTo();
		e1.xProperty().bind(end.xProperty());
		e1.yProperty().bind(end.yProperty().subtract(ARROW_SIZE));

		LineTo e2 = new LineTo();
		e2.xProperty().bind(end.xProperty().add(ARROW_SIZE));
		e2.yProperty().bind(end.yProperty());

		LineTo e3 = new LineTo();
		e3.xProperty().bind(end.xProperty());
		e3.yProperty().bind(end.yProperty().add(ARROW_SIZE));

		path.getElements().addAll(e1, e2, e3);
	}

}

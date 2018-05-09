package de.fluxparticle.animation.path

object PathMaker {

/*
    fun mkCurveEastWest(origin: Point2D, dest: Point2D): Path {
        val dx = dest.getX() - origin.getX()
        val dy = dest.getY() - origin.getY()
        val v = 1.5 * Math.sqrt(dx * dx + dy * dy)
        val controlX1 = origin.getX() + v / 3.0
        val controlY1 = origin.getY()
        val controlX2 = dest.getX() + v / 3.0
        val controlY2 = dest.getY()
        return mkCurve(origin, dest, Point2D(controlX1, controlY1), Point2D(controlX2, controlY2))
    }

    fun mkCurveWestEast(origin: Point2D, dest: Point2D): Path {
        val dx = dest.getX() - origin.getX()
        val dy = dest.getY() - origin.getY()
        val v = 1.5 * Math.sqrt(dx * dx + dy * dy)
        val controlX1 = origin.getX() - v / 3.0
        val controlY1 = origin.getY()
        val controlX2 = dest.getX() - v / 3.0
        val controlY2 = dest.getY()
        return mkCurve(origin, dest, Point2D(controlX1, controlY1), Point2D(controlX2, controlY2))
    }

    fun mkDynamicCurveEastEast(origin: DynamicPoint2D, dest: DynamicPoint2D): Path {
        val v = object : DoubleBinding() {
            init {
                super.bind(arrayOf<Observable>(origin.xProperty(), origin.yProperty(), dest.xProperty(), dest.yProperty()))
            }

            protected override fun computeValue(): Double {
                val dx = (dest.x - origin.x).toDouble()
                val dy = (dest.y - origin.y).toDouble()
                return Math.sqrt(4.0 * dx * dx + 3.0 * dy * dy) - dx
            }
        }
        val v3 = v.divide(3.0)
        val controlX1 = origin.xProperty().add(v3)
        val controlY1 = origin.yProperty()
        val controlX2 = dest.xProperty().subtract(v3)
        val controlY2 = dest.yProperty()
        return mkDynamicCurve(origin, dest, DynamicPoint2D(controlX1, controlY1), DynamicPoint2D(controlX2, controlY2))
    }

    private fun mkCurve(origin: Point2D, dest: Point2D, control1: Point2D, control2: Point2D): Path {
        val path = Path()
        val moveTo = MoveTo(origin.getX(), origin.getY())
        path.getElements().add(moveTo)
        val curve = CubicCurveTo()
        curve.setControlX1(control1.getX())
        curve.setControlY1(control1.getY())
        curve.setControlX2(control2.getX())
        curve.setControlY2(control2.getY())
        curve.setX(dest.getX())
        curve.setY(dest.getY())
        path.getElements().add(curve)
        return path
    }

    private fun mkDynamicCurve(origin: DynamicPoint2D, dest: DynamicPoint2D, control1: DynamicPoint2D, control2: DynamicPoint2D): Path {
        val path = Path()
        val moveTo = MoveTo()
        moveTo.xProperty().bind(origin.xProperty())
        moveTo.yProperty().bind(origin.yProperty())
        path.getElements().add(moveTo)
        val curve = CubicCurveTo()
        curve.controlX1Property().bind(control1.xProperty())
        curve.controlY1Property().bind(control1.yProperty())
        curve.controlX2Property().bind(control2.xProperty())
        curve.controlY2Property().bind(control2.yProperty())
        curve.xProperty().bind(dest.xProperty())
        curve.yProperty().bind(dest.yProperty())
        path.getElements().add(curve)
        return path
    }

    fun addArrow(path: Path, end: Point2D) {
        val elements = path.getElements()
        elements.add(MoveTo(end.getX(), end.getY() - 5.0))
        elements.add(LineTo(end.getX() + 5.0, end.getY()))
        elements.add(LineTo(end.getX(), end.getY() + 5.0))
    }

    fun addDynamicArrow(path: Path, end: DynamicPoint2D) {
        val e1 = MoveTo()
        e1.xProperty().bind(end.xProperty())
        e1.yProperty().bind(end.yProperty().subtract(5))
        val e2 = LineTo()
        e2.xProperty().bind(end.xProperty().add(5))
        e2.yProperty().bind(end.yProperty())
        val e3 = LineTo()
        e3.xProperty().bind(end.xProperty())
        e3.yProperty().bind(end.yProperty().add(5))
        path.getElements().addAll(arrayOf<PathElement>(e1, e2, e3))
    }
*/

}

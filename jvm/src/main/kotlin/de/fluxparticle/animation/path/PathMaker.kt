package de.fluxparticle.animation.path

import de.fluxparticle.animation.point.DynamicPoint2D
import de.fluxparticle.animation.toDoubleExpression
import de.fluxparticle.animation.toObservableValue
import javafx.geometry.Point2D
import javafx.scene.shape.CubicCurveTo
import javafx.scene.shape.LineTo
import javafx.scene.shape.MoveTo
import javafx.scene.shape.Path

object PathMaker {

    val ARROW_SIZE = 5

    fun mkCurveEastWest(origin: Point2D, dest: Point2D): Path {
        val dx = dest.x - origin.x
        val dy = dest.y - origin.y
        val v = 3.0 / 2.0 * Math.sqrt(dx * dx + dy * dy)

        val controlX1 = origin.x + v / 3.0
        val controlY1 = origin.y
        val controlX2 = dest.x + v / 3.0
        val controlY2 = dest.y

        return mkCurve(origin, dest, Point2D(controlX1, controlY1), Point2D(controlX2, controlY2))
    }

    fun mkCurveWestEast(origin: Point2D, dest: Point2D): Path {
        val dx = dest.x - origin.x
        val dy = dest.y - origin.y
        val v = 3.0 / 2.0 * Math.sqrt(dx * dx + dy * dy)

        val controlX1 = origin.x - v / 3.0
        val controlY1 = origin.y
        val controlX2 = dest.x - v / 3.0
        val controlY2 = dest.y

        return mkCurve(origin, dest, Point2D(controlX1, controlY1), Point2D(controlX2, controlY2))
    }

    fun mkDynamicCurveEastEast(origin: DynamicPoint2D, dest: DynamicPoint2D): Path {
        val vOrigin = origin.x.combine(origin.y, ::Pair)
        val vDest = dest.x.combine(dest.y, ::Pair)
        val vValue = vOrigin.combine(vDest) { pOrigin, pDest ->
            val dx = pDest.first - pOrigin.first
            val dy = pDest.second - pOrigin.second
            Math.sqrt(4.0 * dx * dx + 3.0 * dy * dy) - dx
        }

        val vValue3 = vValue.map { it / 3.0 }

        val controlX1 = origin.x.combine(vValue3) { x, v -> x + v }
        val controlY1 = origin.y
        val controlX2 = dest.x.combine(vValue3) { x, v -> x - v }
        val controlY2 = dest.y

        return mkDynamicCurve(origin, dest, DynamicPoint2D(controlX1, controlY1), DynamicPoint2D(controlX2, controlY2))
    }

    private fun mkCurve(origin: Point2D, dest: Point2D, control1: Point2D, control2: Point2D): Path {
        val path = Path()

        val moveTo = MoveTo(origin.x, origin.y)
        path.elements.add(moveTo)

        val curve = CubicCurveTo()

        curve.controlX1 = control1.x
        curve.controlY1 = control1.y

        curve.controlX2 = control2.x
        curve.controlY2 = control2.y

        curve.x = dest.x
        curve.y = dest.y

        path.elements.add(curve)

        return path
    }

    private fun mkDynamicCurve(origin: DynamicPoint2D, dest: DynamicPoint2D, control1: DynamicPoint2D, control2: DynamicPoint2D) = Path().apply {
        val moveTo = MoveTo()
        moveTo.xProperty().bind(origin.x.toObservableValue())
        moveTo.yProperty().bind(origin.y.toObservableValue())
        elements.add(moveTo)

        val curve = CubicCurveTo()

        curve.controlX1Property().bind(control1.x.toObservableValue())
        curve.controlY1Property().bind(control1.y.toObservableValue())

        curve.controlX2Property().bind(control2.x.toObservableValue())
        curve.controlY2Property().bind(control2.y.toObservableValue())

        curve.xProperty().bind(dest.x.toObservableValue())
        curve.yProperty().bind(dest.y.toObservableValue())

        elements.add(curve)
    }

    fun addArrow(path: Path, end: Point2D) {
        val elements = path.elements

        elements.add(MoveTo(end.x, end.y - ARROW_SIZE))
        elements.add(LineTo(end.x + ARROW_SIZE, end.y))
        elements.add(LineTo(end.x, end.y + ARROW_SIZE))
    }

    fun addDynamicArrow(path: Path, end: DynamicPoint2D) {
        val e1 = MoveTo()
        e1.xProperty().bind(end.x.toDoubleExpression())
        e1.yProperty().bind(end.y.toDoubleExpression().subtract(ARROW_SIZE))

        val e2 = LineTo()
        e2.xProperty().bind(end.x.toDoubleExpression().add(ARROW_SIZE))
        e2.yProperty().bind(end.y.toDoubleExpression())

        val e3 = LineTo()
        e3.xProperty().bind(end.x.toDoubleExpression())
        e3.yProperty().bind(end.y.toDoubleExpression().add(ARROW_SIZE))

        path.elements.addAll(e1, e2, e3)
    }

}

package de.fluxparticle.animation

import de.fluxparticle.animation.elementobject.ElementNodeVisitor
import de.fluxparticle.animation.elementobject.ElementObject
import de.fluxparticle.animation.elementobject.ElementPath
import de.fluxparticle.animation.elementobject.ElementRectangle
import de.fluxparticle.animation.path.ARROW_SIZE
import de.fluxparticle.animation.point.DynamicPoint2D
import de.fluxparticle.animation.signal.Signal
import de.fluxparticle.animation.util.Color
import de.fluxparticle.animation.value.Value
import org.w3c.dom.svg.*
import kotlin.browser.document
import kotlin.math.sqrt

/**
 * Created by sreinck on 27.04.18.
 */

private fun newSVG(qualifiedName: String) = document.createElementNS("http://www.w3.org/2000/svg", qualifiedName)

private fun newSVGSVG() = newSVG("svg") as SVGSVGElement

fun newSVGSVG(width: Float, height: Float): SVGSVGElement = newSVGSVG().apply {
    this.width.baseVal.valueAsString = width.toString()
    this.height.baseVal.valueAsString = height.toString()
}

private fun newSVGRect() = newSVG("rect") as SVGRectElement

private fun newSVGPath() = newSVG("path") as SVGPathElement

private fun newSVGRect(x: Float, y: Float, width: Float, height: Float, fill: Color) = newSVGRect().apply {
    this.x.baseVal.valueAsString = x.toString()
    this.y.baseVal.valueAsString = y.toString()
    this.width.baseVal.valueAsString = width.toString()
    this.height.baseVal.valueAsString = height.toString()
    this.setAttribute("fill", fill.toString())
}

private fun newSVGGroup() = newSVG("g") as SVGGElement

private fun newSVGText() = newSVG("text") as SVGTextElement

class JsNodeCreator : ElementNodeVisitor<SVGElement> {

    override fun visitPath(elementPath: ElementPath) = newSVGPath().apply {
        val path = mkDynamicCurveEastEast(elementPath.origin, elementPath.dest)
        val withArrow = addDynamicArrow(path, elementPath.dest)
        bindAttribute("d", withArrow)
        setAttribute("fill", "transparent")
        setAttribute("stroke", "black")
        setAttribute("stroke-width", "2")
    }

    override fun visitRectangle(elementRectangle: ElementRectangle): SVGElement = newSVGRect().apply {
        bindLength(x, elementRectangle.x)
        bindLength(y, elementRectangle.y)
        bindLength(width, elementRectangle.width)
        bindLength(height, elementRectangle.height)
        bindAttribute("fill", elementRectangle.fill.value)
        bindAttribute("fill-opacity", elementRectangle.opacity.value)
    }

    override fun visitObject(elementObject: ElementObject): SVGElement = newSVGGroup().apply {
        val width = ElementObject.OPERAND_WIDTH.toFloat()
        val height = ElementObject.HEIGHT.toFloat()

        if (elementObject.isUseRect) {
            val rectOuter = newSVGRect(-width / 2, -height / 2, width, height, Color.BLACK)
            val rectInner = newSVGRect(-width / 2 + 1, -height / 2 + 1, width - 2, height - 2, Color.WHITE)
            appendChild(rectOuter)
            appendChild(rectInner)
        }

        run {
            val headLine = elementObject.headLine
            val text = newSVGText()
            text.textContent = headLine
            text.setAttribute("text-anchor", "middle")
            text.setAttribute("alignment-baseline", "middle")
            appendChild(text)
        }

        val translate = elementObject.translate.value.map { "translate(${it.x.value}, ${it.y.value})" }
        bindAttribute("transform", translate)
    }

    private fun mkDynamicCurveEastEast(origin: DynamicPoint2D, dest: DynamicPoint2D): Value<String> {
        val vOrigin = origin.x.combine(origin.y, ::Pair)
        val vDest = dest.x.combine(dest.y, ::Pair)
        val vValue = vOrigin.combine(vDest) { pOrigin, pDest ->
            val dx = pDest.first - pOrigin.first
            val dy = pDest.second - pOrigin.second
            sqrt(4.0 * dx * dx + 3.0 * dy * dy) - dx
        }

        val vValue3 = vValue.map { it / 3.0 }

        val controlX1 = origin.x.combine(vValue3) { x, v -> x + v }
        val controlY1 = origin.y
        val controlX2 = dest.x.combine(vValue3) { x, v -> x - v }
        val controlY2 = dest.y

        return mkDynamicCurve(origin, dest, DynamicPoint2D(controlX1, controlY1), DynamicPoint2D(controlX2, controlY2))
    }

    private fun mkDynamicCurve(origin: DynamicPoint2D, dest: DynamicPoint2D, control1: DynamicPoint2D, control2: DynamicPoint2D): Value<String> {
        val p0 = origin.x.combine(origin.y) { x, y -> "$x $y" }
        val p1 = control1.x.combine(control1.y) { x, y -> "$x $y" }
        val p2 = control2.x.combine(control2.y) { x, y -> "$x $y" }
        val p3 = dest.x.combine(dest.y) { x, y -> "$x $y" }

        val control = p1.combine(p2) { c1, c2-> "$c1, $c2"}
        val curve = control.combine(p3) { c, d -> "$c, $d" }

        return p0.combine(curve) { p, c -> "M $p C $c"}
    }

    private fun addDynamicArrow(path: Value<String>, end: DynamicPoint2D): Value<String> {
        val x1 = end.x
        val y1 = end.y.map { y -> y - ARROW_SIZE }
        val e1 = x1.combine(y1) { x, y -> "M $x $y" }

        val x2 = end.x.map { y -> y + ARROW_SIZE }
        val y2 = end.y
        val e2 = x2.combine(y2) { x, y -> "L $x $y" }

        val x3 = end.x
        val y3 = end.y.map { y -> y + ARROW_SIZE }
        val e3 = x3.combine(y3) { x, y -> "L $x $y" }

        return path.combine(e1.combine(e2) { u, v -> "$u $v" }.combine(e3) { u, v -> "$u $v" }) { u, v -> "$u $v" }
    }

}

private fun <T : Any> SVGElement.bindAttribute(name: String, value: Value<T>) {
    setAttribute(name, value.value.toString())
    value.addObserver { newValue ->
        setAttribute(name, newValue.toString())
    }
}

private fun bindLength(length: SVGAnimatedLength, signal: Signal<Double>) {
    length.baseVal.valueAsString = signal.getValue().toString()
    signal.value.addObserver { newValue ->
        length.baseVal.valueAsString = newValue.toString()
    }
}

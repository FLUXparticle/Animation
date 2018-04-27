package de.fluxparticle.animation

import de.fluxparticle.animation.elementobject.ElementNodeVisitor
import de.fluxparticle.animation.elementobject.ElementObject
import de.fluxparticle.animation.elementobject.ElementPath
import de.fluxparticle.animation.elementobject.ElementRectangle
import de.fluxparticle.animation.signal.Signal
import de.fluxparticle.animation.util.Color
import de.fluxparticle.animation.value.Value
import org.w3c.dom.svg.*
import kotlin.browser.document

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

private fun newSVGRect(x: Float, y: Float, width: Float, height: Float, fill: Color) = newSVGRect().apply {
    this.x.baseVal.valueAsString = x.toString()
    this.y.baseVal.valueAsString = y.toString()
    this.width.baseVal.valueAsString = width.toString()
    this.height.baseVal.valueAsString = height.toString()
    this.setAttribute("fill", fill.toString())
}

private fun newSVGGroup() = newSVG("g") as SVGGElement

private fun newSVGText() = newSVG("text") as SVGTextElement

class NodeCreator : ElementNodeVisitor<SVGElement> {

    override fun visitPath(elementPath: ElementPath): SVGElement {
        TODO("not implemented")
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

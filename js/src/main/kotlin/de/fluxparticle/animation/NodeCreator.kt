package de.fluxparticle.animation

import de.fluxparticle.animation.elementobject.ElementNodeVisitor
import de.fluxparticle.animation.elementobject.ElementObject
import de.fluxparticle.animation.elementobject.ElementPath
import de.fluxparticle.animation.elementobject.ElementRectangle
import de.fluxparticle.animation.signal.Signal
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

class NodeCreator : ElementNodeVisitor<SVGElement> {

    override fun visitPath(elementPath: ElementPath): SVGElement {
        TODO("not implemented")
    }

    override fun visitRectangle(elementRectangle: ElementRectangle): SVGElement = newSVGRect().apply {
        bindLength(x, elementRectangle.x)
        bindLength(y, elementRectangle.y)
        bindLength(width, elementRectangle.width)
        bindLength(height, elementRectangle.height)
        bindAttribute("fill", elementRectangle.fill)
        bindAttribute("fill-opacity", elementRectangle.opacity)
    }

    override fun visitObject(elementObject: ElementObject): SVGElement = newSVGRect().apply {
        val width = ElementObject.OPERAND_WIDTH
        val height = ElementObject.HEIGHT

        this.width.baseVal.valueAsString = width.toString()
        this.height.baseVal.valueAsString = height.toString()

        x.baseVal.valueAsString = (elementObject.translate.getValue().x.value - width/2).toString()
        y.baseVal.valueAsString = (elementObject.translate.getValue().y.value - height/2).toString()
        elementObject.translate.value.addObserver { newValue ->
            x.baseVal.valueAsString = (newValue.x.value - width/2).toString()
            y.baseVal.valueAsString = (newValue.y.value - height/2).toString()
        }
    }

}

private fun <T : Any> SVGGraphicsElement.bindAttribute(name: String, signal: Signal<T>) {
    setAttribute(name, signal.getValue().toString())
    signal.value.addObserver { newValue ->
        setAttribute(name, newValue.toString())
    }
}

private fun bindLength(length: SVGAnimatedLength, signal: Signal<Double>) {
    length.baseVal.valueAsString = signal.getValue().toString()
    signal.value.addObserver { newValue ->
        length.baseVal.valueAsString = newValue.toString()
    }
}


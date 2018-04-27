package de.fluxparticle.animation

import org.w3c.dom.svg.SVGRectElement
import org.w3c.dom.svg.SVGSVGElement
import kotlin.browser.document
import kotlin.browser.window

/**
 * Created by sreinck on 26.04.18.
 */

private fun newSVG(qualifiedName: String) = document.createElementNS("http://www.w3.org/2000/svg", qualifiedName)

private fun newSVGSVG() = newSVG("svg") as SVGSVGElement

private fun newSVGSVG(width: Float, height: Float): SVGSVGElement = newSVGSVG().apply {
    this.width.baseVal.valueAsString = width.toString()
    this.height.baseVal.valueAsString = height.toString()
}

private fun newSVGRect() = newSVG("rect") as SVGRectElement

fun main(args: Array<String>) {
    window.onload = {
        val svg = newSVGSVG(400.0f, 300.0f).apply {
            val rect = newSVGRect().apply {
                this.x.baseVal.valueAsString = "50"
                this.y.baseVal.valueAsString = "70"
                this.width.baseVal.valueAsString = "120"
                this.height.baseVal.valueAsString = "100"
                this.style.cssText = "fill: blue"
            }
            appendChild(rect)
            val handler = { obj: SVGRectElement ->
                obj.x.baseVal.value++;
                obj.y.baseVal.value++;
            }
            window.setInterval(handler, 100, rect)
        }
        document.body?.appendChild(svg)
        println("svg: $svg")
        println("width: ${svg.width.baseVal.value} height: ${svg.height.baseVal.value}")
    }
}

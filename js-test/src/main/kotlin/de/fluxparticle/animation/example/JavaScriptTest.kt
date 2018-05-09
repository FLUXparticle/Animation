package de.fluxparticle.animation.example

import de.fluxparticle.animation.*
import kotlin.browser.document
import kotlin.browser.window

/**
 * Created by sreinck on 26.04.18.
 */
typealias Algorithm = (animationQueue: AnimationQueue) -> Box

fun main(args: Array<String>) {

    val algorithms: List<Algorithm> = listOf(
            ::dependencyAlgorithm,
            ::bubbleSortAlgorithm
    )

    var time: Double = 0.0

    window.onload = {
        algorithms.forEach { algorithm ->
            var intervalId: dynamic = null
            val clip = Clip()
            val animationQueue = AnimationQueue(clip)
            val box = algorithm(animationQueue)
            val bounds = box.bounds

            val nodes = animationQueue.getVisitedGroup(JsNodeCreator())

            val svg = newSVGSVG(bounds.width.toFloat(), bounds.height.toFloat()).apply {
                nodes.forEach { appendChild(it) }
            }
            val div = document.createElement("div")
            div.appendChild(svg)
            document.body?.appendChild(div)
            val handler = {
                time += 0.017
                clip.timeProperty().value = time
                if (time > clip.length) {
                    window.clearInterval(intervalId)
                }
            }
            intervalId = window.setInterval(handler, 17)
        }
    }
}

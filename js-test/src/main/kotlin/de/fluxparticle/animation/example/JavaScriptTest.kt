package de.fluxparticle.animation.example

import de.fluxparticle.animation.AnimationQueue
import de.fluxparticle.animation.Clip
import de.fluxparticle.animation.JsNodeCreator
import de.fluxparticle.animation.newSVGSVG
import kotlin.browser.document
import kotlin.browser.window

/**
 * Created by sreinck on 26.04.18.
 */
@JsName("start")
fun start(input: dynamic) {
    val graphDescription = mutableMapOf<String, Array<String>>()

    for (b in input) {
        val name: String = b.name
        val deps: Array<String> = b.deps
        graphDescription[name] = deps
    }

    val dependencyGraph = DependencyGraph(graphDescription)

    val algorithms: List<Algorithm> = listOf(
            DependencyAlgorithm(dependencyGraph),
            BubbleSortAlgorithm()
    )

    var time: Double = 0.0

    window.onload = {
        algorithms.forEach { algorithm ->
            var intervalId: dynamic = null
            val clip = Clip()
            val animationQueue = AnimationQueue(clip)
            val box = algorithm.render(animationQueue)
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

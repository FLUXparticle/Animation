package de.fluxparticle.animation.bubblesort

import de.fluxparticle.animation.AnimationQueue
import de.fluxparticle.animation.Clip
import de.fluxparticle.animation.NodeCreator
import de.fluxparticle.animation.newSVGSVG
import kotlin.browser.document
import kotlin.browser.window

/**
 * Created by sreinck on 26.04.18.
 */
fun main(args: Array<String>) {
    val clip = Clip()
    val animationQueue = AnimationQueue(clip)
    val box = BubbleSortBox(animationQueue)
    val bounds = box.bounds

    bubbleSortAlgorithm(box)

    val nodes = animationQueue.getVisitedGroup(NodeCreator())

    var time: Double = 0.0

    window.onload = {
        val svg = newSVGSVG(bounds.width.toFloat(), bounds.height.toFloat()).apply {
            nodes.forEach { appendChild(it) }
        }
        document.body?.appendChild(svg)
        val handler = {
            time += 0.1
            clip.timeProperty().value = time
        }
        window.setInterval(handler, 100)

    }
}

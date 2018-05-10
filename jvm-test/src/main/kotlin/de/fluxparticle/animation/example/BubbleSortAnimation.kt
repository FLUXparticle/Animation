package de.fluxparticle.animation.example

import javafx.application.Application

/**
 * Created by sreinck on 09.02.16.
 */
class BubbleSortAnimation : JavaTestAnimation() {

    override val title = "Bubble Sort Animation"

    override val algorithm = BubbleSortAlgorithm()

}

fun main(args: Array<String>) {
    Application.launch(BubbleSortAnimation::class.java, *args)
}

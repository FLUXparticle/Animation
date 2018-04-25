package de.fluxparticle.animation.bubblesort

/**
 * Created by sreinck on 09.02.16.
 */
class BubbleSortAlgorithm(private val box: BubbleSortBox) : Runnable {

    override fun run() {
        for (top in box.size() - 1 downTo 0) {
            for (i in 0 until top) {
                if (!box.inOrder(i)) {
                    box.swap(i)
                }
            }
            box.ready(top)
        }
    }

}

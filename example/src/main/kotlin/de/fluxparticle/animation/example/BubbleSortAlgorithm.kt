package de.fluxparticle.animation.example

/**
 * Created by sreinck on 09.02.16.
 */
fun bubbleSortAlgorithm(box: BubbleSortBox) {
    for (top in box.size() - 1 downTo 0) {
        for (i in 0 until top) {
            if (!box.inOrder(i)) {
                box.swap(i)
            }
        }
        box.ready(top)
    }
}

package de.fluxparticle.animation.example

import de.fluxparticle.animation.AnimationQueue
import de.fluxparticle.animation.Box

/**
 * Created by sreinck on 09.05.18.
 */
interface Algorithm {

    fun render(animationQueue: AnimationQueue): Box

}

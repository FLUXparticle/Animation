package de.fluxparticle.animation.example

import de.fluxparticle.animation.AnimationQueue
import de.fluxparticle.animation.Clip
import kotlin.test.Test

/**
 * Created by sreinck on 07.05.18.
 */
class DependencyAlgorithmTest {

    @Test
    fun name() {
        val clip = Clip()
        val animationQueue = AnimationQueue(clip)
        dependencyAlgorithm(animationQueue)
    }

}

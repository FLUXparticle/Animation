package de.fluxparticle.animation.util

/**
 * Created by sreinck on 24.04.18.
 */
interface Interpolatable<T> {

    fun interpolate(endValue: T, t: Double): T

}

package de.fluxparticle.animation.util

import kotlin.math.max
import kotlin.math.min

/**
 * Created by sreinck on 23.04.18.
 */
data class Bounds(val minX: Double, val minY: Double, val width: Double, val height: Double) {
    
    private val maxX = minX + width
    
    private val maxY = minY + height
    
    fun union(other: Bounds): Bounds {
        return fromMinMax(min(this.minX, other.minX), min(this.minY, other.minY), max(this.maxX, other.maxX), max(this.maxY, other.maxY))
    }

    companion object {

        fun fromPoint(x: Double, y: Double) = Bounds(x, y, x, y)

        fun fromMinMax(minX: Double, minY: Double, maxX: Double, maxY: Double) = Bounds(minX, minY, maxX - minX, maxY - minY)

    }
    
}

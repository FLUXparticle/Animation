package de.fluxparticle.animation

import kotlin.collections.HashMap
import kotlin.collections.ArrayList

class AnimationCollection {

    private val animations: MutableMap<Int, MutableList<Timeframe>> = HashMap()

    fun add(priority: Int, Timeframe: Timeframe) {
        var get = animations.get(priority)
        if (get == null) {
            get = ArrayList()
            animations.put(priority, get!!)
        }
        get.add(Timeframe)
    }

    fun appendToClip(clip: Clip) {
        for (values in animations.values) {
            clip.invoke(false) {
                values.forEach {
                    clip.addTimeframe(it)
                }
            }
        }
    }

    fun appendToClipRaw(clip: Clip) {
        for (values in animations.values) {
            values.forEach { clip.addTimeframe(it) }
        }
    }

}

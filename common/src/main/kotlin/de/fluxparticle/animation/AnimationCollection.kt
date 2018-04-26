package de.fluxparticle.animation

class AnimationCollection {

    private val animations: MutableMap<Int, MutableList<Timeframe>> = HashMap()

    fun add(priority: Int, Timeframe: Timeframe) {
        animations.getOrPut(priority, ::mutableListOf).add(Timeframe)
    }

    fun appendToClip(clip: Clip) {
        val sortedValues = sortedValues()
        for (values in sortedValues) {
            clip.invoke() {
                values.forEach {
                    clip.addTimeframe(it)
                }
            }
        }
    }

    fun appendToClipRaw(clip: Clip) {
        val sortedValues = sortedValues()
        for (values in sortedValues) {
            values.forEach { clip.addTimeframe(it) }
        }
    }

    private fun sortedValues(): List<MutableList<Timeframe>> {
        return animations.entries
                .sortedBy { entry -> entry.key }
                .map { entry -> entry.value }
    }

}

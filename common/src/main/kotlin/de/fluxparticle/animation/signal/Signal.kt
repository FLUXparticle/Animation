package de.fluxparticle.animation.signal

import de.fluxparticle.animation.value.Value

fun Double.toTicks() = (this * 6000).toLong()
fun Long.toSeconds() = this / 6000.0
fun <K : Comparable<K>, V> MutableMap<K, V>.floorEntry(floor: K): Map.Entry<K, V> {
    val floorEntry = this
            .filterKeys { k -> k <= floor }
            .maxBy { entry -> entry.key }
    return checkNotNull(floorEntry) { "No such element." }
}
fun <K : Comparable<K>, V> MutableMap<K, V>.lastEntry(): Map.Entry<K, V> {
    val lastEntry = this.maxBy { entry -> entry.key }
    return checkNotNull(lastEntry) { "No such element." }
}

class Signal<R : Any>(time: Value<Double>, initValue: R) {

    private val keyPoints: MutableMap<Long, SignalFunction<R>> = mutableMapOf(0L to SignalConstant(initValue))

    val value: Value<R> = time.map { t -> this.apply(t) }

    fun lastValue(): R {
        val lastEntry = keyPoints.lastEntry()
        val t = lastEntry.key.toSeconds()
        return lastEntry.value.apply(t)
    }

    fun apply(t: Double): R {
        val ticks = t.toTicks()
        val floor = keyPoints.floorEntry(ticks)
        return floor.value.apply(t)
    }

    fun changeValue(pathBuilder: SignalPath.Builder<R>, newValue: R, ticksStart: Long, ticksDuration: Long) {
        val lastEntry = keyPoints.lastEntry()

        val ticksLast = lastEntry.key
        val oldValue = lastEntry.value.apply(0.0)

        if (ticksStart < ticksLast) {
            throw IllegalArgumentException(ticksStart.toString() + " < " + ticksLast)
        }

        if (newValue !== oldValue) {
            val timeStart = ticksStart.toSeconds()
            val timeDuration = ticksDuration.toSeconds()

            val path = pathBuilder.start(oldValue).end(newValue).build()

            keyPoints[ticksStart] = SignalTransform(timeStart, timeDuration, path)
        }

        val ticksEnd = ticksStart + ticksDuration
        keyPoints[ticksEnd] = SignalConstant(newValue)
    }

    fun getValue(): R {
        return value.value
    }

}

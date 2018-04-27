package de.fluxparticle.animation

import de.fluxparticle.animation.signal.SignalEvent
import de.fluxparticle.animation.signal.toTicks
import de.fluxparticle.animation.value.SimpleValue

open class Clip {

    protected val time = SimpleValue(0.0)

    protected var clipTicks: Long = 0

    private var ticksBegin: Long = 0

    protected val keyframes = mutableListOf(0L)

//    private val signals = HashMap<String, Signal<*>>()

    fun timeProperty() = time

    fun invoke(runnable: () -> Unit) {
        ticksBegin = clipTicks
        runnable()
        keyframes.add(clipTicks)
    }

    fun addTimeframe(timeframe: Timeframe) {
        val duration = timeframe.duration
        for (signalEvent in timeframe.signalEvents) {
            addSignalEvents(duration, signalEvent)
        }
    }

    private fun <T : Any> addSignalEvents(duration: Double, signalEvent: SignalEvent<T>) {
        val ticksStart = ticksBegin
        val ticksDuration = duration.toTicks()
        val ticksEnd = ticksStart + ticksDuration

        val signal = signalEvent.signal

//        val name = signal.name
//        signals[name] = signal

        signal.changeValue(signalEvent.pathBuilder, signalEvent.value, ticksStart, ticksDuration)

        if (ticksEnd > clipTicks) {
            clipTicks = ticksEnd
        }
    }

    fun getKeyframeList(): List<Long> {
        return keyframes.toList()
    }

}

package de.fluxparticle.animation

import com.sun.javafx.animation.TickCalculation
import de.fluxparticle.animation.signal.Signal
import de.fluxparticle.animation.signal.SignalEvent
import javafx.animation.KeyFrame
import javafx.animation.KeyValue
import javafx.animation.Timeline
import javafx.beans.property.ReadOnlyDoubleProperty
import javafx.beans.property.ReadOnlyDoubleWrapper
import javafx.util.Duration
import java.util.*
import java.util.function.Predicate

class Clip {

    private val time = ReadOnlyDoubleWrapper(this, "time")

    private var clipTicks: Long = 0

    private var curSequence: Int = 0

    private var ticksBegin: Long = 0

    private var timeline: Timeline? = null

    private val keyframes = ArrayList<Long>()

    private val signals = HashMap<String, Signal<*>>()

    val cntSequences: Int
        get() = keyframes.size

    fun timeProperty(): ReadOnlyDoubleProperty {
        return time.readOnlyProperty
    }

    init {
        keyframes.add(0L)
    }

    @Synchronized fun invoke(resume: Boolean, runnable: () -> Unit) {
        ticksBegin = clipTicks
        runnable()
        if (resume) {
            resume()
        }
        keyframes.add(clipTicks)
    }

    fun addTimeframe(timeframe: Timeframe) {
        if (!Thread.holdsLock(this)) {
            throw IllegalMonitorStateException()
        }

        val duration = timeframe.duration
        for (signalEvent in timeframe.signalEvents) {
            addSignalEvents(duration, signalEvent)
        }
    }

    private fun <T : Any> addSignalEvents(duration: Duration, signalEvent: SignalEvent<T>) {
        val ticksStart = ticksBegin
        val ticksDuration = TickCalculation.fromDuration(duration)
        val ticksEnd = TickCalculation.add(ticksStart, ticksDuration)

        val signal = signalEvent.signal
        val name = signal.name

        signals.put(name, signal)

        signal.changeValue(signalEvent.pathBuilder, signalEvent.value, ticksStart, ticksDuration)

        if (ticksEnd > clipTicks) {
            clipTicks = ticksEnd
        }
    }

    fun play() {
        play(0.0, TickCalculation.toDuration(clipTicks).toSeconds())
    }

    fun resume() {
        val timeStart = time.get()
        val timeEnd = TickCalculation.toDuration(clipTicks).toSeconds()
        play(timeStart, timeEnd)
    }

    fun skipSequence(): Boolean {
        return goToSequence(curSequence + 1)
    }

    fun gotoPrevSequence() {
        goToSequence(curSequence - 1)
    }

    fun goToSequence(nr: Int): Boolean {
        if (nr >= keyframes.size) {
            return false
        }

        val ticks = keyframes[nr]
        val t = TickCalculation.toDuration(ticks).toSeconds()
        time.setValue(t)
        curSequence = nr

        return true
    }

    fun playNextSequence(): Timeline? {
        if (curSequence + 1 >= keyframes.size) {
            return null
        }

        val start = keyframes[curSequence]
        val end = keyframes[curSequence + 1]
        val timeline = playKeyframes(start, end)
        curSequence++

        return timeline
    }

    fun <T : Any> play(signal: Signal<T>, predicate: Predicate<T>): Timeline {
        val startSequence = curSequence
        fastFwd(signal, predicate)
        val endSequence = curSequence

        val start = keyframes[startSequence]
        val end = keyframes[endSequence]
        val timeline = playKeyframes(start, end)

        curSequence = endSequence

        return timeline
    }

    private fun playKeyframes(start: Long, end: Long): Timeline {
        val timeStart = TickCalculation.toDuration(start).toSeconds()
        val timeEnd = TickCalculation.toDuration(end).toSeconds()
        return play(timeStart, timeEnd)
    }

    private fun play(timeStart: Double, timeEnd: Double): Timeline {
        val durationLength = Duration.seconds(timeEnd - timeStart)

        val start = KeyFrame(Duration.ZERO, KeyValue(time, timeStart))
        val end = KeyFrame(durationLength, KeyValue(time, timeEnd))

        val timeline = Timeline(start, end)
        playTimeline(timeline)
        return timeline
    }

    private fun playTimeline(newTimeline: Timeline) {
        if (timeline != null) {
            timeline!!.stop()
        }
        timeline = newTimeline
        timeline!!.play()
    }

    fun getKeyframes(): List<Long> {
        return Collections.unmodifiableList(keyframes)
    }

    fun <T : Any> fastFwd(signal: Signal<T>, predicate: Predicate<T>) {
        if (predicate.test(signal.value)) {
            while (skipSequence() && predicate.test(signal.value)) {
                // empty
            }
        }

        while (skipSequence() && !predicate.test(signal.value)) {
            // empty
        }
    }

    companion object {

        private val APPLE_PRO_RES_422 = "Apple ProRes 422"
    }

}

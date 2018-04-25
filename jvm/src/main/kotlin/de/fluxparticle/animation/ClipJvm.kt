package de.fluxparticle.animation

import de.fluxparticle.animation.signal.Signal
import de.fluxparticle.animation.signal.toSeconds
import javafx.animation.KeyFrame
import javafx.animation.KeyValue
import javafx.animation.Timeline
import javafx.util.Duration
import java.util.function.Predicate

/**
 * Created by sreinck on 25.04.18.
 */
class ClipJvm : Clip() {

    private var curSequence: Int = 0

    private var timeline: Timeline? = null

    val cntSequences: Int
        get() = getKeyframeList().size

    fun play() {
        play(0.0, clipTicks.toSeconds())
    }

    fun resume() {
        val timeStart = time.value
        val timeEnd = clipTicks.toSeconds()
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
        val t = ticks.toSeconds()
        time.value = t
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
        val timeStart = start.toSeconds()
        val timeEnd = end.toSeconds()
        return play(timeStart, timeEnd)
    }

    private fun play(timeStart: Double, timeEnd: Double): Timeline {
        val durationLength = Duration.seconds(timeEnd - timeStart)

        val writableTime = time.toWritableValue()

        val start = KeyFrame(Duration.ZERO, KeyValue(writableTime, timeStart))
        val end = KeyFrame(durationLength, KeyValue(writableTime, timeEnd))

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

    fun <T : Any> fastFwd(signal: Signal<T>, predicate: Predicate<T>) {
        if (predicate.test(signal.value.value)) {
            while (skipSequence() && predicate.test(signal.value.value)) {
                // empty
            }
        }

        while (skipSequence() && !predicate.test(signal.value.value)) {
            // empty
        }
    }

}

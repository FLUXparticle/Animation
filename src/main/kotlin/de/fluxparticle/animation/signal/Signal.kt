package de.fluxparticle.animation.signal

import com.sun.javafx.animation.TickCalculation
import de.fluxparticle.animation.binding.BindingFunction
import javafx.beans.InvalidationListener
import javafx.beans.binding.DoubleExpression
import javafx.beans.binding.ObjectBinding
import javafx.beans.property.ReadOnlyProperty
import javafx.beans.value.ChangeListener
import javafx.util.Duration
import java.util.*

class Signal<R : Any>(time: DoubleExpression, private val bean: Any, private val name: String, initValue: R) : ReadOnlyProperty<R> {

    private val keyPoints: TreeMap<Long, SignalFunction<R>>

    private val value: ObjectBinding<R>

    init {

        keyPoints = TreeMap()
        keyPoints.put(0L, SignalConstant(initValue))

        value = BindingFunction(time) { this.apply(it) }
    }

    fun lastValue(): R {
        val lastEntry = keyPoints.lastEntry()
        val t = TickCalculation.toDuration(lastEntry.key).toSeconds()
        return lastEntry.value.apply(t)
    }

    fun apply(t: Number): R {
        val ticks = TickCalculation.fromDuration(Duration.seconds(t.toDouble()))
        val floor = keyPoints.floorEntry(ticks)

        return floor.value.apply(t as Double)
    }

    fun changeValue(pathBuilder: SignalPath.Builder<R>, newValue: R, ticksStart: Long, ticksDuration: Long) {
        val lastEntry = keyPoints.lastEntry()

        val ticksLast = lastEntry.key
        val oldValue = lastEntry.value.apply(0.0)

        if (ticksStart < ticksLast) {
            throw IllegalArgumentException(ticksStart.toString() + " < " + ticksLast)
        }

        if (newValue !== oldValue) {
            val timeStart = TickCalculation.toDuration(ticksStart).toSeconds()
            val timeDuration = TickCalculation.toDuration(ticksDuration).toSeconds()

            val path = pathBuilder.start(oldValue).end(newValue).build()

            keyPoints.put(ticksStart, SignalTransform(timeStart, timeDuration, path))
        }

        val ticksEnd = ticksStart + ticksDuration
        keyPoints.put(ticksEnd, SignalConstant(newValue))
    }

    override fun getValue(): R {
        return value.value
    }

    override fun addListener(listener: InvalidationListener) {
        value.addListener(listener)
    }

    override fun removeListener(listener: InvalidationListener) {
        value.removeListener(listener)
    }

    override fun addListener(listener: ChangeListener<in R>) {
        value.addListener(listener)
    }

    override fun removeListener(listener: ChangeListener<in R>) {
        value.removeListener(listener)
    }

    override fun getName(): String {
        return name
    }

    override fun getBean(): Any {
        return bean
    }

}

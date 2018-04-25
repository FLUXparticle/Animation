package de.fluxparticle.animation.signal

import de.fluxparticle.animation.value.SimpleValue
import kotlin.test.Test
import kotlin.test.assertEquals

/**
 * Created by sreinck on 24.04.18.
 */
class SignalTest {

    @Test
    fun signal(){
        val time = SimpleValue(0.0)
        val signal = Signal<Double>(time, 0.0)
        signal.changeValue(SignalDoubleLine.Builder(), 1.0, 6_000, 12_000)

        assertEquals(0.0, signal.getValue())

        time.value = 1.0
        assertEquals(0.0, signal.getValue())

        time.value = 2.0
        assertEquals(0.5, signal.getValue())

        time.value = 3.0
        assertEquals(1.0, signal.getValue())
    }

}

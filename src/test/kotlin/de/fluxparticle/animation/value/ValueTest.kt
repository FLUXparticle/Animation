package de.fluxparticle.animation.value

import kotlin.test.Test
import kotlin.test.assertEquals

/**
 * Created by sreinck on 24.04.18.
 */
class ValueTest {

    @Test
    fun simpleValue() {
        val simple = SimpleValue(0.0)

        simple.value = 5.0

        assertEquals(5.0, simple.value)
    }

    @Test
    fun mappedValue() {
        val simple = SimpleValue(0.0)
        val mapped = simple.map { it + 5.0 }

        simple.value = 5.0

        assertEquals(10.0, mapped.value)
    }

}

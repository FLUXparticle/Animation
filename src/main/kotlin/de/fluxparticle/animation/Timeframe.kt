package de.fluxparticle.animation

import de.fluxparticle.animation.signal.SignalEvent
import javafx.util.Duration
import java.util.*

class Timeframe(val duration: Duration, vararg signalEvents: SignalEvent<out Any>) {

    val signalEvents: Collection<SignalEvent<out Any>>

    init {
        this.signalEvents = Arrays.asList(*signalEvents)
    }

}

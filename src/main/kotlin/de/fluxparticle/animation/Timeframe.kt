package de.fluxparticle.animation

import de.fluxparticle.animation.signal.SignalEvent

class Timeframe(val duration: Double, vararg signalEvents: SignalEvent<out Any>) {

    val signalEvents: Collection<SignalEvent<out Any>>

    init {
        this.signalEvents = listOf(*signalEvents)
    }

}

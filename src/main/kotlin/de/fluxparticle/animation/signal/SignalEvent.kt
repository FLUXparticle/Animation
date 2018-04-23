package de.fluxparticle.animation.signal

class SignalEvent<T : Any>(val signal: Signal<T>, val value: T, val pathBuilder: SignalPath.Builder<T>)

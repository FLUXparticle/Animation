package de.fluxparticle.animation

import kotlin.browser.document
import kotlin.browser.window

/**
 * Created by sreinck on 26.04.18.
 */
fun main(args: Array<String>) {
    window.onload = {
        document.body?.innerText = "Hallo Welt!"
        null
    }
}

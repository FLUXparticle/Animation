package de.fluxparticle.animation.output

class Log private constructor(level: Int) {

    private val indent: String

    init {
        val sb = StringBuilder()
        for (i in 0 until level) {
            sb.append(' ')
            sb.append(' ')
        }
        indent = sb.toString()
    }

    fun info(line: String) {
        println(indent + line);
    }

    companion object {

        fun getLog(level: Int): Log {
            return Log(level)
        }
    }

}

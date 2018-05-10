package de.fluxparticle.animation.graph

interface GraphNode {

    val preferredPos: Int

    val size: Int

    val id: Int

    val isReferenced: Boolean

    fun setPos(pos: Int)

    fun setLevel(level: Int)

}

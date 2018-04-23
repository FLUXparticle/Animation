package de.fluxparticle.animation.`object`

interface ElementNodeVisitor<R> {

    fun visitPath(path: ElementPath): R

    fun visitRectangle(rectangle: ElementRectangle): R

    fun visitObject(`object`: ElementObject): R

}

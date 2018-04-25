package de.fluxparticle.animation.elementobject

interface ElementNodeVisitor<R> {

    fun visitPath(elementPath: ElementPath): R

    fun visitRectangle(elementRectangle: ElementRectangle): R

    fun visitObject(elementObject: ElementObject): R

}

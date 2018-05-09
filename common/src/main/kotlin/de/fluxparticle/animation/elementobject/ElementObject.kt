package de.fluxparticle.animation.elementobject

//import de.fluxparticle.animation.path.PathMaker
import de.fluxparticle.animation.point.DynamicPoint2D
import de.fluxparticle.animation.signal.Signal
import de.fluxparticle.animation.value.Value

class ElementObject(private val time: Value<Double>, val isUseRect: Boolean, val isCenter: Boolean, val width: Int, vararg lines: String?) : ElementNode(time) {
    val headLine: String
    val contentLine: Signal<String>?

    var translate: Signal<DynamicPoint2D> = Signal(time, DynamicPoint2D(0.0, 0.0))

    val circle: DynamicPoint2D?

    fun setTranslate(translate: DynamicPoint2D) {
        this.translate = Signal(this.time, translate)
    }

    init {
        val firstLine: String = lines[0]!!
        if (firstLine.contains(".")) {
            this.headLine = firstLine.substring(firstLine.lastIndexOf('.') + 1)
        } else {
            this.headLine = firstLine
        }

        if (lines.size > 1) {
            val secondLine = lines[1]
            if (secondLine != null) {
                this.contentLine = Signal(time, secondLine)
                this.circle = null
            } else {
                this.contentLine = null
                val midY = (ElementObject.HEIGHT * 2 / (lines.size + 1) - ElementObject.HEIGHT / 2).toDouble()
                this.circle = DynamicPoint2D(0.0, midY)
            }
        } else {
            this.contentLine = null
            this.circle = null
        }
    }

/*
    fun copy(): ElementObject {
        val copy: ElementObject
        if (contentLine != null) {
            copy = ElementObject(time, isUseRect, isCenter, width, headLine, contentLine.lastValue())
        } else if (circle != null) {
            copy = ElementObject(time, isUseRect, isCenter, width, headLine, null)
        } else {
            copy = ElementObject(time, isUseRect, isCenter, width, headLine)
        }
        copy.setTranslate(translate!!.lastValue())

        return copy
    }
*/


/*
    fun mkDynamicPath(heapObj: ElementObject): ElementPath {
        val origin = DynamicPoint2D(translateXExpression().add(circle!!.x), translateYExpression().add(circle.y))
        val dest = DynamicPoint2D(heapObj.translateXExpression().subtract(OBJECT_WIDTH / 2 + MARGIN + PathMaker.ARROW_SIZE), heapObj.translateYExpression())

        return ElementPath(time, origin, dest)
    }
*/


    override fun <R> accept(visitor: ElementNodeVisitor<R>): R {
        return visitor.visitObject(this)
    }

    companion object {

        const val MARGIN = 10

        const val HEIGHT = 40

        const val OPERAND_WIDTH = 60

        const val SYMBOL_WIDTH = HEIGHT

        const val FUNCTION_WIDTH = ElementObject.SYMBOL_WIDTH + 2 * MARGIN + 2 * ElementObject.OPERAND_WIDTH

        const val OBJECT_WIDTH = 2 * OPERAND_WIDTH

    }

}

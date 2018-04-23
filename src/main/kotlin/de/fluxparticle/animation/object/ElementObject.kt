package de.fluxparticle.animation.`object`

import de.fluxparticle.animation.binding.BindingFunction
import de.fluxparticle.animation.path.PathMaker
import de.fluxparticle.animation.point.DynamicPoint2D
import de.fluxparticle.animation.signal.Signal
import javafx.beans.binding.DoubleExpression
import javafx.beans.binding.DoubleExpression.doubleExpression
import javafx.geometry.Point2D

class ElementObject(time: DoubleExpression, val isUseRect: Boolean, val isCenter: Boolean, val width: Int, vararg lines: String?) : ElementNode(time) {
    val headLine: String
    val contentLine: Signal<String>?

    private var translate: Signal<DynamicPoint2D> = Signal(time, this, "translate", DynamicPoint2D(0.0, 0.0))

    val circle: Point2D?
/*
    fun setTranslate(translate: DynamicPoint2D) {
        this.translate = Signal(time, this, "translate", translate)
    }
*/

    fun translateSignal(): Signal<DynamicPoint2D> {
        return translate
    }

    fun translateXExpression(): DoubleExpression {
        return doubleExpression(BindingFunction<DynamicPoint2D, Double>(translate) { it.getX() })
    }

    fun translateYExpression(): DoubleExpression {
        return doubleExpression(BindingFunction<DynamicPoint2D, Double>(translate) { it.getY() })
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
                this.contentLine = Signal(time, this, "contentLine", secondLine)
                this.circle = null
            } else {
                this.contentLine = null
                val midY = (ElementObject.HEIGHT * 2 / (lines.size + 1) - ElementObject.HEIGHT / 2).toDouble()
                this.circle = Point2D(0.0, midY)
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

    fun mkDynamicPath(heapObj: ElementObject): ElementPath {
        val origin = DynamicPoint2D(translateXExpression().add(circle!!.x), translateYExpression().add(circle.y))
        val dest = DynamicPoint2D(heapObj.translateXExpression().subtract(OBJECT_WIDTH / 2 + MARGIN + PathMaker.ARROW_SIZE), heapObj.translateYExpression())

        return ElementPath(time, origin, dest)
    }

    override fun <R> accept(visitor: ElementNodeVisitor<R>): R {
        return visitor.visitObject(this)
    }

    companion object {

        val MARGIN = 10

        val HEIGHT = 40

        val OPERAND_WIDTH = 60

        val SYMBOL_WIDTH = HEIGHT

        val FUNCTION_WIDTH = ElementObject.SYMBOL_WIDTH + 2 * MARGIN + 2 * ElementObject.OPERAND_WIDTH

        val OBJECT_WIDTH = 2 * OPERAND_WIDTH
    }

}

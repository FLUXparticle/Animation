package de.fluxparticle.animation

import de.fluxparticle.animation.elementobject.*
import de.fluxparticle.animation.path.PathMaker
import de.fluxparticle.animation.signal.Signal
import de.fluxparticle.animation.value.SimpleValue
import de.fluxparticle.animation.value.Value
import javafx.beans.binding.DoubleExpression
import javafx.beans.property.SimpleDoubleProperty
import javafx.beans.property.SimpleObjectProperty
import javafx.beans.value.ObservableValue
import javafx.beans.value.WritableValue
import javafx.scene.CacheHint
import javafx.scene.Group
import javafx.scene.Node
import javafx.scene.paint.Color
import javafx.scene.shape.Circle
import javafx.scene.shape.Rectangle
import javafx.scene.text.Font
import javafx.scene.text.Text
import org.fxmisc.easybind.EasyBind.map
import org.fxmisc.easybind.EasyBind.monadic

fun <T : Any> Value<T>.toObservableValue(): ObservableValue<T> {
    val property = SimpleObjectProperty<T>(value)
    addObserver { newValue -> property.set(newValue) }
    return property
}

fun <T : Any> SimpleValue<T>.toWritableValue(): WritableValue<T> {
    val property = SimpleObjectProperty<T>(value)
    property.addListener { _, _, newValue -> value = newValue }
    return property
}

fun Value<Double>.toDoubleExpression(): DoubleExpression {
    val property = SimpleDoubleProperty(value)
    addObserver { newValue -> property.set(newValue) }
    return property
}

fun <R : Any> Signal<R>.toObservableValue(): ObservableValue<R> = this.value.toObservableValue()

class JavaNodeCreator : ElementNodeVisitor<Node> {

    override fun visitPath(elementPath: ElementPath): Node {
        val path = PathMaker.mkDynamicCurveEastEast(elementPath.origin, elementPath.dest)
        PathMaker.addDynamicArrow(path, elementPath.dest)

        return bindNode(elementPath, path)
    }

    override fun visitRectangle(elementRectangle: ElementRectangle): Node {
        return bindRectangle(elementRectangle, Rectangle())
    }

    override fun visitObject(elementObject: ElementObject): Node {
        val group = Group()

        val observableValue = elementObject.translate.value.toObservableValue()

        group.translateXProperty().bind( map(observableValue) { it.x }.flatMap { it.toObservableValue() } )
        group.translateYProperty().bind( map(observableValue) { it.y }.flatMap { it.toObservableValue() } )

        val children = group.children

        if (elementObject.isUseRect) {
            val rectOuter = Rectangle((-ElementObject.OPERAND_WIDTH / 2).toDouble(), (-ElementObject.HEIGHT / 2).toDouble(), ElementObject.OPERAND_WIDTH.toDouble(), ElementObject.HEIGHT.toDouble())
            val rectInner = Rectangle((-ElementObject.OPERAND_WIDTH / 2 + 1).toDouble(), (-ElementObject.HEIGHT / 2 + 1).toDouble(), (ElementObject.OPERAND_WIDTH - 2).toDouble(), (ElementObject.HEIGHT - 2).toDouble())

            rectOuter.fill = Color.BLACK
            rectInner.fill = Color.WHITE

            children.addAll(rectOuter, rectInner)
        }

        val maxContentWidth = (elementObject.width - ElementObject.OPERAND_WIDTH / 20).toDouble()

        val contentLine = elementObject.contentLine
        val cntLines = if (contentLine != null || elementObject.circle != null) 2 else 1
        val center = elementObject.isCenter
        run {
            val midY = (ElementObject.HEIGHT / (cntLines + 1) - ElementObject.HEIGHT / 2).toDouble()

            val strText = elementObject.headLine

            if (!strText.isEmpty()) {
                val text = Text(strText)
                fitText(text, maxContentWidth)
                adjustNode(text, center, midY, maxContentWidth)
                children.add(text)
            }
        }

        run {
            val midY = (ElementObject.HEIGHT * 2 / (cntLines + 1) - ElementObject.HEIGHT / 2).toDouble()

            var node: Node? = null
            if (elementObject.circle != null) {
                val radius = Font.getDefault().size / 4.0
                node = Circle(radius, Color.BLACK)
            }

            if (contentLine != null) {
                val value = contentLine.value
                val text = Text(value.value)
                //                Tooltip.install(text, new Tooltip(value));

                fitText(text, maxContentWidth)

                value.addObserver { newValue ->
                    val localText = Text(newValue)

                    fitText(localText, maxContentWidth)

                    text.text = newValue
                    text.font = localText.font
                    //                    Tooltip.install(text, new Tooltip(newValue));

                    adjustNode(text, center, midY, maxContentWidth)
                }

                node = text
            }

            if (node != null) {
                adjustNode(node, center, midY, maxContentWidth)
                children.add(node)
            }
        }

        group.isCache = true
        group.cacheHint = CacheHint.SPEED

        return bindNode(elementObject, group)
    }

    private fun bindRectangle(element: ElementRectangle, rectangle: Rectangle): Rectangle {
        rectangle.xProperty().bind(element.x.toObservableValue())
        rectangle.yProperty().bind(element.y.toObservableValue())
        rectangle.widthProperty().bind(element.width.toObservableValue())
        rectangle.heightProperty().bind(element.height.toObservableValue())
        rectangle.fillProperty().bind( monadic(element.fill.toObservableValue()).map { Color(it.red, it.green, it.blue, 1.0) } )
        return bindNode(element, rectangle)
    }

    private fun <T : Node> bindNode(element: ElementNode, node: T): T {
        node.opacityProperty().bind(element.opacity.toObservableValue())
        node.visibleProperty().bind( monadic(element.opacity.toObservableValue()).map { it > 0.0 } )
        return node
    }

    private fun fitText(text: Text, maxWidth: Double) {
        var boundsText = text.boundsInLocal
        if (boundsText.width > maxWidth) {
            var size = text.font.size
            do {
                size /= 2.0
                text.font = Font(size)
                boundsText = text.boundsInLocal
            } while (boundsText.width > maxWidth)

            var lo = size
            var hi = size * 2
            while (hi - lo > 1) {
                size = (lo + hi) / 2
                text.font = Font(size)
                boundsText = text.boundsInLocal
                if (boundsText.width > maxWidth) {
                    hi = size
                } else {
                    lo = size
                }
            }
            size = lo
            text.font = Font(size)
        }
    }

    private fun adjustNode(node: Node, centerX: Boolean, midY: Double, maxContentWidth: Double) {
        val bounds = node.boundsInLocal

        if (centerX) {
            node.translateX = -(bounds.minX + bounds.maxX) / 2
        } else {
            node.translateX = -(bounds.minX + maxContentWidth / 2)
        }

        node.translateY = midY - (bounds.minY + bounds.maxY) / 2
    }

}

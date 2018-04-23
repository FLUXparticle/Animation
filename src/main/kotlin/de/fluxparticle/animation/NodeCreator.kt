package de.fluxparticle.animation

import de.fluxparticle.animation.`object`.*
import de.fluxparticle.animation.path.PathMaker
import javafx.beans.binding.DoubleExpression
import javafx.scene.CacheHint
import javafx.scene.Group
import javafx.scene.Node
import javafx.scene.paint.Color
import javafx.scene.shape.Circle
import javafx.scene.shape.Rectangle
import javafx.scene.text.Font
import javafx.scene.text.Text

class NodeCreator : ElementNodeVisitor<Node> {

    override fun visitPath(element: ElementPath): Node {
        val path = PathMaker.mkDynamicCurveEastEast(element.origin, element.dest)
        PathMaker.addDynamicArrow(path, element.dest)

        return bindNode(element, path)
    }

    override fun visitRectangle(rectangle: ElementRectangle): Node {
        return bindRectangle(rectangle, Rectangle())
    }

    override fun visitObject(`object`: ElementObject): Node {
        val group = Group()

        group.translateXProperty().bind(`object`.translateXExpression())
        group.translateYProperty().bind(`object`.translateYExpression())

        val children = group.children

        if (`object`.isUseRect) {
            val rectOuter = Rectangle((-ElementObject.OPERAND_WIDTH / 2).toDouble(), (-ElementObject.HEIGHT / 2).toDouble(), ElementObject.OPERAND_WIDTH.toDouble(), ElementObject.HEIGHT.toDouble())
            val rectInner = Rectangle((-ElementObject.OPERAND_WIDTH / 2 + 1).toDouble(), (-ElementObject.HEIGHT / 2 + 1).toDouble(), (ElementObject.OPERAND_WIDTH - 2).toDouble(), (ElementObject.HEIGHT - 2).toDouble())

            rectOuter.fill = Color.BLACK
            rectInner.fill = Color.WHITE

            children.addAll(rectOuter, rectInner)
        }

        val maxContentWidth = (`object`.width - ElementObject.OPERAND_WIDTH / 20).toDouble()

        val contentLine = `object`.contentLine
        val cntLines = if (contentLine != null || `object`.circle != null) 2 else 1
        val center = `object`.isCenter
        run {
            val midY = (ElementObject.HEIGHT / (cntLines + 1) - ElementObject.HEIGHT / 2).toDouble()

            val strText = `object`.headLine

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
            if (`object`.circle != null) {
                val radius = Font.getDefault().size / 4.0
                node = Circle(radius, Color.BLACK)
            }

            if (contentLine != null) {
                val value = contentLine.value
                val text = Text(value)
                //                Tooltip.install(text, new Tooltip(value));

                fitText(text, maxContentWidth)

                contentLine.addListener { observable, oldValue, newValue ->
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

        return bindNode(`object`, group)
    }

    private fun bindRectangle(element: ElementRectangle, rectangle: Rectangle): Rectangle {
        rectangle.xProperty().bind(element.xSignal())
        rectangle.yProperty().bind(element.ySignal())
        rectangle.widthProperty().bind(element.widthSignal())
        rectangle.heightProperty().bind(element.heightSignal())
        rectangle.fillProperty().bind(element.fillSignal())
        return bindNode(element, rectangle)
    }

    private fun <T : Node> bindNode(element: ElementNode, node: T): T {
        node.opacityProperty().bind(element.opacitySignal())
        node.visibleProperty().bind(DoubleExpression.doubleExpression(element.opacitySignal()).greaterThan(0.0))
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

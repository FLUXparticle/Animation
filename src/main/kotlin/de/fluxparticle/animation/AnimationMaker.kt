package de.fluxparticle.animation

import de.fluxparticle.animation.`object`.ElementNode
import de.fluxparticle.animation.`object`.ElementObject
import de.fluxparticle.animation.`object`.ElementRectangle
import de.fluxparticle.animation.point.DynamicPoint2D
import de.fluxparticle.animation.signal.*
import javafx.geometry.Bounds
import javafx.scene.paint.Color
import javafx.util.Duration

object AnimationMaker {

    fun fadeIn(node: ElementNode): Timeframe {
        val seOpacity = SignalEvent(node.opacitySignal(), 1.0, SignalDoubleLine.Builder())
        return Timeframe(Duration.seconds(1.0), seOpacity)
    }

    fun show(node: ElementNode): Timeframe {
        val seOpacity = SignalEvent(node.opacitySignal(), 1.0, SignalDoubleLine.Builder())
        return Timeframe(Duration.seconds(0.0), seOpacity)
    }

    fun fadeOut(node: ElementNode): Timeframe {
        val seOpacity = SignalEvent(node.opacitySignal(), 0.0, SignalDoubleLine.Builder())
        return Timeframe(Duration.seconds(1.0), seOpacity)
    }

    fun hide(node: ElementNode): Timeframe {
        val seOpacity = SignalEvent(node.opacitySignal(), 0.0, SignalDoubleLine.Builder())
        return Timeframe(Duration.seconds(0.0), seOpacity)
    }


    fun move(node: ElementObject, dynDest: DynamicPoint2D, simple: Boolean): Timeframe {
        //		Timeframe tf;

        val seTranslate: SignalEvent<DynamicPoint2D>
        if (simple) {
            seTranslate = SignalEvent(node.translateSignal(), dynDest, SignalInterpolatable.Builder())
        } else {
            seTranslate = SignalEvent(node.translateSignal(), dynDest, SignalCurve.Builder())
        }
        //		if (node.getTranslateY() == dest.getY()) {
        //			tf = new Timeframe(Duration.seconds(1), kvX);
        //		} else {
        //			SignalEvent<Double> kvY = new SignalEvent<>(node.translateYSignal(), dest.getY(), new SignalDoubleLine.Builder());
        //			tf = new Timeframe(Duration.seconds(2), kvX, kvY);
        //		}

        //		group.getChildren().add(path); // DEBUG

        return Timeframe(Duration.seconds((if (simple) 1 else 2).toDouble()), seTranslate)
    }

    fun adjustAreaToBounds(area: ElementRectangle, bounds: Bounds): Timeframe? {
        if (area.boundsInParent == bounds) {
            return null
        }

        val time: Double

        val lastX = area.xSignal().lastValue()
        val lastY = area.ySignal().lastValue()
        val lastWidth = area.widthSignal().lastValue()
        val lastHeight = area.heightSignal().lastValue()
        if ((lastX == bounds.minX || lastWidth == bounds.width) && (lastY == bounds.minY || lastHeight == bounds.height)) {
            time = 1.0
        } else {
            time = 2.0
        }

        val seX = SignalEvent(area.xSignal(), bounds.minX, SignalDoubleLine.Builder())
        val seY = SignalEvent(area.ySignal(), bounds.minY, SignalDoubleLine.Builder())
        val seWidth = SignalEvent(area.widthSignal(), bounds.width, SignalDoubleLine.Builder())
        val seHeight = SignalEvent(area.heightSignal(), bounds.height, SignalDoubleLine.Builder())

        return Timeframe(Duration.seconds(time), seX, seY, seWidth, seHeight)
    }

    fun changeColor(area: ElementRectangle, toColor: Color): Timeframe {
        val seColor = SignalEvent(area.fillSignal(), toColor, SignalInterpolatable.Builder())
        return Timeframe(Duration.seconds(1.0), seColor)
    }

    fun update(obj: ElementObject, newContent: String): Timeframe {
        val seContent = SignalEvent(obj.contentLine!!, newContent, SignalJump.Builder())
        return Timeframe(Duration.seconds(0.1), seContent)
    }

}

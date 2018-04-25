package de.fluxparticle.animation

import de.fluxparticle.animation.elementobject.ElementNode
import de.fluxparticle.animation.elementobject.ElementObject
import de.fluxparticle.animation.elementobject.ElementRectangle
import de.fluxparticle.animation.point.DynamicPoint2D
import de.fluxparticle.animation.signal.*
import de.fluxparticle.animation.util.Bounds
import de.fluxparticle.animation.util.Color

fun fadeIn(node: ElementNode): Timeframe {
    val seOpacity = SignalEvent(node.opacity, 1.0, SignalDoubleLine.Builder())
    return Timeframe(1.0, seOpacity)
}

fun show(node: ElementNode): Timeframe {
    val seOpacity = SignalEvent(node.opacity, 1.0, SignalDoubleLine.Builder())
    return Timeframe(0.0, seOpacity)
}

fun fadeOut(node: ElementNode): Timeframe {
    val seOpacity = SignalEvent(node.opacity, 0.0, SignalDoubleLine.Builder())
    return Timeframe(1.0, seOpacity)
}

fun hide(node: ElementNode): Timeframe {
    val seOpacity = SignalEvent(node.opacity, 0.0, SignalDoubleLine.Builder())
    return Timeframe(0.0, seOpacity)
}


fun move(node: ElementObject, dynDest: DynamicPoint2D, simple: Boolean): Timeframe {
    //		Timeframe tf;

    val seTranslate: SignalEvent<DynamicPoint2D>
    if (simple) {
        seTranslate = SignalEvent(node.translate, dynDest, SignalInterpolatable.Builder())
    } else {
        seTranslate = SignalEvent(node.translate, dynDest, SignalCurve.Builder())
    }
    //		if (node.getTranslateY() == dest.getY()) {
    //			tf = new Timeframe(1), kvX);
    //		} else {
    //			SignalEvent<Double> kvY = new SignalEvent<>(node.translatey, dest.getY(), new SignalDoubleLine.Builder());
    //			tf = new Timeframe(2), kvX, kvY);
    //		}

    //		group.getChildren().add(path); // DEBUG

    return Timeframe((if (simple) 1 else 2).toDouble(), seTranslate)
}

fun adjustAreaToBounds(area: ElementRectangle, bounds: Bounds): Timeframe? {
    if (area.boundsInParent == bounds) {
        return null
    }

    val time: Double

    val lastX = area.x.lastValue()
    val lastY = area.y.lastValue()
    val lastWidth = area.width.lastValue()
    val lastHeight = area.height.lastValue()
    if ((lastX == bounds.minX || lastWidth == bounds.width) && (lastY == bounds.minY || lastHeight == bounds.height)) {
        time = 1.0
    } else {
        time = 2.0
    }

    val seX = SignalEvent(area.x, bounds.minX, SignalDoubleLine.Builder())
    val seY = SignalEvent(area.y, bounds.minY, SignalDoubleLine.Builder())
    val seWidth = SignalEvent(area.width, bounds.width, SignalDoubleLine.Builder())
    val seHeight = SignalEvent(area.height, bounds.height, SignalDoubleLine.Builder())

    return Timeframe(time, seX, seY, seWidth, seHeight)
}

fun changeColor(area: ElementRectangle, toColor: Color): Timeframe {
    val seColor = SignalEvent(area.fill, toColor, SignalInterpolatable.Builder())
    return Timeframe(1.0, seColor)
}

fun update(obj: ElementObject, newContent: String): Timeframe {
    val seContent = SignalEvent(obj.contentLine!!, newContent, SignalJump.Builder())
    return Timeframe(0.1, seContent)
}

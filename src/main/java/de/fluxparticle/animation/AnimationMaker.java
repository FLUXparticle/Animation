package de.fluxparticle.animation;

import de.fluxparticle.animation.object.ElementNode;
import de.fluxparticle.animation.object.ElementObject;
import de.fluxparticle.animation.object.ElementRectangle;
import de.fluxparticle.animation.point.DynamicPoint2D;
import de.fluxparticle.animation.signal.*;
import javafx.geometry.Bounds;
import javafx.scene.paint.Color;
import javafx.util.Duration;

public class AnimationMaker {

	public static Timeframe fadeIn(ElementNode node) {
		SignalEvent<Double> seOpacity = new SignalEvent<>(node.opacitySignal(), 1.0, new SignalDoubleLine.Builder());
		return new Timeframe(Duration.seconds(1), seOpacity);
	}

	public static Timeframe show(ElementNode node) {
		SignalEvent<Double> seOpacity = new SignalEvent<>(node.opacitySignal(), 1.0, new SignalDoubleLine.Builder());
		return new Timeframe(Duration.seconds(0), seOpacity);
	}

	public static Timeframe fadeOut(ElementNode node) {
		SignalEvent<Double> seOpacity = new SignalEvent<>(node.opacitySignal(), 0.0, new SignalDoubleLine.Builder());
		return new Timeframe(Duration.seconds(1), seOpacity);
	}

	public static Timeframe hide(ElementNode node) {
		SignalEvent<Double> seOpacity = new SignalEvent<>(node.opacitySignal(), 0.0, new SignalDoubleLine.Builder());
		return new Timeframe(Duration.seconds(0), seOpacity);
	}


	public static Timeframe move(ElementObject node, DynamicPoint2D dynDest, boolean simple) {
//		Timeframe tf;

		SignalEvent<DynamicPoint2D> seTranslate;
        if (simple) {
            seTranslate = new SignalEvent<>(node.translateSignal(), dynDest, new SignalInterpolatable.Builder<>());
        } else {
            seTranslate = new SignalEvent<>(node.translateSignal(), dynDest, new SignalCurve.Builder());
        }
//		if (node.getTranslateY() == dest.getY()) {
//			tf = new Timeframe(Duration.seconds(1), kvX);
//		} else {
//			SignalEvent<Double> kvY = new SignalEvent<>(node.translateYSignal(), dest.getY(), new SignalDoubleLine.Builder());
//			tf = new Timeframe(Duration.seconds(2), kvX, kvY);
//		}

//		group.getChildren().add(path); // DEBUG

		return new Timeframe(Duration.seconds(simple ? 1 : 2), seTranslate);
	}

	public static Timeframe adjustAreaToBounds(ElementRectangle area, Bounds bounds) {
		if (area.getBoundsInParent().equals(bounds)) {
			return null;
		}

		double time;

		double lastX = area.xSignal().lastValue();
		double lastY = area.ySignal().lastValue();
		double lastWidth = area.widthSignal().lastValue();
		double lastHeight = area.heightSignal().lastValue();
		if ((lastX == bounds.getMinX() || lastWidth == bounds.getWidth()) && (lastY == bounds.getMinY() || lastHeight == bounds.getHeight() )) {
			time = 1.0;
		} else {
			time = 2.0;
		}

		SignalEvent<Double> seX = new SignalEvent<>(area.xSignal(), bounds.getMinX(), new SignalDoubleLine.Builder());
		SignalEvent<Double> seY = new SignalEvent<>(area.ySignal(), bounds.getMinY(), new SignalDoubleLine.Builder());
		SignalEvent<Double> seWidth = new SignalEvent<>(area.widthSignal(), bounds.getWidth(), new SignalDoubleLine.Builder());
		SignalEvent<Double> seHeight = new SignalEvent<>(area.heightSignal(), bounds.getHeight(), new SignalDoubleLine.Builder());

		return new Timeframe(Duration.seconds(time), seX, seY, seWidth, seHeight);
	}

	public static Timeframe changeColor(ElementRectangle area, Color toColor) {
		SignalEvent<Color> seColor = new SignalEvent<>(area.fillSignal(), toColor, new SignalInterpolatable.Builder<>());
		return new Timeframe(Duration.seconds(1.0), seColor);
	}

    public static Timeframe update(ElementObject obj, String newContent) {
        SignalEvent<String> seContent = new SignalEvent<>(obj.getContentLine(), newContent, new SignalJump.Builder<>());
        return new Timeframe(Duration.seconds(0.1), seContent);
    }

}

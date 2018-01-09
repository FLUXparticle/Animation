package de.fluxparticle.animation;

import de.fluxparticle.animation.object.ElementNode;
import de.fluxparticle.animation.object.ElementNodeVisitor;
import de.fluxparticle.animation.object.ElementObject;
import de.fluxparticle.animation.object.ElementRectangle;
import de.fluxparticle.animation.output.Log;
import de.fluxparticle.animation.point.DynamicPoint2D;
import javafx.beans.binding.DoubleExpression;
import javafx.geometry.Bounds;
import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.function.BiConsumer;

public class AnimationQueue {

    private static final Log LOG1 = Log.getLog(1);

	private static final Log LOG2 = Log.getLog(2);

	private static final Log LOG3 = Log.getLog(3);

	protected final Clip clip = new Clip();

    private final List<ElementNode> group = new LinkedList<>();

	protected AnimationCollection transitions;

	public void with(BiConsumer<AnimationQueue, DoubleExpression> consumer) {
        begin();
        consumer.accept(this, this.getTime());
        end();
    }

    public void begin() {
        LOG1.info("{");
		transitions = new AnimationCollection();
	}

	public void add(int priority, ElementNode obj) {
		LOG2.info("ADD " + obj);
		group.add(obj);
		transitions.add(priority, AnimationMaker.show(obj));
	}

	public void fadeIn(int priority, ElementNode obj) {
		LOG2.info("FADE_IN " + obj);
		LOG3.info("ADD " + obj);
		transitions.add(priority, AnimationMaker.fadeIn(obj));
		if (obj instanceof ElementRectangle) {
			group.add(0, obj);
		} else {
			group.add(obj);
		}
	}

	public void changeColor(int priority, ElementRectangle area, Color toColor) {
		LOG2.info("CHANGE_COLOR " + area);
		transitions.add(priority, AnimationMaker.changeColor(area, toColor));
	}

	public boolean adjustAreaToBounds(int priority, ElementRectangle area, Bounds bounds) {
		LOG2.info("CHANGE_AREA " + area);
		Timeframe timeframe = AnimationMaker.adjustAreaToBounds(area, bounds);
		if (timeframe != null) {
			transitions.add(priority, timeframe);
            double lastY = area.ySignal().lastValue();
            return lastY != bounds.getMinY();
		}

        return false;
	}

	public void move(int priority, ElementObject obj, DynamicPoint2D dest, boolean copy, final ElementObject oldObject, boolean simple) {
		LOG2.info("MOVE " + obj);
		if (copy) {
			LOG3.info("ADD (COPY) " + obj);
			group.add(obj);
			transitions.add(priority, AnimationMaker.show(obj));
		}

		transitions.add(priority, AnimationMaker.move(obj, dest, simple));
		if (oldObject != null) {
			transitions.add(priority + 1, AnimationMaker.hide(oldObject));
		}
	}

	public void fadeOut(int priority, ElementNode obj) {
		LOG2.info("FADE_OUT " + obj);
		transitions.add(priority, AnimationMaker.fadeOut(obj));
	}

	public void update(int priority, ElementObject obj, String newContent) {
	    transitions.add(priority, AnimationMaker.update(obj, newContent));
    }

    public void end() {
        transitions.appendToClip(clip);
//        LOG2.info("CLIP " + clip.getCntSequences());
        LOG1.info("}");
    }

    public void endRaw() {
        transitions.appendToClipRaw(clip);
//        LOG2.info("CLIP " + clip.getCntSequences());
        LOG1.info("}");
    }

	public Clip getClip() {
		return clip;
	}

	public List<ElementNode> getGroup() {
		return Collections.unmodifiableList(group);
	}

	public <T> List<T> getVisitedGroup(ElementNodeVisitor<T> visitor) {
		List<T> result = new ArrayList<>(group.size());

		for (ElementNode elementNode : group) {
			T node = elementNode.accept(visitor);
			result.add(node);
		}

		return result;

	}

	public DoubleExpression getTime() {
		return clip.timeProperty();
	}

}

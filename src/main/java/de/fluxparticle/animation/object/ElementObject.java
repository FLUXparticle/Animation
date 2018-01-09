package de.fluxparticle.animation.object;

import de.fluxparticle.animation.binding.BindingFunction;
import de.fluxparticle.animation.path.PathMaker;
import de.fluxparticle.animation.point.DynamicPoint2D;
import de.fluxparticle.animation.signal.Signal;
import javafx.beans.binding.DoubleExpression;
import javafx.geometry.Point2D;

import static javafx.beans.binding.DoubleExpression.doubleExpression;

public class ElementObject extends ElementNode {

	public static final int MARGIN = 10;

	public static final int HEIGHT = 40;

	public static final int OPERAND_WIDTH = 60;

	public static final int SYMBOL_WIDTH = HEIGHT;

	public static final int FUNCTION_WIDTH = ElementObject.SYMBOL_WIDTH + 2 * MARGIN + 2 * ElementObject.OPERAND_WIDTH;

	public static final int OBJECT_WIDTH = 2 * OPERAND_WIDTH;

	private final boolean useRect;
	private final boolean center;
	private final int width;
    private final String headLine;
	private final Signal<String> contentLine;

	private Signal<DynamicPoint2D> translate;
	public void setTranslate(DynamicPoint2D translate) {
		this.translate = new Signal<>(time, this, "translate", translate);
	}
	public Signal<DynamicPoint2D> translateSignal() {
		return translate;
	}
	public DoubleExpression translateXExpression() {
		return doubleExpression(new BindingFunction<>(translate, DynamicPoint2D::getX));
	}
	public DoubleExpression translateYExpression() {
		return doubleExpression(new BindingFunction<>(translate, DynamicPoint2D::getY));
	}

	private final Point2D circle;

	public ElementObject(DoubleExpression time, boolean useRect, boolean center, int width, String... lines) {
		super(time);

		this.useRect = useRect;
		this.center = center;
		this.width = width;

        if (lines[0].contains(".")) {
            this.headLine = lines[0].substring(lines[0].lastIndexOf('.')+1);
        } else {
            this.headLine = lines[0];
        }

        if (lines.length > 1) {
            if (lines[1] != null) {
                this.contentLine = new Signal<>(time, this, "contentLine", lines[1]);
                this.circle = null;
            } else {
                this.contentLine = null;
                double midY = ElementObject.HEIGHT * (2) / (lines.length + 1) - ElementObject.HEIGHT / 2;
                this.circle = new Point2D(0.0, midY);
            }
        } else {
            this.contentLine = null;
            this.circle = null;
        }
	}

    public ElementObject copy() {
        ElementObject copy;
        if (contentLine != null) {
            copy = new ElementObject(time, useRect, center, width, headLine, contentLine.lastValue());
        } else if (circle != null) {
            copy = new ElementObject(time, useRect, center, width, headLine, null);
        } else {
            copy = new ElementObject(time, useRect, center, width, headLine);
        }
        copy.setTranslate(translate.lastValue());

		return copy;
	}

	public ElementPath mkDynamicPath(ElementObject heapObj) {
		DynamicPoint2D origin = new DynamicPoint2D(translateXExpression().add(circle.getX()), translateYExpression().add(circle.getY()));
		DynamicPoint2D dest = new DynamicPoint2D(heapObj.translateXExpression().subtract(OBJECT_WIDTH / 2 + MARGIN + PathMaker.ARROW_SIZE), heapObj.translateYExpression());

		return new ElementPath(time, origin, dest);
	}

	public Point2D getCircle() {
		return circle;
	}

	@Override
	public <R> R accept(ElementNodeVisitor<R> visitor) {
		return visitor.visitObject(this);
	}

	public boolean isUseRect() {
		return useRect;
	}

	public boolean isCenter() {
		return center;
	}

	public int getWidth() {
		return width;
	}

    public String getHeadLine() {
        return headLine;
    }

    public Signal<String> getContentLine() {
        return contentLine;
    }

}

package de.fluxparticle.animation;

import de.fluxparticle.animation.object.*;
import de.fluxparticle.animation.path.PathMaker;
import de.fluxparticle.animation.signal.Signal;
import javafx.beans.binding.DoubleExpression;
import javafx.collections.ObservableList;
import javafx.geometry.Bounds;
import javafx.scene.CacheHint;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Path;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class NodeCreator implements ElementNodeVisitor<Node> {

	@Override
	public Node visitPath(ElementPath element) {
		Path path = PathMaker.mkDynamicCurveEastEast(element.getOrigin(), element.getDest());
		PathMaker.addDynamicArrow(path, element.getDest());

		return bindNode(element, path);
	}

	@Override
	public Node visitRectangle(ElementRectangle rectangle) {
		return bindRectangle(rectangle, new Rectangle());
	}

	@Override
	public Node visitObject(ElementObject object) {
		Group group = new Group();

		group.translateXProperty().bind(object.translateXExpression());
		group.translateYProperty().bind(object.translateYExpression());

		ObservableList<Node> children = group.getChildren();

		if (object.isUseRect()) {
			Rectangle rectOuter = new Rectangle(-ElementObject.OPERAND_WIDTH / 2, -ElementObject.HEIGHT / 2, ElementObject.OPERAND_WIDTH, ElementObject.HEIGHT);
			Rectangle rectInner = new Rectangle(-ElementObject.OPERAND_WIDTH / 2 + 1, -ElementObject.HEIGHT / 2 + 1, ElementObject.OPERAND_WIDTH - 2, ElementObject.HEIGHT - 2);

			rectOuter.setFill(Color.BLACK);
			rectInner.setFill(Color.WHITE);

			children.addAll(rectOuter, rectInner);
		}

		final double maxContentWidth = object.getWidth() - ElementObject.OPERAND_WIDTH / 20;

        Signal<String> contentLine = object.getContentLine();
        int cntLines = (contentLine != null || object.getCircle() != null) ? 2 : 1;
		boolean center = object.isCenter();
		{
			final double midY = ElementObject.HEIGHT / (cntLines + 1) - ElementObject.HEIGHT / 2;

            String strText = object.getHeadLine();

            if (!strText.isEmpty()) {
                Text text = new Text(strText);
                fitText(text, maxContentWidth);
                adjustNode(text, center, midY, maxContentWidth);
                children.add(text);
            }
        }

        {
            final double midY = ElementObject.HEIGHT * 2 / (cntLines + 1) - ElementObject.HEIGHT / 2;

            Node node = null;
            if (object.getCircle() != null) {
                double radius = Font.getDefault().getSize() / 4.0;
                node = new Circle(radius, Color.BLACK);
            }

            if (contentLine != null) {
                String value = contentLine.getValue();
                Text text = new Text(value);
//                Tooltip.install(text, new Tooltip(value));

                fitText(text, maxContentWidth);

                contentLine.addListener((observable, oldValue, newValue) -> {
                    Text localText = new Text(newValue);

                    fitText(localText, maxContentWidth);

                    text.setText(newValue);
                    text.setFont(localText.getFont());
//                    Tooltip.install(text, new Tooltip(newValue));

                    adjustNode(text, center, midY, maxContentWidth);
                });

                node = text;
            }

            if (node != null) {
                adjustNode(node, center, midY, maxContentWidth);
                children.add(node);
            }
        }

        group.setCache(true);
		group.setCacheHint(CacheHint.SPEED);

		return bindNode(object, group);
	}

	private Rectangle bindRectangle(ElementRectangle element, Rectangle rectangle) {
		rectangle.xProperty().bind(element.xSignal());
		rectangle.yProperty().bind(element.ySignal());
		rectangle.widthProperty().bind(element.widthSignal());
		rectangle.heightProperty().bind(element.heightSignal());
		rectangle.fillProperty().bind(element.fillSignal());
		return bindNode(element, rectangle);
	}

	private <T extends Node> T bindNode(ElementNode element, T node) {
		node.opacityProperty().bind(element.opacitySignal());
		node.visibleProperty().bind(DoubleExpression.doubleExpression(element.opacitySignal()).greaterThan(0.0));
		return node;
	}

	private static void fitText(Text text, double maxWidth) {
		Bounds boundsText = text.getBoundsInLocal();
		if (boundsText.getWidth() > maxWidth) {
			double size = text.getFont().getSize();
			do {
				size /= 2;
				text.setFont(new Font(size));
				boundsText = text.getBoundsInLocal();
			} while (boundsText.getWidth() > maxWidth);

			double lo = size;
			double hi = size * 2;
			while (hi - lo > 1) {
				size = (lo + hi) / 2;
				text.setFont(new Font(size));
				boundsText = text.getBoundsInLocal();
				if (boundsText.getWidth() > maxWidth) {
					hi = size;
				} else {
					lo = size;
				}
			}
			size = lo;
			text.setFont(new Font(size));
		}
	}

	private static void adjustNode(Node node, boolean centerX, double midY, double maxContentWidth) {
		Bounds bounds = node.getBoundsInLocal();

		if (centerX) {
			node.setTranslateX(-(bounds.getMinX() + bounds.getMaxX()) / 2);
		} else {
			node.setTranslateX(-(bounds.getMinX() + maxContentWidth / 2));
		}

		node.setTranslateY(midY - (bounds.getMinY() + bounds.getMaxY()) / 2);
	}

}

package de.fluxparticle.animation.bubblesort;

import de.fluxparticle.animation.AnimationQueue;
import de.fluxparticle.animation.object.ElementObject;
import de.fluxparticle.animation.object.ElementRectangle;
import de.fluxparticle.animation.point.DynamicPoint2D;
import javafx.beans.binding.DoubleExpression;
import javafx.geometry.BoundingBox;
import javafx.geometry.Bounds;
import javafx.scene.paint.Color;

/**
 * Created by sreinck on 09.02.16.
 */
public class BubbleSortBox {

    private static final int WIDTH = ElementObject.OPERAND_WIDTH;

    private static final int HEIGHT = ElementObject.HEIGHT;

    private static final int SPACING = 10;

    private final int[] array = { 13, 4, 3, 21, 1 };

    private final ElementObject[] elementObjects = new ElementObject[array.length];

    private final AnimationQueue animationQueue;

    private ElementRectangle rectangleCompare;

    private ElementRectangle rectangleReady;

    public BubbleSortBox(AnimationQueue animationQueue) {
        this.animationQueue = animationQueue;

        animationQueue.with((aq, t) -> {
            for (int i = 0; i < array.length; i++) {
                int value = array[i];

                elementObjects[i] = new ElementObject(t, true, true, WIDTH, Integer.toString(value));

                DynamicPoint2D translate = center(i);
                elementObjects[i].setTranslate(translate);

                aq.fadeIn(0, elementObjects[i]);
            }
        });
    }

    public Bounds getBounds() {
        return new BoundingBox(0, 0, SPACING + array.length * (WIDTH + SPACING), 2 * SPACING + HEIGHT);
    }

    public int size() {
        return array.length;
    }

    public boolean inOrder(int index) {
        boolean result = array[index] < array[index + 1];

        animationQueue.with((aq, t) -> {

            Bounds bs = surround(index, 2);

            Color colorCompare = Color.BLUE;
            rectangleCompare = createOrMove(aq, t, bs, colorCompare, rectangleCompare);

            Color c = result ? Color.GREEN : Color.RED;
            aq.changeColor(1, rectangleCompare, c);
        });

        return result;
    }

    public void swap(int index) {
        int next = index + 1;
        {
            int tmp = array[index];
            array[index] = array[next];
            array[next] = tmp;
        }

        animationQueue.with((aq, t) -> {
            aq.move(0, elementObjects[index], center(next), false, null, true);
            aq.move(0, elementObjects[next], center(index), false, null, true);

            ElementObject tmp = elementObjects[index];
            elementObjects[index] = elementObjects[next];
            elementObjects[next] = tmp;
        });
    }

    public void ready(int index) {
        int range = array.length - index;

        animationQueue.with((aq, t) -> {
            if (rectangleCompare != null) {
                aq.fadeOut(-1, rectangleCompare);
                rectangleCompare = null;
            }

            Bounds bs = surround(index, range);

            Color colorReady = Color.GREEN;
            rectangleReady = createOrMove(aq, t, bs, colorReady, rectangleReady);
        });
    }

    private static ElementRectangle createOrMove(AnimationQueue aq, DoubleExpression t, Bounds bs, Color colorReady, ElementRectangle rectangle) {
        if (rectangle == null) {
            rectangle = new ElementRectangle(t, bs, colorReady);
            aq.fadeIn(0, rectangle);
        } else {
            aq.adjustAreaToBounds(0, rectangle, bs);
            aq.changeColor(0, rectangle, colorReady);
        }

        return rectangle;
    }

    private static DynamicPoint2D center(int index) {
        return new DynamicPoint2D(SPACING + WIDTH / 2 + index * (WIDTH + SPACING), SPACING + HEIGHT / 2);
    }

    private static BoundingBox surround(int index, int range) {
        return new BoundingBox(SPACING / 2 + index * (WIDTH + SPACING), SPACING / 2, range * (WIDTH + SPACING), HEIGHT + SPACING);
    }

}

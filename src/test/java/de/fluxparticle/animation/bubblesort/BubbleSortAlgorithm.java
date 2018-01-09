package de.fluxparticle.animation.bubblesort;

/**
 * Created by sreinck on 09.02.16.
 */
public class BubbleSortAlgorithm implements Runnable {

    private final BubbleSortBox box;

    public BubbleSortAlgorithm(BubbleSortBox box) {
        this.box = box;
    }

    @Override
    public void run() {
        for (int top = box.size() - 1; top >= 0; top--) {
            for (int i = 0; i < top; i++) {
                if (!box.inOrder(i)) {
                    box.swap(i);
                }
            }
            box.ready(top);
        }
    }

}

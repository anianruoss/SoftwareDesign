package jdraw.figures;

import jdraw.framework.Figure;

import java.awt.*;

public class AnimationDecorator extends AbstractDecorator {
    private AnimationThread animationThread;

    public AnimationDecorator(Figure figure) {
        super(figure);
        animationThread = new AnimationThread(this);
        animationThread.start();
    }

    public Figure unwrap() {
        animationThread.stopThread();
        return getInner();
    }

    private class AnimationThread extends Thread {
        private Figure figure;
        private volatile boolean stop = false;
        private int dx = 1, dy = 1;

        AnimationThread(Figure figure) {
            this.figure = figure;
            this.setDaemon(true);
        }

        public void stopThread() {
            stop = true;
        }

        @Override
        public void run() {
            while (!stop) {
                Rectangle bounds = figure.getBounds();

                if (bounds.x + bounds.width > 600 && dx > 0) {
                    dx = -dx;
                }
                if (bounds.x <= 0 && dx < 0) {
                    dx = -dx;
                }
                if (bounds.y + bounds.height > 400 && dy > 0) {
                    dy = -dy;
                }
                if (bounds.y <= 0 && dy < 0) {
                    dy = -dy;
                }

                figure.move(dx, dy);

                try {
                    Thread.sleep(50);
                } catch (InterruptedException e) {
                    stop = true;
                }
            }
        }
    }
}

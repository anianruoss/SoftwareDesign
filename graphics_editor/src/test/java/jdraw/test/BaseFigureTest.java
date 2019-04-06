package jdraw.test;

import jdraw.framework.Figure;
import jdraw.framework.FigureEvent;
import jdraw.framework.FigureListener;
import org.junit.Test;

import java.awt.*;

import static junit.framework.TestCase.assertEquals;

abstract public class BaseFigureTest {
    Figure f;
    Figure g;
    int cnt;

    @Test
    public void testNotification1() {
        FigureListener l = new TestListener();
        f.addFigureListener(l);
        int c = cnt;
        f.move(1, 1);
        assertEquals(
                "figureChanged must be called on a registered listener",
                cnt,
                c + 1
        );
        f.removeFigureListener(l);
        f.move(2, 2);
        assertEquals(
                "figureChanged must not be called on disconnected listener",
                cnt,
                c + 1
        );
    }

    @Test
    public void testNotification2() {
        f.addFigureListener(new TestListener());
        int c = cnt;
        f.move(0, 0);
        assertEquals(
                "Listener was called even if state does not change", cnt, c
        );
    }

    @Test
    final public void testMultiListeners() {
        f.addFigureListener(new TestListener());
        f.addFigureListener(new TestListener());
        int c = cnt;
        f.move(3, 3);
        assertEquals("multiple listeners are not supported", cnt, c + 2);
    }

    @Test
    final public void testRemoveListener() {
        f.addFigureListener(new TestListener());
        f.addFigureListener(new RemoveListener(f));
        f.addFigureListener(new TestListener());
        f.addFigureListener(new TestListener());
        f.move(4, 4);
    }

    @Test
    final public void testCycle() {
        Figure f1 = f;
        Figure f2 = g;
        f1.addFigureListener(new UpdateListener(f2));
        f2.addFigureListener(new UpdateListener(f1));

        f2.move(5, 5);
        assertEquals("Position of the two figures must be equal", f1.getBounds().getLocation(), f2.getBounds().getLocation());
        assertEquals("Figures must both be at position x=15", 15, f1.getBounds().x);
        assertEquals("Figures must both be at position y=15", 15, f1.getBounds().y);

        f1.move(5, 5);
        assertEquals("Position of the two figures must be equal", f1.getBounds().getLocation(), f2.getBounds().getLocation());
        assertEquals("Figures must both be at position x=20", 20, f1.getBounds().x);
        assertEquals("Figures must both be at position y=20", 20, f1.getBounds().y);
    }

    class TestListener implements FigureListener {
        @Override
        public void figureChanged(FigureEvent e) {
            assertEquals(e.getSource(), f);
            cnt++;
        }
    }

    class RemoveListener implements FigureListener {
        private final Figure f;

        RemoveListener(Figure f) {
            this.f = f;
        }

        @Override
        public void figureChanged(FigureEvent e) {
            f.removeFigureListener(this);
        }
    }

    class UpdateListener implements FigureListener {
        private final Figure f;

        UpdateListener(Figure f) {
            this.f = f;
        }

        @Override
        public void figureChanged(FigureEvent e) {
            Point p1 = e.getFigure().getBounds().getLocation();
            Point p2 = f.getBounds().getLocation();
            f.move(p1.x - p2.x, p1.y - p2.y);
        }
    }
}

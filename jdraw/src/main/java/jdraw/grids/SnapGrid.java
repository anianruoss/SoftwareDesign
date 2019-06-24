package jdraw.grids;

import jdraw.framework.*;

import java.awt.*;

public class SnapGrid implements DrawGrid {
    private static final int SNAP = 15;
    private DrawView view;
    private Figure figure;
    private Point snappedPoint;
    private boolean snapped = false;

    public SnapGrid(DrawView view) {
        this.view = view;
        DrawModelListener listener = e -> {
            if (e.getType() == DrawModelEvent.Type.FIGURE_ADDED) {
                figure = e.getFigure();
            }
        };
        view.getModel().addModelChangeListener(listener);
    }

    @Override
    public Point constrainPoint(Point p) {
        for (Figure f : view.getModel().getFigures()) {
            if (!view.getSelection().contains(f) && f != figure) {
                for (FigureHandle h : f.getHandles()) {
                    Point q = h.getLocation();
                    if (isClose(p, q)) {
                        return q;
                    }
                }
            }
        }

        if (snapped) {
            if (isClose(snappedPoint, p)) {
                return snappedPoint;
            } else {
                snapped = false;
                return p;
            }
        }

        for (Figure f : view.getSelection()) {
            for (FigureHandle h : f.getHandles()) {
                FigureHandle i = findNearbyHandle(h);

                if (i != null) {
                    snapped = true;
                    int dx = i.getLocation().x - h.getLocation().x;
                    int dy = i.getLocation().y - h.getLocation().y;

                    return snappedPoint = new Point(p.x + dx, p.y + dy);
                }
            }
        }

        return p;
    }

    private boolean isClose(Point p, Point q) {
        return p.distance(q) < SNAP;
    }

    private FigureHandle findNearbyHandle(FigureHandle h) {
        for (Figure f : view.getModel().getFigures()) {
            if (!view.getSelection().contains(f)) {
                for (FigureHandle i : f.getHandles()) {
                    if (isClose(h.getLocation(), i.getLocation())) {
                        return i;
                    }
                }
            }
        }
        return null;
    }

    @Override
    public int getStepX(boolean right) {
        return 0;
    }

    @Override
    public int getStepY(boolean down) {
        return 0;
    }

    @Override
    public void activate() {

    }

    @Override
    public void deactivate() {

    }

    @Override
    public void mouseDown() {

    }

    @Override
    public void mouseUp() {

    }
}

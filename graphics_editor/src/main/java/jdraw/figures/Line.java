package jdraw.figures;

import jdraw.figures.handles.Handle;
import jdraw.figures.handles.NorthWestHandleState;
import jdraw.figures.handles.SouthEastHandleState;
import jdraw.framework.Figure;

import java.awt.*;
import java.awt.geom.Line2D;
import java.util.ArrayList;

public class Line extends AbstractFigure implements Figure {
    private final Line2D.Double line;
    private static final int TOL = 6;

    public Line(Point start, Point end) {
        line = new Line2D.Double(start, end);
        initializeHandles();
    }

    public Line(Point point) {
        line = new Line2D.Double(point, point);
        initializeHandles();
    }

    @Override
    public void initializeHandles() {
        handles = new ArrayList<>(2);

        handles.add(new Handle(new NorthWestHandleState(this)));
        handles.add(new Handle(new SouthEastHandleState(this)));
    }

    @Override
    public void draw(Graphics g) {
        int x1 = ((int) line.x1);
        int y1 = ((int) line.y1);
        int x2 = ((int) line.x2);
        int y2 = ((int) line.y2);

        g.setColor(Color.BLACK);
        g.drawLine(x1, y1, x2, y2);
    }

    @Override
    public void move(int dx, int dy) {
        if (dx != 0 || dy != 0) {
            line.setLine(
                    line.x1 + dx, line.y1 + dy, line.x2 + dx, line.y2 + dy
            );
            propagateFigureEvent();
        }
    }

    @Override
    public boolean contains(int x, int y) {
        return line.ptSegDist(x, y) <= TOL;
    }

    @Override
    public void setBounds(Point origin, Point corner) {
        Rectangle original = line.getBounds();
        line.setLine(origin, corner);
        if (!original.equals(line.getBounds())) {
            propagateFigureEvent();
        }
    }

    @Override
    public Rectangle getBounds() {
        return line.getBounds();
    }

}

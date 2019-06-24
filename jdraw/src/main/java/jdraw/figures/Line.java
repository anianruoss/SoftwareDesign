package jdraw.figures;

import jdraw.figures.handles.Handle;
import jdraw.figures.handles.LineHandleState;
import jdraw.framework.Figure;

import java.awt.Rectangle;
import java.awt.*;
import java.awt.geom.Line2D;
import java.util.ArrayList;

public class Line extends AbstractFigure {
    private Line2D.Double line;
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

        handles.add(
                new Handle(
                        new LineHandleState(this, LineHandleState.Type.FIRST)
                )
        );
        handles.add(
                new Handle(
                        new LineHandleState(this, LineHandleState.Type.SECOND)
                )
        );
    }

    @Override
    public void draw(Graphics g) {
        g.setColor(Color.BLACK);
        g.drawLine((int) line.x1, (int) line.y1, (int) line.x2, (int) line.y2);
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
        Line2D.Double original = (Line2D.Double) line.clone();
        line.setLine(origin, corner);
        if (!original.equals(line)) {
            propagateFigureEvent();
        }
    }

    @Override
    public Rectangle getBounds() {
        return line.getBounds();
    }

    public Line2D getLine() {
        return (Line2D) line.clone();
    }

    @Override
    public Figure clone() {
        Line clone = (Line) super.clone();
        clone.line = (Line2D.Double) line.clone();
        clone.initializeHandles();

        return clone;
    }

}

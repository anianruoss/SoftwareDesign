package jdraw.figures.handles;

import jdraw.figures.Line;
import jdraw.framework.DrawView;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.geom.Line2D;

public class LineHandleState extends AbstractHandleState {
    private final Type type;

    public LineHandleState(Line owner, Type type) {
        super(owner);
        this.type = type;
    }

    @Override
    public Line getOwner() {
        return (Line) super.getOwner();
    }

    @Override
    public Point getLocation() {
        Line2D line = getOwner().getLine();

        if (type.equals(Type.FIRST)) {
            return new Point((int) line.getX1(), (int) line.getY1());
        } else {
            return new Point((int) line.getX2(), (int) line.getY2());
        }
    }

    @Override
    public Cursor getCursor() {
        return Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR);
    }

    @Override
    public void dragInteraction(int x, int y, MouseEvent e, DrawView v) {
        Line2D line = getOwner().getLine();

        if (type.equals(Type.FIRST)) {
            getOwner().setBounds(
                    new Point(x, y),
                    new Point((int) line.getX2(), (int) line.getY2())
            );
        } else {
            getOwner().setBounds(
                    new Point((int) line.getX1(), (int) line.getY1()),
                    new Point(x, y)
            );
        }
    }

    @Override
    public HandleState swapHorizontal() {
        return this;
    }

    @Override
    public HandleState swapVertical() {
        return this;
    }

    public enum Type {
        FIRST,
        SECOND
    }
}

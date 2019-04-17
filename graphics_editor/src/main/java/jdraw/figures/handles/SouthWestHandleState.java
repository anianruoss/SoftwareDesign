package jdraw.figures.handles;

import jdraw.framework.DrawView;
import jdraw.framework.Figure;

import java.awt.*;
import java.awt.event.MouseEvent;

public class SouthWestHandleState extends AbstractHandleState {

    public SouthWestHandleState(Figure owner) {
        super(owner);
    }

    @Override
    public Point getLocation() {
        Rectangle bounds = getOwner().getBounds();
        return new Point(bounds.x, bounds.y + bounds.height);
    }

    @Override
    public Cursor getCursor() {
        return Cursor.getPredefinedCursor(Cursor.SW_RESIZE_CURSOR);
    }

    @Override
    public void dragInteraction(int x, int y, MouseEvent e, DrawView v) {
        Rectangle bounds = getOwner().getBounds();
        getOwner().setBounds(
                new Point(x, bounds.y), new Point(bounds.x + bounds.width, y)
        );

        if (bounds.x + bounds.width < x) {
            getOwner().swapHorizontal();
        }

        if (y < bounds.y) {
            getOwner().swapVertical();
        }
    }

    @Override
    public HandleState swapHorizontal() {
        return new SouthEastHandleState(getOwner());
    }

    @Override
    public HandleState swapVertical() {
        return new NorthWestHandleState(getOwner());
    }

}

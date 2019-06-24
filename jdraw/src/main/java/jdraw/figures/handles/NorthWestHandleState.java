package jdraw.figures.handles;

import jdraw.framework.DrawView;
import jdraw.framework.Figure;

import java.awt.*;
import java.awt.event.MouseEvent;

public class NorthWestHandleState extends AbstractHandleState {

    public NorthWestHandleState(Figure owner) {
        super(owner);
    }

    @Override
    public Point getLocation() {
        return getOwner().getBounds().getLocation();
    }

    @Override
    public Cursor getCursor() {
        return Cursor.getPredefinedCursor(Cursor.NW_RESIZE_CURSOR);
    }

    @Override
    public void dragInteraction(int x, int y, MouseEvent e, DrawView v) {
        Rectangle bounds = getOwner().getBounds();
        getOwner().setBounds(
                new Point(x, y),
                new Point(bounds.x + bounds.width, bounds.y + bounds.height)
        );

        if (bounds.x + bounds.width < x) {
            getOwner().swapHorizontal();
        }

        if (bounds.y + bounds.height < y) {
            getOwner().swapVertical();
        }
    }

    @Override
    public HandleState swapHorizontal() {
        return new NorthEastHandleState(getOwner());
    }

    @Override
    public HandleState swapVertical() {
        return new SouthWestHandleState(getOwner());
    }
}

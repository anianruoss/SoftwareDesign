package jdraw.figures.handles;

import jdraw.framework.DrawView;
import jdraw.framework.Figure;

import java.awt.*;
import java.awt.event.MouseEvent;

public class SouthHandleState extends AbstractHandleState {

    public SouthHandleState(Figure owner) {
        super(owner);
    }

    @Override
    public Point getLocation() {
        Rectangle bounds = getOwner().getBounds();
        return new Point(bounds.x + bounds.width / 2, bounds.y + bounds.height);
    }

    @Override
    public Cursor getCursor() {
        return Cursor.getPredefinedCursor(Cursor.S_RESIZE_CURSOR);
    }

    @Override
    public void dragInteraction(int x, int y, MouseEvent e, DrawView v) {
        Rectangle bounds = getOwner().getBounds();
        getOwner().setBounds(
                bounds.getLocation(), new Point(bounds.x + bounds.width, y)
        );

        if (y < bounds.y) {
            getOwner().swapVertical();
        }
    }

    @Override
    public HandleState swapHorizontal() {
        return this;
    }

    @Override
    public HandleState swapVertical() {
        return new NorthHandleState(getOwner());
    }
}

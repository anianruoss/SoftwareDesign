package jdraw.figures.handles;

import jdraw.framework.DrawView;
import jdraw.framework.Figure;

import java.awt.*;
import java.awt.event.MouseEvent;

public class EastHandleState extends AbstractHandleState {

    public EastHandleState(Figure owner) {
        super(owner);
    }

    @Override
    public Point getLocation() {
        Rectangle bounds = getOwner().getBounds();
        return new Point(bounds.x + bounds.width, bounds.y + bounds.height / 2);
    }

    @Override
    public Cursor getCursor() {
        return Cursor.getPredefinedCursor(Cursor.E_RESIZE_CURSOR);
    }

    @Override
    public void dragInteraction(int x, int y, MouseEvent e, DrawView v) {
        Rectangle bounds = getOwner().getBounds();
        getOwner().setBounds(
                bounds.getLocation(), new Point(x, bounds.y + bounds.height)
        );

        if (x < bounds.x) {
            getOwner().swapHorizontal();
        }
    }

    @Override
    public HandleState swapHorizontal() {
        return new WestHandleState(getOwner());
    }

    @Override
    public HandleState swapVertical() {
        return this;
    }
}

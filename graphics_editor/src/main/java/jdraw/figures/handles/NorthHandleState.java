package jdraw.figures.handles;

import jdraw.framework.DrawView;
import jdraw.framework.Figure;

import java.awt.*;
import java.awt.event.MouseEvent;

public class NorthHandleState extends AbstractHandleState {

    public NorthHandleState(Figure owner) {
        super(owner);
    }

    @Override
    public Point getLocation() {
        Rectangle bounds = getOwner().getBounds();
        return new Point(bounds.x + bounds.width / 2, bounds.y);
    }

    @Override
    public Cursor getCursor() {
        return Cursor.getPredefinedCursor(Cursor.N_RESIZE_CURSOR);
    }

    @Override
    public void dragInteraction(int x, int y, MouseEvent e, DrawView v) {
        Rectangle bounds = getOwner().getBounds();
        getOwner().setBounds(
                new Point(bounds.x, y),
                new Point(bounds.x + bounds.width, bounds.y + bounds.height)
        );

        if (bounds.y + bounds.height < y) {
            getOwner().swapVertical();
        }
    }

    @Override
    public HandleState swapHorizontal() {
        return this;
    }

    @Override
    public HandleState swapVertical() {
        return new SouthHandleState(getOwner());
    }

}

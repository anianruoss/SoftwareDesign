package jdraw.figures.handles;

import jdraw.framework.DrawView;
import jdraw.framework.Figure;

import java.awt.*;
import java.awt.event.MouseEvent;

public class NorthEastHandleState extends AbstractHandleState {

    public NorthEastHandleState(Figure owner) {
        super(owner);
    }

    @Override
    public Point getLocation() {
        Rectangle bounds = getOwner().getBounds();
        return new Point(bounds.x + bounds.width, bounds.y);
    }

    @Override
    public Cursor getCursor() {
        return Cursor.getPredefinedCursor(Cursor.NE_RESIZE_CURSOR);
    }

    @Override
    public void dragInteraction(int x, int y, MouseEvent e, DrawView v) {
        Rectangle bounds = getOwner().getBounds();
        getOwner().setBounds(
                new Point(bounds.x, y), new Point(x, bounds.y + bounds.height)
        );

        if (x < bounds.x) {
            getOwner().swapHorizontal();
        }

        if (bounds.y + bounds.height < y) {
            getOwner().swapVertical();
        }
    }

}

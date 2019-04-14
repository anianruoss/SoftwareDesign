package jdraw.figures.handles;

import jdraw.framework.DrawView;
import jdraw.framework.Figure;

import java.awt.*;
import java.awt.event.MouseEvent;

public class SouthEastHandleState extends AbstractHandleState {

    public SouthEastHandleState(Figure owner) {
        super(owner);
    }

    @Override
    public Point getLocation() {
        Rectangle bounds = getOwner().getBounds();
        return new Point(bounds.x + bounds.width, bounds.y + bounds.height);
    }

    @Override
    public Cursor getCursor() {
        return Cursor.getPredefinedCursor(Cursor.SE_RESIZE_CURSOR);
    }

    @Override
    public void dragInteraction(int x, int y, MouseEvent e, DrawView v) {
        Rectangle bounds = getOwner().getBounds();
        getOwner().setBounds(bounds.getLocation(), new Point(x, y));

        if (x < bounds.x) {
            getOwner().swapHorizontal();
        }

        if (y < bounds.y) {
            getOwner().swapVertical();
        }
    }

}

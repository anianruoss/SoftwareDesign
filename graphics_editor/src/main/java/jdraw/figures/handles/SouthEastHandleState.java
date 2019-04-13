package jdraw.figures.handles;

import jdraw.framework.DrawView;

import java.awt.*;
import java.awt.event.MouseEvent;

public class SouthEastHandleState extends AbstractHandleState {

    public SouthEastHandleState(Handle handle) {
        super(handle);
    }

    @Override
    public Point getLocation() {
        Rectangle bounds = handle.getOwner().getBounds();
        return new Point(bounds.x + bounds.width, bounds.y + bounds.height);
    }

    @Override
    public Cursor getCursor() {
        return Cursor.getPredefinedCursor(Cursor.SE_RESIZE_CURSOR);
    }

    @Override
    public void dragInteraction(int x, int y, MouseEvent e, DrawView v) {
        Rectangle bounds = handle.getOwner().getBounds();
        handle.getOwner().setBounds(bounds.getLocation(), new Point(x, y));

        if (x < bounds.x) {
            handle.setState(new SouthWestHandleState(handle));
        }

        if (y < bounds.y) {
            handle.setState(new NorthEastHandleState(handle));
        }
    }

}

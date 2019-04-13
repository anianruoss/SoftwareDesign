package jdraw.figures.handles;

import jdraw.framework.DrawView;

import java.awt.*;
import java.awt.event.MouseEvent;

public class NorthWestHandleState extends AbstractHandleState {

    public NorthWestHandleState(Handle handle) {
        super(handle);
    }

    @Override
    public Point getLocation() {
        return handle.getOwner().getBounds().getLocation();
    }

    @Override
    public Cursor getCursor() {
        return Cursor.getPredefinedCursor(Cursor.NW_RESIZE_CURSOR);
    }

    @Override
    public void dragInteraction(int x, int y, MouseEvent e, DrawView v) {
        Rectangle bounds = handle.getOwner().getBounds();
        handle.getOwner().setBounds(
                new Point(x, y),
                new Point(bounds.x + bounds.width, bounds.y + bounds.height)
        );

        if (bounds.x + bounds.width < x) {
            handle.setState(new NorthEastHandleState(handle));
        }

        if (bounds.y + bounds.height < y) {
            handle.setState(new SouthWestHandleState(handle));
        }
    }

}

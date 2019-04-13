package jdraw.figures.handles;

import jdraw.framework.DrawView;

import java.awt.*;
import java.awt.event.MouseEvent;

public class SouthWestHandleState extends AbstractHandleState {

    public SouthWestHandleState(Handle handle) {
        super(handle);
    }

    @Override
    public Point getLocation() {
        Rectangle bounds = handle.getOwner().getBounds();
        return new Point(bounds.x, bounds.y + bounds.height);
    }

    @Override
    public Cursor getCursor() {
        return Cursor.getPredefinedCursor(Cursor.SW_RESIZE_CURSOR);
    }

    @Override
    public void dragInteraction(int x, int y, MouseEvent e, DrawView v) {
        Rectangle bounds = handle.getOwner().getBounds();
        handle.getOwner().setBounds(
                new Point(x, bounds.y), new Point(bounds.x + bounds.width, y)
        );

        if (bounds.x + bounds.width < x) {
            handle.setState(new SouthEastHandleState(handle));
        }

        if (y < bounds.y) {
            handle.setState(new NorthWestHandleState(handle));
        }
    }

}

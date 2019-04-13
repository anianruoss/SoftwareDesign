package jdraw.figures;


import jdraw.figures.handles.*;

import java.awt.*;
import java.util.ArrayList;

public class Ellipse extends AbstractRectangularFigure {
    public Ellipse(Point point) {
        super(point);
        initializeHandles();
    }

    public Ellipse(Point origin, Point corner) {
        super(origin);
        setBounds(origin, corner);
        initializeHandles();
    }

    @Override
    public void initializeHandles() {
        Handle northWestHandle = new Handle(this);
        Handle northEastHandle = new Handle(this);
        Handle southWestHandle = new Handle(this);
        Handle southEastHandle = new Handle(this);

        northWestHandle.setState(new NorthWestHandleState(northWestHandle));
        northEastHandle.setState(new NorthEastHandleState(northEastHandle));
        southWestHandle.setState(new SouthWestHandleState(southWestHandle));
        southEastHandle.setState(new SouthEastHandleState(southEastHandle));

        handles = new ArrayList<>(4);

        handles.add(northWestHandle);
        handles.add(northEastHandle);
        handles.add(southWestHandle);
        handles.add(southEastHandle);
    }

    @Override
    public void draw(Graphics g) {
        Rectangle r = getBounds();

        g.setColor(Color.WHITE);
        g.fillOval(r.x, r.y, r.width, r.height);
        g.setColor(Color.BLACK);
        g.drawOval(r.x, r.y, r.width, r.height);
    }

}

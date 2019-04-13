/*
 * Copyright (c) 2018 Fachhochschule Nordwestschweiz (FHNW)
 * All Rights Reserved.
 */

package jdraw.figures;

import jdraw.figures.handles.*;

import java.awt.*;
import java.util.ArrayList;

public class Rect extends AbstractRectangularFigure {
    public Rect(Point point) {
        super(point);
        initializeHandles();
    }

    public Rect(Point origin, Point corner) {
        super(origin);
        setBounds(origin, corner);
        initializeHandles();
    }

    @Override
    public void initializeHandles() {
        Handle northWestHandle = new Handle(this);
        Handle northHandle = new Handle(this);
        Handle northEastHandle = new Handle(this);
        Handle westHandle = new Handle(this);
        Handle eastHandle = new Handle(this);
        Handle southWestHandle = new Handle(this);
        Handle southHandle = new Handle(this);
        Handle southEastHandle = new Handle(this);

        northWestHandle.setState(new NorthWestHandleState(northWestHandle));
        northHandle.setState(new NorthHandleState(northHandle));
        northEastHandle.setState(new NorthEastHandleState(northEastHandle));
        westHandle.setState(new WestHandleState(westHandle));
        eastHandle.setState(new EastHandleState(eastHandle));
        southWestHandle.setState(new SouthWestHandleState(southWestHandle));
        southHandle.setState(new SouthHandleState(southHandle));
        southEastHandle.setState(new SouthEastHandleState(southEastHandle));

        handles = new ArrayList<>(8);

        handles.add(northWestHandle);
        handles.add(northHandle);
        handles.add(northEastHandle);
        handles.add(westHandle);
        handles.add(eastHandle);
        handles.add(southWestHandle);
        handles.add(southHandle);
        handles.add(southEastHandle);

    }

    @Override
    public void draw(Graphics g) {
        Rectangle r = getBounds();

        g.setColor(Color.WHITE);
        g.fillRect(r.x, r.y, r.width, r.height);
        g.setColor(Color.BLACK);
        g.drawRect(r.x, r.y, r.width, r.height);
    }

}

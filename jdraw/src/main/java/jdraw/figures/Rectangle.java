/*
 * Copyright (c) 2018 Fachhochschule Nordwestschweiz (FHNW)
 * All Rights Reserved.
 */

package jdraw.figures;

import jdraw.figures.handles.*;

import java.awt.*;
import java.util.ArrayList;

public class Rectangle extends AbstractRectangularFigure {
    public Rectangle(Point point) {
        super(point);
        initializeHandles();
    }

    public Rectangle(Point origin, Point corner) {
        super(origin);
        setBounds(origin, corner);
        initializeHandles();
    }

    @Override
    public void initializeHandles() {
        handles = new ArrayList<>(8);

        handles.add(new Handle(new NorthWestHandleState(this)));
        handles.add(new Handle(new NorthEastHandleState(this)));
        handles.add(new Handle(new SouthWestHandleState(this)));
        handles.add(new Handle(new SouthEastHandleState(this)));
        handles.add(new Handle(new NorthHandleState(this)));
        handles.add(new Handle(new WestHandleState(this)));
        handles.add(new Handle(new EastHandleState(this)));
        handles.add(new Handle(new SouthHandleState(this)));
    }

    @Override
    public void draw(Graphics g) {
        java.awt.Rectangle r = getBounds();

        g.setColor(Color.WHITE);
        g.fillRect(r.x, r.y, r.width, r.height);
        g.setColor(Color.BLACK);
        g.drawRect(r.x, r.y, r.width, r.height);
    }

}

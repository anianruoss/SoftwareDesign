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
        handles = new ArrayList<>(4);

        handles.add(new Handle(new NorthWestHandleState(this)));
        handles.add(new Handle(new NorthEastHandleState(this)));
        handles.add(new Handle(new SouthWestHandleState(this)));
        handles.add(new Handle(new SouthEastHandleState(this)));
    }

    @Override
    public void swapHorizontal() {
        Handle NW = (Handle) handles.get(0);
        Handle NE = (Handle) handles.get(1);
        Handle SW = (Handle) handles.get(2);
        Handle SE = (Handle) handles.get(3);

        HandleState NWstate = NW.getState();
        HandleState NEstate = NE.getState();
        HandleState SWstate = SW.getState();
        HandleState SEstate = SE.getState();

        NW.setState(NEstate);
        NE.setState(NWstate);
        SW.setState(SEstate);
        SE.setState(SWstate);
    }

    @Override
    public void swapVertical() {
        Handle NW = (Handle) handles.get(0);
        Handle NE = (Handle) handles.get(1);
        Handle SW = (Handle) handles.get(2);
        Handle SE = (Handle) handles.get(3);

        HandleState NWstate = NW.getState();
        HandleState NEstate = NE.getState();
        HandleState SWstate = SW.getState();
        HandleState SEstate = SE.getState();

        NW.setState(SWstate);
        NE.setState(SEstate);
        SW.setState(NWstate);
        SE.setState(NEstate);
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

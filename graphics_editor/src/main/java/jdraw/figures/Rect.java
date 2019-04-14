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
    public void swapHorizontal() {
        Handle NW = (Handle) handles.get(0);
        Handle NE = (Handle) handles.get(1);
        Handle SW = (Handle) handles.get(2);
        Handle SE = (Handle) handles.get(3);
        Handle W = (Handle) handles.get(5);
        Handle E = (Handle) handles.get(6);

        HandleState NWstate = NW.getState();
        HandleState NEstate = NE.getState();
        HandleState SWstate = SW.getState();
        HandleState SEstate = SE.getState();
        HandleState Wstate = W.getState();
        HandleState Estate = E.getState();

        NW.setState(NEstate);
        NE.setState(NWstate);
        SW.setState(SEstate);
        SE.setState(SWstate);
        W.setState(Estate);
        E.setState(Wstate);
    }

    @Override
    public void swapVertical() {
        Handle NW = (Handle) handles.get(0);
        Handle NE = (Handle) handles.get(1);
        Handle SW = (Handle) handles.get(2);
        Handle SE = (Handle) handles.get(3);
        Handle N = (Handle) handles.get(4);
        Handle S = (Handle) handles.get(7);

        HandleState NWstate = NW.getState();
        HandleState NEstate = NE.getState();
        HandleState SWstate = SW.getState();
        HandleState SEstate = SE.getState();
        HandleState Nstate = N.getState();
        HandleState Sstate = S.getState();

        NW.setState(SWstate);
        NE.setState(SEstate);
        SW.setState(NWstate);
        SE.setState(NEstate);
        N.setState(Sstate);
        S.setState(Nstate);
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

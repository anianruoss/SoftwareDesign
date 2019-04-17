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
    public void draw(Graphics g) {
        Rectangle r = getBounds();

        g.setColor(Color.WHITE);
        g.fillOval(r.x, r.y, r.width, r.height);
        g.setColor(Color.BLACK);
        g.drawOval(r.x, r.y, r.width, r.height);
    }

}

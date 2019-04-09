package jdraw.figures;


import java.awt.*;

public class Ellipse extends AbstractRectangularFigure {

    public Ellipse(Point point) {
        super(point);
    }

    public Ellipse(Point origin, Point corner) {
        super(origin);
        setBounds(origin, corner);
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

package jdraw.figures;

import jdraw.framework.Figure;

import java.awt.*;

abstract public class AbstractRectangularFigure extends AbstractFigure {
    private Rectangle rectangle;

    AbstractRectangularFigure(Point origin) {
        rectangle = new Rectangle(origin);
    }

    @Override
    public void setBounds(Point origin, Point corner) {
        Rectangle original = new Rectangle(rectangle);
        rectangle.setFrameFromDiagonal(origin, corner);

        if (!original.equals(rectangle)) {
            propagateFigureEvent();
        }
    }

    @Override
    public void move(int dx, int dy) {
        if (dx != 0 || dy != 0) {
            rectangle.translate(dx, dy);
            propagateFigureEvent();
        }
    }

    @Override
    public boolean contains(int x, int y) {
        return rectangle.contains(x, y);
    }

    @Override
    public Rectangle getBounds() {
        return rectangle.getBounds();
    }

    @Override
    public Figure clone() {
        AbstractRectangularFigure clone =
                (AbstractRectangularFigure) super.clone();
        clone.rectangle = (Rectangle) rectangle.clone();
        clone.initializeHandles();

        return clone;
    }
}

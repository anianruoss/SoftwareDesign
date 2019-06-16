package jdraw.figures;

import jdraw.framework.Figure;

import java.awt.*;

public class BorderDecorator extends AbstractDecorator {
    static private final int offset = 5;

    public BorderDecorator(Figure figure) {
        super(figure);
    }

    @Override
    public void draw(Graphics g) {
        inner.draw(g);

        Rectangle bounds = inner.getBounds();
        int x = bounds.x;
        int y = bounds.y;
        int height = bounds.height;
        int width = bounds.width;

        g.setColor(Color.WHITE);
        g.drawLine(x - offset, y - offset, x - offset, y + height + offset);
        g.drawLine(x - offset, y - offset, x + width + offset, y - offset);

        g.setColor(Color.BLACK);
        g.drawLine(
                x + width + offset, y - offset,
                x + width + offset, y + height + offset
        );
        g.drawLine(
                x - offset, y + height + offset,
                x + width + offset, y + height + offset
        );
    }

    @Override
    public Rectangle getBounds() {
        Rectangle bounds = (Rectangle) inner.getBounds().clone();
        bounds.grow(offset, offset);

        return bounds;
    }

}

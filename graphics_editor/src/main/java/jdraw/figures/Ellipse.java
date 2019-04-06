package jdraw.figures;

import jdraw.framework.Figure;
import jdraw.framework.FigureEvent;
import jdraw.framework.FigureHandle;

import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.util.List;

public class Ellipse extends BaseFigure implements Figure {
    private final Ellipse2D.Double ellipse;

    public Ellipse(int x, int y, int w, int h) {
        ellipse = new Ellipse2D.Double(x, y, w, h);
    }

    @Override
    public void draw(Graphics g) {
        int x = ((int) ellipse.x);
        int y = ((int) ellipse.y);
        int width = ((int) ellipse.width);
        int height = ((int) ellipse.height);

        g.setColor(Color.WHITE);
        g.fillOval(x, y, width, height);
        g.setColor(Color.BLACK);
        g.drawOval(x, y, width, height);
    }

    @Override
    public void move(int dx, int dy) {
        if (dx != 0 || dy != 0) {
            ellipse.setFrame(
                    ellipse.x + dx,
                    ellipse.y + dy,
                    ellipse.width,
                    ellipse.height
            );

            figureListenerList.forEach(
                    figureListener -> figureListener.figureChanged(
                            new FigureEvent(this)
                    )
            );
        }
    }

    @Override
    public Rectangle getBounds() {
        return ellipse.getBounds();
    }

    @Override
    public boolean contains(int x, int y) {
        return ellipse.contains(x, y);
    }

    @Override
    public void setBounds(Point origin, Point corner) {
        ellipse.setFrameFromDiagonal(origin, corner);

        figureListenerList.forEach(
                figureListener -> figureListener.figureChanged(
                        new FigureEvent(this)
                )
        );
    }

    @Override
    public List<FigureHandle> getHandles() {
        return null;
    }
}

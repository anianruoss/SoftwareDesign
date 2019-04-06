package jdraw.figures;

import jdraw.framework.Figure;
import jdraw.framework.FigureEvent;
import jdraw.framework.FigureHandle;

import java.awt.*;
import java.awt.geom.Line2D;
import java.util.List;

public class Line extends BaseFigure implements Figure {
    private final Line2D.Double line;

    public Line(int x, int y, int w, int h) {
        line = new Line2D.Double(x, y, x + w, y + h);
    }

    @Override
    public void draw(Graphics g) {
        int x1 = ((int) line.x1);
        int y1 = ((int) line.y1);
        int x2 = ((int) line.x2);
        int y2 = ((int) line.y2);

        g.setColor(Color.BLACK);
        g.drawLine(x1, y1, x2, y2);
    }

    @Override
    public void move(int dx, int dy) {
        if (dx != 0 || dy != 0) {
            line.setLine(
                    line.x1 + dx, line.y1 + dy, line.x2 + dx, line.y2 + dy
            );

            figureListenerList.forEach(
                    figureListener -> figureListener.figureChanged(
                            new FigureEvent(this)
                    )
            );
        }
    }

    @Override
    public boolean contains(int x, int y) {
        return line.contains(x, y);
    }

    @Override
    public void setBounds(Point origin, Point corner) {
        line.setLine(origin, corner);

        figureListenerList.forEach(
                figureListener -> figureListener.figureChanged(
                        new FigureEvent(this)
                )
        );
    }

    @Override
    public Rectangle getBounds() {
        return line.getBounds();
    }

    @Override
    public List<FigureHandle> getHandles() {
        return null;
    }
}

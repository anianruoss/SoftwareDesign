package jdraw.figures;

import jdraw.figures.handles.OwnerDecorator;
import jdraw.framework.Figure;
import jdraw.framework.FigureEvent;
import jdraw.framework.FigureHandle;
import jdraw.framework.FigureListener;

import java.awt.*;
import java.util.List;

public class AbstractDecorator extends AbstractFigure implements FigureListener {
    Figure inner;

    public AbstractDecorator(Figure figure) {
        inner = figure;
        inner.addFigureListener(this);
    }

    @Override
    public void draw(Graphics g) {
        inner.draw(g);
    }

    @Override
    public void move(int dx, int dy) {
        inner.move(dx, dy);
    }


    @Override
    public boolean contains(int x, int y) {
        return inner.contains(x, y);
    }

    @Override
    public void setBounds(Point origin, Point corner) {
        inner.setBounds(origin, corner);
    }

    @Override
    public Rectangle getBounds() {
        return inner.getBounds();
    }

    @Override
    public List<FigureHandle> getHandles() {
        List<FigureHandle> figureHandles = inner.getHandles();

        if (!figureHandles.isEmpty()) {
            figureHandles.replaceAll(
                    figureHandle -> new OwnerDecorator(figureHandle, this)
            );
        }

        return figureHandles;
    }

    @Override
    public Figure clone() {
        AbstractDecorator clone = (AbstractDecorator) super.clone();
        clone.inner = inner.clone();
        clone.inner.addFigureListener(clone);

        return clone;
    }

    @Override
    public void initializeHandles() {
        inner.initializeHandles();
    }

    @Override
    public void swapHorizontal() {
        inner.swapHorizontal();
    }

    @Override
    public void swapVertical() {
        inner.swapVertical();
    }

    @Override
    public void figureChanged(FigureEvent e) {
        propagateFigureEvent();
    }

    @Override
    public final boolean isInstanceOf(Class<?> type) {
        return type.isAssignableFrom(this.getClass()) || inner.isInstanceOf(type);
    }

    @Override
    public final <T> T getInstanceOf(Class<T> type) {
        if (type.isAssignableFrom(this.getClass())) {
            return type.cast(this); // checked version of (T)this
        } else {
            return inner.getInstanceOf(type);
        }
    }

}

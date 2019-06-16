package jdraw.figures.handles;

import jdraw.framework.DrawView;
import jdraw.framework.Figure;
import jdraw.framework.FigureHandle;

import java.awt.*;
import java.awt.event.MouseEvent;

public class OwnerDecorator implements FigureHandle {
    private FigureHandle inner;
    private Figure owner;

    public OwnerDecorator(FigureHandle figureHandle, Figure owner) {
        inner = figureHandle;
        this.owner = owner;
    }

    @Override
    public Figure getOwner() {
        return owner;
    }

    @Override
    public Point getLocation() {
        return inner.getLocation();
    }

    @Override
    public void draw(Graphics g) {
        inner.draw(g);
    }

    @Override
    public Cursor getCursor() {
        return inner.getCursor();
    }

    @Override
    public boolean contains(int x, int y) {
        return inner.contains(x, y);
    }

    @Override
    public void startInteraction(int x, int y, MouseEvent e, DrawView v) {
        inner.startInteraction(x, y, e, v);
    }

    @Override
    public void dragInteraction(int x, int y, MouseEvent e, DrawView v) {
        inner.dragInteraction(x, y, e, v);
    }

    @Override
    public void stopInteraction(int x, int y, MouseEvent e, DrawView v) {
        inner.stopInteraction(x, y, e, v);
    }
}

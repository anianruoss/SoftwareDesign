package jdraw.figures;

import jdraw.framework.Figure;
import jdraw.framework.FigureHandle;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class BundleDecorator extends AbstractDecorator {
    public BundleDecorator(Figure figure) {
        super(figure);
    }

    @Override
    public void move(int dx, int dy) {
    }

    @Override
    public void setBounds(Point origin, Point corner) {
    }

    @Override
    public List<FigureHandle> getHandles() {
        return new ArrayList<>();
    }
}

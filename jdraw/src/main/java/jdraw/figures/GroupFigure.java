package jdraw.figures;

import jdraw.figures.handles.*;
import jdraw.framework.Figure;
import jdraw.framework.FigureGroup;

import java.awt.Rectangle;
import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class GroupFigure extends AbstractFigure implements FigureGroup {
    private List<Figure> figures = new ArrayList<>();

    public GroupFigure(List<Figure> figures) {
        this.figures.addAll(figures);
        initializeHandles();
    }

    @Override
    public void draw(Graphics g) {
        figures.forEach(f -> f.draw(g));
    }

    @Override
    public void move(int dx, int dy) {
        figures.forEach(f -> f.move(dx, dy));
        propagateFigureEvent();
    }

    @Override
    public boolean contains(int x, int y) {
        return getBounds().contains(new Point(x, y));
    }

    @Override
    public void setBounds(Point origin, Point corner) {

    }

    @Override
    public Rectangle getBounds() {
        Rectangle bounds = figures.get(0).getBounds();
        figures.forEach(f -> bounds.add(f.getBounds()));

        return bounds;
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
    public Iterable<Figure> getFigureParts() {
        return Collections.unmodifiableList(figures);
    }

    @Override
    public Figure clone() {
        GroupFigure clone = (GroupFigure) super.clone();
        clone.figures = new ArrayList<>();
        figures.forEach(figure -> clone.figures.add(figure.clone()));
        clone.initializeHandles();

        return clone;
    }
}

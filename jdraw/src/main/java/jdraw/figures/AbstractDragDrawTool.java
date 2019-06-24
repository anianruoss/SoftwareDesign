package jdraw.figures;

import jdraw.commands.AddFigureCommand;
import jdraw.framework.DrawContext;
import jdraw.framework.Figure;

import java.awt.Rectangle;
import java.awt.*;
import java.awt.event.MouseEvent;

public abstract class AbstractDragDrawTool extends AbstractDrawTool {

    private final DrawContext context;
    private Point anchor;
    private Figure figure;

    AbstractDragDrawTool(DrawContext context, String name, String icon) {
        super(name, icon);
        this.context = context;
    }

    @Override
    public void activate() {
        context.showStatusText(getName() + " Mode");
    }

    @Override
    public void deactivate() {
        context.showStatusText("");
    }

    abstract Figure createFigure(Point p);

    @Override
    public void mouseDown(int x, int y, MouseEvent e) {
        if (figure != null) {
            throw new IllegalStateException();
        }
        anchor = new Point(x, y);
        figure = createFigure(anchor);
        context.getModel().addFigure(figure);
    }

    @Override
    public void mouseDrag(int x, int y, MouseEvent e) {
        figure.setBounds(anchor, new Point(x, y));
    }

    @Override
    public void mouseUp(int x, int y, MouseEvent e) {
        Rectangle rectangle = figure.getBounds();

        if (rectangle.width == 0 && rectangle.height == 0) {
            context.getModel().removeFigure(figure);
        } else {
            this.context.getModel().getDrawCommandHandler().addCommand(
                    new AddFigureCommand(this.context.getModel(), figure)
            );
        }

        anchor = null;
        figure = null;
    }
}

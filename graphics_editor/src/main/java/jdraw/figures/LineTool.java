package jdraw.figures;

import jdraw.framework.DrawContext;
import jdraw.framework.Figure;

import java.awt.*;

public class LineTool extends AbstractDragDrawTool {

    public LineTool(DrawContext context) {
        super(context, "Line", "line.png");
    }

    @Override
    Figure createFigure(Point point) {
        return new Line(point);
    }

}

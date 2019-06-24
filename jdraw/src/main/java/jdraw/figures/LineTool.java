package jdraw.figures;

import jdraw.framework.DrawContext;
import jdraw.framework.Figure;

import java.awt.*;

public class LineTool extends AbstractDragDrawTool {

    LineTool(DrawContext context, String name, String icon) {
        super(context, name, icon);
    }

    @Override
    Figure createFigure(Point point) {
        return new Line(point);
    }

}

package jdraw.figures;

import jdraw.framework.DrawContext;
import jdraw.framework.Figure;

import java.awt.*;

public class EllipseTool extends AbstractDragDrawTool {

    public EllipseTool(DrawContext context) {
        super(context, "Ellipse", "oval.png");
    }

    @Override
    Figure createFigure(Point point) {
        return new Ellipse(point);
    }

}

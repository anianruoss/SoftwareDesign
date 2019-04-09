/*
 * Copyright (c) 2018 Fachhochschule Nordwestschweiz (FHNW)
 * All Rights Reserved.
 */

package jdraw.figures;

import jdraw.framework.DrawContext;
import jdraw.framework.Figure;

import java.awt.*;

public class RectTool extends AbstractDragDrawTool {

    public RectTool(DrawContext context) {
        super(context, "Rectangle", "rectangle.png");
    }

    @Override
    Figure createFigure(Point point) {
        return new Rect(point);
    }

}

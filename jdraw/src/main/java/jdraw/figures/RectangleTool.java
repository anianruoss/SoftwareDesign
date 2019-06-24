/*
 * Copyright (c) 2018 Fachhochschule Nordwestschweiz (FHNW)
 * All Rights Reserved.
 */

package jdraw.figures;

import jdraw.framework.DrawContext;
import jdraw.framework.Figure;

import java.awt.*;

public class RectangleTool extends AbstractDragDrawTool {

    RectangleTool(DrawContext context, String name, String icon) {
        super(context, name, icon);
    }

    @Override
    Figure createFigure(Point point) {
        return new Rectangle(point);
    }

}

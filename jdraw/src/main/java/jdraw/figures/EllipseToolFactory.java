package jdraw.figures;

import jdraw.framework.DrawContext;
import jdraw.framework.DrawTool;

public class EllipseToolFactory extends AbstractDrawToolFactory {
    @Override
    public DrawTool createTool(DrawContext context) {
        return new EllipseTool(context, getName(), getIconName());
    }
}

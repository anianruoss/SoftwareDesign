package jdraw.figures;

import jdraw.framework.DrawContext;
import jdraw.framework.DrawTool;
import jdraw.framework.DrawView;

import java.awt.*;

abstract public class BaseFigureTool implements DrawTool {
    static final String IMAGES = "/images/";
    final DrawContext context;
    final DrawView view;

    BaseFigureTool(DrawContext context) {
        this.context = context;
        this.view = context.getView();
    }

    @Override
    public void deactivate() {
        this.context.showStatusText("");
    }

    @Override
    public Cursor getCursor() {
        return Cursor.getPredefinedCursor(Cursor.CROSSHAIR_CURSOR);
    }

}

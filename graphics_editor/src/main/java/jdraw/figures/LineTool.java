package jdraw.figures;

import jdraw.framework.DrawContext;
import jdraw.framework.DrawTool;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;

public class LineTool extends BaseFigureTool implements DrawTool {
    private Line newLine = null;
    private Point anchor = null;

    public LineTool(DrawContext context) {
        super(context);
    }

    @Override
    public void activate() {
        this.context.showStatusText("Line Mode");
    }

    @Override
    public void mouseDown(int x, int y, MouseEvent e) {
        if (newLine != null) {
            throw new IllegalStateException();
        }
        anchor = new Point(x, y);
        newLine = new Line(x, y, 0, 0);
        view.getModel().addFigure(newLine);
    }

    @Override
    public void mouseDrag(int x, int y, MouseEvent e) {
        newLine.setBounds(anchor, new Point(x, y));
        Rectangle r = newLine.getBounds();
        this.context.showStatusText("w: " + r.width + ", h: " + r.height);
    }

    @Override
    public void mouseUp(int x, int y, MouseEvent e) {
        newLine = null;
        anchor = null;
        this.context.showStatusText("Ellipse Mode");
    }

    @Override
    public Icon getIcon() {
        return new ImageIcon(getClass().getResource(IMAGES + "line.png"));
    }

    @Override
    public String getName() {
        return "Line";
    }

}

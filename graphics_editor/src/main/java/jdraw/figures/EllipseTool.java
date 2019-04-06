package jdraw.figures;

import jdraw.framework.DrawContext;
import jdraw.framework.DrawTool;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;

public class EllipseTool extends BaseFigureTool implements DrawTool {

    private Ellipse newEllipse = null;
    private Point anchor = null;

    public EllipseTool(DrawContext context) {
        super(context);
    }

    @Override
    public void activate() {
        context.showStatusText("Ellipse Mode");
    }

    @Override
    public void mouseDown(int x, int y, MouseEvent e) {
        if (newEllipse != null) {
            throw new IllegalStateException();
        }
        anchor = new Point(x, y);
        newEllipse = new Ellipse(x, y, 0, 0);
        view.getModel().addFigure(newEllipse);
    }

    @Override
    public void mouseDrag(int x, int y, MouseEvent e) {
        newEllipse.setBounds(anchor, new Point(x, y));
        Rectangle r = newEllipse.getBounds();
        this.context.showStatusText("w: " + r.width + ", h: " + r.height);
    }

    @Override
    public void mouseUp(int x, int y, MouseEvent e) {
        newEllipse = null;
        anchor = null;
        this.context.showStatusText("Ellipse Mode");
    }

    @Override
    public Icon getIcon() {
        return new ImageIcon(getClass().getResource(IMAGES + "oval.png"));
    }

    @Override
    public String getName() {
        return "Ellipse";
    }
}

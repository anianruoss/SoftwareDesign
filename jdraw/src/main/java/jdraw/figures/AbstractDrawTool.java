package jdraw.figures;

import jdraw.framework.DrawTool;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;

abstract public class AbstractDrawTool implements DrawTool {
    private static final String IMAGES = "/images/";
    private final String name;
    private final String icon;

    AbstractDrawTool(String name, String icon) {
        this.name = name;
        this.icon = icon;
    }

    @Override
    public final String getName() {
        return name;
    }

    @Override
    public void activate() {
    }

    @Override
    public void deactivate() {
    }

    @Override
    public void mouseDown(int x, int y, MouseEvent e) {
    }

    @Override
    public void mouseDrag(int x, int y, MouseEvent e) {
    }

    @Override
    public void mouseUp(int x, int y, MouseEvent e) {
    }


    @Override
    public Cursor getCursor() {
        return Cursor.getPredefinedCursor(Cursor.CROSSHAIR_CURSOR);
    }

    @Override
    public final Icon getIcon() {
        if (icon != null) {
            return new ImageIcon(getClass().getResource(IMAGES + icon));
        } else {
            return null;
        }
    }
}

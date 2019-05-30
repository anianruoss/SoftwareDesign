package jdraw.grids;

import jdraw.framework.DrawGrid;

import java.awt.*;

public class FixedGrid implements DrawGrid {
    private final int stepX;
    private final int stepY;

    public FixedGrid(int stepX, int stepY) {
        this.stepX = stepX;
        this.stepY = stepY;
    }

    @Override
    public Point constrainPoint(Point p) {
        int x = Math.round((float) p.x / stepX) * stepX;
        int y = Math.round((float) p.y / stepY) * stepY;

        return new Point(x, y);
    }

    @Override
    public int getStepX(boolean right) {
        return right ? stepX : -stepX;
    }

    @Override
    public int getStepY(boolean down) {
        return down ? -stepY : stepY;
    }

    @Override
    public void activate() {
    }

    @Override
    public void deactivate() {
    }

    @Override
    public void mouseDown() {
    }

    @Override
    public void mouseUp() {
    }

}

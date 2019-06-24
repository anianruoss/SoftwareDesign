package jdraw.commands;

import jdraw.framework.DrawCommand;
import jdraw.framework.Figure;

import java.awt.*;

public class SetBoundsCommand implements DrawCommand {
    private final Figure figure;
    private final Point undoOrigin;
    private final Point redoOrigin;
    private final Point undoCorner;
    private final Point redoCorner;

    public SetBoundsCommand(Figure figure, Point undoOrigin, Point undoCorner,
                            Point redoOrigin, Point redoCorner) {
        this.figure = figure;
        this.undoOrigin = undoOrigin;
        this.redoOrigin = redoOrigin;
        this.undoCorner = undoCorner;
        this.redoCorner = redoCorner;
    }

    @Override
    public void redo() {
        figure.setBounds(redoOrigin, redoCorner);
    }

    @Override
    public void undo() {
        figure.setBounds(undoOrigin, undoCorner);
    }
}

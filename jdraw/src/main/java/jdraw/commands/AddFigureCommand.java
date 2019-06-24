package jdraw.commands;

import jdraw.framework.DrawCommand;
import jdraw.framework.DrawModel;
import jdraw.framework.Figure;

public class AddFigureCommand implements DrawCommand {
    private final DrawModel drawModel;
    private final Figure figure;

    public AddFigureCommand(DrawModel drawModel, Figure figure) {
        this.drawModel = drawModel;
        this.figure = figure;
    }

    @Override
    public void redo() {
        drawModel.addFigure(figure);
    }

    @Override
    public void undo() {
        drawModel.removeFigure(figure);
    }
}

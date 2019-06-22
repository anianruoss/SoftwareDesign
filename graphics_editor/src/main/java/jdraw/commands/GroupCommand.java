package jdraw.commands;

import jdraw.framework.DrawCommand;
import jdraw.framework.DrawModel;
import jdraw.framework.Figure;
import jdraw.framework.FigureGroup;

public class GroupCommand implements DrawCommand {
    private final DrawModel drawModel;
    private final FigureGroup figureGroup;
    private final boolean group;


    public GroupCommand(DrawModel drawModel, FigureGroup figureGroup,
                        boolean group) {
        this.drawModel = drawModel;
        this.figureGroup = figureGroup;
        this.group = group;
    }

    private void group() {
        figureGroup.getFigureParts().forEach(drawModel::removeFigure);
        drawModel.addFigure((Figure) figureGroup);
    }

    private void ungroup() {
        drawModel.removeFigure((Figure) figureGroup);
        figureGroup.getFigureParts().forEach(drawModel::addFigure);
    }

    @Override
    public void redo() {
        if (this.group) {
            group();
        } else {
            ungroup();
        }
    }

    @Override
    public void undo() {
        if (this.group) {
            ungroup();
        } else {
            group();
        }
    }
}

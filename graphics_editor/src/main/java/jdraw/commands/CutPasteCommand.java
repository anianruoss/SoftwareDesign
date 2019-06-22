package jdraw.commands;

import jdraw.framework.DrawCommand;
import jdraw.framework.DrawModel;
import jdraw.framework.Figure;

import java.util.List;

public class CutPasteCommand implements DrawCommand {
    private final DrawModel drawModel;
    private final List<Figure> figures;
    private final boolean cut;


    public CutPasteCommand(DrawModel drawModel, List<Figure> figures,
                           boolean cut) {
        this.drawModel = drawModel;
        this.figures = figures;
        this.cut = cut;
    }

    private void cut() {
        drawModel.clipboard.clear();
        figures.forEach(drawModel.clipboard::add);
        figures.forEach(drawModel::removeFigure);
    }

    private void paste() {
        drawModel.clipboard.clear();
        figures.forEach(drawModel.clipboard::add);
        figures.forEach(drawModel::addFigure);
    }

    @Override
    public void redo() {
        if (cut) {
            cut();
        } else {
            paste();
        }
    }

    @Override
    public void undo() {
        if (cut) {
            paste();
        } else {
            cut();
        }
    }
}

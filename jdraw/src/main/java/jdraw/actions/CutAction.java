package jdraw.actions;

import jdraw.commands.CutPasteCommand;
import jdraw.framework.DrawContext;
import jdraw.framework.DrawModel;
import jdraw.framework.Figure;

import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;

public class CutAction extends AbstractAction {
    public CutAction(DrawContext drawContext) {
        super(drawContext);
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        DrawModel drawModel = drawContext.getModel();
        drawModel.clipboard.clear();

        List<Figure> figures = new ArrayList<>();

        drawContext.getView().getSelection().forEach(figure -> {
            drawModel.removeFigure(figure);
            drawModel.clipboard.add(figure);
            figures.add(figure);
        });

        drawModel.getDrawCommandHandler().addCommand(
                new CutPasteCommand(drawModel, figures, true)
        );
    }
}

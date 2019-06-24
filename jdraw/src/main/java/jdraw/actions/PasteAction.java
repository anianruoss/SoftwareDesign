package jdraw.actions;

import jdraw.commands.CutPasteCommand;
import jdraw.framework.DrawContext;
import jdraw.framework.DrawModel;
import jdraw.framework.DrawView;
import jdraw.framework.Figure;

import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;

public class PasteAction extends AbstractAction {
    public PasteAction(DrawContext drawContext) {
        super(drawContext);
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        DrawModel drawModel = drawContext.getModel();
        DrawView drawView = drawContext.getView();
        drawView.clearSelection();

        List<Figure> figures = new ArrayList<>();

        drawModel.clipboard.get().forEach(figure -> {
            figure.move(10, 10);
            Figure clone = figure.clone();
            drawModel.addFigure(clone);
            drawView.addToSelection(clone);
            figures.add(clone);
        });

        drawModel.getDrawCommandHandler().addCommand(
                new CutPasteCommand(drawModel, figures, false)
        );
    }
}

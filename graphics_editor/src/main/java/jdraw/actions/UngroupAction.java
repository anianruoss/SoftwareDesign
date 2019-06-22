package jdraw.actions;

import jdraw.commands.GroupCommand;
import jdraw.framework.*;

import java.awt.event.ActionEvent;
import java.util.List;

public class UngroupAction extends AbstractAction {
    public UngroupAction(DrawContext drawContext) {
        super(drawContext);
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        DrawView drawView = drawContext.getView();

        drawView.getSelection().forEach(figure -> {
            if (figure instanceof FigureGroup) {
                DrawModel drawModel = drawContext.getModel();

                drawModel.removeFigure(figure);
                drawView.removeFromSelection(figure);

                FigureGroup figureGroup = (FigureGroup) figure;
                List<Figure> figureParts =
                        (List<Figure>) figureGroup.getFigureParts();
                figureParts.forEach(
                        figurePart -> {
                            drawModel.addFigure(figurePart);
                            drawView.addToSelection(figurePart);
                        }
                );

                drawModel.getDrawCommandHandler().addCommand(
                        new GroupCommand(drawModel, figureGroup, false)
                );
            }
        });
    }
}

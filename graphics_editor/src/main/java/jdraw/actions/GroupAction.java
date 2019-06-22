package jdraw.actions;

import jdraw.commands.GroupCommand;
import jdraw.figures.GroupFigure;
import jdraw.framework.DrawContext;
import jdraw.framework.DrawModel;
import jdraw.framework.DrawView;
import jdraw.framework.Figure;

import java.awt.event.ActionEvent;
import java.util.List;

public class GroupAction extends AbstractAction {
    public GroupAction(DrawContext drawContext) {
        super(drawContext);
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        DrawView drawView = drawContext.getView();
        List<Figure> selection = drawView.getSelection();

        if (selection != null && 1 < selection.size()) {
            DrawModel drawModel = drawContext.getModel();

            selection.forEach(figure -> {
                drawModel.removeFigure(figure);
                drawView.removeFromSelection(figure);
            });

            GroupFigure groupFigure = new GroupFigure(selection);
            drawModel.addFigure(groupFigure);
            drawView.addToSelection(groupFigure);
            drawModel.getDrawCommandHandler().addCommand(
                    new GroupCommand(drawModel, groupFigure, true)
            );
        }
    }
}

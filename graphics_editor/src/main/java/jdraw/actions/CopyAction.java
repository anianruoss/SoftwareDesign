package jdraw.actions;

import jdraw.framework.DrawContext;
import jdraw.framework.DrawModel;

import java.awt.event.ActionEvent;

public class CopyAction extends AbstractAction {
    public CopyAction(DrawContext drawContext) {
        super(drawContext);
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        DrawModel drawModel = drawContext.getModel();
        drawModel.clipboard.clear();
        drawContext.getView().getSelection().forEach(
                figure -> drawModel.clipboard.add(figure.clone())
        );
    }
}

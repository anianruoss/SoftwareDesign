package jdraw.actions;

import jdraw.commands.DecoratorCommand;
import jdraw.figures.AbstractDecorator;
import jdraw.framework.DrawContext;
import jdraw.framework.DrawModel;
import jdraw.framework.DrawView;
import jdraw.framework.Figure;

import java.awt.event.ActionEvent;
import java.util.List;

public abstract class AbstractRemoveDecoratorAction extends AbstractAction {
    private final Class<? extends AbstractDecorator> Decorator;

    public AbstractRemoveDecoratorAction(
            Class<? extends AbstractDecorator> Decorator,
            DrawContext drawContext) {
        super(drawContext);
        this.Decorator = Decorator;
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        DrawView drawView = drawContext.getView();
        List<Figure> selection = drawView.getSelection();

        if (selection != null) {
            DrawModel drawModel = drawContext.getModel();
            selection.forEach(figure -> {
                if (figure.isInstanceOf(Decorator)) {
                    Figure inner = Decorator.cast(figure).unwrap();
                    drawView.removeFromSelection(figure);
                    drawModel.removeFigure(figure);
                    drawModel.addFigure(inner);
                    drawView.addToSelection(inner);

                    drawModel.getDrawCommandHandler().addCommand(
                            new DecoratorCommand(
                                    drawModel, Decorator, inner, false
                            )
                    );
                }
            });
        }
    }
}

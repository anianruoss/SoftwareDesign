package jdraw.actions;

import jdraw.commands.DecoratorCommand;
import jdraw.figures.AbstractDecorator;
import jdraw.framework.DrawContext;
import jdraw.framework.DrawModel;
import jdraw.framework.DrawView;
import jdraw.framework.Figure;

import java.awt.event.ActionEvent;
import java.lang.reflect.InvocationTargetException;
import java.util.List;

public abstract class AbstractAddDecoratorAction extends AbstractAction {
    private final Class<? extends AbstractDecorator> Decorator;

    public AbstractAddDecoratorAction(
            Class<? extends AbstractDecorator> Decorator,
            DrawContext drawContext) {
        super(drawContext);
        this.Decorator = Decorator;
    }


    public void actionPerformed(ActionEvent actionEvent) {
        DrawView drawView = drawContext.getView();
        List<Figure> selection = drawView.getSelection();
        drawView.clearSelection();

        if (selection != null) {
            DrawModel drawModel = drawContext.getModel();

            selection.forEach(figure -> {
                Figure decoratedFigure = null;

                try {
                    decoratedFigure = this.Decorator.getDeclaredConstructor(
                            Figure.class
                    ).newInstance(figure);
                } catch (InstantiationException | IllegalAccessException
                        | InvocationTargetException | NoSuchMethodException e) {
                    e.printStackTrace();
                }

                drawModel.removeFigure(figure);
                drawModel.addFigure(decoratedFigure);
                drawView.addToSelection(decoratedFigure);

                drawModel.getDrawCommandHandler().addCommand(
                        new DecoratorCommand(
                                drawModel, Decorator, decoratedFigure, true
                        )
                );
            });
        }
    }
}

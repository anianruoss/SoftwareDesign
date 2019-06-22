package jdraw.commands;

import jdraw.figures.AbstractDecorator;
import jdraw.framework.DrawCommand;
import jdraw.framework.DrawModel;
import jdraw.framework.Figure;

import java.lang.reflect.InvocationTargetException;

public class DecoratorCommand implements DrawCommand {
    private final DrawModel drawModel;
    private final Class<? extends AbstractDecorator> Decorator;
    private Figure figure;
    private boolean isDecorated;
    private boolean add;

    public DecoratorCommand(DrawModel drawModel,
                            Class<? extends AbstractDecorator> Decorator,
                            Figure figure, boolean isDecorated) {
        this.drawModel = drawModel;
        this.Decorator = Decorator;
        this.figure = figure;
        this.isDecorated = isDecorated;
        this.add = isDecorated;
    }

    private void decorate() {
        if (!isDecorated) {
            drawModel.removeFigure(figure);
            try {
                figure = this.Decorator.getDeclaredConstructor(
                        Figure.class
                ).newInstance(figure);
            } catch (InstantiationException | IllegalAccessException
                    | InvocationTargetException | NoSuchMethodException e) {
                e.printStackTrace();
            }
            drawModel.addFigure(figure);
            isDecorated = true;
        }

    }

    private void unwrap() {
        if (isDecorated) {
            drawModel.removeFigure(figure);
            figure = ((AbstractDecorator) figure).unwrap();
            drawModel.addFigure(figure);
            isDecorated = false;
        }
    }

    @Override
    public void redo() {
        if (add) {
            decorate();
        } else {
            unwrap();
        }
    }

    @Override
    public void undo() {
        if (add) {
            unwrap();
        } else {
            decorate();
        }
    }
}

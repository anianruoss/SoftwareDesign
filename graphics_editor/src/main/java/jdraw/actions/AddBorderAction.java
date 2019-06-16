package jdraw.actions;

import jdraw.figures.BorderDecorator;
import jdraw.framework.DrawContext;

public class AddBorderAction extends AbstractAddDecoratorAction {
    public AddBorderAction(DrawContext drawContext) {
        super(BorderDecorator.class, drawContext);
    }
}

package jdraw.actions;

import jdraw.figures.BorderDecorator;
import jdraw.framework.DrawContext;

public class RemoveBorderAction extends AbstractRemoveDecoratorAction {
    public RemoveBorderAction(DrawContext drawContext) {
        super(BorderDecorator.class, drawContext);
    }
}

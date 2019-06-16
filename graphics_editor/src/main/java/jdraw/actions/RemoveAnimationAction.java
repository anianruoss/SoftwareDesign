package jdraw.actions;

import jdraw.figures.AnimationDecorator;
import jdraw.framework.DrawContext;

public class RemoveAnimationAction extends AbstractRemoveDecoratorAction {
    public RemoveAnimationAction(DrawContext drawContext) {
        super(AnimationDecorator.class, drawContext);
    }
}

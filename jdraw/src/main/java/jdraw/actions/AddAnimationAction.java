package jdraw.actions;

import jdraw.figures.AnimationDecorator;
import jdraw.framework.DrawContext;

public class AddAnimationAction extends AbstractAddDecoratorAction {

    public AddAnimationAction(DrawContext drawContext) {
        super(AnimationDecorator.class, drawContext);
    }
}

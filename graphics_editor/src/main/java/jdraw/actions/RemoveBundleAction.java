package jdraw.actions;

import jdraw.figures.BundleDecorator;
import jdraw.framework.DrawContext;

public class RemoveBundleAction extends AbstractRemoveDecoratorAction {
    public RemoveBundleAction(DrawContext drawContext) {
        super(BundleDecorator.class, drawContext);
    }
}

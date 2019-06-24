package jdraw.actions;

import jdraw.figures.BundleDecorator;
import jdraw.framework.DrawContext;

public class AddBundleAction extends AbstractAddDecoratorAction {
    public AddBundleAction(DrawContext drawContext) {
        super(BundleDecorator.class, drawContext);
    }
}

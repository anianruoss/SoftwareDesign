package jdraw.actions;

import jdraw.framework.DrawContext;

import javax.swing.event.MenuEvent;
import javax.swing.event.MenuListener;
import java.awt.event.ActionEvent;

public abstract class AbstractAction
        extends javax.swing.AbstractAction
        implements MenuListener {

    final DrawContext drawContext;

    public AbstractAction(DrawContext drawContext) {
        this.drawContext = drawContext;
    }

    public void menuSelected(MenuEvent menuEvent) {
        setEnabled(true);
    }

    public void menuDeselected(MenuEvent menuEvent) {
        setEnabled(true);
    }

    public void menuCanceled(MenuEvent menuEvent) {
        setEnabled(true);
    }

    public abstract void actionPerformed(ActionEvent actionEvent);

}

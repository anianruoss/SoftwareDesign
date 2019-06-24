package jdraw.figures.handles;

import jdraw.framework.DrawView;
import jdraw.framework.Figure;

import java.awt.event.MouseEvent;

public abstract class AbstractHandleState implements HandleState {
    private final Figure owner;

    AbstractHandleState(Figure owner) {
        this.owner = owner;
    }

    @Override
    public Figure getOwner() {
        return owner;
    }

    @Override
    public void startInteraction(int x, int y, MouseEvent e, DrawView v) {
    }

    @Override
    public void stopInteraction(int x, int y, MouseEvent e, DrawView v) {
    }
}

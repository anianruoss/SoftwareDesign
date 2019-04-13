package jdraw.figures.handles;

import jdraw.framework.DrawView;

import java.awt.event.MouseEvent;

public abstract class AbstractHandleState implements HandleState {
    final Handle handle;

    AbstractHandleState(Handle handle) {
        this.handle = handle;
    }

    @Override
    public void startInteraction(int x, int y, MouseEvent e, DrawView v) {
    }

    @Override
    public void stopInteraction(int x, int y, MouseEvent e, DrawView v) {
    }


}

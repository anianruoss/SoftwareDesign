package jdraw.figures.handles;


import jdraw.framework.DrawView;
import jdraw.framework.Figure;

import java.awt.*;
import java.awt.event.MouseEvent;

public interface HandleState {

    Point getLocation();

    Figure getOwner();

    Cursor getCursor();

    void startInteraction(int x, int y, MouseEvent e, DrawView v);

    void dragInteraction(int x, int y, MouseEvent e, DrawView v);

    void stopInteraction(int x, int y, MouseEvent e, DrawView v);

    HandleState swapHorizontal();

    HandleState swapVertical();
}

package jdraw.figures;

import jdraw.framework.Figure;
import jdraw.framework.FigureListener;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

abstract public class BaseFigure implements Figure {
    final List<FigureListener> figureListenerList =
            new CopyOnWriteArrayList<>();

    @Override
    public Figure clone() {
        return null;
    }

    @Override
    public void addFigureListener(FigureListener listener) {
        figureListenerList.add(listener);
    }

    @Override
    public void removeFigureListener(FigureListener listener) {
        figureListenerList.remove(listener);
    }

}

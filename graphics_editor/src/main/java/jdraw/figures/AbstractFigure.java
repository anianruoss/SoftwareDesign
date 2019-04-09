package jdraw.figures;

import jdraw.framework.Figure;
import jdraw.framework.FigureEvent;
import jdraw.framework.FigureHandle;
import jdraw.framework.FigureListener;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

abstract public class AbstractFigure implements Figure {
    private final List<FigureListener> figureListenerList =
            new CopyOnWriteArrayList<>();

    @Override
    public Figure clone() {
        return null;
    }

    @Override
    public void addFigureListener(FigureListener listener) {
        if (listener != null && !figureListenerList.contains(listener)) {
            figureListenerList.add(listener);
        }
    }

    @Override
    public void removeFigureListener(FigureListener listener) {
        figureListenerList.remove(listener);
    }

    void propagateFigureEvent() {
        figureListenerList.forEach(
                figureListener -> figureListener.figureChanged(
                        new FigureEvent(this)
                )
        );
    }

    @Override
    public List<FigureHandle> getHandles() {
        return null;
    }

}

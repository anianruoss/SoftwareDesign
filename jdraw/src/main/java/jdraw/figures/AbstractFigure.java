package jdraw.figures;

import jdraw.figures.handles.Handle;
import jdraw.framework.Figure;
import jdraw.framework.FigureEvent;
import jdraw.framework.FigureHandle;
import jdraw.framework.FigureListener;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

abstract public class AbstractFigure implements Figure {
    List<FigureListener> figureListenerList = new CopyOnWriteArrayList<>();
    List<FigureHandle> handles;

    @Override
    public Figure clone() {
        try {
            AbstractFigure clone = (AbstractFigure) super.clone();
            clone.figureListenerList = new CopyOnWriteArrayList<>();
            return clone;

        } catch (CloneNotSupportedException e) {
            throw new InternalError(e.getMessage());
        }
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
        return handles;
    }

    @Override
    public void swapHorizontal() {
        handles.forEach(h -> {
            Handle handle = (Handle) h;
            handle.setState(handle.getState().swapHorizontal());
        });
    }

    @Override
    public void swapVertical() {
        handles.forEach(h -> {
            Handle handle = (Handle) h;
            handle.setState(handle.getState().swapVertical());
        });
    }

    @Override
    public boolean isInstanceOf(Class<?> type) {
        return type.isAssignableFrom(this.getClass());
    }

    @Override
    public <T> T getInstanceOf(Class<T> type) {
        return type.cast(this); // checked version of (T)this
    }
}

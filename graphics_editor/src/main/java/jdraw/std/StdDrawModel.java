/*
 * Copyright (c) 2018 Fachhochschule Nordwestschweiz (FHNW)
 * All Rights Reserved.
 */

package jdraw.std;

import jdraw.framework.*;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * Provide a standard behavior for the drawing model. This class initially does not implement the methods
 * in a proper way.
 * It is part of the course assignments to do so.
 *
 * @author Anian Ruoss
 */
public class StdDrawModel implements DrawModel, FigureListener {

    /**
     * The draw command handler. Initialized here with a dummy implementation.
     */
    // TODO initialize with your implementation of the undo/redo-assignment.
    private DrawCommandHandler handler = new EmptyDrawCommandHandler();
    private final List<Figure> figureList = new ArrayList<>();
    private final List<DrawModelListener> drawModelListenerList =
            new CopyOnWriteArrayList<>();

    @Override
    public void addFigure(Figure f) {
        if (!figureList.contains(f)) {
            figureList.add(f);
            f.addFigureListener(this);
            notifyListeners(f, DrawModelEvent.Type.FIGURE_ADDED);
        }
    }

    @Override
    public Iterable<Figure> getFigures() {
        return figureList;
    }

    @Override
    public void removeFigure(Figure f) {
        if (figureList.contains(f)) {
            figureList.remove(f);
            f.removeFigureListener(this);
            notifyListeners(f, DrawModelEvent.Type.FIGURE_REMOVED);
        }
    }

    @Override
    public void addModelChangeListener(DrawModelListener listener) {
        drawModelListenerList.add(listener);
    }

    @Override
    public void removeModelChangeListener(DrawModelListener listener) {
        drawModelListenerList.remove(listener);
    }

    @Override
    public void figureChanged(FigureEvent e) {
        notifyListeners(e.getFigure(), DrawModelEvent.Type.FIGURE_CHANGED);
    }

    private void notifyListeners(Figure f, DrawModelEvent.Type type) {
        drawModelListenerList.forEach(
                drawModelListener -> drawModelListener.modelChanged(
                        new DrawModelEvent(this, f, type)
                )
        );
    }


    /**
     * Retrieve the draw command handler in use.
     *
     * @return the draw command handler.
     */
    @Override
    public DrawCommandHandler getDrawCommandHandler() {
        return handler;
    }

    @Override
    public void setFigureIndex(Figure f, int index)
            throws IllegalArgumentException, IndexOutOfBoundsException {
        if (index < 0 || figureList.size() <= index) {
            throw new IndexOutOfBoundsException("Index out of range");
        }

        int figureIndex = figureList.indexOf(f);

        if (figureIndex < 0) {
            throw new IllegalArgumentException("Unknown figure");
        }

        if (figureIndex != index) {
            figureList.remove(f);
            figureList.add(index, f);
            notifyListeners(f, DrawModelEvent.Type.DRAWING_CHANGED);
        }
    }

    @Override
    public void removeAllFigures() {
        if (figureList.size() != 0) {
            figureList.forEach(figure -> figure.removeFigureListener(this));
            figureList.clear();
            notifyListeners(null, DrawModelEvent.Type.DRAWING_CLEARED);
        }
    }
}

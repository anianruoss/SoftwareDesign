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
public class StdDrawModel implements DrawModel {

    /**
     * The draw command handler. Initialized here with a dummy implementation.
     */
    // TODO initialize with your implementation of the undo/redo-assignment.
    private DrawCommandHandler handler = new EmptyDrawCommandHandler();
    private final List<Figure> figureList = new ArrayList<>();
    private final List<FigureListener> figureListenerList =
            new CopyOnWriteArrayList<>();
    private final List<DrawModelListener> drawModelListenerList =
            new CopyOnWriteArrayList<>();

    @Override
    public void addFigure(Figure f) {
        if (!figureList.contains(f)) {
            figureList.add(f);
            figureListenerList.add(
                    e -> {
                        for (DrawModelListener listener : drawModelListenerList) {
                            listener.modelChanged(
                                    new DrawModelEvent(
                                            this,
                                            e.getFigure(),
                                            DrawModelEvent.Type.FIGURE_CHANGED
                                    )
                            );
                        }
                    }
            );
            f.addFigureListener(figureListenerList.get(figureList.size() - 1));

            for (DrawModelListener listener : drawModelListenerList) {
                listener.modelChanged(
                        new DrawModelEvent(
                                this, f, DrawModelEvent.Type.FIGURE_ADDED
                        )
                );
            }
        }
    }

    @Override
    public Iterable<Figure> getFigures() {
        return figureList;
    }

    @Override
    public void removeFigure(Figure f) {
        if (figureList.contains(f)) {
            int figureIndex = figureList.indexOf(f);
            figureList.remove(f);
            f.removeFigureListener(figureListenerList.get(figureIndex));
            figureListenerList.remove(figureIndex);

            for (DrawModelListener listener : drawModelListenerList) {
                listener.modelChanged(
                        new DrawModelEvent(
                                this, f, DrawModelEvent.Type.FIGURE_REMOVED
                        )
                );
            }
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
        List<Figure> tmpFigureList = new ArrayList<>();
        List<FigureListener> tmpFigureListenerList = new ArrayList<>();

        boolean figureInList = false;
        FigureListener figureListener = e -> {
            for (DrawModelListener listener : drawModelListenerList) {
                listener.modelChanged(
                        new DrawModelEvent(
                                this,
                                e.getFigure(),
                                DrawModelEvent.Type.FIGURE_CHANGED
                        )
                );
            }
        };

        for (int i = 0; i < figureList.size(); ++i) {
            Figure figure = figureList.get(i);
            if (figure != f) {
                tmpFigureList.add(figure);
                tmpFigureListenerList.add(figureListenerList.get(i));
            } else {
                figureInList = true;
            }
        }

        if (!figureInList) {
            throw new IllegalArgumentException("Unknown figure");
        }

        if (index < 0 || figureList.size() <= index) {
            throw new IndexOutOfBoundsException("Index out of range");
        }

        figureList.clear();
        figureListenerList.clear();

        for (int i = 0; i < index; ++i) {
            figureList.add(tmpFigureList.get(i));
            figureListenerList.add(tmpFigureListenerList.get(i));
        }

        figureList.add(f);
        figureListenerList.add(figureListener);

        for (int i = index; i < tmpFigureListenerList.size(); ++i) {
            figureList.add(tmpFigureList.get(i));
            figureListenerList.add(tmpFigureListenerList.get(i));
        }

        for (DrawModelListener listener : drawModelListenerList) {
            listener.modelChanged(
                    new DrawModelEvent(
                            this, f, DrawModelEvent.Type.DRAWING_CHANGED
                    )
            );
        }
    }

    @Override
    public void removeAllFigures() {
        if (figureList.size() != 0) {
            for (int i = 0; i < figureList.size(); ++i) {
                figureList.get(i).removeFigureListener(
                        figureListenerList.get(i)
                );
            }
            figureList.clear();
            figureListenerList.clear();

            for (DrawModelListener listener : drawModelListenerList) {
                listener.modelChanged(
                        new DrawModelEvent(
                                this, null, DrawModelEvent.Type.DRAWING_CLEARED
                        )
                );
            }
        }
    }
}

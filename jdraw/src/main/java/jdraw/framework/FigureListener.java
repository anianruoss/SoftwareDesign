/*
 * Copyright (c) 2018 Fachhochschule Nordwestschweiz (FHNW)
 * All Rights Reserved.
 */

package jdraw.framework;

import java.util.EventListener;

/**
 * Listener interested in figure changes.
 *
 * @author Dominik Gruntz &amp; Christoph Denzler
 * @version 2.5
 * @see FigureEvent
 */
public interface FigureListener extends EventListener {

    /**
     * Sent when a figure has changed.
     *
     * @param e figure event
     */
    void figureChanged(FigureEvent e);
}

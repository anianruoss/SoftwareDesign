/*
 * Copyright (c) 2018 Fachhochschule Nordwestschweiz (FHNW)
 * All Rights Reserved.
 */
package jdraw.std;

import jdraw.figures.EllipseTool;
import jdraw.figures.GroupFigure;
import jdraw.figures.LineTool;
import jdraw.figures.RectTool;
import jdraw.framework.*;
import jdraw.grids.FixedGrid;
import jdraw.grids.SnapGrid;

import javax.swing.*;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.File;
import java.util.LinkedList;
import java.util.List;

/**
 * Standard implementation of interface DrawContext.
 *
 * @author Dominik Gruntz &amp; Christoph Denzler
 * @version 2.6, 24.09.09
 * @see DrawView
 */
@SuppressWarnings("serial")
public class StdContext extends AbstractContext {
    /**
     * Constructs a standard context with a default set of drawing tools.
     *
     * @param view the view that is displaying the actual drawing.
     */
    public StdContext(DrawView view) {
        super(view, null);
    }

    /**
     * Constructs a standard context. The drawing tools available can be
     * parameterized using <code>toolFactories</code>.
     *
     * @param view          the view that is displaying the actual drawing.
     * @param toolFactories a list of DrawToolFactories that are available to the user
     */
    public StdContext(DrawView view, List<DrawToolFactory> toolFactories) {
        super(view, toolFactories);
    }

    /**
     * Creates and initializes the "Edit" menu.
     *
     * @return the new "Edit" menu.
     */
    @Override
    protected JMenu createEditMenu() {
        JMenu editMenu = new JMenu("Edit");
        final JMenuItem undo = new JMenuItem("Undo");
        undo.setAccelerator(KeyStroke.getKeyStroke("control Z"));
        editMenu.add(undo);
        undo.addActionListener(e -> {
                    final DrawCommandHandler h = getModel().getDrawCommandHandler();
                    if (h.undoPossible()) {
                        h.undo();
                    }
                }
        );

        final JMenuItem redo = new JMenuItem("Redo");
        redo.setAccelerator(KeyStroke.getKeyStroke("control Y"));
        editMenu.add(redo);
        redo.addActionListener(e -> {
                    final DrawCommandHandler h = getModel().getDrawCommandHandler();
                    if (h.redoPossible()) {
                        h.redo();
                    }
                }
        );
        editMenu.addSeparator();

        JMenuItem sa = new JMenuItem("SelectAll");
        sa.setAccelerator(KeyStroke.getKeyStroke("control A"));
        editMenu.add(sa);
        sa.addActionListener(e -> {
                    for (Figure f : getModel().getFigures()) {
                        getView().addToSelection(f);
                    }
                    getView().repaint();
                }
        );

        editMenu.addSeparator();
        JMenuItem cut = new JMenuItem("Cut");
        JMenuItem copy = new JMenuItem("Copy");
        JMenuItem paste = new JMenuItem("Paste");
        cut.addActionListener(actionEvent -> {
            DrawModel drawModel = getModel();
            drawModel.clipboard.clear();

            getView().getSelection().forEach(figure -> {
                drawModel.removeFigure(figure);
                drawModel.clipboard.add(figure);
            });
        });
        copy.addActionListener(actionEvent -> {
            DrawModel drawModel = getModel();
            drawModel.clipboard.clear();
            getView().getSelection().forEach(
                    figure -> drawModel.clipboard.add(figure.clone())
            );
        });
        paste.addActionListener(actionEvent -> {
            DrawModel drawModel = getModel();
            DrawView drawView = getView();
            drawView.clearSelection();

            drawModel.clipboard.get().forEach(
                    figure -> {
                        figure.move(10, 10);
                        Figure clone = figure.clone();
                        drawModel.addFigure(clone);
                        drawView.addToSelection(clone);
                    }
            );
        });
        cut.setEnabled(true);
        copy.setEnabled(true);
        paste.setEnabled(true);
        editMenu.add(cut);
        editMenu.add(copy);
        editMenu.add(paste);

        editMenu.addSeparator();
        JMenuItem clear = new JMenuItem("Clear");
        editMenu.add(clear);
        clear.addActionListener(e -> getModel().removeAllFigures());

        editMenu.addSeparator();
        JMenuItem group = new JMenuItem("Group");
        group.addActionListener(e -> {
            List<Figure> selection = getView().getSelection();

            if (selection != null && 1 < selection.size()) {
                DrawModel drawModel = getModel();
                DrawView drawView = getView();

                selection.forEach(figure -> {
                    drawModel.removeFigure(figure);
                    drawView.removeFromSelection(figure);
                });

                GroupFigure groupFigure = new GroupFigure(selection);
                drawModel.addFigure(groupFigure);
                drawView.addToSelection(groupFigure);
            }
        });
        group.setEnabled(true);
        editMenu.add(group);

        JMenuItem ungroup = new JMenuItem("Ungroup");
        ungroup.addActionListener(actionEvent -> {
            for (Figure f : getView().getSelection()) {
                if (f instanceof FigureGroup) {
                    DrawModel drawModel = getModel();
                    DrawView drawView = getView();

                    drawModel.removeFigure(f);
                    drawView.removeFromSelection(f);

                    ((FigureGroup) f).getFigureParts().forEach(
                            figure -> {
                                drawModel.addFigure(figure);
                                drawView.addToSelection(figure);
                            }
                    );

                }
            }
        });
        ungroup.setEnabled(true);
        editMenu.add(ungroup);

        editMenu.addSeparator();

        JMenu orderMenu = new JMenu("Order...");
        JMenuItem frontItem = new JMenuItem("Bring To Front");
        frontItem.addActionListener(e ->
                bringToFront(getView().getModel(), getView().getSelection())
        );
        orderMenu.add(frontItem);
        JMenuItem backItem = new JMenuItem("Send To Back");
        backItem.addActionListener(e ->
                sendToBack(getView().getModel(), getView().getSelection())
        );
        orderMenu.add(backItem);
        editMenu.add(orderMenu);

        JMenu grid = new JMenu("Grid...");

        JMenuItem snapGrid = new JMenuItem("Snap Grid");
        snapGrid.addActionListener(
                e -> getView().setGrid(new SnapGrid(getView()))
        );
        grid.add(snapGrid);

        JMenuItem grid50x50 = new JMenuItem("50x50 Grid");
        grid50x50.addActionListener(
                e -> getView().setGrid(new FixedGrid(50, 50)));
        grid.add(grid50x50);

        JMenuItem grid100x100 = new JMenuItem("100x100 Grid");
        grid100x100.addActionListener(
                e -> getView().setGrid(new FixedGrid(100, 100))
        );
        grid.add(grid100x100);

        JMenuItem deactivateGrid = new JMenuItem("Deactivate Grid");
        deactivateGrid.addActionListener(e -> getView().setGrid(null));
        grid.add(deactivateGrid);

        editMenu.add(grid);

        return editMenu;
    }

    /**
     * Creates and initializes items in the file menu.
     *
     * @return the new "File" menu.
     */
    @Override
    protected JMenu createFileMenu() {
        JMenu fileMenu = new JMenu("File");
        JMenuItem open = new JMenuItem("Open");
        fileMenu.add(open);
        open.setAccelerator(KeyStroke.getKeyStroke("control O"));
        open.addActionListener(e -> doOpen());

        JMenuItem save = new JMenuItem("Save");
        save.setAccelerator(KeyStroke.getKeyStroke("control S"));
        fileMenu.add(save);
        save.addActionListener(e -> doSave());

        JMenuItem exit = new JMenuItem("Exit");
        fileMenu.add(exit);
        exit.addActionListener(e -> System.exit(0));

        return fileMenu;
    }

    @Override
    protected void doRegisterDrawTools() {
        DrawTool rectangleTool = new RectTool(this);
        addTool(rectangleTool);
        DrawTool ellipseTool = new EllipseTool(this);
        addTool(ellipseTool);
        DrawTool lineTool = new LineTool(this);
        addTool(lineTool);
    }

    /**
     * Changes the order of figures and moves the figures in the selection
     * to the front, i.e. moves them to the end of the list of figures.
     *
     * @param model     model in which the order has to be changed
     * @param selection selection which is moved to front
     */
    public void bringToFront(DrawModel model, List<Figure> selection) {
        // the figures in the selection are ordered according to the order in
        // the model
        List<Figure> orderedSelection = new LinkedList<>();
        int pos = 0;
        for (Figure f : model.getFigures()) {
            pos++;
            if (selection.contains(f)) {
                orderedSelection.add(0, f);
            }
        }
        for (Figure f : orderedSelection) {
            model.setFigureIndex(f, --pos);
        }
    }

    /**
     * Changes the order of figures and moves the figures in the selection
     * to the back, i.e. moves them to the front of the list of figures.
     *
     * @param model     model in which the order has to be changed
     * @param selection selection which is moved to the back
     */
    public void sendToBack(DrawModel model, List<Figure> selection) {
        // the figures in the selection are ordered according to the order in
        // the model
        List<Figure> orderedSelection = new LinkedList<>();
        for (Figure f : model.getFigures()) {
            if (selection.contains(f)) {
                orderedSelection.add(f);
            }
        }
        int pos = 0;
        for (Figure f : orderedSelection) {
            model.setFigureIndex(f, pos++);
        }
    }

    /**
     * Handles the saving of a drawing to a file.
     */
    private void doSave() {
        JFileChooser chooser = new JFileChooser(getClass().getResource("").getFile());
        chooser.setDialogTitle("Save Graphic");
        chooser.setDialogType(JFileChooser.SAVE_DIALOG);

        chooser.setFileFilter(new FileNameExtensionFilter("JDraw Graphics (*.draw)", "draw"));
        chooser.addChoosableFileFilter(new FileNameExtensionFilter("JDraw Graphics (*.xml)", "xml"));
        chooser.addChoosableFileFilter(new FileNameExtensionFilter("JDraw Graphics (*.json)", "json"));

        int res = chooser.showSaveDialog(this);

        if (res == JFileChooser.APPROVE_OPTION) {
            // save graphic
            File file = chooser.getSelectedFile();
            FileFilter filter = chooser.getFileFilter();
            if (filter instanceof FileNameExtensionFilter && !filter.accept(file)) {
                file = new File(chooser.getCurrentDirectory(), file.getName() + "." + ((FileNameExtensionFilter) filter).getExtensions()[0]);
            }
            System.out.println("save current graphic to file " + file.getName() + " using format "
                    + ((FileNameExtensionFilter) filter).getExtensions()[0]);
        }
    }

    /**
     * Handles the opening of a new drawing from a file.
     */
    private void doOpen() {
        JFileChooser chooser = new JFileChooser(getClass().getResource("")
                .getFile());
        chooser.setDialogTitle("Open Graphic");
        chooser.setDialogType(JFileChooser.OPEN_DIALOG);
        chooser.setFileFilter(new javax.swing.filechooser.FileFilter() {
            @Override
            public String getDescription() {
                return "JDraw Graphic (*.draw)";
            }

            @Override
            public boolean accept(File f) {
                return f.isDirectory() || f.getName().endsWith(".draw");
            }
        });
        int res = chooser.showOpenDialog(this);

        if (res == JFileChooser.APPROVE_OPTION) {
            // read jdraw graphic
            System.out.println("read file "
                    + chooser.getSelectedFile().getName());
        }
    }

}

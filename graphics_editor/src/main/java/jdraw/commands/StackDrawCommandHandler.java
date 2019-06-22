package jdraw.commands;

import jdraw.framework.DrawCommand;
import jdraw.framework.DrawCommandHandler;

import java.util.Stack;

public class StackDrawCommandHandler implements DrawCommandHandler {
    private Stack<DrawCommand> redoStack = new Stack<>();
    private Stack<DrawCommand> undoStack = new Stack<>();
    private CompositeCommand script = null;

    @Override
    public void addCommand(DrawCommand cmd) {
        redoStack.clear();

        if (script == null) {
            undoStack.push(cmd);
        } else {
            script.addCommand(cmd);
        }
    }

    @Override
    public void undo() {
        if (undoPossible()) {
            DrawCommand drawCommand = undoStack.pop();
            redoStack.push(drawCommand);
            drawCommand.undo();
        }
    }

    @Override
    public void redo() {
        if (redoPossible()) {
            DrawCommand drawCommand = redoStack.pop();
            undoStack.push(drawCommand);
            drawCommand.redo();
        }
    }

    @Override
    public boolean undoPossible() {
        return !undoStack.empty();
    }

    @Override
    public boolean redoPossible() {
        return !redoStack.empty();
    }

    @Override
    public void beginScript() {
        if (script != null) {
            throw new IllegalStateException();
        }

        script = new CompositeCommand();
    }

    @Override
    public void endScript() {
        if (script == null) {
            throw new IllegalStateException();
        }

        CompositeCommand tmp = script;
        script = null;

        if (!tmp.drawCommands.isEmpty()) {
            addCommand(tmp);
        }
    }

    @Override
    public void clearHistory() {
        undoStack.clear();
        redoStack.clear();
    }
}

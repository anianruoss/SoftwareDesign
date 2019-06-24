package jdraw.commands;

import jdraw.framework.DrawCommand;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

public class CompositeCommand implements DrawCommand {
    final List<DrawCommand> drawCommands = new ArrayList<>();

    public void addCommand(DrawCommand drawCommand) {
        drawCommands.add(drawCommand);
    }

    @Override
    public void redo() {
        drawCommands.forEach(DrawCommand::redo);
    }

    @Override
    public void undo() {
        int size = drawCommands.size();
        ListIterator<DrawCommand> iterator = drawCommands.listIterator(size);

        while (iterator.hasPrevious()) {
            iterator.previous().undo();
        }
    }
}

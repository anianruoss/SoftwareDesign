package colorpicker;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ColorButton
        extends JButton implements ColorListener, ActionListener {

    private ColorModel model;
    private Type type;

    ColorButton(ColorModel model, Type type, String label) {
        super(label);
        this.type = type;
        this.model = model;
        addActionListener(this);
        model.addColorListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Color c = model.getColor();
        switch (type) {
            case BRIGHTER:
                model.setColor(c.brighter());
                break;
            case DARKER:
                model.setColor(c.darker());
                break;
        }
    }

    @Override
    public void notifyColorValueChange(Color c) {
        switch (type) {
            case BRIGHTER:
                setEnabled(!c.equals(c.brighter()));
                break;
            case DARKER:
                setEnabled(!c.equals(c.darker()));
                break;
        }
    }

    enum Type {BRIGHTER, DARKER}
}

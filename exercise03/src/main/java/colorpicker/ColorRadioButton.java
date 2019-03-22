package colorpicker;

import javax.swing.*;
import java.awt.*;

class ColorRadioButton extends JRadioButton {
    ColorRadioButton(ColorModel model, String label, Color color) {
        super(label, false);
        addActionListener(e -> model.setColor(color));
        model.addColorListener(c -> setSelected(c.equals(color)));
    }
}

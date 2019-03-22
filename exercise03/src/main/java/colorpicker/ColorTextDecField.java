package colorpicker;

import javax.swing.*;

class ColorTextDecField extends JTextField {
    ColorTextDecField(ColorModel model, ColorChannel channel) {
        super("", 5);
        setEditable(false);
        model.addColorListener(
                c -> setText(Integer.toString(channel.getValue(c)))
        );
    }
}

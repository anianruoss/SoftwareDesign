package colorpicker;


import javax.swing.*;

class ColorTextHexField extends JTextField {
    ColorTextHexField(ColorModel model, ColorChannel channel) {
        super("", 3);
        setEditable(false);
        model.addColorListener(
                c -> setText(Integer.toHexString(channel.getValue(c)))
        );
    }
}

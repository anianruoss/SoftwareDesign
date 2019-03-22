package colorpicker;

import javax.swing.*;

class ColorScrollBar extends JScrollBar {
    ColorScrollBar(ColorModel model, ColorChannel channel, int orientation,
                   int val) {
        super(orientation, val, 0, 0, 255);
        setBackground(channel.getColor());
        addAdjustmentListener(
                e -> model.setColor(
                        channel.modifiedColor(model.getColor(), getValue())
                )
        );
        model.addColorListener(c -> setValue(channel.getValue(c)));
    }
}

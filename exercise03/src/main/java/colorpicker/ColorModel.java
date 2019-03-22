package colorpicker;

import java.awt.*;
import java.util.LinkedList;
import java.util.List;

class ColorModel {
    private Color color;
    private List<ColorListener> listeners = new LinkedList<>();

    void addColorListener(ColorListener l) {
        listeners.add(l);
    }

    void removeColorListener(ColorListener l) {
        listeners.remove(l);
    }

    Color getColor() {
        return color;
    }

    void setColor(Color color) {
        if (!color.equals(this.color)) {
            this.color = color;

            for (ColorListener l : this.listeners) {
                l.notifyColorValueChange(color);
            }
        }
    }
}

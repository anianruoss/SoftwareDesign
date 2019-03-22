package colorpicker;

import java.awt.*;

@FunctionalInterface
public interface ColorListener {
    void notifyColorValueChange(Color c);
}

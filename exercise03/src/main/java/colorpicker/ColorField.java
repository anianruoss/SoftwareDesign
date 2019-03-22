package colorpicker;

import javax.swing.*;
import java.awt.*;

public class ColorField extends JComponent {
    private static final int SIZE = 175;
    private Color color;

    ColorField(ColorModel model) {
        color = model.getColor();
        model.addColorListener(
                c -> {
                    color = c;
                    repaint();
                }
        );
        setPreferredSize(new Dimension(SIZE, SIZE));
    }

    @Override
    public void paint(Graphics g) {
        Dimension d = getSize();
        g.setColor(color);
        g.fillRect(0, 0, d.width, d.height);
        g.setColor(Color.black);
        g.drawRect(0, 0, d.width - 1, d.height - 1);
    }
}

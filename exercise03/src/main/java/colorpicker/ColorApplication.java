package colorpicker;

import javax.swing.*;
import java.awt.*;

public class ColorApplication extends JFrame {
    private ColorApplication() {
        setTitle("Color Application");
        setBackground(Color.LIGHT_GRAY);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Container container = getContentPane();
        container.setLayout(new BorderLayout());

        JPanel topPanel = new JPanel(new GridLayout(1, 2, 5, 5));
        container.add(topPanel, BorderLayout.NORTH);
        JPanel bottomPanel = new JPanel(new FlowLayout());
        container.add(bottomPanel, BorderLayout.CENTER);

        ColorModel model = new ColorModel();
        JPanel panel;

        // ScrollBar
        panel = new JPanel(new GridLayout(3, 1, 3, 3));
        topPanel.add(panel);
        panel.add(
                new ColorScrollBar(
                        model, ColorChannel.RED, JScrollBar.HORIZONTAL, 0
                )
        );
        panel.add(
                new ColorScrollBar(
                        model, ColorChannel.GREEN, JScrollBar.HORIZONTAL, 0
                )
        );
        panel.add(
                new ColorScrollBar(
                        model, ColorChannel.BLUE, JScrollBar.HORIZONTAL, 0
                )
        );

        // TextField
        panel = new JPanel(new GridLayout(3, 2));
        topPanel.add(panel);
        panel.add(new ColorTextDecField(model, ColorChannel.RED));
        panel.add(new ColorTextHexField(model, ColorChannel.RED));
        panel.add(new ColorTextDecField(model, ColorChannel.GREEN));
        panel.add(new ColorTextHexField(model, ColorChannel.GREEN));
        panel.add(new ColorTextDecField(model, ColorChannel.BLUE));
        panel.add(new ColorTextHexField(model, ColorChannel.BLUE));

        // Color Field
        bottomPanel.add(new ColorField(model));

        // CheckBox
        panel = new JPanel(new GridLayout(0, 1));
        bottomPanel.add(panel);
        panel.add(new ColorRadioButton(model, "Red", Color.red));
        panel.add(new ColorRadioButton(model, "Blue", Color.blue));
        panel.add(new ColorRadioButton(model, "Green", Color.green));
        panel.add(new ColorRadioButton(model, "Yellow", Color.yellow));
        panel.add(new ColorRadioButton(model, "Cyan", Color.cyan));
        panel.add(new ColorRadioButton(model, "Orange", Color.orange));

        // Button
        panel = new JPanel(new GridLayout(2, 1, 5, 5));
        bottomPanel.add(panel);
        panel.add(
                new ColorButton(model, ColorButton.Type.BRIGHTER, "Brighter")
        );
        panel.add(
                new ColorButton(model, ColorButton.Type.DARKER, "Darker")
        );

        // MenuBar
        JMenuBar menuBar = new JMenuBar();
        setJMenuBar(menuBar);

        JMenu fileMenu = new JMenu("File");
        menuBar.add(fileMenu);

        JMenuItem exitMenuItem = new JMenuItem("Exit");
        fileMenu.add(exitMenuItem);
        exitMenuItem.addActionListener(e -> System.exit(0));

        JMenu attributesMenu = new JMenu("Attributes");
        menuBar.add(attributesMenu);
        attributesMenu.add(new ColorMenuItem(model, "Red", Color.red));
        attributesMenu.add(new ColorMenuItem(model, "Blue", Color.blue));
        attributesMenu.add(new ColorMenuItem(model, "Green", Color.green));
        attributesMenu.add(new ColorMenuItem(model, "Yellow", Color.yellow));
        attributesMenu.add(new ColorMenuItem(model, "Cyan", Color.cyan));
        attributesMenu.add(new ColorMenuItem(model, "Orange", Color.orange));

        model.setColor(Color.black);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new ColorApplication();
            frame.pack();
            frame.setVisible(true);
        });
    }
}

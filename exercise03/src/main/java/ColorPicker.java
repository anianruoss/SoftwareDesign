import javax.swing.*;
import java.awt.*;

public class ColorPicker extends JFrame {
    private int r = 0, g = 0, b = 0;

    private ColorPicker() {
        super("Color Picker");
        setLayout(new BorderLayout());

        // color scroll bars
        JScrollBar redScrollBar = new JScrollBar(
                JScrollBar.HORIZONTAL, 0, 10, 0, 255
        );
        redScrollBar.setBackground(new Color(255, 0, 0));
        JScrollBar blueScrollBar = new JScrollBar(
                JScrollBar.HORIZONTAL, 0, 10, 0, 255
        );
        blueScrollBar.setBackground(new Color(0, 255, 0));
        JScrollBar greenScrollBar = new JScrollBar(
                JScrollBar.HORIZONTAL, 0, 10, 0, 255
        );
        greenScrollBar.setBackground(new Color(0, 0, 255));

        // color number fields
        JLabel redIntLabel = new JLabel(Integer.toString(r));
        JLabel redHexLabel = new JLabel(Integer.toHexString(r));
        JLabel blueIntLabel = new JLabel(Integer.toString(b));
        JLabel blueHexLabel = new JLabel(Integer.toHexString(b));
        JLabel greenIntLabel = new JLabel(Integer.toString(g));
        JLabel greenHexLabel = new JLabel(Integer.toHexString(g));

        // color radio buttons
        JRadioButton redButton = new JRadioButton("Red");
        JRadioButton blueButton = new JRadioButton("Blue");
        JRadioButton greenButton = new JRadioButton("Green");
        JRadioButton yellowButton = new JRadioButton("Yellow");
        JRadioButton cyanButton = new JRadioButton("Cyan");
        JRadioButton orangeButton = new JRadioButton("Orange");
        JRadioButton blackButton = new JRadioButton("Black");

        // button group ensures that only one radio button is selected at a time
        ButtonGroup radioButtonGroup = new ButtonGroup();
        radioButtonGroup.add(redButton);
        radioButtonGroup.add(blueButton);
        radioButtonGroup.add(greenButton);
        radioButtonGroup.add(yellowButton);
        radioButtonGroup.add(cyanButton);
        radioButtonGroup.add(orangeButton);
        radioButtonGroup.add(blackButton);

        // color shade buttons
        JButton darkerButton = new JButton("Darker");
        JButton brighterButton = new JButton("Brighter");


        // north panel
        JPanel northPanel = new JPanel();
        northPanel.setLayout(new FlowLayout());
        add(northPanel, BorderLayout.NORTH);

        // center panel
        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new FlowLayout());
        add(centerPanel, BorderLayout.CENTER);

        // color scroll bar panel
        JPanel colorScrollBarPanel = new JPanel();
        colorScrollBarPanel.setLayout(new GridLayout(3, 1));
        northPanel.add(colorScrollBarPanel);
        colorScrollBarPanel.add(redScrollBar);
        colorScrollBarPanel.add(blueScrollBar);
        colorScrollBarPanel.add(greenScrollBar);

        // color number panel
        JPanel colorNumberPanel = new JPanel();
        colorScrollBarPanel.setLayout(new GridLayout(3, 2));
        northPanel.add(colorNumberPanel);
        colorNumberPanel.add(redIntLabel);
        colorNumberPanel.add(redHexLabel);
        colorNumberPanel.add(blueIntLabel);
        colorNumberPanel.add(blueHexLabel);
        colorNumberPanel.add(greenIntLabel);
        colorNumberPanel.add(greenHexLabel);

        // color area panel
        JPanel colorAreaPanel = new JPanel();
        colorAreaPanel.setLayout(new CardLayout(100, 100));
        centerPanel.add(colorAreaPanel);
        colorAreaPanel.setAlignmentX(JComponent.CENTER_ALIGNMENT);
        colorAreaPanel.setBackground(new Color(r, b, g));

        // color radio button panel
        JPanel radioButtonPanel = new JPanel();
        radioButtonPanel.setLayout(new GridLayout(0, 1));
        centerPanel.add(radioButtonPanel);
        radioButtonPanel.add(redButton);
        radioButtonPanel.add(blueButton);
        radioButtonPanel.add(greenButton);
        radioButtonPanel.add(yellowButton);
        radioButtonPanel.add(cyanButton);
        radioButtonPanel.add(orangeButton);
        radioButtonPanel.add(blackButton);

        // color shade button panel
        JPanel shadeButtonPanel = new JPanel();
        shadeButtonPanel.setLayout(new GridLayout(2, 1));
        centerPanel.add(shadeButtonPanel);
        shadeButtonPanel.add(darkerButton);
        shadeButtonPanel.add(brighterButton);


        // color scroll bar adjustment listeners
        redScrollBar.addAdjustmentListener(l -> {
            r = l.getValue();
            colorAreaPanel.setBackground(new Color(r, g, b));
            redIntLabel.setText(Integer.toString(r));
            redHexLabel.setText(Integer.toHexString(r));
        });

        blueScrollBar.addAdjustmentListener(l -> {
            b = l.getValue();
            colorAreaPanel.setBackground(new Color(r, g, b));
            blueIntLabel.setText(Integer.toString(b));
            blueHexLabel.setText(Integer.toHexString(b));
        });

        greenScrollBar.addAdjustmentListener(l -> {
            g = l.getValue();
            colorAreaPanel.setBackground(new Color(r, g, b));
            greenIntLabel.setText(Integer.toString(g));
            greenHexLabel.setText(Integer.toHexString(g));
        });

        // radio button action listeners
        redButton.addActionListener(e -> {
            r = 255;
            b = 0;
            g = 0;
            colorAreaPanel.setBackground(new Color(r, g, b));
            redIntLabel.setText(Integer.toString(r));
            redHexLabel.setText(Integer.toHexString(r));
            blueIntLabel.setText(Integer.toString(b));
            blueHexLabel.setText(Integer.toHexString(b));
            greenIntLabel.setText(Integer.toString(g));
            greenHexLabel.setText(Integer.toHexString(g));
        });
        blueButton.addActionListener(e -> {
            r = 0;
            b = 255;
            g = 0;
            colorAreaPanel.setBackground(new Color(r, g, b));
            redIntLabel.setText(Integer.toString(r));
            redHexLabel.setText(Integer.toHexString(r));
            blueIntLabel.setText(Integer.toString(b));
            blueHexLabel.setText(Integer.toHexString(b));
            greenIntLabel.setText(Integer.toString(g));
            greenHexLabel.setText(Integer.toHexString(g));
        });
        greenButton.addActionListener(e -> {
            r = 0;
            b = 0;
            g = 255;
            colorAreaPanel.setBackground(new Color(r, g, b));
            redIntLabel.setText(Integer.toString(r));
            redHexLabel.setText(Integer.toHexString(r));
            blueIntLabel.setText(Integer.toString(b));
            blueHexLabel.setText(Integer.toHexString(b));
            greenIntLabel.setText(Integer.toString(g));
            greenHexLabel.setText(Integer.toHexString(g));
        });
        yellowButton.addActionListener(e -> {
            r = 255;
            b = 0;
            g = 255;
            colorAreaPanel.setBackground(new Color(r, g, b));
            redIntLabel.setText(Integer.toString(r));
            redHexLabel.setText(Integer.toHexString(r));
            blueIntLabel.setText(Integer.toString(b));
            blueHexLabel.setText(Integer.toHexString(b));
            greenIntLabel.setText(Integer.toString(g));
            greenHexLabel.setText(Integer.toHexString(g));
        });
        cyanButton.addActionListener(e -> {
            r = 0;
            b = 255;
            g = 255;
            colorAreaPanel.setBackground(new Color(r, g, b));
            redIntLabel.setText(Integer.toString(r));
            redHexLabel.setText(Integer.toHexString(r));
            blueIntLabel.setText(Integer.toString(b));
            blueHexLabel.setText(Integer.toHexString(b));
            greenIntLabel.setText(Integer.toString(g));
            greenHexLabel.setText(Integer.toHexString(g));
        });
        orangeButton.addActionListener(e -> {
            r = 255;
            b = 0;
            g = 165;
            colorAreaPanel.setBackground(new Color(r, g, b));
            redIntLabel.setText(Integer.toString(r));
            redHexLabel.setText(Integer.toHexString(r));
            blueIntLabel.setText(Integer.toString(b));
            blueHexLabel.setText(Integer.toHexString(b));
            greenIntLabel.setText(Integer.toString(g));
            greenHexLabel.setText(Integer.toHexString(g));
        });
        blackButton.addActionListener(e -> {
            r = 0;
            b = 0;
            g = 0;
            colorAreaPanel.setBackground(new Color(r, g, b));
            redIntLabel.setText(Integer.toString(r));
            redHexLabel.setText(Integer.toHexString(r));
            blueIntLabel.setText(Integer.toString(b));
            blueHexLabel.setText(Integer.toHexString(b));
            greenIntLabel.setText(Integer.toString(g));
            greenHexLabel.setText(Integer.toHexString(g));
        });

        // color share action listeners
        darkerButton.addActionListener(e -> {
            r = Math.max(0, r - 10);
            b = Math.max(0, b - 10);
            g = Math.max(0, g - 10);
            colorAreaPanel.setBackground(new Color(r, g, b));
            redIntLabel.setText(Integer.toString(r));
            redHexLabel.setText(Integer.toHexString(r));
            blueIntLabel.setText(Integer.toString(b));
            blueHexLabel.setText(Integer.toHexString(b));
            greenIntLabel.setText(Integer.toString(g));
            greenHexLabel.setText(Integer.toHexString(g));
        });
        brighterButton.addActionListener(e -> {
            r = Math.min(255, r + 10);
            b = Math.min(255, b + 10);
            g = Math.min(255, g + 10);
            colorAreaPanel.setBackground(new Color(r, g, b));
            redIntLabel.setText(Integer.toString(r));
            redHexLabel.setText(Integer.toHexString(r));
            blueIntLabel.setText(Integer.toString(b));
            blueHexLabel.setText(Integer.toHexString(b));
            greenIntLabel.setText(Integer.toString(g));
            greenHexLabel.setText(Integer.toHexString(g));
        });


        // menu items
        JMenuItem exitMenuItem = new JMenuItem("Exit");
        JMenuItem redMenuItem = new JMenuItem("Red");
        JMenuItem blueMenuItem = new JMenuItem("Blue");
        JMenuItem greenMenuItem = new JMenuItem("Green");
        JMenuItem yellowMenuItem = new JMenuItem("Yellow");
        JMenuItem cyanMenuItem = new JMenuItem("Cyan");
        JMenuItem orangeMenuItem = new JMenuItem("Orange");
        JMenuItem blackMenuItem = new JMenuItem("Black");

        // menus
        JMenu exitMenu = new JMenu("File");
        exitMenu.add(exitMenuItem);

        JMenu colorMenu = new JMenu("Colors");
        colorMenu.add(redMenuItem);
        colorMenu.add(blueMenuItem);
        colorMenu.add(greenMenuItem);
        colorMenu.add(yellowMenuItem);
        colorMenu.add(cyanMenuItem);
        colorMenu.add(orangeMenuItem);
        colorMenu.add(blackMenuItem);

        // menu bar
        JMenuBar menuBar = new JMenuBar();
        setJMenuBar(menuBar);
        menuBar.add(exitMenu);
        menuBar.add(colorMenu);

        // menu item action listeners
        exitMenuItem.addActionListener(e -> System.exit(0));
        redMenuItem.addActionListener(e -> {
            r = 255;
            b = 0;
            g = 0;
            colorAreaPanel.setBackground(new Color(r, g, b));
            redIntLabel.setText(Integer.toString(r));
            redHexLabel.setText(Integer.toHexString(r));
            blueIntLabel.setText(Integer.toString(b));
            blueHexLabel.setText(Integer.toHexString(b));
            greenIntLabel.setText(Integer.toString(g));
            greenHexLabel.setText(Integer.toHexString(g));
        });
        blueMenuItem.addActionListener(e -> {
            r = 0;
            b = 255;
            g = 0;
            colorAreaPanel.setBackground(new Color(r, g, b));
            redIntLabel.setText(Integer.toString(r));
            redHexLabel.setText(Integer.toHexString(r));
            blueIntLabel.setText(Integer.toString(b));
            blueHexLabel.setText(Integer.toHexString(b));
            greenIntLabel.setText(Integer.toString(g));
            greenHexLabel.setText(Integer.toHexString(g));
        });
        greenMenuItem.addActionListener(e -> {
            r = 0;
            b = 0;
            g = 255;
            colorAreaPanel.setBackground(new Color(r, g, b));
            redIntLabel.setText(Integer.toString(r));
            redHexLabel.setText(Integer.toHexString(r));
            blueIntLabel.setText(Integer.toString(b));
            blueHexLabel.setText(Integer.toHexString(b));
            greenIntLabel.setText(Integer.toString(g));
            greenHexLabel.setText(Integer.toHexString(g));
        });
        yellowMenuItem.addActionListener(e -> {
            r = 255;
            b = 0;
            g = 255;
            colorAreaPanel.setBackground(new Color(r, g, b));
            redIntLabel.setText(Integer.toString(r));
            redHexLabel.setText(Integer.toHexString(r));
            blueIntLabel.setText(Integer.toString(b));
            blueHexLabel.setText(Integer.toHexString(b));
            greenIntLabel.setText(Integer.toString(g));
            greenHexLabel.setText(Integer.toHexString(g));
        });
        cyanMenuItem.addActionListener(e -> {
            r = 0;
            b = 255;
            g = 255;
            colorAreaPanel.setBackground(new Color(r, g, b));
            redIntLabel.setText(Integer.toString(r));
            redHexLabel.setText(Integer.toHexString(r));
            blueIntLabel.setText(Integer.toString(b));
            blueHexLabel.setText(Integer.toHexString(b));
            greenIntLabel.setText(Integer.toString(g));
            greenHexLabel.setText(Integer.toHexString(g));
        });
        orangeMenuItem.addActionListener(e -> {
            r = 255;
            b = 0;
            g = 165;
            colorAreaPanel.setBackground(new Color(r, g, b));
            redIntLabel.setText(Integer.toString(r));
            redHexLabel.setText(Integer.toHexString(r));
            blueIntLabel.setText(Integer.toString(b));
            blueHexLabel.setText(Integer.toHexString(b));
            greenIntLabel.setText(Integer.toString(g));
            greenHexLabel.setText(Integer.toHexString(g));
        });
        blackMenuItem.addActionListener(e -> {
            r = 0;
            b = 0;
            g = 0;
            colorAreaPanel.setBackground(new Color(r, g, b));
            redIntLabel.setText(Integer.toString(r));
            redHexLabel.setText(Integer.toHexString(r));
            blueIntLabel.setText(Integer.toString(b));
            blueHexLabel.setText(Integer.toHexString(b));
            greenIntLabel.setText(Integer.toString(g));
            greenHexLabel.setText(Integer.toHexString(g));
        });


        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame f = new ColorPicker();
            f.pack();
            f.setVisible(true);
        });
    }
}

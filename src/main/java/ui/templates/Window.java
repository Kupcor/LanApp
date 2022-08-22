package ui.templates;

import javax.swing.*;
import javax.swing.plaf.nimbus.NimbusLookAndFeel;
import java.awt.*;

public abstract class Window extends JFrame {
    protected int width;
    protected int height;
    ImageIcon imageIcon = new ImageIcon("src\\\\externalResources\\\\logo\\\\FinAppLogo.png");

    protected Window(int width, int height) {
        assert width > 0 : "Window width must be greater than 0.";
        assert height > 0 : "Window height must be greater than 0.";

        this.width = width;
        this.height = height;

        this.setSize(new Dimension(this.width, this.height));
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setTitle("LanApp");
        this.setResizable(false);
        this.setVisible(true);
    }
}
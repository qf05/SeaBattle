package ru.levspb.view;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class MainJPanel extends JPanel {

    private Image image;

    public MainJPanel() {
        try {
            image = ImageIO.read(new File("resource\\background.png"));
            Toolkit toolkit = Toolkit.getDefaultToolkit();
            Toolkit.getDefaultToolkit().setDynamicLayout(true);
            Dimension dimension = toolkit.getScreenSize();
            image = image.getScaledInstance(dimension.width, dimension.height, Image.SCALE_SMOOTH);
        } catch (IOException ioe) {
            System.out.println("Could not read in the pic");
        }
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(image, 0, 0, this);
    }
}


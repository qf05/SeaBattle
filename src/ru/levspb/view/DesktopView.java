package ru.levspb.view;

import ru.levspb.model.Field;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class DesktopView {

    public static final Dimension DIMENSION = getDimension();


    private DesktopView() {
    }

    public static void openWindow(int rows, int columns) {
        JFrame jFrame = new JFrame() {
        };
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jFrame.setTitle("Sea Battle");
        int height = DIMENSION.height;
        jFrame.setLocation(DIMENSION.width / 4, height / 5);
        GridLayout layout = new GridLayout(rows, columns);
        Border margin = new EmptyBorder(height / 40, height / 40, height / 40, height / 40);
        JPanel jPanel = new MainJPanel();
        jPanel.setBorder(margin);
        jPanel.setLayout(layout);
        jFrame.add(jPanel);
        for (FieldButton button : Field.buttonsMap.values()) {
            jPanel.add(button);
        }
        jFrame.pack();
        jFrame.setVisible(true);
    }

    private static Dimension getDimension() {
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Toolkit.getDefaultToolkit().setDynamicLayout(true);
        return toolkit.getScreenSize();
    }
}

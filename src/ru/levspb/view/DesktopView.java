package ru.levspb.view;

import ru.levspb.enums.FieldsName;
import ru.levspb.model.Field;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class DesktopView {

    public static final Dimension DIMENSION = getDimension();
    public static final int CELL_SIZE = DIMENSION.height / 20;


    private DesktopView() {
    }

    public static void openWindow(int rows, int columns) {
        JFrame jFrame = new JFrame() {
        };
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jFrame.setTitle("Sea Battle");
        int height = DIMENSION.height;
        jFrame.setLocation(DIMENSION.width / 4, height / 5);
        GridLayout layout = new GridLayout(rows + 2, columns + 2);
        Border margin = new EmptyBorder(height / 40, height / 40, height / 40, height / 40);
        JPanel jPanel = new MainJPanel();
        jPanel.setBorder(margin);
        jPanel.setLayout(layout);
        jFrame.add(jPanel);
        printLetters(jPanel, columns);
        final List<FieldButton> buttons = new ArrayList<>(Field.buttonsMap.values());
        for (int i = 0; i < columns; i++) {
            jPanel.add(new FieldNameJLabel("" + (i + 1)));
            for (int j = 0; j < rows; j++) {
                jPanel.add(buttons.get(i * columns + j));
            }
            jPanel.add(new FieldNameJLabel("" + (i + 1)));
        }
        printLetters(jPanel, columns);
        jFrame.pack();
        jFrame.setVisible(true);
    }

    private static Dimension getDimension() {
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Toolkit.getDefaultToolkit().setDynamicLayout(true);
        return toolkit.getScreenSize();
    }

    private static void printLetters(JPanel jPanel, int columns) {
        jPanel.add(new FieldNameJLabel(""));
        for (int i = 0; i < columns; i++) {
            jPanel.add(new FieldNameJLabel(FieldsName.getByValue(i).name()));
        }
        jPanel.add(new FieldNameJLabel(""));
    }
}

package ru.levspb.view;

import javax.swing.*;
import java.awt.*;

import static ru.levspb.view.DesktopView.CELL_SIZE;

public class FieldNameJLabel extends JLabel {
    public FieldNameJLabel(String text) {
        super(text, SwingConstants.CENTER);
        this.setFont(new Font(Font.DIALOG, Font.BOLD, DesktopView.DIMENSION.height / 40));
        this.setPreferredSize(new Dimension(CELL_SIZE, CELL_SIZE));
    }
}

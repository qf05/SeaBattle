package ru.levspb.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

import static ru.levspb.view.DesktopView.CELL_SIZE;

public class FieldButton extends JToggleButton {
    private boolean transparent;
    private ImageIcon img;

    private ActionListener actionListener;

    public FieldButton(String name) {
        super();
        this.transparent = false;
        this.setRolloverEnabled(true);
        this.setMargin(new Insets(0, 0, 0, 0));
        this.setPreferredSize(new Dimension(CELL_SIZE, CELL_SIZE));
        this.setName(name);
        this.setBackground(Color.WHITE);
        this.addComponentListener(getComponentAdapter());
        this.actionListener = new MyActionListener(name);
        this.addActionListener(actionListener);
    }

    public void setImg(ImageIcon img) {
        this.img = img;
        resizeBoomIcon();
    }

    public boolean isTransparent() {
        return transparent;
    }

    public void setTransparent() {
        this.transparent = true;
        this.setIcon(null);
        this.removeActionListener();
    }

    public void removeActionListener() {
        this.setOpaque(false);
        this.setFocusPainted(false);
        this.setContentAreaFilled(false);
        this.removeActionListener(actionListener);
        this.setIgnoreRepaint(true);
    }

    private ComponentAdapter getComponentAdapter() {
        return new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                super.componentResized(e);
                resizeBoomIcon();
            }
        };
    }

    private void resizeBoomIcon() {
        if (!transparent && img != null) {
            Dimension size = this.getSize();
            Insets insets = this.getInsets();
            size.width -= insets.left + insets.right;
            size.height -= insets.top + insets.bottom;
            if (size.width > size.height) {
                size.width = -1;
            } else {
                size.height = -1;
            }
            Image scaled = img.getImage().getScaledInstance(size.width, size.height, Image.SCALE_SMOOTH);
            this.setIcon(new ImageIcon(scaled));
        }
    }
}
package ru.levspb.view;

import ru.levspb.enums.ShotResult;
import ru.levspb.logic.GameLogic;

import javax.swing.*;
import java.awt.event.ActionEvent;

import static ru.levspb.model.Field.buttonsMap;

public class MyActionListener extends AbstractAction {

    private static final String IMG_BOOM_FILE = "resource\\boom.png";

    private static final ImageIcon IMG_BOOM = new ImageIcon(IMG_BOOM_FILE);

    private final String key;

    public MyActionListener(String key) {
        this.key = key;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        FieldButton button = buttonsMap.get(key);
        System.out.println(button.getName());
        System.out.println(button.isTransparent());
        ShotResult shotResult = GameLogic.shot(button.getName());
        switch (shotResult) {
            case KILL:
            case HIT:
                button.setImg(IMG_BOOM);
                break;
            case END_OF_GAME:
                JOptionPane.showMessageDialog(button, "YOU WIN!!!");
            default:
                button.setTransparent();
        }
        button.setEnabled(false);
        button.setFocusPainted(false);
    }
}

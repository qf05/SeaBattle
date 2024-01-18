package ru.levspb.view;

import ru.levspb.Main;
import ru.levspb.enums.ShotResult;
import ru.levspb.logic.GameLogic;

import javax.swing.*;
import java.awt.event.ActionEvent;

import static ru.levspb.model.Field.buttonsMap;

public class MyActionListener extends AbstractAction {

    private static int move_count = 0;
    private static final String IMG_BOOM_FILE = "resource\\boom.png";

    private static final ImageIcon IMG_BOOM = new ImageIcon(IMG_BOOM_FILE);

    private final String key;

    public MyActionListener(String key) {
        this.key = key;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        move_count++;
        FieldButton button = buttonsMap.get(key);
        System.out.println(button.getName());
        System.out.println(button.isTransparent());
        ShotResult shotResult = GameLogic.shot(button.getName());
        switch (shotResult) {
            case KILL:
            case HIT:
                button.setImg(IMG_BOOM);
                button.removeActionListener();
                break;
            case END_OF_GAME:
                JOptionPane.showMessageDialog(button, "YOU WIN!!! \n You needed " + move_count + "moves.");
                move_count = 0;
                Main.startGame();
            default:
                button.setTransparent();
        }
    }
}

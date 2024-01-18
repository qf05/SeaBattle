package ru.levspb;

import ru.levspb.logic.GameLogic;
import ru.levspb.view.DesktopView;

import javax.swing.*;

public class Main {
    private static int width = 10;
    private static int length = 10;
    private static int fourDecker = 1;
    private static int threeDecker = 2;
    private static int twoDecker = 3;
    private static int oneDecker = 4;
    public static void main(String[] args) {
        startGame();
    }

    public static void startGame(){
        GameLogic.startGame(width, length, fourDecker, threeDecker, twoDecker, oneDecker);
        DesktopView.openWindow(width,length);
    }
}
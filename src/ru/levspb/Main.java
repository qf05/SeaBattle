package ru.levspb;

import ru.levspb.enums.ShipSize;
import ru.levspb.enums.ShotResult;
import ru.levspb.logic.GameLogic;
import ru.levspb.logic.PrintField;
import ru.levspb.model.Field;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import static ru.levspb.enums.ShipSize.*;

public class Main {
    public static void main(String[] args) {
        Field.instanceField(10, 10);
        Map<ShipSize, Integer> ships = new HashMap<>();
        ships.put(FOUR_DECKER, 2);
        ships.put(THREE_DECKER, 2);
        ships.put(TWO_DECKER, 3);
        ships.put(ONE_DECKER, 4);
        GameLogic.initShips(ships);
        Scanner scanner = new Scanner(System.in);
        int moves = 0;
        while (true) {
            PrintField.printField();
            String shot = scanner.next();
            moves++;
            if (ShotResult.END_OF_GAME == GameLogic.shot(shot)) {
                break;
            }
        }
        PrintField.printField();
        System.out.println("CONGRATULATIONS!!!");
        System.out.println("You won in " + moves + " moves.");
    }
}
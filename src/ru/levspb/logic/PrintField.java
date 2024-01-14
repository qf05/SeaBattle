package ru.levspb.logic;

import ru.levspb.enums.FieldsName;
import ru.levspb.model.CellOfField;

import static ru.levspb.model.Field.*;

public class PrintField {

    private static final PrintField INSTANCE = new PrintField();

    private PrintField() {
    }

    public static void printField() {
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        char[][] field = FIELD.getField();

        INSTANCE.printLetter(field[0].length);
        for (int i = 0; i < field.length; i++) {
            INSTANCE.printLeftNumber(i + 1);
            for (int j = 0; j < field[0].length; j++) {
                System.out.print("[" + field[i][j] + "]");
            }
            System.out.println(" " + (i + 1));
        }
        INSTANCE.printLetter(field[0].length);
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
    }

    public static void setOccupiedFields(String[] shipCoordinate) {
        for (String coordinate : shipCoordinate) {
            CellOfField cell = CellOfField.cellOfField(coordinate);
            int x = cell.getX();
            int y = cell.getY();
            FIELD.getField()[y][x] = SHIP_FIELD;
            INSTANCE.aroundShip(y, x);
        }
    }

    private void printLeftNumber(int number) {
        String length = number + "";
        for (int i = 0; i < 5 - length.length(); i++) {
            System.out.print(" ");
        }
        System.out.print(number + " ");
    }

    private void printLetter(int lenght) {
        System.out.print("       ");
        for (int i = 0; i < lenght; i++) {
            System.out.print(FieldsName.getByValue(i).name() + "  ");
        }
        System.out.println();
    }

    private void aroundShip(int y, int x) {
        for (int i = y - 1; i <= y + 1; i++) {
            for (int j = x - 1; j <= x + 1; j++) {
                if (i >= 0 && j >= 0 && i < FIELD.getWidth() && j < FIELD.getLength() &&
                        SHIP_FIELD != FIELD.getField()[i][j] && SHOT_FIELD != FIELD.getField()[i][j]) {
                    FIELD.getField()[i][j] = SHOT_FIELD;
                }
            }
        }
    }

}

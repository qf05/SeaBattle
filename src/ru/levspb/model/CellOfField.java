package ru.levspb.model;

import ru.levspb.enums.FieldsName;

import static ru.levspb.model.Field.FIELD;

public class CellOfField {

    private final int xCoordinate;

    private final int yCoordinate;

    private CellOfField(int xCoordinate, int yCoordinate) {
        this.xCoordinate = xCoordinate;
        this.yCoordinate = yCoordinate;
    }

    public static CellOfField cellOfField(String input) {
        try {
            input = input.toUpperCase().replace(" ", "").trim();
            String letter = input.substring(0, 1);
            String digit = null;
            if (input.length() == 2) {
                digit = input.substring(1, 2);
            }
            if (input.length() == 3) {
                digit = input.substring(1, 3);
            }
            int x = FieldsName.valueOf(letter).DIGIT_FIELD_NAME;
            int y = Integer.parseInt(digit) - 1;
            if (x <= FIELD.getLength() && y <= FIELD.getWidth() && x >= 0 && y >= 0) {
                return new CellOfField(x, y);
            }
        } catch (Exception e) {
            System.out.println("Input error coordinate");
        }
        throw new IllegalArgumentException("Input error coordinate");
    }

    public int getX() {
        return xCoordinate;
    }

    public int getY() {
        return yCoordinate;
    }
}

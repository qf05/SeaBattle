package ru.levspb.model;

import ru.levspb.enums.FieldsName;
import ru.levspb.view.FieldButton;

import java.util.LinkedHashMap;
import java.util.Map;

public class Field {

    public static final char INIT_FIELD = '.';
    public static final char SHOT_FIELD = 'O';

    public static final char SHIP_FIELD = 'X';

    public static final Map<String, FieldButton> buttonsMap = new LinkedHashMap<>();

    public static Field FIELD;
    private final int width;

    private final int length;
    private final char[][] field;

    private Field(int width, int length) {
        this.width = width;
        this.length = length;
        field = new char[width][length];
        initField(field);
    }

    public static void instanceField(int width, int length) {
        FIELD = new Field(width, length);
    }

    public static void cleanField() {
        FIELD.initField(FIELD.getField());
    }

    public int getWidth() {
        return width;
    }

    public int getLength() {
        return length;
    }

    public char[][] getField() {
        return field;
    }

    private void initField(char[][] field) {
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < length; j++) {
                field[i][j] = INIT_FIELD;
                String fieldName = FieldsName.getByValue(i) + "" + (j + 1);
                FieldButton button = new FieldButton(fieldName);
                buttonsMap.put(fieldName, button);
            }
        }
    }
}

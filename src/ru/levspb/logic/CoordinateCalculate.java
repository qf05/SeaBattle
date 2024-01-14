package ru.levspb.logic;

import ru.levspb.enums.FieldsName;
import ru.levspb.enums.ShipSize;

import java.util.ArrayList;
import java.util.List;

import static ru.levspb.model.Field.FIELD;
import static ru.levspb.model.Field.INIT_FIELD;

public class CoordinateCalculate {

    private static final CoordinateCalculate INSTANCE = new CoordinateCalculate();

    private CoordinateCalculate() {
    }

    public static String[] generateCoordinate(ShipSize shipSize) {
        List<String[]> possibleCoordinate = INSTANCE.getPossibleCoordinates(shipSize);
        if (possibleCoordinate.isEmpty()) {
            throw new NullPointerException("Not possible coordinate");
        }
        String[] coordinate = possibleCoordinate.get((int) (Math.random() * (possibleCoordinate.size() - 1)));
        PrintField.setOccupiedFields(coordinate);
        return coordinate;
    }

    private List<String[]> getPossibleCoordinates(ShipSize shipSize) {
        List<String[]> possibleCoordinates = new ArrayList<>();
        for (int i = 0; i < FIELD.getWidth(); i++) {
            possibleCoordinates.addAll(secondIteration(shipSize, i));
        }
        return possibleCoordinates;
    }

    private List<String[]> secondIteration(ShipSize shipSize, int firstIteration) {
        List<String[]> possibleCoordinate = new ArrayList<>();
        for (int j = 0; j < FIELD.getLength(); j++) {
            possibleCoordinate.addAll(calculateShipCoordinate(shipSize, firstIteration, j));
        }
        return possibleCoordinate;
    }

    private List<String[]> calculateShipCoordinate(ShipSize shipSize, int y, int x) {
        List<String[]> coordinates = new ArrayList<>();
        String[] coordinate = checkLine(shipSize, y, x);
        if (coordinate != null) {
            coordinates.add(coordinate);
        }
        coordinate = checkColumn(shipSize, y, x);
        if (coordinate != null) {
            coordinates.add(coordinate);
        }
        return coordinates;
    }

    private String[] checkLine(ShipSize shipSize, int y, int x) {
        if (x <= FIELD.getLength() - shipSize.DECK_COUNT) {
            String[] coordinate = new String[shipSize.DECK_COUNT];
            boolean canSetShip = true;
            for (int k = 0; k < shipSize.DECK_COUNT && canSetShip; k++) {
                coordinate[k] = checkLineCell(y, x, k);
                if (coordinate[k] == null) {
                    canSetShip = false;
                }
            }
            if (canSetShip) {
                return coordinate;
            }
        }
        return null;
    }

    private String[] checkColumn(ShipSize shipSize, int y, int x) {
        if (y <= FIELD.getWidth() - shipSize.DECK_COUNT) {
            String[] coordinate = new String[shipSize.DECK_COUNT];
            boolean canSetShip = true;
            for (int k = 0; k < shipSize.DECK_COUNT && canSetShip; k++) {
                coordinate[k] = checkColumnCell(y, x, k);
                if (coordinate[k] == null) {
                    canSetShip = false;
                }
            }
            if (canSetShip) {
                return coordinate;
            }
        }
        return null;
    }

    private String checkLineCell(int y, int x, int k) {
        if (INIT_FIELD == FIELD.getField()[y][x + k]) {
            return FieldsName.getByValue(x + k).name() + (y + 1);
        } else {
            return null;
        }
    }

    private String checkColumnCell(int y, int x, int k) {
        if (INIT_FIELD == FIELD.getField()[y + k][x]) {
            return FieldsName.getByValue(x).name() + (y + k + 1);
        } else {
            return null;
        }
    }


}

package ru.levspb.logic;

import ru.levspb.enums.ShipSize;
import ru.levspb.enums.ShotResult;
import ru.levspb.model.CellOfField;
import ru.levspb.model.Ship;

import java.util.*;

import static ru.levspb.enums.ShotResult.*;
import static ru.levspb.model.Field.*;

public class GameLogic {

    private static final GameLogic INSTANCE = new GameLogic();
    private static final List<Ship> ships = new ArrayList<>();

    private GameLogic() {
    }

    public static void initShips(Map<ShipSize, Integer> shipsMap) {
        cleanField();
        shipsMap.keySet().stream()
                .sorted(Collections.reverseOrder(Comparator.comparingInt(o -> o.DECK_COUNT)))
                .forEach(i -> {
                    int count = shipsMap.get(i);
                    for (int j = 0; j < count; j++) {
                        ships.add(new Ship(i));
                    }
                });
        cleanField();
    }

    public static ShotResult shot(String userInput) {
        userInput = userInput.toUpperCase().replaceAll(" ", "").trim();
        ShotResult shotResult = INSTANCE.checkHit(userInput);
        System.out.println(shotResult.shotResult);
        if (ShotResult.KILL == shotResult && INSTANCE.checkFinishGame()) {
            shotResult = END_OF_GAME;
            System.out.println(shotResult.shotResult);
        }
        return shotResult;
    }

    private ShotResult checkHit(String userInput) {
        CellOfField shot = CellOfField.cellOfField(userInput);
        if (INIT_FIELD != FIELD.getField()[shot.getY()][shot.getX()]) {
            return MISSED;
        }
        for (Ship ship : ships) {
            if (ship.isLive()) {
                for (String coordinate : ship.getCoordinates()) {
                    if (coordinate.equals(userInput)) {
                        FIELD.getField()[shot.getY()][shot.getX()] = SHIP_FIELD;
                        return hitTheShip(ship);
                    }
                }
            }
        }
        FIELD.getField()[shot.getY()][shot.getX()] = SHOT_FIELD;
        return MISSED;
    }

    private ShotResult hitTheShip(Ship ship) {
        ship.setLives(ship.getLives() - 1);
        if (ship.getLives() == 0) {
            ship.setLive(false);
            PrintField.setOccupiedFields(ship.getCoordinates());
            return KILL;
        } else {
            return HIT;
        }
    }

    private boolean checkFinishGame() {
        return ships.stream().noneMatch(Ship::isLive);
    }

}

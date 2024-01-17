package ru.levspb.logic;

import ru.levspb.enums.ShipSize;
import ru.levspb.enums.ShotResult;
import ru.levspb.model.CellOfField;
import ru.levspb.model.Field;
import ru.levspb.model.Ship;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import static ru.levspb.enums.ShipSize.*;
import static ru.levspb.enums.ShotResult.*;
import static ru.levspb.model.Field.*;

public class GameLogic {

    private static final GameLogic INSTANCE = new GameLogic();
    private static final List<Ship> ships = new ArrayList<>();

    private GameLogic() {
    }

    public static void startGame(int width, int length, int fourDeckerCount, int treeDeckerCount, int twoDeckerCount, int oneDeckerCount) {
        Field.instanceField(width, length);
        Map<ShipSize, Integer> ships = new LinkedHashMap<>();
        ships.put(FOUR_DECKER, fourDeckerCount);
        ships.put(THREE_DECKER, treeDeckerCount);
        ships.put(TWO_DECKER, twoDeckerCount);
        ships.put(ONE_DECKER, oneDeckerCount);
        INSTANCE.initShips(ships);
//        Scanner scanner = new Scanner(System.in);
//        int moves = 0;
//        while (true) {
//            PrintField.printField();
//            String shot = scanner.next();
//            moves++;
//            if (ShotResult.END_OF_GAME == shot(shot)) {
//                break;
//            }
//        }
//        PrintField.printField();
//        System.out.println("CONGRATULATIONS!!!");
//        System.out.println("You won in " + moves + " moves.");
    }

    private void initShips(Map<ShipSize, Integer> shipsMap) {
        cleanField();
        shipsMap.forEach((shipSize, countShips) -> {
            for (int i = 0; i < countShips; i++) {
                ships.add(new Ship(shipSize));
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

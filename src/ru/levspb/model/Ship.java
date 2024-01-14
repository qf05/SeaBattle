package ru.levspb.model;

import ru.levspb.enums.ShipSize;
import ru.levspb.logic.CoordinateCalculate;

public class Ship {

    private final int size;

    private final String[] coordinates;

    private int lives;

    private boolean isLive;

    public Ship(ShipSize size) {
        this.size = size.DECK_COUNT;
        this.lives = size.DECK_COUNT;
        this.isLive = true;
        this.coordinates = CoordinateCalculate.generateCoordinate(size);
    }

    public int getSize() {
        return size;
    }

    public String[] getCoordinates() {
        return coordinates;
    }

    public boolean isLive() {
        return isLive;
    }

    public void setLive(boolean live) {
        isLive = live;
    }

    public int getLives() {
        return lives;
    }

    public void setLives(int lives) {
        this.lives = lives;
    }
}

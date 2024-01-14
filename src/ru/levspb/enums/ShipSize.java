package ru.levspb.enums;

public enum ShipSize {
    FOUR_DECKER(4),
    THREE_DECKER(3),
    TWO_DECKER(2),
    ONE_DECKER(1);

    public final int DECK_COUNT;

    ShipSize(int deckCount) {
        this.DECK_COUNT = deckCount;
    }
}

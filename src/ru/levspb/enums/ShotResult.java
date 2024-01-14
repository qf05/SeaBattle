package ru.levspb.enums;

public enum ShotResult {
    MISSED("Мимо"),
    HIT("Ранил"),
    KILL("Убил"),
    END_OF_GAME("Победа!");

    public final String shotResult;

    ShotResult(String shotResult) {
        this.shotResult = shotResult;
    }
}

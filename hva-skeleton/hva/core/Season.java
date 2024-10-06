package hva.core;

public enum Season {
    Winter, Spring, Summer, Autumn;

    public Season nextSeason() {
        return values()[(this.ordinal() + 1) % 4];
    }
}

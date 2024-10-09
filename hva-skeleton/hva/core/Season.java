package hva.core;

import java.io.Serializable;

public enum Season implements Serializable {
    Spring, Summer, Autumn, Winter;

    public Season nextSeason() {
        return values()[(this.ordinal() + 1) % 4];
    }
}

package hva.core;

import java.io.Serializable;

public abstract class Tree implements Serializable {

    //Atributes
    protected Season season;
    protected int age;
    protected int difficultyClean;

    // Constructor
    public Tree(int age, int diffClean) {
        this.age = age;
        this.difficultyClean = diffClean;
    }

    // Getters
    //Methods
    void nextSeasonCall() {
        this.season = season.nextSeason();
    }

    abstract int seasonalEffort();

    abstract int cleaningEffort();

}

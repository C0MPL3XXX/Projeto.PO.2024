package hva.core;

public abstract class Tree extends HotelEntity {

    //Atributes
    protected Season season;
    protected int age;
    protected int difficultyClean;
    protected int seasonsPassed;

    // Constructor
    public Tree(int age, int diffClean, String id, String name) {
        super(id, name);
        this.age = age;
        this.difficultyClean = diffClean;
    }

    //Methods
    void nextSeason() {
        this.season = season.nextSeason();
        seasonsPassed += 1;
        if (seasonsPassed % 4 == 0) {
            age += 1;
        }
    }

    abstract int seasonalEffort();

    abstract int cleaningEffort();

}

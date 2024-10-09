package hva.core;

public class DeciduousTree extends Tree {

    //Constructor
    public DeciduousTree(int a, int diffClean) {
        super(a, diffClean);
    }

    int seasonalEffort() {
        switch (season) {
            case Spring:
                return 1;
            case Summer:
                return 2;
            case Autumn:
                return 5;
            case Winter:
                return 0;
            default:
                return -1; //This value should never be used, if it is we will be able to tell
        }
    }

    int cleaningEffort() {
        return (int) Math.round(this.difficultyClean * this.seasonalEffort() * Math.log(this.age + 1));
    }

}

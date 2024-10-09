package hva.core;

public class EvergreenTree extends Tree {

    //Constructor
    public EvergreenTree(int a, int diffClean) {
        super(a, diffClean);
    }

    int seasonalEffort() {
        switch (season) {
            case Spring:
                return 1;
            case Summer:
                return 1;
            case Autumn:
                return 1;
            case Winter:
                return 2;
            default:
                return -1; //This value should never be used, if it is we will be able to tell
        }
    }

    int cleaningEffort() {
        return (int) Math.round(this.difficultyClean * this.seasonalEffort() * Math.log(this.age + 1));
    }

}

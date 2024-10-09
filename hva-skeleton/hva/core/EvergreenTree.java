package hva.core;

public class EvergreenTree extends Tree {

    //Constructor
    public EvergreenTree(int a, int diffClean) {
        super(a, diffClean);
    }

    @Override
    int seasonalEffort() {
        return switch (season) {
            case Spring ->
                1;
            case Summer ->
                1;
            case Autumn ->
                1;
            case Winter ->
                2;
            default ->
                -1;
        }; //This value should never be used, if it is we will be able to tell
    }

    @Override
    int cleaningEffort() {
        return (int) Math.round(this.difficultyClean * this.seasonalEffort() * Math.log(this.age + 1));
    }

}

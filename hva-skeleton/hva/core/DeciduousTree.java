package hva.core;

public class DeciduousTree extends Tree {

    //Constructor
    public DeciduousTree(int a, int diffClean) {
        super(a, diffClean);
    }

    @Override
    int seasonalEffort() {
        return switch (season) {
            case Spring ->
                1;
            case Summer ->
                2;
            case Autumn ->
                5;
            case Winter ->
                0;
            default ->
                -1;
        }; //This value should never be used, if it is we will be able to tell
    }

    @Override
    int cleaningEffort() {
        return (int) Math.round(this.difficultyClean * this.seasonalEffort() * Math.log(this.age + 1));
    }

}

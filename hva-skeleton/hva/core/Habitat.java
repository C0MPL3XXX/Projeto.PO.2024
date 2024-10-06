package hva.core;

import java.util.TreeMap;

public class Habitat {

    private String UniqueId;
    private String name;
    private int area;
    private TreeMap<String, Animal> animals = new TreeMap<>();
    private TreeMap<String, Tree> trees = new TreeMap<>();
    private TreeMap<String, Integer> speciesImpact = new TreeMap<>();

    public TreeMap<String, Animal> getAnimals() {
        return this.animals;
    }

    public int getArea() {
        return this.area;
    }

    public TreeMap<String, Integer> getSpeciesImpact() {
        return this.speciesImpact;
    }
    //int impactOf(Specie s){}
}

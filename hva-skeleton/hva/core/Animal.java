package hva.core;

import java.io.Serializable;
import java.util.*;

public class Animal implements Serializable {

    private String uniqueId;
    private String name;
    private ArrayList<Integer> health = new ArrayList<Integer>();
    private Habitat currentHabitat;
    private Species species;

    public Animal(String id, String n, Habitat habitat, Species s) {
        this.uniqueId = id;
        this.name = n;
        this.currentHabitat = habitat;
        this.species = s;
    }

    public Species getSpecies() {
        return this.species;
    }

    int sameSpecies() {
        int same = 0;
        for (Animal c : currentHabitat.getAnimals().values()) {
            if (this.species.equals(c.getSpecies())) {
                same += 1;
            }
        }
        return same;
    }

    public int difSpecies() {
        return this.currentHabitat.getAnimals().size() - this.sameSpecies();
    }

    int computeSatisfaction() {
        int satisfaction = 20 + (3 * this.sameSpecies()) - (2 * this.difSpecies())
                + (this.currentHabitat.getArea() / this.currentHabitat.getAnimals().size())
                + this.currentHabitat.getSpeciesImpact().get(this.getSpecies().getId()).intValue();

        return satisfaction;
    }

    //public String toString(){}
    //private transferTo(Habitat h){}
}

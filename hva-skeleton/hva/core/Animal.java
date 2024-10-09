package hva.core;

import java.io.Serializable;
import java.util.*;

public class Animal implements Serializable {

    //Atributes
    private String uniqueId;
    private String name;
    private ArrayList<String> health = new ArrayList<String>();
    private Habitat currentHabitat;
    private Species species;

    //Constructor
    public Animal(String id, String n, Habitat habitat, Species s) {
        this.uniqueId = id;
        this.name = n;
        this.currentHabitat = habitat;
        this.species = s;
    }

    // Getters
    public Species getSpecies() {
        return this.species;
    }

    public String getUniqueId() {
        return this.uniqueId;
    }

    // Methods
    int sameSpecies() { //Passa por todos os animais no habitat e recorda quais são da mesma especie
        int same = 0;
        for (Animal c : currentHabitat.getAnimals().values()) {
            if (this.species.equals(c.getSpecies())) {
                same += 1;
            }
        }
        return same;
    }

    public int difSpecies() {
        return this.currentHabitat.getAnimals().size() - this.sameSpecies(); // Usando sameSpecies arranja-se difSpecies mais facilmente em conjunto da população
    }

    int computeSatisfaction() {     // Usado para saber a satisfação de um animal
        int satisfaction = 20 + (3 * this.sameSpecies()) - (2 * this.difSpecies())
                + (this.currentHabitat.getArea() / this.currentHabitat.getAnimals().size())
                + this.currentHabitat.getSpeciesImpact().get(this.getSpecies().getId()).intValue(); // Igual à formula no enunciado, usando varios metados

        return satisfaction;
    }

    public String toString() { // Mostra informação do animal em string que pode ser usado para um print
        if (this.health.isEmpty()) {
            return "ANIMAL|" + this.uniqueId + "|" + this.name + "|" + this.getSpecies().getId()
                    + "|VOID|" + this.currentHabitat.getUniqueId();
        }
        return "ANIMAL|" + this.uniqueId + "|" + this.name + "|" + this.getSpecies().getId()
                + "|" + this.health + "|" + this.currentHabitat.getUniqueId();
    }

    private void transferTo(Habitat h) { // Muda de habitat
        this.currentHabitat.remove(this);
        h.add(this);
        this.currentHabitat = h;
    }
}

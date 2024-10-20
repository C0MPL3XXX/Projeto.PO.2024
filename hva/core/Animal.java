package hva.core;

import java.util.*;

public class Animal extends HotelEntity {

    //Atributes
    private final ArrayList<VaccinationResults> health = new ArrayList<>();
    private Habitat currentHabitat;
    private final Species species;

    //Constructor
    public Animal(String id, String name, Habitat habitat, Species s) {
        super(id, name);
        this.currentHabitat = habitat;
        this.species = s;
    }

    // Getters
    public Species getSpecies() {
        return species;
    }

    public ArrayList<VaccinationResults> getHealth() {
        return health;
    }

    public Habitat getHabitat(){
        return currentHabitat;
    }

    public void setHabitat(Habitat h){
        currentHabitat = h;
    }

    // Methods

    double computeSatisfaction(IAnimalSatisfaction c) {     // Usado para saber a satisfação de um animal
       return c.calculate(this);
    }

    @Override
    public String toString() { // Mostra informação do animal em string que pode ser usado para um print
        if (this.health.isEmpty()) {
            return "ANIMAL|" + getId() + "|" + getName() + "|" + getSpecies().getId()
                    + "|VOID|" + currentHabitat.getId();
        }
        return "ANIMAL|" + getId() + "|" + getName() + "|" + getSpecies().getId()
                + "|" + health + "|" + currentHabitat.getId();
    }
}

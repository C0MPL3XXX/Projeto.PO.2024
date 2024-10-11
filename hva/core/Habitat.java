package hva.core;

import java.io.Serializable;
import java.util.HashMap;

public class Habitat extends HotelEntity implements Serializable {

    //Atributes
    private int area;
    private HashMap<String, Animal> animals = new HashMap<>();
    private HashMap<String, Tree> trees = new HashMap<>();
    private HashMap<String, Integer> speciesImpact = new HashMap<>();
    private HashMap<String, Employee> employees = new HashMap<>();

    //Constructor
    public Habitat(String uniqueId, String name, int area) {
        super(uniqueId, name);
        this.area = area;
    }

    // Getters
    public HashMap<String, Animal> getAnimals() {
        return animals;
    }

    public int getArea() {
        return area;
    }

    public HashMap<String, Integer> getSpeciesImpact() {
        return speciesImpact;
    }

    public HashMap<String, Employee> getEmployees() {
        return employees;
    }

    // Methods
    void addEmployee(Zookeeper z) {
        employees.put(z.getId(), z);
    }

    void removeEmployee(Zookeeper z) {
        employees.remove(z.getId());
    }

    void addAnimal(Animal a) {
        animals.put(a.getId(), a);
        if (speciesImpact.containsKey(a.getSpecies().getName()) == false) {
            speciesImpact.put(a.getSpecies().getName(), 0);
        }
    }

    void removeAnimal(Animal a) {
        if (animals.containsKey(a.getId())) {
            animals.remove(a.getId());
            boolean remove = true;
            for (Animal b : animals.values()) {
                if (a.getSpecies() == b.getSpecies()) {
                    remove = false;
                    break;
                }
            }
            if (remove) {
                speciesImpact.remove(a.getSpecies().getName());
            }
        }
    }

    void addTree(Tree t) {
        trees.put(t.getId(), t);
    }

    void removeTree(String id) {
        trees.remove(id);
    }

    int impactOf(Species s) {
        return speciesImpact.get(s.getId());
    }

    public int workHabitat() {
        int totalWorkTrees = 0;
        for (Tree t : trees.values()) {
            totalWorkTrees += t.cleaningEffort();
        }
        return this.area + (3 * animals.size()) + totalWorkTrees;
    }
}

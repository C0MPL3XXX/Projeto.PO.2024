package hva.core;

import java.io.Serializable;
import java.util.TreeMap;

public class Habitat implements Serializable {

    //Atributes
    private String uniqueId;
    private String name;
    private int area;
    private TreeMap<String, Animal> animals = new TreeMap<>();
    private TreeMap<String, Tree> trees = new TreeMap<>();
    private TreeMap<String, Integer> speciesImpact = new TreeMap<>();
    private TreeMap<String, Employee> employees = new TreeMap<>();

    // Getters
    public String getUniqueId() {
        return uniqueId;
    }

    public TreeMap<String, Animal> getAnimals() {
        return this.animals;
    }

    public int getArea() {
        return this.area;
    }

    public TreeMap<String, Integer> getSpeciesImpact() {
        return this.speciesImpact;
    }

    public TreeMap<String, Employee> getEmployees() {
        return this.employees;
    }

    // Methods
    void addEmployee(Zookeeper z) {
        employees.put(z.getId(), z);
    }

    void removeEmployee(Zookeeper z) {
        employees.remove(z.getId());
    }

    void add(Animal a) {
        animals.put(a.getId(), a);
        if (speciesImpact.containsKey(a.getSpecies().getName()) == false) {
            speciesImpact.put(a.getSpecies().getName(), 0);
        }
    }

    void remove(Animal a) {
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

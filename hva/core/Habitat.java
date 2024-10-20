package hva.core;

import java.io.Serializable;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class Habitat extends HotelEntity implements Serializable {

    //Atributes
    private int area;
    private final Map<String, Animal> animals = new HashMap<>();
    private final Map<String, Tree> trees = new HashMap<>();
    private final Map<String, Integer> speciesImpact = new HashMap<>();
    private final Map<String, Employee> employees = new HashMap<>();

    //Constructor
    public Habitat(String uniqueId, String name, int area) {
        super(uniqueId, name);
        this.area = area;
    }

    // Getters
    public Collection<Animal> getAnimals() {
        return animals.values();
    }

    public int getArea() {
        return area;
    }

    public void setArea(int a) {
        area = a;
    }

    public Map<String, Integer> getSpeciesImpact() {
        return speciesImpact;
    }

    public Collection<Tree> getTrees() {
        return trees.values();
    }

    public void setSpeciesImpact(String speciesId, String impact) {
        
        int impactNum = switch (impact) {
            case "POS" -> 20;
            case "NEG" -> -20;
            case "NEU" -> 0;
            default -> 0;
        };

        speciesImpact.put(speciesId, impactNum);
    }

    public Map<String, Employee> getEmployees() {
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

    @Override
    public String toString() { // Mostra informação do animal em string que pode ser usado para um print
            return "HABITAT|" + getId() + "|" + getName() + "|" + getArea()
                    + "|" + trees.size(); 
    }
}

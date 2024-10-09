package hva.core;

import java.io.Serializable;
import java.util.TreeMap;

public class Species implements Serializable {

    //Atributes
    private String uniqueId;
    private String name;
    private TreeMap<String, Animal> animals = new TreeMap<>();
    private TreeMap<String, Employee> employees = new TreeMap<>();

    //Constructor
    public Species(String id, String name) {

    }

    //Getters
    public TreeMap<String, Employee> getEmployees() {
        return this.employees;
    }

    public TreeMap<String, Animal> getAnimals() {
        return this.animals;
    }

    public String getId() {
        return this.uniqueId;
    }

    public String getName() {
        return this.name;
    }

    //Methods
    @Override
    public boolean equals(Object other) {
        if (other instanceof Species) {
            Species s = (Species) other;
            return this.uniqueId.equals(s.getId());
        }
        return false;
    }

    void addEmployee(Veterinarian v) {
        employees.put(v.getUniqueId(), v);
    }

    void removeEmployee(Veterinarian v) {
        employees.remove(v.getUniqueId());
    }
}

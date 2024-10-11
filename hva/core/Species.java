package hva.core;

import java.util.TreeMap;

public class Species extends HotelEntity {

    //Atributes
    private TreeMap<String, Animal> animals = new TreeMap<>();
    private TreeMap<String, Employee> employees = new TreeMap<>();

    //Constructor
    public Species(String id, String name) {
        super(id, name);
    }

    //Getters
    public TreeMap<String, Employee> getEmployees() {
        return this.employees;
    }

    public TreeMap<String, Animal> getAnimals() {
        return this.animals;
    }

    //Methods
    void addEmployee(Veterinarian v) {
        employees.put(v.getId(), v);
    }

    void removeEmployee(String id) {
        employees.remove(id);
    }

    void addAnimal(Animal a) {
        animals.put(a.getId(), a);
    }

    void removeAnimal(String id) {
        animals.remove(id);
    }
}

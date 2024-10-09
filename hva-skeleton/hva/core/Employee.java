package hva.core;

import java.io.Serializable;
import java.util.HashSet;

public abstract class Employee implements Serializable {

    //Atributes
    protected Hotel hotel;
    protected String uniqueId;
    protected String name;
    protected HashSet<String> responsibilities = new HashSet<>();

    //Constructor
    public Employee(String uniqueId, String name, Hotel hotel) {
        this.uniqueId = uniqueId;
        this.name = name;
        this.hotel = hotel;
    }

    //Getters
    public String getUniqueId() {
        return uniqueId;
    }

    //Methods
    abstract int computeSatisfaction();

    abstract void addResponsability(String h);

    abstract void removeResponsability(String h);
}

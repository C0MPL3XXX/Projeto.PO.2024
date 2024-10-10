package hva.core;

import java.util.HashSet;

public abstract class Employee extends HotelEntity {

    //Atributes
    protected Hotel hotel;
    protected HashSet<String> responsibilities = new HashSet<>();

    //Constructor
    public Employee(String uniqueId, String name, Hotel hotel) {
        super(name, name);
        this.hotel = hotel;
    }

    //Getter
    public HashSet<String> getResponsibilities() {
        return responsibilities;
    }

    //Methods
    abstract int computeSatisfaction();

    abstract void addResponsability(String h);

    abstract void removeResponsability(String h);
}

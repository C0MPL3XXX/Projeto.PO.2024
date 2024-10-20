package hva.core;

import java.util.HashSet;

public abstract class Employee extends HotelEntity {

    //Atributes
    protected Hotel hotel;
    protected HashSet<String> responsibilities = new HashSet<>();

    //Constructor
    public Employee(String uniqueId, String name, Hotel hotel) {
        super(uniqueId, name);
        this.hotel = hotel;
    }

    //Getter
    public HashSet<String> getResponsibilities() {
        return responsibilities;
    }

    public Hotel getHotel(){
        return hotel;
    }

    //Methods
    abstract int accept(IEmployeeSatisfaction i);

    abstract void addResponsability(String h);

    abstract void removeResponsability(String h);

    @Override
    public abstract String toString();
}

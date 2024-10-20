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
    abstract double calculateSatisfaction(IEmployeeSatisfaction i);

    abstract void addResponsibility(String h);

    abstract void removeResponsibility(String h);

    @Override
    public abstract String toString();
}

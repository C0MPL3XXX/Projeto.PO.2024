package hva.core;

public class Zookeeper extends Employee {

    //Constructor
    public Zookeeper(String id, String n, Hotel h) {
        super(id, n, h);
    }

    //Methods
    @Override
    double calculateSatisfaction(IEmployeeSatisfaction i) {
        return i.calculate(this);
    }

    @Override
    void addResponsibility(String id) {
        Habitat h = hotel.findHabitat(id);
        responsibilities.add(h.getId());
        h.addEmployee(this);
    }

    @Override
    void removeResponsibility(String id) {
        Habitat h = hotel.getHabitat(id);
        responsibilities.remove(h.getId());
        h.removeEmployee(this);
    }

    @Override
    public String toString() {
        if (responsibilities.isEmpty()) {
            return "TRT|" + getId() + "|" + getName();
        } else {
            return "TRT|" + getId() + "|" + getName() + "|" + responsibilities;
        }
    }
}

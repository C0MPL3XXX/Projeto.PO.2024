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
        responsibilities.add(id);
    }

    @Override
    void removeResponsibility(String id) {
        responsibilities.remove(id);
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

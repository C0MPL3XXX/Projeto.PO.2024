package hva.core;

public class Zookeeper extends Employee {

    //Constructor
    public Zookeeper(String id, String n, Hotel h) {
        super(id, n, h);
    }

    //Methods
    @Override
    int computeSatisfaction() {
        int total = 300;
        for (Habitat h : hotel.getHabitats().values()) {
            if (responsibilities.contains(h.getUniqueId())) {
                total -= h.workHabitat() / h.getEmployees().size();
            }
        }
        return total;
    }

    @Override
    void addResponsability(String id) {
        Habitat h = hotel.findHabitat(id);
        responsibilities.add(h.getUniqueId());
        h.addEmployee(this);
    }

    @Override
    void removeResponsability(String id) {
        Habitat h = hotel.findHabitat(id);
        responsibilities.remove(h.getUniqueId());
        h.removeEmployee(this);
    }

}

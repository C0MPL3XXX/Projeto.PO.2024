package hva.core;

public class Veterinarian extends Employee {

    //Constructor
    public Veterinarian(String id, String n, Hotel h) {
        super(id, n, h);
    }

    //Methods
    @Override
    int computeSatisfaction() {
        int total = 20;
        for (Species s : hotel.getSpecies().values()) {
            if (responsibilities.contains(s.getId())) {
                total -= s.getAnimals().size() / s.getEmployees().size();
            }
        }
        return total;
    }

    @Override
    void addResponsability(String id) {
        Species s = hotel.findSpecies(id);
        responsibilities.add(s.getId());
        s.addEmployee(this);
    }

    @Override
    void removeResponsability(String id) {
        Species s = hotel.findSpecies(id);
        responsibilities.remove(s.getId());
        s.removeEmployee(this);
    }

}

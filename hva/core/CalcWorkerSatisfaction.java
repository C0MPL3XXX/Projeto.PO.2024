package hva.core;

public class CalcWorkerSatisfaction implements IEmployeeSatisfaction{
    @Override
    public double calculate(Veterinarian v){
        double total = 20;
        for (Species s : v.getHotel().getSpecies()) {
            if (v.getResponsibilities().contains(s.getId())) {
                total -= s.getAnimals().size() / s.getEmployees().size();
            }
        }
        return total;
    }
    @Override
    public double calculate(Zookeeper z){
        double total = 300;
        for (Habitat h : z.getHotel().getHabitats()) {
            if (z.getResponsibilities().contains(h.getId())) {
                total -= h.workHabitat() / h.getEmployees().size();
            }
        }
        return total;
    }
}

package hva.core;

public class CalcWorkerSatisfaction implements IEmployeeSatisfaction{
    @Override
    public int calculate(Veterinarian v){
        int total = 20;
        for (Species s : v.getHotel().getSpecies().values()) {
            if (v.getResponsibilities().contains(s.getId())) {
                total -= s.getAnimals().size() / s.getEmployees().size();
            }
        }
        return total;
    }
    @Override
    public int calculate(Zookeeper z){
        int total = 300;
        for (Habitat h : z.getHotel().getHabitats().values()) {
            if (z.getResponsibilities().contains(h.getId())) {
                total -= h.workHabitat() / h.getEmployees().size();
            }
        }
        return total;
    }
}

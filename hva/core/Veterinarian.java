package hva.core;

public class Veterinarian extends Employee {

    //Constructor
    public Veterinarian(String id, String n, Hotel h) {
        super(id, n, h);
    }

    //Methods
    @Override
    int accept(IEmployeeSatisfaction i) {
        return i.calculate(this);
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
        s.removeEmployee(getId());
    }

    public int dano(Vaccine v, Animal a) {
        if (v.getVaccineSpecies().contains(a.getSpecies().getId())) {
            return -1;
        }
        String specieVaccine = v.getVaccineSpecies().get(0);
        for (String s : v.getVaccineSpecies()) {
            if (s.length() > specieVaccine.length()) {
                specieVaccine = s;
            }
        }
        return bigger(specieVaccine, a.getSpecies().getId()).length() - sameCharacters(specieVaccine, a.getId());

    }

    int sameCharacters(String str1, String str2) {
        int c = 0;

        for (int i = 0; i < str1.length(); i++) {
            if (str2.indexOf(str1.charAt(i)) >= 0) {
                c += 1;
            }
        }
        return c;
    }

    String bigger(String str1, String str2) {
        if (str1.length() > str2.length()) {
            return str1;
        }
        return str2;
    }

    void vaccinate(Animal a, Vaccine v) {
        VaccinationResults result;
        int dano = dano(v, a);
        if (dano == -1) {
            result = VaccinationResults.NORMAL;
        }
        if (dano == 0) {
            result = VaccinationResults.CONFUSION;
        }
        if (0 < dano && dano < 5) {
            result = VaccinationResults.ACCIDENT;
        } else {
            result = VaccinationResults.ERROR;
        }
        VaccineApplication VaccineUsed = new VaccineApplication(result, this, a, v);
        if (VaccineUsed.isCorrect()) {
            a.getHealth().add(result);
            v.addUsage(VaccineUsed);
        }
    }

    @Override
    public String toString() {
        if (responsibilities.isEmpty()) {
            return "VET|" + getId() + "|" + getName();
        } else {
            return "VET|" + getId() + "|" + getName() + "|" + responsibilities;
        }
    }
}

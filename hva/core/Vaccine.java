package hva.core;

import java.util.ArrayList;

public class Vaccine extends HotelEntity {

    //Atributes
    private final ArrayList<VaccineApplication> vaccineRegister = new ArrayList<>();
    private final ArrayList<String> vaccineSpecies = new ArrayList<>();

    //Constructor
    public Vaccine(String id, String name, String[] species) {
        super(id, name);
        vaccineSpecies.addAll(vaccineSpecies);
    }

    //Getter
    public ArrayList<String> getVaccineSpecies() {
        return vaccineSpecies;
    }

    //Methods
    public void addUsage(VaccineApplication v) {
        vaccineRegister.add(v);
    }

    @Override
    public String toString() {
        return "VACINA|" + this.getId() + "|" + this.getName() + "|" + vaccineRegister.size()
                + "|" + vaccineSpecies;
    }
}

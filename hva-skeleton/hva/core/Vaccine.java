package hva.core;

import java.util.ArrayList;
import java.util.LinkedList;

public class Vaccine extends HotelEntity {

    //Atributes
    private LinkedList<VaccineApplication> vaccineRegister = new LinkedList<>();
    private ArrayList<String> vaccineSpecies = new ArrayList<>();

    //Constructor
    public Vaccine(String id, String name) {
        super(id, name);
    }

    //Getter
    public ArrayList<String> getVaccineSpecies() {
        return vaccineSpecies;
    }

    //Methods
    public void addVaccineSpecies(Species s) {
        vaccineSpecies.add(s.getId());
    }

    public void addUsage(VaccineApplication v) {
        vaccineRegister.add(v);
    }

}

package hva.core;

import java.util.ArrayList;
import java.util.Arrays;

public class Vaccine extends HotelEntity {

    //Atributes
    private final ArrayList<VaccineApplication> vaccineRegister = new ArrayList<>();
    private final ArrayList<String> vaccineSpecies = new ArrayList<>();

    //Constructor
    public Vaccine(String id, String name, String[] species) {
        super(id, name);
        vaccineSpecies.addAll(Arrays.asList(species));
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
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < vaccineSpecies.size(); i++) {
            sb.append(vaccineSpecies.get(i));
            if (i < vaccineSpecies.size() - 1) {
                sb.append(',');
            }
        }

        if (sb.isEmpty()) {
            return "VACINA|" + getId() + "|" + getName() + "|" + vaccineRegister.size();
        } else {
            return "VACINA|" + getId() + "|" + getName() + "|" + vaccineRegister.size()
                + "|" + sb.toString();
        }
    }
}

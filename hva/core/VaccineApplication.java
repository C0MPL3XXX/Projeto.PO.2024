package hva.core;

import java.io.Serializable;

public class VaccineApplication implements Serializable {

    //Atribute
    private final VaccinationResults result;
    private final Veterinarian vet;
    private final Animal animal;
    private final Vaccine vaccine;

    //Constructor
    public VaccineApplication(VaccinationResults result, Veterinarian vet, Animal animal, Vaccine vaccine) {
        this.result = result;
        this.vet = vet;
        this.animal = animal;
        this.vaccine = vaccine;
    }

    //Method
    public boolean isCorrect() {
        for (String s1 : vet.getResponsibilities()) {
            for (String s2 : vaccine.getVaccineSpecies()) {
                if (s1.equals(s2)) {
                    return true;

                }
            }
        }
        return false;
    }

}

package hva.core;

import java.io.Serializable;

public class VaccineApplication implements Serializable {

    //Atribute
    private VaccinationResults result;

    //Constructor
    public VaccineApplication(VaccinationResults result) {
        this.result = result;
    }

    //Method
    public boolean isCorrect() {

    }

}

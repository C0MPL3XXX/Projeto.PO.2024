package hva.core;

import java.io.Serializable;

public class Species implements Serializable {

    //Atributes
    private String uniqueId;
    private String name;

    //Constructor
    public Species(String id, String name) {

    }

    //Getters
    public String getId() {
        return this.uniqueId;
    }

    public String getName() {
        return this.name;
    }

    //Methods
    @Override
    public boolean equals(Object other) {
        if (other instanceof Species) {
            Species s = (Species) other;
            return this.uniqueId.equals(s.getId());
        }
        return false;
    }
}

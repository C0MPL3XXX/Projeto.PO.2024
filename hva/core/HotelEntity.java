package hva.core;

import java.io.Serializable;

public abstract class HotelEntity implements Serializable {

    //Atributes every Entity has to have
    private final String id;
    private final String name;

    //Constructor
    public HotelEntity(String id, String name) {
        this.id = id;
        this.name = name;
    }

    //Getters
    public String getName() {
        return this.name;
    }

    public String getId() {
        return this.id;
    }

    //Methods
    public int hashCodeObj() {
        return this.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj != null && obj.getClass() == getClass()) {
            return (this.id == null ? ((HotelEntity) obj).id == null : this.id.equals(((HotelEntity) obj).id));
        }
        return false;
    }
}

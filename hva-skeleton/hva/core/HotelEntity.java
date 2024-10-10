package hva.core;

public abstract class HotelEntity {

    //Atributes evert Entity has to have
    private String id;
    private String name;

    //Constructor
    public HotelEntity(String id, String name) {
        this.id = id;
        this.name = name;
    }

    //Getters
    protected String getName() {
        return this.name;
    }

    protected String getId() {
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

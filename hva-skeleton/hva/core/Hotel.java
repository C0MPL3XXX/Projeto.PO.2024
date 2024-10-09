package hva.core;

import hva.core.exception.*;
import java.io.*;
import java.util.HashMap;

public class Hotel implements Serializable {

    @Serial
    private static final long serialVersionUID = 202407081733L;

    // FIXME define attributes
    private Season season;
    private HashMap<String, Habitat> habitats = new HashMap<>();
    private HashMap<String, Species> species = new HashMap<>();
    private HashMap<String, Animal> animals = new HashMap<>();
    private HashMap<String, Employee> employees = new HashMap<>();

    // FIXME define contructor(s)
    public Hotel() {
    }

    // FIXME define more methods
    public void addAnimal(String idA, String name, String idHab, String idSpe) {

    }

    public Habitat findHabitat(String id) {
        return habitats.get(id);
    }

    public HashMap<String, Habitat> getHabitats() {
        return habitats;
    }

    public Species findSpecies(String id) {
        return species.get(id);
    }

    public HashMap<String, Species> getSpecies() {
        return species;
    }

    /**
     * Read text input file and create corresponding domain entities.
     *
     * @param filename name of the text input file
     * @throws UnrecognizedEntryException if some entry is not correct
     * @throws IOException if there is an IO erro while processing the text file
     *
     */
    void importFile(String filename) throws UnrecognizedEntryException, IOException /* FIXME maybe other exceptions */ {
        //FIXME implement method
    }
}

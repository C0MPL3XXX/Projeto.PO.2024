package hva.core;

import hva.app.exception.*;
import hva.core.exception.*;
import java.io.*;
import java.util.HashMap;

public class Hotel implements Serializable {

    @Serial
    private static final long serialVersionUID = 202407081733L;

    private boolean hasChanged = false;

    // FIXME define attributes
    private Season season;
    private HashMap<String, Habitat> habitats = new HashMap<>();
    private HashMap<String, Species> species = new HashMap<>();
    private HashMap<String, Animal> animals = new HashMap<>();
    private HashMap<String, Employee> employees = new HashMap<>();
    private HashMap<String, Vaccine> vaccines = new HashMap<>();
    private HashMap<String, Tree> trees = new HashMap<>();

    // FIXME define contructor(s)
    public Hotel() {
    }

    public void setChanged(boolean val) {
        hasChanged = val;
    }

    public boolean hasChanged() {
        return hasChanged;
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

    public HashMap<String, Vaccine> getVaccineRegister() {
        return vaccines;
    }

    public Tree findTree(String id) {
        return trees.get(id);
    }

    void addResponsibility(String id, String responsibility) {
        employees.get(id).addResponsability(responsibility);
    }

    /**
     * Read text input file and create corresponding domain entities.
     *
     * @param filename name of the text input file
     * @throws UnrecognizedEntryException if some entry is not correct
     * @throws IOException if there is an IO erro while processing the text file
     *
     */
    //void importFile(String filename) throws UnrecognizedEntryException, IOException /* FIXME maybe other exceptions */ {
    //FIXME implement method
    //}
    void importFile(String name) throws UnrecognizedEntryException, IOException {
        Parser parser = new Parser(this);
        parser.parseFile(name);
    }

    public void registerSpecies(String id, String name) {
        species.put(id, new Species(id, name));
    }

    public void registerAnimal(String id, String name, String habitatId, String speciesId) throws DuplicateAnimalKeyException {
        if (animals.containsKey(id)) {
            throw new DuplicateAnimalKeyException(id);
        }

        Animal animal = animals.put(id, new Animal(id, name, findHabitat(habitatId), findSpecies(speciesId)));
        species.get(speciesId).addAnimal(animal);
    }

    public void registerEmployee(String uniqueId, String name, String empType) throws DuplicateEmployeeKeyException {
        if (employees.containsKey(uniqueId)) {
            throw new DuplicateEmployeeKeyException(uniqueId);
        }

        Employee employee = switch (uniqueId) {
            case "VET" ->
                new Veterinarian(uniqueId, name, this);
            case "TRT" ->
                new Zookeeper(uniqueId, name, this);
            default ->
                null;
        };

        if (employee != null) {
            employees.put(uniqueId, employee);

        }
    }

    public void registerVaccine(String id, String name, String[] speciesIds) throws DuplicateVaccineKeyException, UnknownSpeciesKeyException {
        if (vaccines.containsKey(id)) {
            throw new DuplicateVaccineKeyException(id);
        }

        for (String speciesId : speciesIds) {
            if (!species.containsKey(speciesId)) {
                throw new UnknownSpeciesKeyException(speciesId);
            }
        }

        vaccines.put(id, new Vaccine(id, name, speciesIds));
    }

    public void createTree(String id, String name, String type, int age, int diff) throws DuplicateTreeKeyException {
        if (trees.containsKey(id)) {
            throw new DuplicateTreeKeyException(id);
        }
        Tree tree = switch (type) {
            case "PERENE" ->
                new EvergreenTree(age, diff, id, name);
            case "CADUCA" ->
                new DeciduousTree(age, diff, id, name);
            default ->
                null;
        };
        if (tree != null) {
            trees.put(id, tree);
        }
    }

    public Habitat registerHabitat(String id, String name, int area) throws DuplicateHabitatKeyException {
        if (habitats.containsKey(id)) {
            throw new DuplicateHabitatKeyException(id);
        }

        return habitats.put(id, new Habitat(id, name, area));
    }
}

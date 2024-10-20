package hva.core;

import hva.app.exception.*;
import hva.core.exception.*;
import java.io.*;
import java.util.TreeMap;

public class Hotel implements Serializable {

    @Serial
    private static final long serialVersionUID = 202407081733L;

    private boolean hasChanged = false;

    // FIXME define attributes
    private Season season = Season.Spring;
    private TreeMap<String, Habitat> habitats = new TreeMap<>();
    private TreeMap<String, Species> species = new TreeMap<>();
    private TreeMap<String, Animal> animals = new TreeMap<>();
    private TreeMap<String, Employee> employees = new TreeMap<>();
    private TreeMap<String, Vaccine> vaccines = new TreeMap<>();
    private TreeMap<String, Tree> trees = new TreeMap<>();

    // FIXME define contructor(s)
    public Hotel() {
    }

    public void setChanged(boolean val) {
        hasChanged = val;
    }

    public boolean hasChanged() {
        return hasChanged;
    }

    public Habitat findHabitat(String id) {
        return habitats.get(id);
    }

    public TreeMap<String, Habitat> getHabitats() {
        return habitats;
    }

    public TreeMap<String, Animal> getAnimals() {
        return animals;
    }

    public Species findSpecies(String id) {
        return species.get(id);
    }

    public TreeMap<String, Species> getSpecies() {
        return species;
    }

    public TreeMap<String, Vaccine> getVaccines() {
        return vaccines;
    }

    public Tree findTree(String id) {
        return trees.get(id);
    }

    public TreeMap<String, Employee> getEmployees(){
        return employees;
    }

    void addResponsibility(String id, String responsibility) throws NoResponsibilityException {
        if (!species.containsKey(responsibility) && !animals.containsKey(responsibility)) {
            throw new NoResponsibilityException(id, responsibility);
        }

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

        setChanged(true);
    }

    public void registerAnimal(String id, String name, String habitatId, String speciesId) throws DuplicateAnimalKeyException {
        if (animals.containsKey(id)) {
            throw new DuplicateAnimalKeyException(id);
        }

        Animal animal = new Animal(id, name, findHabitat(habitatId), findSpecies(speciesId));

        animals.put(id, animal);
        species.get(speciesId).addAnimal(animal);

        setChanged(true);
    }

    public void registerEmployee(String uniqueId, String name, String empType) throws DuplicateEmployeeKeyException {
        if (employees.containsKey(uniqueId)) {
            throw new DuplicateEmployeeKeyException(uniqueId);
        }

        Employee employee = switch (empType) {
            case "VET" ->
                new Veterinarian(uniqueId, name, this);
            case "TRT" ->
                new Zookeeper(uniqueId, name, this);
            default -> throw new IllegalArgumentException();
        };

        employees.put(uniqueId, employee);
        setChanged(true);
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

        setChanged(true);
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

        setChanged(true);
    }

    public Habitat registerHabitat(String id, String name, int area) throws DuplicateHabitatKeyException {
        if (habitats.containsKey(id)) {
            throw new DuplicateHabitatKeyException(id);
        }

        Habitat habitat = new Habitat(id, name, area);

        habitats.put(id, habitat);

        setChanged(true);

        return habitat;
    }
}

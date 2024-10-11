package hva.core;

import hva.app.exception.*;
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
    private HashMap<String, Vaccine> vaccines = new HashMap<>();
    private HashMap<String, Tree> trees = new HashMap<>();

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

    public HashMap<String, Vaccine> getVaccineRegister() {
        return vaccines;
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
    void importFile(String name) throws HotelException {
        try (BufferedReader reader = new BufferedReader(new FileReader(name))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] fields = line.split("|");
                try {
                    registerEntry(fields);
                } catch (UnknownDataException | PublicationExistsException | UnknownAgentException | AgentExistsException
                        | InvalidIdentifierException e) {
                    e.printStackTrace();
                }
            }
        } catch (IOException e1) {
            throw new ImportFileException();
        }
    }

    public void registerSpecies(String... fields) {
        Species species = new Species(fields[1], fields[2]);
        addSpecies(species);
    }

    public void addSpecies(Species species) {
        this.species.put(species.getId(), species);
    }

    public void registerAnimal(String id, String name, String habitatId, String speciesId) throws DuplicateAnimalKeyException {
        if (animals.containsKey(id)) {
            throw new DuplicateAnimalKeyException(id);
        }
        Animal animal = new Animal(id, name, findHabitat(habitatId), findSpecies(speciesId));
        addAnimal(animal);
    }

    public void addAnimal(Animal animal) {
        this.animals.put(animal.getId(), animal);
        animal.getSpecies().getAnimals().put(animal.getId(), animal);
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
    }

    public void createTree(String id, String name, String type, int age, int diff) throws DuplicateTreeKeyException {
        if (trees.containsKey(id)) {
            throw new DuplicateTreeKeyException(id);
        }

    }
}

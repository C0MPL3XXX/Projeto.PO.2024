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
    private HashMap<String, Vaccine> vaccines = new HashMap<>();

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
                    // DAVID should not happen
                    e.printStackTrace();
                }
            }
        } catch (IOException e1) {
            throw new ImportFileException();
        }
    }

    public void registerEntry(String... fields) throws UnknownDataException, PublicationExistsException,
            UnknownAgentException, AgentExistsException, InvalidIdentifierException {
        switch (fields[0]) {
            case "ESPÉCIE" ->
                registerSpecies(fields);
            case "ANIMAL" ->
                registerAnimal(fields);
            case "VETERINÁRIO", "TRATADOR" ->
                registerPublication(fields);
            case "ÁRVORE" ->
                registerConnection(fields);
            default ->
                throw new UnknownDataException(fields[0]);
        }
    }

    public void registerSpecies(String... fields) {
        Species species = new Species(fields[1], fields[2]);
        addSpecies(species);
    }

    public void addSpecies(Species species) {
        this.species.put(species.getId(), species);
    }

    public void registerAnimal(String id, String name, Habitat habitat, Species species) {
        Animal animal = new Animal(id, name, habitat, species);
        addAnimal(animal);
    }

    public void addAnimal(Animal animal) {
        this.animals.put(animal.getId(), animal);
        animal.getSpecies().getAnimals().put(animal.getId(), animal);
    }

    public void registerEmployee(String uniqueId, String name, Hotel hotel) {
        Employee employee = switch (uniqueId) {
            case "VETERINÁRIO" ->
                new Veterinarian(uniqueId, name, hotel);
            case "TRATADOR" ->
                new Zookeeper(uniqueId, name, hotel);
        };

        if (fields.length) {
             >= 4 
        
        }
        {
            adicionar as responsabilidades do {
                field[3]   
            }  ao employee
        }
        registerEmployee(employee);
    }
}

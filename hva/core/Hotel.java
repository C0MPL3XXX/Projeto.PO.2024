package hva.core;

import hva.core.exception.*;
import java.io.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Map;
import java.util.TreeMap;

public class Hotel implements Serializable {

    @Serial
    private static final long serialVersionUID = 202407081733L;

    private boolean hasChanged = false;

    private Season season = Season.Spring;
    private final Map<String, Habitat> habitats = new TreeMap<>();
    private final Map<String, Species> species = new TreeMap<>();
    private final Map<String, Animal> animals = new TreeMap<>();
    private final Map<String, Employee> employees = new TreeMap<>();
    private final Map<String, Vaccine> vaccines = new TreeMap<>();
    private final Map<String, Tree> trees = new TreeMap<>();
    private final ArrayList<String> vaccinesUsed = new ArrayList<>();

    /**
     * Read text input file and create corresponding domain entities.
     *
     * @param filename name of the text input file
     * @throws UnrecognizedEntryException if some entry is not correct
     * @throws IOException if there is an IO erro while processing the text file
     *
     */
    void importFile(String name) throws UnrecognizedEntryException, IOException {
        Parser parser = new Parser(this);
        parser.parseFile(name);
    }

    public void setChanged(boolean val) {
        hasChanged = val;
    }

    public boolean hasChanged() {
        return hasChanged;
    }


    public boolean containsAnimal(String key) {
        return animals.containsKey(key);
    }

    public boolean containsSpecies(String key) {
        return species.containsKey(key);
    }

    public boolean containsEmployee(String key) {
        return employees.containsKey(key);
    }

    public boolean containsHabitat(String key) {
        return habitats.containsKey(key);
    }

    public boolean containsTree(String key) {
        return trees.containsKey(key);
    }

    public boolean containsVaccine(String key) {
        return vaccines.containsKey(key);
    }


    public Collection<Animal> getAnimals() {
        return animals.values();
    }

    public Collection<Species> getSpecies() {
        return species.values();
    }

    public Collection<Employee> getEmployees() {
        return employees.values();
    }

    public Collection<Habitat> getHabitats() {
        return habitats.values();
    }

    public Collection<Vaccine> getVaccines() {
        return vaccines.values();
    }


    public HashSet<String> getAnimalsInHabitat(String habitatId) {
        HashSet<String> habitatAnimals = new HashSet<>();
        for(Animal a: habitats.get(habitatId).getAnimals()){
                habitatAnimals.add(a.toString());
        }
        return habitatAnimals;
    }

    public ArrayList<String> getVetWork(String id){
        return employees.get(id).getWorkDone(); 
    }

    public ArrayList<VaccinationResults> getHealth(String id){
        return animals.get(id).getHealth();
    }

    public ArrayList<String> getVaccinesUsed(){
        return vaccinesUsed;
    }

    public void registerAnimal(String id, String name, String habitatId, String speciesId) {
        Animal animal = new Animal(id, name, habitats.get(habitatId), species.get(speciesId));

        animals.put(id, animal);
        species.get(speciesId).addAnimal(animal);

        setChanged(true);
    }

    public void registerSpecies(String id, String name) {
        species.put(id, new Species(id, name));

        setChanged(true);
    }

    public void registerEmployee(String id, String name, String empType) {
        Employee employee = switch (empType) {
            case "VET" ->
                new Veterinarian(id, name, this);
            case "TRT" ->
                new Zookeeper(id, name, this);
            default -> throw new IllegalArgumentException();
        };

        employees.put(id, employee);
        setChanged(true);
    }

    public void registerHabitat(String id, String name, int area) {
        Habitat habitat = new Habitat(id, name, area);

        habitats.put(id, habitat);

        setChanged(true);
    }

    public void registerVaccine(String id, String name, String[] speciesIds) {
        vaccines.put(id, new Vaccine(id, name, speciesIds));

        setChanged(true);
    }

    public String registerTree(String id, String name, String type, int age, int diff) {
        Tree t = switch (type) {
            case "PERENE" ->
                new EvergreenTree(age, diff, id, name);
            case "CADUCA" ->
                new DeciduousTree(age, diff, id, name);
            default -> throw new IllegalArgumentException();
        };

        setChanged(true);

        trees.put(id, t);
        return t.toString();
    }


    public int advanceSeason() {
        season = season.nextSeason();

        return season.ordinal();
    }

    public void addTreeToHabitat(String habitatId, String treeId) {
        Habitat h = habitats.get(habitatId);
        Tree t = trees.get(treeId);

        h.addTree(t);
    }

    public void addVaccineUsage(String s){
        vaccinesUsed.add(s);
    }

    public void transferAnimal(String animalId, String habitatId) {
        Animal a = animals.get(animalId);
        Habitat h = habitats.get(habitatId);

        a.getHabitat().removeAnimal(a);
        h.addAnimal(a);
        a.setHabitat(h);
    }

    public int calculateAnimalSatisfaction(String animalId) {
        IAnimalSatisfaction c = new CalcAnimalSatisfaction();
        Animal a = animals.get(animalId);

        return (int)Math.round(a.computeSatisfaction(c));
    }

    public int calculateEmployeeSatisfaction(String employeeId) {
        IEmployeeSatisfaction c = new CalcWorkerSatisfaction();
        Employee e = employees.get(employeeId);

        return (int)Math.round(e.calculateSatisfaction(c));
    }

    public int showGlobalSatisfaction() {
        double total = 0;

        IEmployeeSatisfaction calcSatisfactionWorker = new CalcWorkerSatisfaction();
        for (Employee e : employees.values())
        {
            double s = e.calculateSatisfaction(calcSatisfactionWorker);
            total += s;
        }

        IAnimalSatisfaction calcSatisfactionAnimal = new CalcAnimalSatisfaction();
        for(Animal a : animals.values())
        {
            double s = a.computeSatisfaction(calcSatisfactionAnimal);
            total += s;
        }
        return (int)Math.round(total);
        
    }

    public void setHabitatArea(String habitatId, int area) {
        habitats.get(habitatId).setArea(area);
    }
    
    public void setHabitatSpeciesImpact(String habitatId, String specciesId, String impact) {
        habitats.get(habitatId).setSpeciesImpact(specciesId, impact);
    }

    public Collection<Tree> getTreesInHabitat(String habitatId) {
        return habitats.get(habitatId).getTrees();
    }

    public boolean employeeContainsResponsibility(String employeeKey, String responsibilityKey) {
        return employees.get(employeeKey).getResponsibilities().contains(responsibilityKey);
    }

    public void addEmployeeResponsibility(String employeeKey, String responsibilityKey) {
        employees.get(employeeKey).addResponsibility(responsibilityKey);
    }

    public void removeEmployeeResponsibility(String employeeKey, String responsibilityKey){
        employees.get(employeeKey).removeResponsibility(responsibilityKey);
    }

    public void vaccinateAnimal(Animal a, Vaccine vaccine, Veterinarian vet){
        vet.vaccinate(a, vaccine);
    }
}

package hva.core;

public class CalcAnimalSatisfaction implements IAnimalSatisfaction{
    
    int sameSpecies(Animal a) { //Passa por todos os animais no habitat e recorda quais são da mesma especie
        int same = 0;
        for (Animal c : a.getHabitat().getAnimals()) {
            if (a.getSpecies().equals(c.getSpecies())) {
                same += 1;
            }
        }
        return same;
    }

    public int difSpecies(Animal a) {
        return a.getHabitat().getAnimals().size() - sameSpecies(a); // Usando sameSpecies arranja-se difSpecies mais facilmente em conjunto da população
    }

    @Override
    public double calculate(Animal a){ 
        double satisfaction = 20 + (3 * sameSpecies(a)) - (2 * difSpecies(a))
        + (a.getHabitat().getArea() / a.getHabitat().getAnimals().size())
        + a.getHabitat().getSpeciesImpact().get(a.getSpecies().getId()); // Igual à formula no enunciado, usando varios metados

    return satisfaction;
    }

}

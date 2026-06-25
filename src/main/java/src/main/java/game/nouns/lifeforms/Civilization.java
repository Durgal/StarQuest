package src.main.java.game.nouns.lifeforms;

import src.main.java.game.data.Data;
import src.main.java.generators.MarkovChain;
import src.main.java.utilities.Math;

public class Civilization {

    String name;

    Species species;

    public Civilization() { /**/ }
    
    public Civilization(Species species) {
        this.name = generateCivilizationName(species);
        this.species = Data.species.get(species.getName());
    }

    private String generateCivilizationName(Species species) {
        MarkovChain chain = Data.civilization_generator.get(species);
        if (chain == null) return "UNKNOWN";
        int length = 4 + Math.getRandom().nextInt(5); // 4–8 characters
        return chain.generateName(length).toUpperCase();
    }


    public Lifeform getSpecies() {
        return species;
    }
}

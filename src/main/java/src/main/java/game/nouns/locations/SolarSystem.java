package src.main.java.game.nouns.locations;

import src.main.java.game.data.Data;
import src.main.java.generators.MarkovChain;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import src.main.java.game.Universe;


public class SolarSystem extends Place {

    Set<Planet> planets = new HashSet<>();
    
    Universe parent;

    public SolarSystem() {
        name = generateRandomName();
    }

    // create the planets in the solar system
    public List<Planet> makePlanets(Integer n) {
        for (int i = 0; i < n; i++) {
            Planet planet = Planet.random();
            planet.setSolarSystem(this);
            planets.add(planet);
        }
        return new ArrayList<>(planets);
    }

    public List<Planet> getPlanets() {
        return new ArrayList<>(planets);
    }

    private String generateRandomName() {
        MarkovChain chain = Data.solar_system_generator.get(Data.SEED);
        if (chain == null) throw new IllegalArgumentException("Error generating solar system name");
        return chain.generateName(8).toUpperCase();
    }

    public String getName() {
        return name;
    }
    
    public Universe getUniverse() {
        return parent;
    }

    public void setUniverse(Universe universe) {
        parent = universe;
    }
}

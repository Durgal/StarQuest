package src.main.java.game.nouns.locations;

import src.main.java.game.data.Data;
import src.main.java.generators.MarkovChain;
import java.util.ArrayList;
import java.util.List;

// a Continent exists on a planet and has a series of Locations (ie towns, dungeons, etc)
public class Continent extends Place implements Comparable<Continent> {

    Planet parent;

    List<Location> locations = new ArrayList<>();

    public Continent() { }
    
    public Continent(Planet planet) {
        parent = planet;
        name = generateRandomName(planet.type);
    }

    public String getName() { return name; }

    public List<Location> makeLocations(int n) {
        for (int i = 0; i < n; i++) {
            locations.add(new Location(this));
        }
        return locations;
    }

    public List<Location> getLocations() {
        return locations;
    }

    private String generateRandomName(Planet.Category type) {
        MarkovChain chain = Data.continent_generator.get(type);
        if (chain == null) throw new IllegalArgumentException("Unknown planet type: " + type);
        return chain.generateName(8).toUpperCase();
    }

    public Planet getPlanet() {
        return parent;
    }

    @Override
    public int compareTo(Continent o) {
        return this.name.compareTo(o.name);
    }
}

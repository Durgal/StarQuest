package src.main.java.game.nouns.locations;

import src.main.java.game.data.Data;
import src.main.java.generators.LifeformGenerator;
import src.main.java.generators.MarkovChain;
import src.main.java.utilities.Math;
import src.main.java.game.nouns.lifeforms.*;

import java.util.*;

public class Planet extends Place implements Comparable<Planet> {

    List<Continent> continents = new ArrayList<>();

    Set<Lifeform> lifeforms = new HashSet<>();

    Set<Civilization> civilizations = new HashSet<>();

    SolarSystem parent;
    Category type;
    Species natives;
    String rainfall;
    String topography;
    String vegetation;
    Climate climate;
    String temperature;
    String storm;

    Integer tilt = Math.randomRange(0,180);

    Integer moons = Math.randomRange(0,2);

    public Planet() {
        // default constructor
    }

    public Planet(String type, String natives, String rainfall, String topography, String vegetation, String climate, String temperature, String storm) {
        this.name = generateRandomName(Category.valueOf(type));
        this.type = Category.valueOf(type);
        this.natives = Data.species.get(natives);
        this.rainfall = rainfall;
        this.topography = topography;
        this.vegetation = vegetation;
        this.climate = Climate.valueOf(climate);
        this.temperature = temperature;
        this.storm = storm;
        this.civilizations = LifeformGenerator.getCivilizations(this); // CREATE ALL CIVILIZATIONS ON THE PLANET!
        this.lifeforms.addAll(LifeformGenerator.getMonsters(this)); // CREATE ALL MONSTERS ON THE PLANET!
    }

    public List<Continent> makeContinents(int n) {
        for (int i = 0; i < n; i++) {
            continents.add(new Continent(this));
        }
        return continents;
    }

    public String getName() { return name; }

    public List<Continent> getContinents() {
        return continents;
    }

    public List<String> getContinentNames() {
        return continents.stream().map(Continent::getName).toList();
    }

    public Category getCategory() {
        return type;
    }

    public Climate getClimate() {
        return climate;
    }

    public String getNativeSpecies() {
        return natives.getName();
    }

    @Override
    public int compareTo(Planet o) {
        return this.name.compareTo(o.name);
    }

    public Place getSolarSystem() {
        return parent;
    }

    public void setSolarSystem(SolarSystem system) {
        parent = system;
    }

    public enum Category {
        TERRAN,
        JUNGLE,
        VOLCANIC,
        DESERT,
        OCEANIC,
        DESOLATE,
        ARCTIC
    }

    enum Climate {
        TEMPERATE,
        TROPICAL,
        ARID,
        POLAR,
        NONE
    }

    public static Planet random() {
        return Math.shuffle(Data.planets);
    }

    private String generateRandomName(Category type) {
        MarkovChain chain = Data.planet_generator.get(type);
        if (chain == null) throw new IllegalArgumentException("Unknown planet type: " + type);
        return chain.generateName(8).toUpperCase();
    }

    public static <E> E getRandomLifeform(Set<E> set) {
        return set.stream().skip(Math.getRandom().nextInt(set.size())).findFirst().orElse(null);
    }

    public static <Monster> Monster getRandomMonster(Set<Monster> set) {
        return set.stream().skip(Math.getRandom().nextInt(set.size())).findFirst().orElse(null);
    }

    public static <NPC> NPC getRandomCivilization(Set<NPC> set) {
        return set.stream().skip(Math.getRandom().nextInt(set.size())).findFirst().orElse(null);
    }

    public Set<Monster> getMonsters() {
        Set<Monster> monsters = new HashSet<>();
        for (Lifeform lifeform : lifeforms) {
            if (lifeform instanceof Monster) {
                monsters.add((Monster) lifeform);
            }
        }
        return monsters;
    }


}

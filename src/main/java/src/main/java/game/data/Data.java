package src.main.java.game.data;

import src.main.java.game.nouns.lifeforms.Monster;
import src.main.java.game.nouns.locations.Planet;
import src.main.java.game.nouns.lifeforms.Species;
import src.main.java.game.nouns.things.Thing;
import src.main.java.text_based_mechanics.parts_of_speech.Word;
import src.main.java.generators.MarkovChain;

import java.util.*;
import src.main.java.game.Material;

// this class contains all the global data loaded from the resource directory
public class Data {

    public static final Long SEED = System.currentTimeMillis(); //12345L; //
    public static Map<Species.Type, Species> species;
    public static Map<String, Monster> monsters;
    public static Map<String, Thing> objects;
    public static Map<String, Planet> planets;
    public static Map<String, Material> materials;

    public static LinkedHashMap<String, Word> dictionary;

    public static final Map<Long, MarkovChain> solar_system_generator;
    public static final Map<Planet.Category, MarkovChain> planet_generator;
    public static final Map<Planet.Category, MarkovChain> continent_generator;
    public static Map<Planet.Category, MarkovChain> location_generator;
    public static final Map<Species, MarkovChain> civilization_generator;
    public static final Map<Species, MarkovChain> npc_male_generator;
    public static final Map<Species, MarkovChain> npc_female_generator;
    public static final Map<List<?>, MarkovChain> monster_generator;



    static {
        species = new LinkedHashMap<>();
        monsters = new LinkedHashMap<>();
        objects = new LinkedHashMap<>();
        planets = new LinkedHashMap<>();
        materials = new LinkedHashMap<>();
        dictionary = new LinkedHashMap<>();
        solar_system_generator = new LinkedHashMap<>();
        planet_generator = new EnumMap<>(Planet.Category.class);
        continent_generator = new EnumMap<>(Planet.Category.class);
        location_generator = new EnumMap<>(Planet.Category.class);
        civilization_generator = new LinkedHashMap<>();
        npc_male_generator = new LinkedHashMap<>();
        npc_female_generator = new LinkedHashMap<>();
        monster_generator = new LinkedHashMap<>();
    }

    public static void load() {
        // load all resources
        ResourceLoader.load();
    }

//    public static Planet random(Class<Planet> planetClass) {
//        List<Planet> values = new ArrayList<>(planets.values());
//        Collections.shuffle(values);
//        return (values.get(0));
//    }


}

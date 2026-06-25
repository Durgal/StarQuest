package src.main.java.game.nouns.lifeforms;

import src.main.java.game.data.Data;
import src.main.java.text_based_mechanics.parts_of_speech.Noun;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import src.main.java.game.nouns.lifeforms.mechanics.Attributes;
import src.main.java.game.nouns.locations.Planet;

import src.main.java.utilities.Math;

public class Species extends Lifeform {

    public enum Type {
        NONE(""),
        HUMAN("ADAPTIVE AND AMBITIOUS EXPLORERS DRIVEN BY CURIOSITY AND CONFLICT, THRIVING ON ALL KINDS OF WORLDS."),
        IGNEOS("STONEBORN SMITHS AND WARRIORS WHO HONOR FIRE AS THE SOURCE OF CREATION AND INHABIT VOLCANIC PLANETS."),
        NUMERIAN("SYNTHETIC BEINGS OF LOGIC AND REASON, SEEKING PURPOSE AMID THE RUINS OF DESOLATE WORLDS."),
        PROTOZOAN("PHOTON-BASED ENTITIES WHO PERCEIVE TIME AS FLUID AND EXISTENCE AS A WHOLE, ROAMING VAST DESERT PLANETS."),
        GUARDIAN("TOWERING ROOT PEOPLE OF THE JUNGLE REALMS, DEFENDING THE BALANCE OF LIFE, THRIVING ON JUNGLE WORLDS."),
        ARKON("PARASITIC CEPHALOIDS THAT BIND TO THEIR HOST, SEEING THROUGH A SINGLE HIDEOUS EYE, HIDDEN IN THE DEPTHS OF OCEAN PLANETS."),
        OKAMI("PRIMAL GIANTS OF FUR AND FANG, MARKED BY THREE EYES AND CURVED TUSKS, WHO WALK AMONG ARCTIC WORLDS.");

        private final String description;

        Type(String description) {
            this.description = description;
        }

        public String getDescription() {
            return description;
        }
        
        // Static helper method to get all names as String[]
        public static String[] names() {
            return Arrays.stream(Type.values())
                         .map(Type::name)
                         .toArray(String[]::new);
        }
    }
    
    protected List<Integer> modifiers = new ArrayList<>();

    public String getName() {
        return name;
    }

    public Species() {
        // default constructor
    }

    public Species(String name, String size, String physique, String type, String material, String power, String agility, String vitality, String logic, String spirit, String luck) {
        this.name = name;
        this.size = Noun.Size.valueOf(size);
        this.physique = Lifeform.Physique.valueOf(physique);
        this.type = Classification.valueOf(type);
        this.material = Data.materials.get(material);
        this.modifiers.add(Integer.valueOf(power));
        this.modifiers.add(Integer.valueOf(agility));
        this.modifiers.add(Integer.valueOf(vitality));
        this.modifiers.add(Integer.valueOf(logic));
        this.modifiers.add(Integer.valueOf(spirit));
        this.modifiers.add(Integer.valueOf(luck));
        this.statistics = new Attributes(Integer.valueOf(power), Integer.valueOf(agility), Integer.valueOf(vitality), Integer.valueOf(logic), Integer.valueOf(spirit), Integer.valueOf(luck));
    }
    
    // return species attribute modifiers
    public List<Integer> getModifiers() {
        return this.modifiers;
    }

    // return a random Species
    public static Species random() {
        List<Species> values = new ArrayList<>(Data.species.values());
        Math.shuffle(values);
        return (values.get(0));
    }

    // return a Species of type
    public static Species random(String species) {
        return (Data.species.get(species));
    }
    
    // return a Species of type for the Planet
    public static Species random(Planet planet) {
        return (Data.species.get(planet.getNativeSpecies()));
    }
    
    // select a Species from a list
    public static Species choose(Species ... species) {
        List<Species> list = new ArrayList<>(Arrays.asList(species));
        Math.shuffle(list);
        return list.subList(0, list.size()).get(0);
    }

}

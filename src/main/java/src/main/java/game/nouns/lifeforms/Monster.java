package src.main.java.game.nouns.lifeforms;

import src.main.java.game.data.Data;
import src.main.java.game.Material;
import src.main.java.game.nouns.lifeforms.mechanics.Attack;
import src.main.java.game.nouns.locations.Planet;
import src.main.java.generators.MarkovChain;
import src.main.java.text_based_mechanics.parts_of_speech.Noun;


import java.util.*;
import src.main.java.game.mechanics.Action;

import src.main.java.utilities.Math;


public class Monster extends Lifeform {
    
    Set<Characteristics> characteristics = new TreeSet<>();
    Set<Attack> attacks = new TreeSet<>();
    Queue<Action> tasks = new PriorityQueue();
    Locomotion locomotion;
    Instinct instinct;
    Planet.Category biome;
    Diet diet;


    Integer eyes;
    Integer arms;
    Integer legs;
    Boolean tail;
    Boolean gills;
    Boolean wings;

    public enum Characteristics { // TODO make into class, pass in and adjust statistics / skills per each Characteristic, contain random description for characteristic, Color, etc
        HORNS,          // charge attack
        TUSKS,          // gore attack
        SPORES,         // spore attack
        VENOM,          // venomous spray
        CLAWS,          // claw attack
        MANDIBLES,      // grapple attack
        FINS,           // faster swimming
        FRILLS,         // greater intimidation
        ANTENNAE,       // blind sense
        SHELL,          // greater defense
        SPIKED,         // physical attacks hurt the attacker
        BEAK,           // beak attack
        FANGS,          // bite attack
        WHISKERS,       // blind sense
        TONGUE,         // sticky tongue
        BLUBBER,        // exists in cold climates without penalties
        TENTACLES,      // grapple attack
        MUCOUS,         // greater grapple defense
        TRANSLUCENT,    // greater sneak
        BIOLUMINESCENT, // can see in dark rooms
        ELECTROGENESIS, // can generate electric pulses
        PYROGENESIS,    // can generate extreme heat
        HYDROGENESIS,   // can generate water blasts
        TERRAGENESIS,   // can generate earth quakes
        MONOCULAR,      // eyes operate independently -- greater defense vs sneak
        ECHOLOCATION,   // detects enemies through sound
        CAMOUFLAGE,     // greater ability to hide
        REGENERATIVE,   // heals over time
        PARASITIC,      // can leech life from a host
        SWARM           // a collection of tiny creatures -- can surround a Lifeform
    }

    public enum Pattern {   // TODO ?
        NONE,
        SPOTS,
        STRIPES,
        HEX,
        SPIRALS,
        FRACTAL,
        ABSTRACT
    }

    public enum Diet {
        NONE,
        HERBIVORE,
        CARNIVORE,
        OMNIVORE
    }

    public enum Locomotion {
        NORMAL,
        FLY,
        SWIM,
        CLIMB,
        BURROW,
        JUMP
    }

    public enum Instinct {
        CAUTIOUS,       // hide in shell, like a clam
        PROTECTIVE,     // protect other creatures, like a mama bear
        HOSTILE,        // attack on site, like a hungry wolf
        SNEAKY,         // sneak attack from the darkness, like a leopard
        LAZY,           // sleep sleep sleep, like a panda
        SCAVENGER,      // search through rubble for food
        PACK_HUNTER,    // always in a group, hunting
        HERD,           // always in a group, defensive
        HIVE_MIND,      // if one gets attacked, swarm!
        HIBERNATOR,     // found in caves, asleep
        NOCTURNAL,      // found only at night
        GIBBERING       // completely mad
    }

    public Monster() {

    }

    public Monster(String name, String biome, String type, String size, String material, String locomotion, String diet, String instinct, String eyes,
                   String arms, String legs, String tail, String wings, List<String> characteristics) {
        super();
        this.word = name;
        this.name = name;
        this.biome = Planet.Category.valueOf(biome);
        this.locomotion = Locomotion.valueOf(locomotion);
        this.emotion = Emotion.HUNGRY;
        this.diet = Diet.valueOf(diet);
        this.instinct = Instinct.valueOf(instinct);
        this.eyes = Integer.valueOf(eyes);
        this.arms = Integer.valueOf(arms);
        this.legs = Integer.valueOf(legs);
        this.tail = Boolean.valueOf(tail);
        this.wings = Boolean.valueOf(wings);
        this.size = Noun.Size.valueOf(size);
        this.physique = Physique.valueOf("AVERAGE"); // TODO: base this off characteristics / instinct ?
        this.type = Classification.valueOf(type);
        this.material = Data.materials.get(material);

        for (String characteristic : characteristics) {
            this.characteristics.add(Characteristics.valueOf(characteristic));
        }
    }

    private String generateRandomName(Lifeform.Classification type, Characteristics characteristics, Instinct instinct) {
        MarkovChain chain = Data.monster_generator.get(Arrays.asList(type,characteristics,instinct));
        if (chain == null) throw new IllegalArgumentException("Unknown monster type: " + type + " " + characteristics + " " + instinct);
        return chain.generateName(8).toUpperCase();
    }

    public String getName() {
        return name;
    }

    public void generateName() {
        this.name = generateRandomName(type,Math.random(characteristics),instinct);
    }

    public Set<Characteristics> getCharacteristics() {
        return characteristics;
    }

    public boolean hasCharacteristic(Characteristics characteristic) {
        return characteristics.contains(characteristic);
    }

    public Diet getDiet() {
        return diet;
    }

//    public void inheritTraits() {
//        this.setSize(species.getSize());
//        this.setPhysique(species.physique);
//        this.setType(species.getType());
//        this.setMaterial(species.material);
//        // TODO: abilities
//        // TODO: attacks
//        // TODO: elements
//    }

    public void setMaterial(Material material) {
        this.material = material;
    }

    public void addCharacteristic(Characteristics characteristic) {
        characteristics.add(characteristic);
    }

    public void setDiet(Diet diet) {
        this.diet = diet;
    }

    public void setLocomotion(Locomotion locomotion) {
        this.locomotion = locomotion;
    }

    public void setInstinct(Instinct instinct) {
        this.instinct = instinct;
    }

    public void setEyes(Integer eyes) {
        this.eyes = eyes;
    }

    public void setArms(Integer arms) {
        this.arms = arms;
    }

    public void setLegs(Integer legs) {
        this.legs = legs;
    }

    public Integer getLegs() {
        return legs;
    }

    public void setTail(Boolean tail) {
        this.tail = tail;
    }

    public void setWings(Boolean wings) {
        this.wings = wings;
    }

    public void setGills(Boolean gills) {
        this.gills = gills;
    }

    public void setPhysique(Lifeform.Physique physique) {
        this.physique = physique;
    }

    public void setSize(Noun.Size size) {
        this.size = size;
    }

    public Locomotion getLocomotion() {
        return locomotion;
    }
}

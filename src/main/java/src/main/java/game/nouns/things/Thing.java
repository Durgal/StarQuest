package src.main.java.game.nouns.things;

import src.main.java.game.data.Data;
import src.main.java.text_based_mechanics.parts_of_speech.Noun;

import java.util.ArrayList;
import java.util.List;

import src.main.java.utilities.Math;

public abstract class Thing extends Noun {

    private State state;
    
    public enum State {
        
        DEFAULT,
        
        // Structural Integrity
        CRACKED,
        DENTED,
        BROKEN,
        SHATTERED,
        RUSTED,
        SPLINTERED,

        // Position / Orientation
        UPRIGHT,
        ON_ITS_SIDE,
        UPSIDE_DOWN,
        TILTED,
        LEANING,
        HALF_BURIED,

        // Surface / Condition
        CLEAN,
        DIRTY,
        DUSTY,
        BLOODIED,
        STAINED,
        MUDDY,
        COVERED_IN_WEBS,
        COVERED_IN_MOSS,

        // Activity / Functionality
        ACTIVE,
        INACTIVE,
        FLICKERING,
        SMOLDERING,
        GLOWING,

        // Magical / Special
        ENCHANTED,
        CURSED,
        CORRUPTED
    }
    
    public Thing() {
        // default constructor
    }

    public Thing(String name, String material, String size, String sentient) {
        this.word = name;
        this.material = Data.materials.get(material);
        this.state = State.DEFAULT;
        this.size = Size.valueOf(size);
        this.sentient = Boolean.valueOf(sentient);
    }

    // return a random Thing
    public static Thing random() {
        List<Thing> values = new ArrayList<>(Data.objects.values());
        Math.shuffle(values);
        return (values.get(0));
    }

    public static Item random(Class<Item> i) {
        List<Item> values = (List<Item>) get(i);
        Math.shuffle(values);
        return (values.get(0));
    }

    // TODO return random based on Level
    public static Item random(Class<Item> i, Integer level) {
        List<Item> values = (List<Item>) get(i);
        Math.shuffle(values);
        return (values.get(0));
    }

    protected static <T extends Thing> List<? extends Thing> get(Class<T> c) {
        return Data.objects.values().stream().filter(e -> e.getClass().equals(Item.class)).toList();
    }

//    protected static List<Item> getItems() {
//        return (List<Item>) (List<? extends Thing>) Data.objects.values().stream().filter(e -> e.getClass().equals(Item.class)).toList();
//    }

    public State getState() {
        return state;
    }

}

package src.main.java.text_based_mechanics.parts_of_speech;

import java.util.ArrayList;
import java.util.List;
import src.main.java.game.data.Data;
import src.main.java.game.Material;


public class Noun extends Word {

    // A "Noun" describes a Lifeform (person), Location (place), or Thing
    
    public Noun(String word) {
        super(word);
    }

    public enum Size {
        MICRO,      // smaller than 1 inch
        TINY,       // 1 inch to 1 foot
        SMALL,      // 1 to 4 feet
        MEDIUM,     // 4 to 8 feet
        LARGE,      // 8 to 16 feet
        HUGE,       // 16 to 32 feet
        COLOSSAL    // larger than 32 feet
    }

    protected Size size;
    protected Material material;
    protected Boolean sentient;

    protected Noun() {
        // default constructor
    }

    public static Boolean isNoun(String word) {
        return Data.dictionary.get(word) instanceof Noun;
    }
    
    public Material getMaterial() {
        return material;
    }
    
    public Adjective getAdjective() {
        return material.describe();
    }
    
    public Size getSize() {
        return size;
    }

    public List<String> getSuggestedVerbs() {
        return new ArrayList(); // nothing
    }
}

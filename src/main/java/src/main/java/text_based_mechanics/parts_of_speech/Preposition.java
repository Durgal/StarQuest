package src.main.java.text_based_mechanics.parts_of_speech;

import java.util.*;

public class Preposition extends Word {

    public Preposition(String word) {
        super(word);
    }

    static final HashMap<String, Category> PREPOSITIONS;

    static {
        PREPOSITIONS = new HashMap<>();

        PREPOSITIONS.put("AT", Category.AT);
        PREPOSITIONS.put("ON", Category.ON);
        PREPOSITIONS.put("ATOP", Category.ON);
        PREPOSITIONS.put("OVER", Category.ABOVE);
        PREPOSITIONS.put("ABOVE", Category.ABOVE);
        PREPOSITIONS.put("BELOW", Category.BELOW);
        PREPOSITIONS.put("UNDER", Category.BELOW);
        PREPOSITIONS.put("UNDERNEATH", Category.BELOW);
        PREPOSITIONS.put("BENEATH", Category.BELOW);
        PREPOSITIONS.put("IN", Category.IN);
        PREPOSITIONS.put("INSIDE", Category.IN);
        PREPOSITIONS.put("INTO", Category.IN);
        PREPOSITIONS.put("BETWEEN", Category.IN);
        PREPOSITIONS.put("OUT", Category.OUT);
        PREPOSITIONS.put("OUTSIDE", Category.OUT);
        PREPOSITIONS.put("BEHIND", Category.BEHIND);
        PREPOSITIONS.put("BESIDE", Category.BESIDE);
        PREPOSITIONS.put("NEXT", Category.BESIDE);
        PREPOSITIONS.put("TO", Category.TO);
    }

    enum Category { ON, IN, OUT, AT, BELOW, ABOVE, BESIDE, BEHIND, TO }


    public static Boolean isPreposition(String word) {
        return PREPOSITIONS.containsKey(word);
    }

    public static String getPreposition(String preposition) {
        return PREPOSITIONS.get(preposition).toString();
    }

}

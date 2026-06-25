package src.main.java.text_based_mechanics.parts_of_speech;

import java.util.*;

import src.main.java.game.data.Data;
import src.main.java.text_based_mechanics.parts_of_speech.adjective.*;
import src.main.java.utilities.Enum;
import src.main.java.utilities.Math;

public class Adjective extends Word implements Comparable<Adjective> {

    private String type;

    public static Adjective valueOf(String adjective) {
        Word word = Data.dictionary.get(adjective);
        if (Adjective.class.isInstance(word)) {
            return (Adjective) word;
        }
        else {
            return null;
        }
    }

    @Override
    public int compareTo(Adjective o) {
        return this.word.compareTo(o.word);
    }

    protected enum Determiner {
        A,
        AN,
        THE,
        THAT,
        MY,
        YOUR
    }

//    private static final List<Class<? extends Adjective>> types = Arrays.asList(Descriptive.class,Color.class,Shape.class);
//    protected Type type;

    public Adjective() {
        // TODO: select a random adjective ?
    }

    public Adjective(String adjective) {
        this.word = adjective;
        this.type = Enum.getNames(Descriptive.class).contains(word) ? Enum.getName(Descriptive.class) : null;
        this.type = Enum.getNames(Age.class).contains(word) ? Enum.getName(Age.class) : this.type;
        this.type = Enum.getNames(Color.class).contains(word) ? Enum.getName(Color.class) : this.type;
        this.type = Enum.getNames(Quantity.class).contains(word) ? Enum.getName(Quantity.class) : this.type;
        this.type = Enum.getNames(Shape.class).contains(word) ? Enum.getName(Shape.class) : this.type;
    }

    public Adjective(Descriptive adjective) {
        this.word = adjective.toString();
        this.type = Enum.getName(adjective.getClass());
    }

    public Adjective(Color adjective) {
        this.word = adjective.toString();
        this.type = Enum.getName(adjective.getClass());
    }

    public Adjective(Shape adjective) {
        this.word = adjective.toString();
        this.type = Enum.getName(adjective.getClass());
    }

    public Adjective(Age adjective) {
        this.word = adjective.toString();
        this.type = Enum.getName(adjective.getClass());
    }

    public Adjective(Quantity adjective) {
        this.word = adjective.toString();
        this.type = Enum.getName(adjective.getClass());
    }

    public String toString() {
        return word.toUpperCase();
    }

    // get a random adjective from a set
    public static Adjective random(Set<Adjective> adjectives) {
        return Math.random(adjectives);
    }

//    // get specific adjective(s) (color, descriptive, shape, etc) based on class type
//    private static List<? extends Adjective> filter(Set<Adjective> adjectives, Class<? extends Adjective> type) {
//        List<Class<? extends Adjective>> typeFilterSet = (Arrays.asList(type));
//        return adjectives.stream().filter(adjective -> typeFilterSet.contains(adjective.getClass())).toList();
//    }

    private static <T extends java.lang.Enum<?>> List<? extends Adjective> filter(Set<Adjective> adjectives, Class<T> type) {
        List<String> typeFilterSet = (List.of(type.getSimpleName().toUpperCase()));
        return adjectives.stream().filter(adjective -> typeFilterSet.contains(adjective.type)).toList();
    }

    // get all adjectives (ordered)
    public static Set<Adjective> get(Set<Adjective> adjectives) {

        // Adjective Type Order:
        // Determiner (a, the, one, that, my)               []
        // JJQ Quantity (one, seven, many, few)             [unused if only one]
        // JJD Descriptor (beautiful, valuable, indecent)   [required, IF examining an object explicitly]
        // JJZ Size (big, small, tiny)                      [required, if not average]
        // JJA Age (young, old)                             [optional, if not average]
        // JJS Shape (round, long, tall)                    [optional, IF examining an object explicitly]
        // JJC Color (blue, gray, red, yellow)              [required, IF examining an object explicitly]
        // Material (wooden, metal, glass)
        // Thing (Dog, Cat, Monster, Tea-Cup)
        //
        // note: not all adjectives need to be filled for a Noun

        Set<Adjective> result = new LinkedHashSet<>();

//        result.addAll(filter(adjectives,Determiner.class)); // TODO
        result.addAll(filter(adjectives,Quantity.class));
        result.addAll(filter(adjectives,Descriptive.class));
//        result.addAll(filter(adjectives,Size.class)); // TODO: contained in noun class.
        result.addAll(filter(adjectives,Age.class));
        result.addAll(filter(adjectives,Shape.class));
        result.addAll(filter(adjectives,Color.class));

        return result;
    }

}

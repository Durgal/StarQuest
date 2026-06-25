package src.main.java.game;

import src.main.java.game.data.Data;
import src.main.java.game.nouns.lifeforms.Lifeform;
import src.main.java.game.nouns.lifeforms.Species;
import src.main.java.game.nouns.lifeforms.mechanics.Element;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import src.main.java.text_based_mechanics.parts_of_speech.Adjective;
import src.main.java.utilities.Math;

public class Material {

    enum State {
        SOLID,
        LIQUID,
        GAS,
        NONE
    }

    enum Color {
        RED,
        ORANGE,
        YELLOW,
        GREEN,
        BLUE,
        PURPLE,
        WHITE,
        GRAY,
        BLACK,
        BROWN,
        TAN,
        BRASS,
        CHROME,
        NICKEL,
        PLATINUM,
        GOLD,
        SILVER,
        COPPER,
        TRANSPARENT
    }

    String  name;           // material name
    State   state;          // solid / liquid / gas
    Color   color;          // color range
    Adjective adjective;    // adjective
    Boolean flammable;      // can catch fire
    Boolean freezable;      // can be frozen
    Boolean buoyant;        // can float
    Boolean soluble;        // can dissolve in liquid
    Boolean conductive;     // can act as path for electric current
    Boolean corrosive;      // can corrode other objects
    Boolean elastic;        // can be stretched
    Integer temperature;    // default material temperature
    Integer strength;       // HP modifier
    Integer density;        // how dense / hard / tough
    Integer melting;        // melting point
    Integer boiling;        // boiling point

    // examples:
    // NAME   STATE  COLOR       ADJECTIVE FLAME FREEZ BUOY  SOLUB CONDU CORRO ELAST TMP STR DEN MELT BOIL
    // GLASS  SOLID  TRANSPARENT A         FALSE FALSE FALSE FALSE FALSE FALSE FALSE 70  1   100 2700 4000
    // WOOD   SOLID  BROWN       B         TRUE  FALSE TRUE  FALSE FALSE FALSE FALSE 70  3   100 0    0
    // COPPER SOLID  COPPER      C         FALSE FALSE FALSE FALSE TRUE  FALSE FALSE 70  5   200 1900 4600
    // PAPER  SOLID  WHITE       D         TRUE  FALSE FALSE TRUE  FALSE FALSE FALSE 70  1   10  0    0
    // ACID   LIQUID GREEN       E         TRUE  TRUE  FALSE TRUE  FALSE TRUE  FALSE 70  1   10  0    0
    // RUBBER SOLID  BLACK       F         FALSE FALSE TRUE  FALSE FALSE FALSE TRUE  70  2   50  180  300

    public Material() {
        // default constructor
    }

    public Material(String name, String state, String color, String adjective, String flammable, String freezable,
             String buoyant, String soluble, String conductive, String corrosive, String elastic,
             String temperature, String strength, String density, String melting, String boiling) {
        this.name = name;
        this.state = State.valueOf(state);
        this.color = Color.valueOf(color);
        this.adjective = Adjective.valueOf(adjective);
        this.flammable = Boolean.valueOf(flammable);
        this.freezable = Boolean.valueOf(freezable);
        this.buoyant = Boolean.valueOf(buoyant);
        this.soluble = Boolean.valueOf(soluble);
        this.conductive = Boolean.valueOf(conductive);
        this.corrosive = Boolean.valueOf(corrosive);
        this.elastic = Boolean.valueOf(elastic);
        this.temperature = Integer.valueOf(temperature);
        this.strength = Integer.valueOf(strength);
        this.density = Integer.valueOf(density);
        this.melting = Integer.valueOf(melting);
        this.boiling = Integer.valueOf(boiling);
    }

    @Override
    public String toString() {
        return name;
    }
    
    State getState() {
        return state;
    }

    Boolean isFlammable() {
        return flammable;
    }

    Boolean isFreezable() {
        return freezable;
    }

    Boolean isBuoyant() {
        return buoyant;
    }

    Boolean isSoluable() {
        return soluble;
    }

    Boolean isConductive() {
        return conductive;
    }

    Boolean isCorrosive() {
        return corrosive;
    }

    Boolean isElastic() {
        return elastic;
    }

    Boolean isMeltable() {
        return melting > 0;
    }

    Boolean isBoilable() {
        return boiling > 0;
    }

    // return the adjective
    public Adjective describe() {
        return adjective;
    }
    
    // return a random material
    public static Material random() {
        List<Material> values = new ArrayList<>(Data.materials.values());
        Math.shuffle(values);
        return (values.get(0));
    }

    // return a random material based on a type of Lifeform
    public static Material random(Lifeform lifeform) {
        List<Material> values = null;

        Lifeform.Classification type = lifeform.getType();

        switch (type) {
            case BEAST -> {
                values = new ArrayList<>(Arrays.asList(
                        Data.materials.get("FUR"),
                        Data.materials.get("SCALES"),
                        Data.materials.get("WOOL"),
                        Data.materials.get("SKIN"),
                        Data.materials.get("FEATHER")
                ));
                Math.shuffle(values);
            }
            case ABYSSAL -> {
                values = new ArrayList<>(Arrays.asList(
                        Data.materials.get("SKIN"),
                        Data.materials.get("CARAPACE")
                ));
                Math.shuffle(values);
            }
            case PLANT -> {
                values = new ArrayList<>(Arrays.asList(
                        Data.materials.get("GRASS"),
                        Data.materials.get("MOSS"),
                        Data.materials.get("WOOD"),
                        Data.materials.get("VINE")
                ));
                Math.shuffle(values);
            }
            case ARTHROPOD -> {
                values = new ArrayList<>(Arrays.asList(
                        Data.materials.get("CARAPACE")
                ));
                Math.shuffle(values);
            }
            case HUMANOID -> {
                values = new ArrayList<>(Arrays.asList(
                        Data.materials.get("SKIN")
                ));
                Math.shuffle(values);
            }
            case CONSTRUCT -> {
                values = new ArrayList<>(Arrays.asList(
                        Data.materials.get("IRON"),
                        Data.materials.get("BRASS"),
                        Data.materials.get("CHROME"),
                        Data.materials.get("NICKEL"),
                        Data.materials.get("COPPER"),
                        Data.materials.get("PLATINUM")
                ));
                Math.shuffle(values);
            }
            case ELEMENTAL -> {
                values = new ArrayList<>(Arrays.asList(
                        Data.materials.get("DIRT"),
                        Data.materials.get("LAVA"),
                        Data.materials.get("ROCK"),
                        Data.materials.get("STEAM"),
                        Data.materials.get("WATER")
                ));
                Math.shuffle(values);
            }
            case UNKNOWN -> {
                values = new ArrayList<>(Arrays.asList(
                        Data.materials.get("NULL")
                ));
                Math.shuffle(values);
            }
            default -> {

            }
        }

        return (values.get(0));
    }

    // return a random material based on a type of Element
    public static Material random(Element.Category element) {
        List<Material> values = null;

        switch (element) {
            case NONE -> {
                values = new ArrayList<>(Arrays.asList(
                        Data.materials.get("IRON")
                ));
                Math.shuffle(values);
            }
            case FIRE -> {
                values = new ArrayList<>(Arrays.asList(
                        Data.materials.get("LAVA"),
                        Data.materials.get("ROCK")
                ));
                Math.shuffle(values);
            }
            case WATER -> {
                values = new ArrayList<>(Arrays.asList(
                        Data.materials.get("WATER"),
                        Data.materials.get("STEAM")
                ));
                Math.shuffle(values);
            }
            case WIND -> {
                values = new ArrayList<>(Arrays.asList(
                        Data.materials.get("DUST"),
                        Data.materials.get("SAND"),
                        Data.materials.get("GLASS")
                ));
                Math.shuffle(values);
            }
            case EARTH -> {
                values = new ArrayList<>(Arrays.asList(
                        Data.materials.get("MARBLE"),
                        Data.materials.get("ROCK")
                ));
                Math.shuffle(values);
            }
            case PLANT -> {
                values = new ArrayList<>(Arrays.asList(
                        Data.materials.get("VINE"),
                        Data.materials.get("WOOD")
                ));
                Math.shuffle(values);
            }
            case ICE -> {
                values = new ArrayList<>(Arrays.asList(
                        Data.materials.get("ICE"),
                        Data.materials.get("SNOW")
                ));
                Math.shuffle(values);
            }
            case DARK -> {
                values = new ArrayList<>(Arrays.asList(
                        Data.materials.get("JADE"),
                        Data.materials.get("BONE")
                ));
                Math.shuffle(values);
            }
            case SOLAR -> {
                values = new ArrayList<>(Arrays.asList(
                        Data.materials.get("SANDSTONE"),
                        Data.materials.get("SAND")
                ));
                Math.shuffle(values);
            }
            default -> {

            }
        }

        return (values.get(0));
    }

}

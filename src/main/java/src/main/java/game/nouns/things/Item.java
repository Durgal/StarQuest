package src.main.java.game.nouns.things;

import java.util.*;
import java.util.stream.Collectors;

import src.main.java.utilities.Math;

public class Item extends Thing {

    Type type;

    Boolean container;

    // Type determines function of item
    public enum Type {
        DEFAULT,
        AMMUNITION,
        FURNITURE,
        INSTRUMENT,
        CONTAINER,
        GADGET,
        JEWELRY,
        KEY,
        FOOD,
        TOOL,
        MEDICINE,
        LIGHT,
        DATA,
        TOY,
        APPAREL,
        SWITCH
    }

    public Item() {
        // default constructor
    }

    public Item(String name, String type, String material, String size, String sentient, String container) {
        super(name, material, size, sentient);
        this.type = Type.valueOf(type);
        this.container = Boolean.valueOf(container);
    }

    // return a random Item
    public static Item random() {
        List<Thing> values = new ArrayList<>(get(Item.class));
        Math.shuffle(values);
        return (Item) values.get(0);
    }

    // return a random Item of Category
    public static Item random(Type category) {
        ArrayList<Item> values = new ArrayList<>(filter((Collection<Item>) get(Item.class),category));
        Math.shuffle(values);
        return values.get(0);
    }

    // filter a list of Item based on Type
    private static List<Item> filter(Collection<Item> items, Type type) {
        List<Item> filteredItems;
        Set<Type> typeFilterSet = new HashSet<>(Arrays.asList(type));
        filteredItems = items.stream().filter(item -> typeFilterSet.contains(item.type)).collect(Collectors.toList());
        return filteredItems;
    }
    
    @Override
    public List<String> getSuggestedVerbs() {
        return switch (type) {
            case DEFAULT -> List.of("EXAMINE", "TAKE", "DROP", "USE");
            case AMMUNITION -> List.of("USE", "TAKE", "DROP");
            case FURNITURE -> List.of("EXAMINE", "SIT", "MOVE");
            case INSTRUMENT -> List.of("PLAY", "EXAMINE", "USE");
            case CONTAINER -> List.of("OPEN", "CLOSE", "TAKE", "PUT", "EXAMINE");
            case GADGET -> List.of("USE", "EXAMINE", "TAKE", "DROP");
            case JEWELRY -> List.of("WEAR", "EXAMINE", "TAKE", "DROP");
            case KEY -> List.of("USE", "TAKE", "DROP");
            case FOOD -> List.of("EAT", "TAKE", "DROP");
            case TOOL -> List.of("USE", "TAKE", "DROP", "EXAMINE");
            case MEDICINE -> List.of("USE", "EXAMINE", "TAKE", "DROP");
            case LIGHT -> List.of("TURNON", "TURNOFF", "TAKE", "DROP", "EXAMINE");
            case DATA -> List.of("USE", "EXAMINE", "TAKE", "DROP");
            case TOY -> List.of("PLAY", "EXAMINE", "TAKE", "DROP");
            case APPAREL -> List.of("WEAR", "UNEQUIP", "TAKE", "DROP", "EXAMINE");
            case SWITCH -> List.of("TOGGLE", "EXAMINE");
        };
    }

}

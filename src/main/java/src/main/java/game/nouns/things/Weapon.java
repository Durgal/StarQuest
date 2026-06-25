package src.main.java.game.nouns.things;

public class Weapon extends Thing {

    Type type;          // the damage type of the weapon (slash / pierce / crush)
    Integer modifier;   // the damage modifier for the weapon
    Integer hands;      // the total number of hands required to use weapon

    protected enum Type {
        SLASH,
        PIERCE,
        CRUSH,
        RANGED
    }

    public Weapon() {
        // default constructor
    }

    public Weapon(String name, String category, String mod, String hands) {
        super(name, "IRON", "SMALL", "false");
        this.type = Type.valueOf(category);
        this.modifier = Integer.valueOf(mod);
        this.hands = Integer.valueOf(hands);
    }

}

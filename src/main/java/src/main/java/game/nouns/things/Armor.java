package src.main.java.game.nouns.things;

public class Armor extends Thing {

    Type type;          // the damage type of armor (head / body / legs etc)
    Integer modifier;   // the damage reduction modifier for the armor

    protected enum Type {
        HEAD,
        BODY,
        HANDS,
        LEGS,
        FEET,
        OFFHAND
    }

    public Armor() {
        // default constructor
    }

    public Armor(String name, String category, String mod) {
        super(name, "IRON", "SMALL", "false");
        this.type = Type.valueOf(category);
        this.modifier = Integer.valueOf(mod);
    }

}

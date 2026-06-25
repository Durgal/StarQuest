package src.main.java.game.nouns.lifeforms.mechanics;

public class Attributes {

    public enum  Attribute {
        NONE,
        POWER,
        AGILITY,
        VITALITY,
        LOGIC,
        SPIRIT,
        LUCK
    }

    public static Integer BASE = 5;
    
    // POWER AGILITY VITALITY LOGIC SPIRIT LUCK
    public static Integer POWER;
    public static Integer AGILITY;
    public static Integer VITALITY;
    public static Integer LOGIC;
    public static Integer SPIRIT;
    public static Integer LUCK;

    public static Integer ARMOR = 0;

    // derived attributes
    public static Integer LIFE;
    public static Integer DEFENSE;
    public static Integer ENERGY;
    public static Integer SPEED;

    public Attributes() { }
    
    public Attributes(int power, int agility, int vitality, int logic, int spirit, int luck) {
        POWER    = BASE + power;
        AGILITY  = BASE + agility;
        VITALITY = BASE + vitality;
        LOGIC    = BASE + logic;
        SPIRIT   = BASE + spirit;
        LUCK     = BASE + luck;

        LIFE = (POWER/2 + VITALITY/2);
        DEFENSE = (AGILITY/2 + ARMOR);
        ENERGY = (LOGIC/2 + SPIRIT/4 + VITALITY/4);
        SPEED = (AGILITY);
    }

    public String get(Attribute attribute) {
        String value;
        switch (attribute) {
            case Attribute.POWER:
                value = POWER.toString();
                break;
            case Attribute.AGILITY:
                value = AGILITY.toString();
                break;
            case Attribute.VITALITY:
                value = VITALITY.toString();
                break;
            case Attribute.LOGIC:
                value = LOGIC.toString();
                break;
            case Attribute.SPIRIT:
                value = SPIRIT.toString();
                break;
            case Attribute.LUCK:
                value = LUCK.toString();
                break;
            default:
                value = "";
                break;
        }
        return value;
    }
    
    public Integer value(Attribute attribute) {
        Integer value;
        switch (attribute) {
            case Attribute.POWER:
                value = POWER;
                break;
            case Attribute.AGILITY:
                value = AGILITY;
                break;
            case Attribute.VITALITY:
                value = VITALITY;
                break;
            case Attribute.LOGIC:
                value = LOGIC;
                break;
            case Attribute.SPIRIT:
                value = SPIRIT;
                break;
            case Attribute.LUCK:
                value = LUCK;
                break;
            default:
                value = 0;
                break;
        }
        return value;
    }
}

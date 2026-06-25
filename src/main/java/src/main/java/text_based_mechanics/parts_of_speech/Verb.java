package src.main.java.text_based_mechanics.parts_of_speech;

import java.util.*;

public class Verb extends Word {

    public Verb(String word) {
        super(word);
    }

    static final HashMap<String, Category> VERBS;

    static {
        VERBS = new HashMap<>();

        // TODO: OBSCURE PHRASAL VERBS MARKED WITH "//"

        VERBS.put("USE", Category.USE);     // TODO: this will act as a help statement that suggests verbs to go with noun classification (container, weapon, monster, person, etc)
        VERBS.put("LOOK", Category.LOOK);
        VERBS.put("EXAMINE", Category.LOOK);
        VERBS.put("OBSERVE", Category.LOOK);
        VERBS.put("INSPECT", Category.LOOK);
        VERBS.put("CHECKOUT", Category.LOOK); //
        VERBS.put("LOOKAROUND", Category.LOOK); //
        VERBS.put("SCAN", Category.LOOK);
        VERBS.put("GLANCE", Category.LOOK);
        VERBS.put("GAZE", Category.LOOK);
        VERBS.put("STARE", Category.LOOK);
        VERBS.put("GAPE", Category.LOOK);
        VERBS.put("PEER", Category.LOOK);
        VERBS.put("PEEK", Category.LOOK);
        VERBS.put("LISTEN", Category.LISTEN);
        VERBS.put("SMELL", Category.SMELL);
        VERBS.put("SNIFF", Category.SMELL);
        VERBS.put("WHIFF", Category.SMELL);
        VERBS.put("TAKE", Category.TAKE);
        VERBS.put("GET", Category.TAKE);
        VERBS.put("PICKUP", Category.TAKE); //
        VERBS.put("ACQUIRE", Category.TAKE);
        VERBS.put("OBTAIN", Category.TAKE);
        VERBS.put("GRAB", Category.TAKE);
//        VERBS.put("CLEANOUT", Category.TAKEALL); //
//        VERBS.put("EMPTYOUT", Category.TAKEALL); //
        VERBS.put("GIVE", Category.GIVE);
        VERBS.put("OFFER", Category.GIVE);
        VERBS.put("BESTOW", Category.GIVE);
        VERBS.put("HANDOVER", Category.GIVE); //
        VERBS.put("BEQUEATH", Category.GIVE);
        VERBS.put("ENTRUST", Category.GIVE);
        VERBS.put("EQUIP", Category.EQUIP);
        VERBS.put("PUTON", Category.EQUIP); //
        VERBS.put("WEAR", Category.EQUIP);
        VERBS.put("DON", Category.EQUIP);
        VERBS.put("REMOVE", Category.REMOVE);
        VERBS.put("UNEQUIP", Category.REMOVE);
        VERBS.put("DOFF", Category.REMOVE);
        VERBS.put("TAKEOFF", Category.REMOVE); //
        VERBS.put("SETDOWN", Category.DROP); //
        VERBS.put("PUTDOWN", Category.DROP); //
        VERBS.put("DROP", Category.DROP);
        VERBS.put("OPEN", Category.OPEN);
        VERBS.put("CLOSE", Category.CLOSE);
        VERBS.put("SHUT", Category.CLOSE);
        VERBS.put("SLAM", Category.CLOSE);
        VERBS.put("EXTINGUISH", Category.EXTINGUISH);
        VERBS.put("BLOWOUT", Category.EXTINGUISH); //
        VERBS.put("PUTOUT", Category.EXTINGUISH); //
        VERBS.put("PUT", Category.PUT);
        VERBS.put("SET", Category.PUT);
        VERBS.put("DEPOSIT", Category.PUT);
        VERBS.put("LAY", Category.PUT);
        VERBS.put("PUSH", Category.PUSH);
        VERBS.put("SHOVE", Category.PUSH);
        VERBS.put("KNOCKDOWN", Category.PUSH); //
        VERBS.put("KNOCKOVER", Category.PUSH); //
        VERBS.put("PULL", Category.PULL);
        VERBS.put("TUG", Category.PULL);
        VERBS.put("DRAG", Category.PULL);
        VERBS.put("TURN", Category.TURN);
        VERBS.put("SPIN", Category.TURN);
        VERBS.put("ROTATE", Category.TURN);
        VERBS.put("TURNAROUND", Category.TURN); //
        VERBS.put("TOUCH", Category.TOUCH);
        VERBS.put("FEEL", Category.TOUCH);
        VERBS.put("HOLD", Category.TOUCH);
        VERBS.put("POKE", Category.TOUCH);
        VERBS.put("STROKE", Category.TOUCH);
        VERBS.put("TAP", Category.TOUCH);
        VERBS.put("PRESS", Category.TOUCH);
        VERBS.put("PAT", Category.TOUCH);
        VERBS.put("PROD", Category.TOUCH);
        VERBS.put("FIDDLE", Category.TOUCH);
        VERBS.put("FINGER", Category.TOUCH);
        VERBS.put("WAVE", Category.WAVE);
        VERBS.put("GESTURE", Category.WAVE);
        VERBS.put("GESTICULATE", Category.WAVE);
        VERBS.put("SIGNAL", Category.WAVE);
        VERBS.put("BECKON", Category.WAVE);
        VERBS.put("DANCE", Category.DANCE);
        VERBS.put("GYRATE", Category.DANCE);
        VERBS.put("GETDOWN", Category.DANCE); //
        VERBS.put("WHIRL", Category.DANCE);
        VERBS.put("TWERK", Category.DANCE);
        VERBS.put("FLIRT", Category.FLIRT);
        VERBS.put("HITON", Category.FLIRT); //
        VERBS.put("KISS", Category.KISS);
        VERBS.put("SMOOCH", Category.KISS);
        VERBS.put("CANOODLE", Category.KISS);
        VERBS.put("SNOG", Category.KISS);
        VERBS.put("POINT", Category.POINT);
        VERBS.put("SPEAK", Category.SPEAK);
        VERBS.put("TALK", Category.SPEAK);
        VERBS.put("SAY", Category.SPEAK);
        VERBS.put("YELL", Category.YELL);
        VERBS.put("SCREAM", Category.YELL);
        VERBS.put("SHRIEK", Category.YELL);
        VERBS.put("WAIL", Category.YELL);
        VERBS.put("WHISPER", Category.WHISPER);
        VERBS.put("MUTTER", Category.WHISPER);
        VERBS.put("MURMUR", Category.WHISPER);
        VERBS.put("LAUGH", Category.LAUGH);
        VERBS.put("CHORTLE", Category.LAUGH);
        VERBS.put("SNICKER", Category.LAUGH);
        VERBS.put("GUFFAW", Category.LAUGH);
        VERBS.put("SING", Category.SING);
        VERBS.put("YODEL", Category.SING);
        VERBS.put("ATTACK", Category.ATTACK);
        VERBS.put("SLICE", Category.ATTACK);
        VERBS.put("CHARGE", Category.ATTACK);
        VERBS.put("STRIKE", Category.ATTACK);
        VERBS.put("SHOOT", Category.ATTACK);
        VERBS.put("HIT", Category.ATTACK);
        VERBS.put("BEATUP", Category.ATTACK); //
        VERBS.put("TAKEOUT", Category.ATTACK); //
        VERBS.put("THROW", Category.THROW);
        VERBS.put("YEET", Category.THROW);
        VERBS.put("PUNCH", Category.PUNCH);
        VERBS.put("WALLOP", Category.PUNCH);
        VERBS.put("WHACK", Category.PUNCH);
        VERBS.put("CURSE", Category.CAST);
        VERBS.put("HEX", Category.CAST);
        VERBS.put("BREAK", Category.BREAK);
        VERBS.put("DESTROY", Category.BREAK);
        VERBS.put("DEMOLISH", Category.BREAK);
        VERBS.put("JUMP", Category.JUMP);
        VERBS.put("HOP", Category.JUMP);
        VERBS.put("PARKOUR", Category.JUMP);
        VERBS.put("LEAP", Category.JUMP);
        VERBS.put("CLIMB", Category.CLIMB);
        VERBS.put("HIDE", Category.CONCEAL);
        VERBS.put("CONCEAL", Category.CONCEAL);
        VERBS.put("FOLLOW", Category.FOLLOW);
        VERBS.put("CHASE", Category.FOLLOW);
        VERBS.put("ACCOMPANY", Category.FOLLOW);
        VERBS.put("ESCORT", Category.FOLLOW);
        VERBS.put("TRACK", Category.FOLLOW);
        VERBS.put("TRAIL", Category.FOLLOW);
        VERBS.put("PURSUE", Category.FOLLOW);
        VERBS.put("STALK", Category.FOLLOW);
        VERBS.put("HUNT", Category.FOLLOW);
        VERBS.put("GOAFTER", Category.FOLLOW); //
        VERBS.put("PRAY", Category.PRAY);
        VERBS.put("EAT", Category.EAT);
        VERBS.put("TASTE", Category.EAT);
        VERBS.put("LICK", Category.EAT);
        VERBS.put("NOM", Category.EAT);
        VERBS.put("DRINK", Category.DRINK);
        VERBS.put("SWALLOW", Category.DRINK);
        VERBS.put("SLEEP", Category.SLEEP);
        VERBS.put("WHERE", Category.WHERE);
        VERBS.put("ENTER", Category.ENTER);
        VERBS.put("GO", Category.ENTER);
        VERBS.put("GETIN", Category.ENTER); //
        VERBS.put("GOIN", Category.ENTER); //
        VERBS.put("GOINTO", Category.ENTER); //
        VERBS.put("EXIT", Category.EXIT);
        VERBS.put("ESCAPE", Category.EXIT);
        VERBS.put("LEAVE", Category.EXIT);
        VERBS.put("VACATE", Category.EXIT);
        VERBS.put("GETOUT", Category.EXIT); //
        VERBS.put("GOOUT", Category.EXIT); //
//        VERBS.put("GOUP", Category.EXIT); //TODO
//        VERBS.put("GODOWN", Category.EXIT); //TODO
//        VERBS.put("CLIMBUP", Category.EXIT); //TODO
//        VERBS.put("CLIMBDOWN", Category.EXIT); //TODO
    }

    public enum Category {
    NOTHING,
    USE,
    LOOK,
    LISTEN,
    SMELL,
    TAKE,
    GIVE,
    EQUIP,
    REMOVE,
    DROP,
    OPEN,
    CLOSE,
    EXTINGUISH,
    PUT,
    PUSH,
    PULL,
    TURN,
    TOUCH,
    WAVE,
    DANCE,
    FLIRT,
    KISS,
    POINT,
    SPEAK,
    YELL,
    WHISPER,
    LAUGH,
    SING,
    ATTACK,
    THROW,
    PUNCH,
    CAST,
    BREAK,
    JUMP,
    CLIMB,
    CONCEAL,
    FOLLOW,
    PRAY,
    CURSE,
    EAT,
    DRINK,
    SLEEP,
    WHERE,
    ENTER,
    EXIT
    }

    public static Boolean isVerb(String word) {
        return VERBS.containsKey(word);
    }

    public static Verb getVerb(String verb) {
        return new Verb(getAction(verb));
    }

    public static String getAction(String verb) {
        return VERBS.get(verb).toString();
    }
    
    public static Category getCategory(Verb verb) {
        return VERBS.get(verb.toString());
    }

}

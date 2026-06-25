package src.main.java.game.nouns.lifeforms.mechanics;

import java.util.HashMap;
import java.util.Map;
import src.main.java.utilities.Enum;

public class Alignment {

    public enum Type {
        NONE,
        ORDER,
        HARMONY,
        CHAOS
    }
    
    private final Map<Type,Integer> ALIGNMENT = new HashMap<>();
    private static final int MAX_POINTS = 12;

    public final static Type HARMONY = Type.HARMONY;
    public final static Type ORDER = Type.ORDER;
    public final static Type CHAOS = Type.CHAOS;

    // initialize a blank alignment
    public Alignment() {
        for (Type type : Type.values()) ALIGNMENT.put(type,0);
    }
    
    // force the alignment
    public Alignment(Type type) {
        ALIGNMENT.put(type, MAX_POINTS);
    }
    
    public static Alignment random() {
        return new Alignment(Enum.choose(Type.HARMONY, Type.ORDER, Type.CHAOS));
    }
    
    public void addPoint(Type type, int amount) {
        int total = ALIGNMENT.values().stream().mapToInt(Integer::intValue).sum();
        if (total + amount > MAX_POINTS) normalize();
        ALIGNMENT.put(type, ALIGNMENT.get(type) + amount);
        normalize();
    }
    
    public Type getAlignment() {
        return ALIGNMENT.entrySet().stream()
                .max(Map.Entry.comparingByValue())
                .map(Map.Entry::getKey)
                .orElse(Type.NONE);
    }
    
    private void normalize() {
        int total = ALIGNMENT.values().stream().mapToInt(Integer::intValue).sum();
        if (total > MAX_POINTS) {
            double ratio = (double) MAX_POINTS / total;
            for (Type t : ALIGNMENT.keySet()) {
                ALIGNMENT.put(t, (int)Math.round(ALIGNMENT.get(t) * ratio));
            }
        }
    }
    
    // additional ideas:
    // Bounty - 0 to 100 (the authorities are after you!) based on your actions.

}

package src.main.java.game.nouns.lifeforms;

import src.main.java.game.nouns.lifeforms.mechanics.Alignment;
import src.main.java.game.Material;
import src.main.java.game.nouns.lifeforms.mechanics.Attributes;
import src.main.java.game.nouns.lifeforms.mechanics.Element;
import src.main.java.game.nouns.lifeforms.mechanics.Skills;
import src.main.java.text_based_mechanics.parts_of_speech.Noun;
import src.main.java.game.nouns.locations.*;

import java.util.*;
import java.util.stream.Collectors;
import src.main.java.game.data.Data;
import src.main.java.game.mechanics.Action;
import src.main.java.game.nouns.lifeforms.mechanics.Attributes.Attribute;
import src.main.java.game.nouns.lifeforms.mechanics.Skills.Skill;

import src.main.java.utilities.Math;
import src.main.java.utilities.Enum;

public abstract class Lifeform extends Noun {

    String name;
    
    protected Classification type;

    protected Physique physique;

    protected Element.Category element;

    protected Alignment alignment;
    
    protected Action action;

    protected Emotion emotion;

    protected Condition condition;

    protected Place location;

    protected Attributes statistics;

    protected Skills skills;
    
    protected Integer level;
    
    protected List<Noun> inventory;

    public enum Classification {
        ABYSSAL,        // alien entities, often with powers drawn from their minds
        BEAST,          // natural and wild
        ARTHROPOD,      // lifeforms with hard exoskeletons
        CONSTRUCT,      // artificially created
        ELEMENTAL,      // composed of elements (fire, rock, water)
        HUMANOID,       // bipeds with culture
        PLANT,          // biologically composed of vegetation
        UNKNOWN         // creatures that don't fit into any of the other groups
    }

    public enum Emotion {
        NONE,
        HUNGRY,
        ANGRY,
        CONTENT,
        SLEEPY,
        SAD
    }

    public enum Condition {
        NONE,           //
        POISONED,       // POISON DAMAGE
        BURNING,        // FIRE DAMAGE
        FREEZING,       // ICE DAMAGE
        PARALYZED,      // ELECTRIC DAMAGE
        CORRODING,      // ACID DAMAGE
        ENRAGED,        // 
        AFRAID,         // 
        BLINDED,        // 
        DEAFENED,       // 
        CHARMED,        // 
        RESTRAINED,     // 
        PRONE,          // 
        UNCONCIOS,      // 
        UNCONSCIOUS     // 
    }

    public enum Physique { // TODO: make this effect stats (heavier += vitality / slender += agility etc) or the inverse
        AVERAGE,
        SLENDER,
        HEAVY,
        MUSCULAR
    }
 
    public enum Gender {
        MALE,
        FEMALE,
        NONE;
        
        static Gender random() {
            return Enum.choose(Gender.MALE, Gender.FEMALE);
        }
        
        // Static helper method to get all names as String[]
        public static String[] names() {
            return Arrays.stream(Gender.values())
                         .map(Gender::name)
                         .toArray(String[]::new);
        }
    }

    Lifeform() {
        super();
        name = "default_name";      // name of Lifeform
        word = "default_entity";    // species
        statistics = new Attributes();
        skills = new Skills(statistics);
        action = new Action(this);
        material = new Material();
        alignment = Alignment.random();
        inventory = new ArrayList();
        emotion = Emotion.NONE;
        sentient = true;
        level = 1;
    }

    public String getName() {
        return name;
    }
    
    public Classification getType() {
        return type;
    }

    public void setType(Lifeform.Classification type) {
        this.type = type;
    }

    public Material getMaterial() {
        return material;
    }

    public void setMaterial(Material material) {
        this.material = material;
    }

    public void setAlignment(Alignment alignment) {
        this.alignment = alignment;
    }

    public Element.Category getElement() {
        return element;
    }

    public void setElement(Element.Category element) {
        this.element = element;
    }

    public Size getSize() {
        return size;
    }

    public void setSize(Noun.Size size) {
        this.size = size;
    }
    
    public Action getAction() {
        return action;
    }
    
    public void setAction(Action action) {
        this.action = action;
    }
    
    public Condition getCondition() {
        return condition;
    }
    
    public void setCondition(Condition condition) {
        this.condition = condition;
    }
    
    public Emotion getEmotion() {
        return emotion;
    }
    
    public void setEmotion(Emotion emotion) {
        this.emotion = emotion;
    }

    public String getAttribute(Attribute attribute) {
        return statistics.get(attribute);
    }
    
    public String getSkill(Skill skill) {
        return skill.get(skill);
    }
    
    public String getLevel() {
        return Integer.toString(level);
    }

    public String getLife() {
        return Integer.toString(Attributes.LIFE);
    }

    public String getEnergy() {
        return Integer.toString(Attributes.ENERGY);
    }

    public String getDefense() {
        return Integer.toString(Attributes.DEFENSE);
    }
    
    public String getAlignment() {
        return alignment.getAlignment().name();
    }
    
    public void setLocation(Place location) {
        if (Location.class.isInstance(location)) {
            this.location = location;
//            this.location = ((Location)location).getEntrance(); // force player into entrance room
        }
        if (Room.class.isInstance(location)) {
            this.location = location;
            ((Room)this.location).setVisited();
        }
    }

    public Place getLocation() {
        return location;
    }

    public <T extends Place> T getLocation(Class<T> clazz) {

        Place current = location;

        if (!isIn(current)) {
            return null;
        }

        // Traverse upwards through parent references until we find the requested type
        while (current != null) {
            if (clazz.isInstance(current)) {
                return clazz.cast(current);
            }

            if (current instanceof Room room) {             // Room knows its Location
                current = room.getLocation();
            } else if (current instanceof Location loc) {   // Location knows its Continent
                current = loc.getContinent();
            } else if (current instanceof Continent cont) { // Continent knows its Planet
                current = cont.getPlanet();
            } else if (current instanceof Planet planet) {  // Planet knows its SolarSystem
                current = planet.getSolarSystem();
            } else {
                return null;
            }
        }

        return null;
    }
    
    public Class<? extends Place> getLocationType() {
        return location.getClass();
    }
    
    public boolean isIn(Place place) {
        Place current = location;

        while (current != null) {
            if (place.getClass().isInstance(current)) {
                return true; // found a match
            }

            // Move up the hierarchy
            if (current instanceof Room room) {
                current = room.getLocation();
            } else if (current instanceof Location loc) {
                current = loc.getContinent();
            } else if (current instanceof Continent cont) {
                current = cont.getPlanet();
            } else if (current instanceof Planet) {
                current = null; // top reached
            } else {
                return false; // unknown subclass
            }
        }

        return false; // no match found
    }
    
    public Room getRoom() {
        return (getLocationType() == Room.class) ? (Room) location : null;
    }
    
    public List<Noun> getInventory() {
        return inventory;
    }
    
    public void addToInventory(Noun noun) {
        inventory.add(noun);
    }
    
    public boolean hasItem(Noun noun) {
        return inventory.contains(noun);
    }

    // select a Lifeform from a list
    public static Class<? extends Lifeform> choose(Lifeform ... lifeforms) {
        List<Lifeform> list = new ArrayList<>(Arrays.asList(lifeforms));
        Math.shuffle(list);
        return list.subList(0, list.size()).get(0).getClass();
    }
    
    // get a random Lifeform based on Type
    public static Monster random(Classification type) {
        ArrayList<Monster> values = new ArrayList<>(filter((Collection<Monster>) get(Monster.class),type));
        Math.shuffle(values);
        return values.get(0);
    }
    
    // filter a list of Item based on Type
    private static List<Monster> filter(Collection<Monster> lifeforms, Classification type) {
        List<Monster> filteredItems;
        Set<Classification> typeFilterSet = new HashSet<>(Arrays.asList(type));
        filteredItems = lifeforms.stream().filter(lifeform -> typeFilterSet.contains(lifeform.type)).collect(Collectors.toList());
        return filteredItems;
    }
    
    protected static <T extends Lifeform> List<? extends Lifeform> get(Class<T> c) {
        return Data.monsters.values().stream().filter(e -> e.getClass().equals(Monster.class)).toList();
    }
    
    @Override
    public List<String> getSuggestedVerbs() {
        return List.of("TALK", "YELL", "WHISPER", "ATTACK", "FOLLOW", "FLIRT", "KISS");
    }

}

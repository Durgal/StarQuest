package src.main.java.game.nouns.locations;

import src.main.java.game.nouns.things.Item;
import src.main.java.text_based_mechanics.parts_of_speech.Noun;

import java.util.ArrayList;
import java.util.List;
import src.main.java.game.nouns.lifeforms.Lifeform;
import src.main.java.game.nouns.lifeforms.NPC;
import src.main.java.game.nouns.things.Thing;

import src.main.java.utilities.Enum;

/*
> Solar System
    > Planet 1
        > Continent 1
            > Location 1
                > Room A
                > Room B
                > Room C
            > Location N
            > . . .
        > Continent N
        > . . .
    > Planet N
    > . . .
> Solar System N
> . . .
 */


// Rooms describe places that the player has visited or is currently existing.
// They are the smallest sized location and contain the actual fine details randomly defined based on their parent location.
// The player will only "know" about a Room if it is adjacent, but they can choose to "fast travel"
// to other locations they have been to TODO: (as long as it is only one step up or down the Type list?)
public class Room extends Place implements Comparable<Room> {

    private Room north;
    private Room south;
    private Room east;
    private Room west;
    private final Location parent;
    private Boolean hasVisited = false;

    // a list of Lifeforms and Things contained in this Room
    private List<Noun> content = new ArrayList<>();

    public enum Cardinal {
        NORTH,
        EAST,
        SOUTH,
        WEST
    }

    enum Function {
        RESIDENCE,
        SHOP,
        LIBRARY,
        BANK,
        UNIVERSITY
    }
    
    public Room() {
        parent = null;
    }
    
    public Room(Location location) {
        name = "TODO";
        parent = location;
        size = Enum.choose(Size.SMALL,Size.MEDIUM,Size.LARGE);
        generate();
    }
    
    public String getName() {
        return name;
    }
    
    public List<Thing> getThings() {
        List<Thing> things = new ArrayList<>();
        for (Noun noun : content) {
            if (noun instanceof Thing thing) {
                things.add(thing);
            }
        }
        return things;
    }
    
    public Thing getThing(Noun thing) {
        for (Noun noun : content) {
            if (noun.equals(thing)) {
                if (noun instanceof Thing t) {
                    return t;
                }
            }
        }
        return null;
    }
    
    public List<Lifeform> getLifeforms() {
        List<Lifeform> lifeforms = new ArrayList<>();
        for (Noun noun : content) {
            if (noun instanceof Lifeform lifeform) {
                lifeforms.add(lifeform);
            }
        }
        return lifeforms;
    }

    public Boolean hasNoun(Noun noun) {
        return content.contains(noun);
    }
    
    public void removeObject(Noun object) {
        if (hasNoun(object)) {
            content.remove(object);
        }
    }
    
    public String getAtmosphere() {
        return "SCARY";
    }

    public boolean isExplored() {
        return hasVisited;
    }
    
    public void setVisited() {
        hasVisited = true;
    }
    
    public Location getLocation() {
        return parent;
    }

    public void connect(Room room, Cardinal cardinal) {
        switch (cardinal) {
            case NORTH:
                if (room.north != this) {
                    room.north = this;
                }
                break;
            case EAST:
                if (room.east != this) {
                    room.east = this;
                }
                break;
            case SOUTH:
                if (room.south != this) {
                    room.south = this;
                }
                break;
            case WEST:
                if (room.west != this) {
                    room.west = this;
                }
                break;
        }
    }

    public void disconnect(Cardinal cardinal) {
        switch (cardinal) {
            case NORTH:
                if (north != null) {
                    north.south = null; // break reverse link
                    north = null;
                }
                break;
            case SOUTH:
                if (south != null) {
                    south.north = null;
                    south = null;
                }
                break;
            case EAST:
                if (east != null) {
                    east.west = null;
                    east = null;
                }
                break;
            case WEST:
                if (west != null) {
                    west.east = null;
                    west = null;
                }
                break;
        }
    }

    public Room getNorth() { return north; }
    public Room getSouth() { return south; }
    public Room getEast()  { return east; }
    public Room getWest()  { return west; }

    public List<Room> getNeighbors() {
        List<Room> neighbors = new ArrayList<>();
        if (north != null) neighbors.add(north);
        if (south != null) neighbors.add(south);
        if (east  != null) neighbors.add(east);
        if (west  != null) neighbors.add(west);
        return neighbors;
    }

    public void generate() {
        // TODO: create all the objects inside of the Room
        content.add(Item.random(Item.Type.FURNITURE));
        content.add(Lifeform.random(Lifeform.Classification.BEAST));
//        content.add(NPC.random("IGNEOS"));
    }

    @Override
    public int compareTo(Room o) {
        return this.name.compareTo(o.name);
    }

}

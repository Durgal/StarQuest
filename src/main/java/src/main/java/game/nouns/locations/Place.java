package src.main.java.game.nouns.locations;

import java.util.ArrayList;

import java.util.List;
import src.main.java.text_based_mechanics.parts_of_speech.Noun;

import src.main.java.utilities.Math;

public abstract class Place extends Noun {

    protected String name;

    private final List<Place> connections;
    
    public Place() {
        connections = new ArrayList<>();
    }

    // UNIVERSE
    //   GALAXY
    //      SOLAR SYSTEM
    //         PLANET
    //            CONTINENT
    //               LOCATION
    //                  ROOM


    public void connect(Place other) {
        if (!connections.contains(other)) {
            connections.add(other);
        }
    }

    public List<Place> getConnections() {
        return connections;
    }
    
    // return a random Place from a list
    public static Place choose(List<? extends Place> places) {
        return places.get(Math.getRandom().nextInt(places.size()));
    }
    
    @Override
    public List<String> getSuggestedVerbs() {
        return List.of("LOOK", "ENTER", "EXIT", "OPEN", "CLOSE");
    }

}

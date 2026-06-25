package src.main.java.game.nouns.locations;

import java.util.ArrayList;
import java.util.List;

public class Galaxy extends Place {

    List<SolarSystem> systems = new ArrayList<>();

    public Galaxy() {
        // TODO: !
    }

    // create the planets in the solar system
    public List<SolarSystem> makeSolarSystems(Integer n) {
        for (int i = 0; i < n; i++) {
            systems.add(new SolarSystem());
        }
        return systems;
    }

    public List<SolarSystem> getSolarSystems() {
        return systems;
    }

}

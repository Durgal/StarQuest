package src.main.java.game;

import src.main.java.game.nouns.lifeforms.Player;
import src.main.java.game.nouns.locations.*;
import src.main.java.generators.LocationGenerator;
import src.main.java.utilities.Math;

// dynamically generated universe containing a graph of Locations
public class Universe {

    SolarSystem system;
    
    Player player;

    public Universe() {
//        system = LocationGenerator.generate(new SolarSystem());
//        system.setUniverse(this);
    }

    public Universe(Player player) {
        system = LocationGenerator.generate(new SolarSystem());
        this.player = player;
        system.setUniverse(this);
    }
    
    public Planet getRandomPlanet() {
        Planet planet = Math.random(system.getPlanets());
        return planet;
    }

    public Continent getRandomContinent() {
        Planet planet = Math.random(system.getPlanets());
        Continent continent = Math.random(planet.getContinents());
        return continent;
    }

    public Location getRandomLocation() {
        Planet planet = Math.random(system.getPlanets());
        Continent continent = Math.random(planet.getContinents());
        Location location = Math.random(continent.getLocations());
        return location;
    }

    public Room getRandomRoom() {
        Planet planet = Math.random(system.getPlanets());
        Continent continent = Math.random(planet.getContinents());
        Location location = Math.random(continent.getLocations());
        Room room = Math.random(location.getRooms());
        return room;
    }

    public void setPlayerLocation() {
        player.setLocation(getRandomRoom());
    }
    
    public void setPlayerLocation(Place place) {
        player.setLocation(place);
    }

    public Room getPlayerLocation() {
        return player.getRoom();
    }

    public Player getPlayer() {
        return player;
    }

}

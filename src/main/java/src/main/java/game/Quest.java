package src.main.java.game;

import src.main.java.game.data.Data;
import src.main.java.game.mechanics.Processor;
import src.main.java.game.nouns.lifeforms.Monster;
import src.main.java.game.nouns.lifeforms.Player;
import src.main.java.game.nouns.locations.Location;
import src.main.java.game.nouns.locations.Planet;
import src.main.java.game.nouns.lifeforms.Species;
import src.main.java.game.nouns.things.Thing;
import src.main.java.generators.AsciiGenerator;
import src.main.java.text_based_mechanics.parser.Command;
import src.main.java.text_based_mechanics.parser.Parser;

import java.util.Map;

import src.main.java.text_based_mechanics.parts_of_speech.Word;
import src.main.java.utilities.Math;

public class Quest {

    static Universe universe;
    static Player player;
    
    public static void create_universe() {
        
        // load global constants
        Data.load();
        
        // create the player
        player = new Player(Data.species.get(Species.Type.HUMAN));
        
        // create universe
        universe = new Universe(player);
        universe.setPlayerLocation(); // randomize player location
        
        System.out.println(AsciiGenerator.generateAsciiPlanetTexture(80,40,5));
    }

    public static String start(String input) {
        
        Boolean loop = true;
        
        Output.start();

        // DEBUG //
        Map<String, Material> mat = Data.materials;
        Map<Species.Type, Species> r = Data.species;
        Map<String, Planet> p = Data.planets;
        Map<String, Thing> o = Data.objects;
        Map<String, Word> words = Data.dictionary;
        Map<String, Monster> mon = Data.monsters;
        // DEBUG //

        int a = Math.randomRange(1,5);
        int b = Math.randomRange(1,5);
        int c = Math.randomRange(1,5);
        int d = Math.randomRange(1,5);

        // TODO: compare dictionary vs DICTIONARY.txt and print out which words are missing (~6000) add back in common words (BESIDE, ONTO, etc)

        // main game loop
        if (loop) {

            // describe current location / events
            //Narrarator.story(player); // TODO

            // parse user input
            Command command = Parser.parse(input);

            // process the commands
            new Processor(player).process(command); // TODO: pass back room changes to the narrarator here

            // process other entity commands
            
            return Output.stop();
        }
        
        return Output.stop();
    }
    
    // print data to information panel
    public static String information() {
        Output.start();
        System.out.println(Location.printLocationDetails(player));
        return Output.stop();
    }
    
    // print data to visuals panel
    public static String visual() {
        Output.start();
        Location.printAsciiMap(player);
        return Output.stop();
    }

    public static void restart_game() {
        Quest.create_universe();
    }

    public static String prologue() {
        Output.start();
        Narrarator.story(player);
        return Output.stop();
    }

    public static Universe getUniverse() {
        return universe;
    }
    
    public static void setUniverse(Universe load) {
        universe = load;
        player = universe.getPlayer();
        player.setLocation(universe.getPlayerLocation());
    }
    
    public static Player getPlayer() {
        return player;
    }

}

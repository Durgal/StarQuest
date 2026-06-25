package src.main.java.game.mechanics;


import src.main.java.game.nouns.lifeforms.Player;
import src.main.java.text_based_mechanics.parser.Command;
import src.main.java.text_based_mechanics.parts_of_speech.Noun;
import src.main.java.game.nouns.locations.*;


import src.main.java.game.mechanics.Action;



// this class takes in a Command instance and attempts to execute, based on
// material properties, size, preposition used and the function of whatever noun is passed in
public class Processor {

    Player player;
    Place room;
    Boolean loop = true;

    public Processor(Player player) {
        this.player = player;
        this.room = player.getLocation();
    }

    // process the command(s)
    public Boolean process(Command command) {
        if (command == null) {
            return false;
        }
        
        print(command);

        // determine whether to continue game loop
        for (String word : command.getCommands()) {
            loop = word.contains("EXIT") ? false : true; // TODO: figure out a better way to end the game (probably using GUI, since players will need to EXIT buildings etc)
        }

        // execute commands
        execute(command);

        return loop; // TODO: return changes in room
    }

    private void execute(Command command) {

        Class<? extends Place> type = player.getLocationType();

        switch(type.getSimpleName().toUpperCase()) {
            case "ROOM":
                // check if the noun(s) in the command exist in the current Room
                for (Noun noun : command.getNouns()) {
                    Room room = (Room) player.getLocation();
                    if (!room.hasNoun(noun)) {
                        System.out.println("THE ROOM HAS NO "+noun.toString()+"!");
                        System.out.println("TRY USING THE \"LOOK\" ACTION.");
                        return;
                    }
                }
                
                new Action(player).execute(command);
                
                break;
            case "LOCATION":
                // THIS PLACE DESCRIBES THE CURRENT LOCATION AND WHAT OTHER PLACES CAN BE REACHED FROM HERE!
                // TODO: POSSIBLE VERBS...
                // ENTER >>> GO TO ENTRANCE!
                // GO/TRAVEL >>> GO TO ANOTHER LOCATION!
                // EXIT >>> GO BACK TO SOLAR SYSTEM!
                new Action(player).execute(command);
                break;
            case "CONTINENT":
                // container for LOCATIONS > ROOMS
                break;
            case "PLANET":
                // container for CONTINENTS > LOCATIONS > ROOMS
                break;
            case "SOLARSYSTEM":
                // THIS PLACE DESCRIBES THE CURRENT SOLAR SYSTEM AND WHAT OTHER PLANETS CAN BE REACHED FROM HERE!
                // TODO: POSSIBLE VERBS...
                // ENTER >>> GO TO PLANET! (RANDOM CONTINENT)
                // GO/TRAVEL >>> GO TO ANOTHER PLANET OR SOLAR SYSTEM!
                // EXIT >>> GO TO A DIFFERENT SOLAR SYSTEM?
                break;
            default:
                break;
        }
    }
    
    // print out the command(s) TODO: DEBUG (echo the command)
    private void print(Command command) {
        System.out.print("COMMAND: ");
        for (String word : command.getCommands()) {
            System.out.print(word+" ");
        }
        System.out.print("\n");
    }

}

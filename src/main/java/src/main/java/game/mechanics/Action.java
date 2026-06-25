/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package src.main.java.game.mechanics;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import src.main.java.game.nouns.lifeforms.Lifeform;
import src.main.java.game.nouns.locations.Place;
import src.main.java.game.nouns.locations.Room;
import src.main.java.text_based_mechanics.parser.Command;
import src.main.java.text_based_mechanics.parts_of_speech.Noun;
import src.main.java.text_based_mechanics.parts_of_speech.Verb;
import src.main.java.text_based_mechanics.parts_of_speech.Verb.Category;

/**
 *
 * @author Christopher
 */
public class Action {
    
    Lifeform lifeform;
    Verb.Category verb = Verb.Category.NOTHING;
    
    
    public Action() {
        //
    }
    
    public Action(Lifeform lifeform) {
        this.lifeform = lifeform;
    }
    
    @Override
    public String toString() {
        return verb.name();
    }

    private Room doUse(Lifeform lifeform, Command command) {
        // Example: USE + NOUN [+ PREP + NOUN]
        Room room = lifeform.getRoom();
        verb = Verb.getCategory(command.getVerb());

        // Validate the action before performing it
        if (!isValidAction(Category.valueOf(command.getVerb().toString()), commandToPattern(command))) {
            System.out.println("WHAT WOULD YOU LIKE TO USE?");
            return room;
        }

        // Command is valid, resolve the noun(s)
        List<Noun> objects = command.getNouns();
        Noun object = objects.get(0);
        if (!lifeform.hasItem(object)) {
            System.out.println("YOU DO NOT HAVE A " + object.toString() + ".");
            return room;
        }

        // Provide context-sensitive help / suggestions
        System.out.println("TO USE THE " + object.toString() + ", TRY: " + object.getSuggestedVerbs());

        return room;
    }

    private Place doLook(Lifeform lifeform, Command command) {
        // Example: LOOK + NOUN [+ PREP + NOUN]
        Room room = lifeform.getRoom();
        verb = Verb.getCategory(command.getVerb());

        if (!isValidAction(Category.LOOK, commandToPattern(command))) {
            System.out.println("WHAT WOULD YOU LIKE TO LOOK AT?");
            return room;
        }

        Noun object = null;
                
        // TODO: move these into a block(s) where we KNOW there will be a NOUN!
        if (!command.getNouns().isEmpty()) {
            List<Noun> objects = command.getNouns();
            object = objects.get(0);
            object = lifeform.getRoom().getThing(object); // convert to real object from room
        }
        
        switch (commandToPattern(command)) {
            case VERB -> System.out.println("WHAT WOULD YOU LIKE TO LOOK AT?");
            case VERB_NOUN -> System.out.println("YOU LOOK AT THE " + object.toString() + ". (TODO DETAILS)");
            case VERB_PREP_NOUN -> System.out.println("YOU LOOK " + command.getPreposition().toString() + " THE " + object.toString() + ". IT IS " + object.getSize().name() + " AND MADE OF " + object.getMaterial().toString() + "." );
        }
        
        return lifeform.getRoom();
    }

    private Place doListen(Lifeform lifeform, Command command) {
        // Example: LISTEN + NOUN [+ PREP + NOUN]
        Room room = lifeform.getRoom();
        verb = Verb.getCategory(command.getVerb());

        if (!isValidAction(Category.LISTEN, commandToPattern(command))) {
            System.out.println("WHAT WOULD YOU LIKE TO LISTEN TO?");
            return room;
        }

        List<Noun> objects = command.getNouns();
        Noun object = objects.get(0);
        
        switch (commandToPattern(command)) {
            case VERB -> System.out.println("YOU LISTEN CAREFULLY.");
            case VERB_NOUN -> System.out.println("YOU LISTEN TO THE " + object.toString() + ".");
            case VERB_PREP_NOUN -> System.out.println("YOU LISTEN " + command.getPreposition().toString() + " THE " + object.toString() + ".");
        }
        
        return lifeform.getRoom();
    }

    private Place doSmell(Lifeform lifeform, Command command) {
        // Example: SMELL [+ NOUN + PREP + NOUN]
        Room room = lifeform.getRoom();
        verb = Verb.getCategory(command.getVerb());

        if (!isValidAction(Category.SMELL, commandToPattern(command))) {
            System.out.println("WHAT WOULD YOU LIKE TO SMELL?");
            return room;
        }
        
        List<Noun> objects = command.getNouns();
        Noun object = objects.get(0);

        switch (commandToPattern(command)) {
            case VERB -> System.out.println("YOU TAKE A DEEP BREATH.");
            case VERB_NOUN -> System.out.println("YOU SMELL THE " + object.toString() + ".");
            case VERB_PREP_NOUN -> System.out.println("YOU SMELL " + command.getPreposition().toString() + " THE " + object.toString() + ".");
        }
        
        return lifeform.getRoom();
    }

    private Place doTake(Lifeform lifeform, Command command) {
        // Example: TAKE + NOUN [+ PREP + NOUN]
        Room room = lifeform.getRoom();
        verb = Verb.getCategory(command.getVerb());

        if (!isValidAction(Category.TAKE, commandToPattern(command))) {
            System.out.println("WHAT WOULD YOU LIKE TO TAKE?");
            return room;
        }

        List<Noun> objects = command.getNouns();
        Noun object = objects.get(0);
        if (object.getSize().ordinal() < lifeform.getSize().ordinal()) {
            System.out.println("YOU CAN'T TAKE THE " + object.toString() + ".");
            return room;
        }

        lifeform.addToInventory(object);
        room.removeObject(object);
        System.out.println("YOU TAKE THE " + object.toString() + ".");

        return room;
    }

    private Place doGive(Lifeform lifeform, Command command) {
        // Example: GIVE + NOUN [+ PREP + NOUN]
        return lifeform.getLocation(); // placeholder
    }

    private Place doEquip(Lifeform lifeform, Command command) {
        // EQUIP + NOUN
        return lifeform.getLocation(); // placeholder
    }

    private Place doRemove(Lifeform lifeform, Command command) {
        // REMOVE + NOUN
        return lifeform.getLocation(); // placeholder
    }

    private Place doDrop(Lifeform lifeform, Command command) {
        // DROP + NOUN
        return lifeform.getLocation(); // placeholder
    }

    private Place doOpen(Lifeform lifeform, Command command) {
        // OPEN + NOUN
        return lifeform.getLocation(); // placeholder
    }

    private Place doClose(Lifeform lifeform, Command command) {
        // CLOSE + NOUN
        return lifeform.getLocation(); // placeholder
    }

    private Place doExtinguish(Lifeform lifeform, Command command) {
        // EXTINGUISH + NOUN
        return lifeform.getLocation(); // placeholder
    }

    private Place doPut(Lifeform lifeform, Command command) {
        // PUT + NOUN + PREP + NOUN
        return lifeform.getLocation(); // placeholder
    }

    private Place doPush(Lifeform lifeform, Command command) {
        // PUSH + NOUN
        return lifeform.getLocation(); // placeholder
    }

    private Place doPull(Lifeform lifeform, Command command) {
        // PULL + NOUN
        return lifeform.getLocation(); // placeholder
    }

    private Place doTurn(Lifeform lifeform, Command command) {
        // TURN + NOUN [+ PREP + NOUN]
        return lifeform.getLocation(); // placeholder
    }

    private Place doTouch(Lifeform lifeform, Command command) {
        // TOUCH + NOUN
        return lifeform.getLocation(); // placeholder
    }

    private Place doWave(Lifeform lifeform, Command command) {
        // WAVE [+ NOUN or PREP + NOUN]
        return lifeform.getLocation(); // placeholder
    }

    private Place doDance(Lifeform lifeform, Command command) {
        // DANCE
        return lifeform.getLocation(); // placeholder
    }

    private Place doFlirt(Lifeform lifeform, Command command) {
        // FLIRT [+ PREP + NOUN]
        return lifeform.getLocation(); // placeholder
    }

    private Place doKiss(Lifeform lifeform, Command command) {
        // KISS [+ NOUN]
        return lifeform.getLocation(); // placeholder
    }

    private Place doPoint(Lifeform lifeform, Command command) {
        // POINT [+ PREP + NOUN or NOUN + PREP + NOUN]
        return lifeform.getLocation(); // placeholder
    }

    private Place doSpeak(Lifeform lifeform, Command command) {
        // SPEAK [+ NOUN or PREP + NOUN]
        return lifeform.getLocation(); // placeholder
    }

    private Place doYell(Lifeform lifeform, Command command) {
        // YELL [+ NOUN or PREP + NOUN]
        return lifeform.getLocation(); // placeholder
    }

    private Place doWhisper(Lifeform lifeform, Command command) {
        // WHISPER [+ NOUN or PREP + NOUN]
        return lifeform.getLocation(); // placeholder
    }

    private Place doLaugh(Lifeform lifeform, Command command) {
        // LAUGH [+ NOUN or PREP + NOUN]
        return lifeform.getLocation(); // placeholder
    }

    private Place doSing(Lifeform lifeform, Command command) {
        // SING [+ NOUN or PREP + NOUN]
        return lifeform.getLocation(); // placeholder
    }

    private Place doAttack(Lifeform lifeform, Command command) {
        // ATTACK + NOUN [+ PREP + NOUN]
        return lifeform.getLocation(); // placeholder
    }

    private Place doThrow(Lifeform lifeform, Command command) {
        // THROW + NOUN [+ PREP + NOUN]
        return lifeform.getLocation(); // placeholder
    }

    private Place doPunch(Lifeform lifeform, Command command) {
        // PUNCH + NOUN [+ PREP + NOUN]
        return lifeform.getLocation(); // placeholder
    }

    private Place doCast(Lifeform lifeform, Command command) {
        // CAST + NOUN [+ PREP + NOUN]
        return lifeform.getLocation(); // placeholder
    }

    private Place doBreak(Lifeform lifeform, Command command) {
        // BREAK + NOUN
        return lifeform.getLocation(); // placeholder
    }

    private Place doJump(Lifeform lifeform, Command command) {
        // JUMP [+ PREP + NOUN]
        return lifeform.getLocation(); // placeholder
    }

    private Place doClimb(Lifeform lifeform, Command command) {
        // CLIMB + NOUN [+ PREP + NOUN]
        return lifeform.getLocation(); // placeholder
    }

    private Place doConceal(Lifeform lifeform, Command command) {
        // CONCEAL [+ NOUN]
        return lifeform.getLocation(); // placeholder
    }

    private Place doFollow(Lifeform lifeform, Command command) {
        // FOLLOW + NOUN
        return lifeform.getLocation(); // placeholder
    }

    private Place doPray(Lifeform lifeform, Command command) {
        // PRAY
        return lifeform.getLocation(); // placeholder
    }

    private Place doEat(Lifeform lifeform, Command command) {
        // EAT + NOUN
        return lifeform.getLocation(); // placeholder
    }

    private Place doDrink(Lifeform lifeform, Command command) {
        // DRINK + NOUN
        return lifeform.getLocation(); // placeholder
    }

    private Place doSleep(Lifeform lifeform, Command command) {
        // SLEEP
        return lifeform.getLocation(); // placeholder
    }

    private Place doWhere(Lifeform lifeform, Command command) {
        // WHERE [+ NOUN]
        return lifeform.getLocation(); // placeholder
    }

    private Place doEnter(Lifeform lifeform, Command command) {
        // ENTER [+ NOUN]
        return lifeform.getLocation(); // placeholder
    }

    private Place doExit(Lifeform lifeform, Command command) {
        // EXIT [+ NOUN]
        return lifeform.getLocation(); // placeholder
    }
    

    // Define the four supported command shapes
    enum Pattern {
        VERB,
        VERB_NOUN,
        VERB_PREP_NOUN,
        VERB_NOUN_PREP_NOUN
    }

    // Map each Category (or Verb) to its valid action patterns
    private static final Map<Verb.Category, Set<Pattern>> validActionPattern = new HashMap<>();

    static {
        validActionPattern.put(Verb.Category.USE, Set.of(
            Pattern.VERB_NOUN,
            Pattern.VERB_PREP_NOUN,
            Pattern.VERB_NOUN_PREP_NOUN
        ));

        validActionPattern.put(Verb.Category.LOOK, Set.of(
            Pattern.VERB,
            Pattern.VERB_NOUN,
            Pattern.VERB_PREP_NOUN,
            Pattern.VERB_NOUN_PREP_NOUN
        ));

        validActionPattern.put(Verb.Category.LISTEN, Set.of(
            Pattern.VERB,
            Pattern.VERB_NOUN,
            Pattern.VERB_PREP_NOUN
        ));

        validActionPattern.put(Verb.Category.SMELL, Set.of(
            Pattern.VERB,
            Pattern.VERB_NOUN,
            Pattern.VERB_PREP_NOUN
        ));

        validActionPattern.put(Verb.Category.TAKE, Set.of(
            Pattern.VERB_NOUN,
            Pattern.VERB_NOUN_PREP_NOUN
        ));

        validActionPattern.put(Verb.Category.GIVE, Set.of(
            Pattern.VERB_NOUN,
            Pattern.VERB_NOUN_PREP_NOUN
        ));

        validActionPattern.put(Verb.Category.EQUIP, Set.of(
            Pattern.VERB_NOUN
        ));

        validActionPattern.put(Verb.Category.REMOVE, Set.of(
            Pattern.VERB_NOUN
        ));

        validActionPattern.put(Verb.Category.DROP, Set.of(
            Pattern.VERB_NOUN
        ));

        validActionPattern.put(Verb.Category.OPEN, Set.of(
            Pattern.VERB_NOUN
        ));

        validActionPattern.put(Verb.Category.CLOSE, Set.of(
            Pattern.VERB_NOUN
        ));

        validActionPattern.put(Verb.Category.EXTINGUISH, Set.of(
            Pattern.VERB_NOUN
        ));

        validActionPattern.put(Verb.Category.PUT, Set.of(
            Pattern.VERB_NOUN_PREP_NOUN
        ));

        validActionPattern.put(Verb.Category.PUSH, Set.of(
            Pattern.VERB_NOUN
        ));

        validActionPattern.put(Verb.Category.PULL, Set.of(
            Pattern.VERB_NOUN
        ));

        validActionPattern.put(Verb.Category.TURN, Set.of(
            Pattern.VERB,
            Pattern.VERB_NOUN,
            Pattern.VERB_NOUN_PREP_NOUN
        ));

        validActionPattern.put(Verb.Category.TOUCH, Set.of(
            Pattern.VERB_NOUN
        ));

        validActionPattern.put(Verb.Category.WAVE, Set.of(
            Pattern.VERB,
            Pattern.VERB_NOUN,
            Pattern.VERB_PREP_NOUN
        ));

        validActionPattern.put(Verb.Category.DANCE, Set.of(
            Pattern.VERB
        ));

        validActionPattern.put(Verb.Category.FLIRT, Set.of(
            Pattern.VERB,
            Pattern.VERB_PREP_NOUN
        ));

        validActionPattern.put(Verb.Category.KISS, Set.of(
            Pattern.VERB,
            Pattern.VERB_NOUN
        ));

        validActionPattern.put(Verb.Category.POINT, Set.of(
            Pattern.VERB,
            Pattern.VERB_PREP_NOUN,
            Pattern.VERB_NOUN_PREP_NOUN
        ));

        validActionPattern.put(Verb.Category.SPEAK, Set.of(
            Pattern.VERB,
            Pattern.VERB_NOUN,
            Pattern.VERB_PREP_NOUN
        ));

        validActionPattern.put(Verb.Category.YELL, Set.of(
            Pattern.VERB,
            Pattern.VERB_NOUN,
            Pattern.VERB_PREP_NOUN
        ));

        validActionPattern.put(Verb.Category.WHISPER, Set.of(
            Pattern.VERB,
            Pattern.VERB_NOUN,
            Pattern.VERB_PREP_NOUN
        ));

        validActionPattern.put(Verb.Category.LAUGH, Set.of(
            Pattern.VERB,
            Pattern.VERB_NOUN,
            Pattern.VERB_PREP_NOUN
        ));

        validActionPattern.put(Verb.Category.SING, Set.of(
            Pattern.VERB,
            Pattern.VERB_NOUN,
            Pattern.VERB_PREP_NOUN
        ));

        validActionPattern.put(Verb.Category.ATTACK, Set.of(
            Pattern.VERB_NOUN,
            Pattern.VERB_NOUN_PREP_NOUN
        ));

        validActionPattern.put(Verb.Category.THROW, Set.of(
            Pattern.VERB_NOUN,
            Pattern.VERB_NOUN_PREP_NOUN
        ));

        validActionPattern.put(Verb.Category.PUNCH, Set.of(
            Pattern.VERB_NOUN,
            Pattern.VERB_NOUN_PREP_NOUN
        ));

        validActionPattern.put(Verb.Category.CAST, Set.of(
            Pattern.VERB_NOUN,
            Pattern.VERB_NOUN_PREP_NOUN
        ));

        validActionPattern.put(Verb.Category.BREAK, Set.of(
            Pattern.VERB_NOUN
        ));

        validActionPattern.put(Verb.Category.JUMP, Set.of(
            Pattern.VERB,
            Pattern.VERB_PREP_NOUN
        ));

        validActionPattern.put(Verb.Category.CLIMB, Set.of(
            Pattern.VERB_NOUN,
            Pattern.VERB_PREP_NOUN
        ));

        validActionPattern.put(Verb.Category.CONCEAL, Set.of(
            Pattern.VERB,
            Pattern.VERB_NOUN
        ));

        validActionPattern.put(Verb.Category.FOLLOW, Set.of(
            Pattern.VERB_NOUN
        ));

        validActionPattern.put(Verb.Category.PRAY, Set.of(
            Pattern.VERB
        ));

        validActionPattern.put(Verb.Category.EAT, Set.of(
            Pattern.VERB_NOUN
        ));

        validActionPattern.put(Verb.Category.DRINK, Set.of(
            Pattern.VERB_NOUN
        ));

        validActionPattern.put(Verb.Category.SLEEP, Set.of(
            Pattern.VERB
        ));

        validActionPattern.put(Verb.Category.WHERE, Set.of(
            Pattern.VERB,
            Pattern.VERB_NOUN
        ));
        
        validActionPattern.put(Verb.Category.ENTER, Set.of(
            Pattern.VERB,
            Pattern.VERB_NOUN
        ));

        validActionPattern.put(Verb.Category.EXIT, Set.of(
            Pattern.VERB,
            Pattern.VERB_NOUN
        ));
    }

    // execute the command
    public Place execute(Command command) {
        Category verb = Verb.Category.valueOf(command.getVerb().toString());
        
            return switch (verb) {
                case USE -> doUse(lifeform, (command));
                case LOOK -> doLook(lifeform, (command));
                case LISTEN -> doListen(lifeform, (command));
                case SMELL -> doSmell(lifeform, (command));
                case TAKE -> doTake(lifeform, (command));
                case GIVE -> doGive(lifeform, (command));
                case EQUIP -> doEquip(lifeform, (command));
                case REMOVE -> doRemove(lifeform, (command));
                case DROP -> doDrop(lifeform, (command));
                case OPEN -> doOpen(lifeform, (command));
                case CLOSE -> doClose(lifeform, (command));
                case EXTINGUISH -> doExtinguish(lifeform, (command));
                case PUT -> doPut(lifeform, (command));
                case PUSH -> doPush(lifeform, (command));
                case PULL -> doPull(lifeform, (command));
                case TURN -> doTurn(lifeform, (command));
                case TOUCH -> doTouch(lifeform, (command));
                case WAVE -> doWave(lifeform, (command));
                case DANCE -> doDance(lifeform, (command));
                case FLIRT -> doFlirt(lifeform, (command));
                case KISS -> doKiss(lifeform, (command));
                case POINT -> doPoint(lifeform, (command));
                case SPEAK -> doSpeak(lifeform, (command));
                case YELL -> doYell(lifeform, (command));
                case WHISPER -> doWhisper(lifeform, (command));
                case LAUGH -> doLaugh(lifeform, (command));
                case SING -> doSing(lifeform, (command));
                case ATTACK -> doAttack(lifeform, (command));
                case THROW -> doThrow(lifeform, (command));
                case PUNCH -> doPunch(lifeform, (command));
                case CAST -> doCast(lifeform, (command));
                case BREAK -> doBreak(lifeform, (command));
                case JUMP -> doJump(lifeform, (command));
                case CLIMB -> doClimb(lifeform, (command));
                case CONCEAL -> doConceal(lifeform, (command));
                case FOLLOW -> doFollow(lifeform, (command));
                case PRAY -> doPray(lifeform, (command));
                case EAT -> doEat(lifeform, (command));
                case DRINK -> doDrink(lifeform, (command));
                case SLEEP -> doSleep(lifeform, (command));
                case WHERE -> doSleep(lifeform, (command));
                case ENTER -> doEnter(lifeform, (command));
                case EXIT -> doExit(lifeform, (command));
                default -> lifeform.getLocation(); // no action
            };
    }
    
    // check if the pattern is valid
    private static boolean isValidAction(Verb.Category category, Pattern pattern) {
        Set<Pattern> valid = validActionPattern.get(category);
        return valid != null && valid.contains(pattern);
    }
    
    // get pattern from command
    private static Pattern commandToPattern(Command command) {
        if (command == null || command.getVerb() == null) {
            throw new IllegalArgumentException("Command or verb cannot be null");
        }

        int nounCount = (command.getNouns() == null) ? 0 : command.getNouns().size();
        boolean hasPreposition = (command.getPreposition() != null);

        // VERB
        if (nounCount == 0 && !hasPreposition) {
            return Pattern.VERB;
        }

        // VERB + NOUN
        if (nounCount == 1 && !hasPreposition) {
            return Pattern.VERB_NOUN;
        }

        // VERB + PREPOSITION + NOUN
        if (nounCount == 1 && hasPreposition) {
            return Pattern.VERB_PREP_NOUN;
        }

        // VERB + NOUN + PREPOSITION + NOUN
        if (nounCount == 2 && hasPreposition) {
            return Pattern.VERB_NOUN_PREP_NOUN;
        }

        // If it doesn’t match any known form
        throw new IllegalArgumentException("Unrecognized command pattern: " + nounCount + " nouns, preposition=" + hasPreposition);
    }
}

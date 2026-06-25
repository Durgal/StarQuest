/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package src.main.java.game.nouns.lifeforms.mechanics;

import java.util.Arrays;

/**
 *
 * @author Christopher
 */
public class Sciences {
    
    public enum Science {
        NONE(""),
        KINETICS("HARNESS THE MOTION OF FROCE TO STRIKE, PUSH, AND SHATTER."),
        CYBERNETICS("INFUSE MACHINERY AND FLESH WITH CONTROLLED POWER AND PRECISION."),
        ESPIONAGE("MOVE UNSEEN, GATHER SECRETS, AND STRIKE WITH CALCULATED FINESSE."),
        ENGINEERING("CRAFT TOOLS AND DEVICES TO MANIPULATE THE WORLD."),
        CHEMISTRY("TRANSMUTE SUBSTANCES TO HEAL, HARM, OR ALTER THE FLOW OF LIFE."),
        BIOLOGY("COMMAND THE FORCES OF NATURE, SHAPING LIFE AND ENVIRONMENTS TO YOUR WILL."),
        OPTICS("MANIPULATE LIGHT AND PERCEPTION TO SHAPE REALITY AND UNDERSTANDING."),
        QUANTUM("HARNESS THE UNSEEN THREADS OF THE COSMOS TO BEND POSSIBILITY."),
        ANATOMY("UNDERSTAND AND MANIPULATE THE BODY, USING KNOWLEDGE OF FLESH AND BONE TO HEAL"),
        PSYCHOLOGY("GUIDE MINDS, INSPIRE ALLIES, AND UNRAVEL THE MOTIVES OF OTHERS.");

        private final String description;

        Science(String description) {
            this.description = description;
        }

        public String getDescription() {
            return description;
        }

        public String getName() {
            return this.name().replace("_", " ");
        }
        
        // Static helper method to get all names as String[]
        public static String[] names() {
            return Arrays.stream(Science.values())
                         .map(Science::getName)
                         .toArray(String[]::new);
        }
    }

}

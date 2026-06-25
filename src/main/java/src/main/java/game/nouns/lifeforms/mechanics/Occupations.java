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
public class Occupations {
    
    public enum Occupation {
        NONE(""),
        REAVER("FERAL WARRIORS WHO EMBRACE CLOSE COMBAT, CHANNELING RAGE AND INSTINCT INTO UNRELENTING STRENGTH."),
        DRIFTER("SILENT MARKSMEN AND MERCENARIES WHO TRACK, CAPTURE, OR ELIMINATE TARGETS WITH PRECISION."),
        ALCHEMIST("TRANSMUTERS OF LIFE AND MATTER WHO HARNESS AETHERIC ENERGY TO REWRITE THE LAWS OF NATURE."),
        ARCHITECT("MASTERS OF TECHNOLOGICAL CREATION WHO SCULPT REALITY THROUGH QUANTUM DESIGN AND CONTROL."),
        SENTINEL("SHIELD-BEARERS AND HEALERS DEVOTED TO ORDER, PROTECTION, AND THE PRESERVATION OF LIFE.");

        private final String description;

        Occupation(String description) {
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
            return Arrays.stream(Occupation.values())
                         .map(Occupation::getName)
                         .toArray(String[]::new);
        }
    }

}

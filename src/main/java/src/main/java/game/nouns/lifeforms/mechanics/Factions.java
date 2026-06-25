/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package src.main.java.game.nouns.lifeforms.mechanics;

import java.util.Arrays;
import src.main.java.utilities.Enum;
import src.main.java.game.nouns.lifeforms.Species;
import static src.main.java.game.nouns.lifeforms.mechanics.Factions.Faction.*;
import src.main.java.game.nouns.lifeforms.mechanics.Occupations.Occupation;


public class Factions {

    public enum Faction {

        NONE("", Species.Type.NONE, Alignment.Type.NONE),
        
        // === Species-based factions ===
        FEDERATION("HUMANS UNITED BY COMMERCE, DIPLOMACY, AND EXPLORATION — AMBITIOUS, OPPORTUNISTIC, AND ADAPTIVE.", Species.Type.HUMAN, Alignment.Type.ORDER),
        CRUCIBLE("IGNEOS ARTISANS AND WARRIORS BOUND TO FORGE AND PROTECT, HONORING FIRE AS THE SOURCE OF CREATION.", Species.Type.IGNEOS, Alignment.Type.ORDER),
        ANOMALY("NUMERIANS WHO SEEK ENLIGHTENMENT THROUGH ENTROPY, EMBRACING CHAOS AS THE PUREST FORM OF ORDER.", Species.Type.NUMERIAN, Alignment.Type.CHAOS),
        CONTINUUM("ANCIENT PROTOZOANS WHO OBSERVE AND SHAPE SPACETIME, GUIDING EXISTENCE THROUGH SILENT EQUILIBRIUM.", Species.Type.PROTOZOAN, Alignment.Type.HARMONY),
        ROOTGUARD("GUARDIANS DEDICATED TO PRESERVING ECOSYSTEMS AND MAINTAINING THE NATURAL BALANCE OF THEIR WORLDS.", Species.Type.GUARDIAN, Alignment.Type.HARMONY),
        EYE("ARKON CULTISTS AND CONSPIRATORS WHO MANIPULATE OTHERS TO SPREAD THEIR INFLUENCE THROUGH SHADOW AND DECEIT.", Species.Type.ARKON, Alignment.Type.CHAOS),
        TRIBUNAL("OKAMI CLANS UNITED BY SURVIVAL, RITUAL, AND LOYALTY TO GROZOK; BOUND BY AN ANCIENT TRIBAL HIERARCHY.", Species.Type.OKAMI, Alignment.Type.HARMONY),

        // === Class-based factions ===
        WHISPERING_HAND("DISCIPLINED BOUNTY HUNTERS AND OPERATIVES WHO ENFORCE ORDER THROUGH SILENCE AND PRECISION.", Occupation.DRIFTER, Alignment.Type.ORDER),
        GENOMIC_COVEN("ALCHEMISTS WHO TRANSMUTE LIFE ITSELF, REWRITING NATURE THROUGH UNORTHODOX AND AMBIGUOUS MEANS.", Occupation.ALCHEMIST, Alignment.Type.CHAOS),
        QUANTUM_COUNCIL("ARCHITECTS OF QUANTUM ENERGY WHO SEEK BALANCE AND HARMONY, CHANNELING CHAOS INTO ORDER AND UNDERSTANDING.", Occupation.ARCHITECT, Alignment.Type.HARMONY),
        ORDER_OF_AEGIS("SENTINELS DEVOTED TO DISCIPLINE, HONOR, AND THE PRESERVATION OF LIFE.", Occupation.SENTINEL, Alignment.Type.ORDER),
        RED_BROTHERHOOD("PRIMAL WARRIORS OF THE REAVER CLANS, BOUND BY BLOOD OATHS AND DRIVEN BY THE CHAOS OF WAR.", Occupation.REAVER, Alignment.Type.CHAOS);


        private final String description;
        private final String requirement;
        private final String alignment;

        Faction(String description, Occupation occupation, Alignment.Type alignment) {
            this.description = description;
            this.requirement = occupation.getName();
            this.alignment = alignment.name();
        }
        
        Faction(String description, Species.Type species, Alignment.Type alignment) {
            this.description = description;
            this.requirement = species.name();
            this.alignment = alignment.name();
        }

        public String getName() {
            String faction = "";
            switch (this.name()) {
                case "FEDERATION" -> faction = "THE "+this.name();
                case "CRUCIBLE" -> faction = "THE "+this.name();
                case "ANOMALY" -> faction = "THE "+this.name();
                case "CONTINUUM" -> faction = "THE "+this.name();
                case "ROOTGUARD" -> faction = this.name();
                case "EYE" -> faction = "THE "+this.name();
                case "TRIBUNAL" -> faction = "THE "+this.name();
                case "WHISPERING_HAND" -> faction = this.name();
                case "GENOMIC_COVEN" -> faction = this.name();
                case "QUANTUM_COUNCIL" -> faction = this.name();
                case "ORDER_OF_AEGIS" -> faction = this.name();
                case "RED_BROTHERHOOD" -> faction = this.name();
            }
            return faction.replace("_"," ");
        }
        
        public static Faction getEnum(String name) {
            Faction faction = NONE;
            switch (name) {
                case "THE FEDERATION" -> faction = FEDERATION;
                case "THE CRUCIBLE" -> faction = CRUCIBLE;
                case "THE ANOMALY" -> faction = ANOMALY;
                case "THE CONTINUUM" -> faction = CONTINUUM;
                case "ROOTGUARD" -> faction = ROOTGUARD;
                case "THE EYE" -> faction = EYE;
                case "THE TRIBUNAL" -> faction = TRIBUNAL;
                case "WHISPERING HAND" -> faction = WHISPERING_HAND;
                case "GENOMIC COVEN" -> faction = GENOMIC_COVEN;
                case "QUANTUM COUNCIL" -> faction = QUANTUM_COUNCIL;
                case "ORDER OF AEGIS" -> faction = ORDER_OF_AEGIS;
                case "RED BROTHERHOOD" -> faction = RED_BROTHERHOOD;
            }
            return faction;
        }
        
        public String getDescription() {
            return description;
        }

        public String getRequirement() {
            return requirement;
        }
        
        public String getAlignment() {
            return alignment;
        }

        public static Faction random() {
            return Enum.choose(FEDERATION, CRUCIBLE, ANOMALY, CONTINUUM, ROOTGUARD, EYE, TRIBUNAL, WHISPERING_HAND, GENOMIC_COVEN, QUANTUM_COUNCIL, ORDER_OF_AEGIS, RED_BROTHERHOOD);
        }

        // Static helper method to get all names as String[]
        public static String[] names() {
            return Arrays.stream(Faction.values())
                         .map(Faction::getName)
                         .toArray(String[]::new);
        }
        
        // Static help method to get filtered set of Factions as a String[]
        public static String[] names(Species.Type species, Occupation occupation) {
            return Arrays.stream(Faction.values())
                         .filter(f -> {
                             boolean isNone = f.getRequirement().contains(Species.Type.NONE.name());
                             boolean matchesSpecies = f.getRequirement().contains(species.name());
                             boolean matchesOccupation = f.getRequirement().contains(occupation.name());
                             return isNone || matchesSpecies || matchesOccupation;
                         })
                         .map(Faction::getName)
                         .toArray(String[]::new);
        }
    }
}
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package src.main.java.game.nouns.lifeforms.mechanics;
import java.util.Arrays;
import src.main.java.game.nouns.lifeforms.mechanics.Attributes.Attribute;
import src.main.java.game.nouns.lifeforms.mechanics.Skills.Skill;
import src.main.java.utilities.Enum;


public class Backgrounds {

    public enum Background {

        NONE("", Attribute.NONE, Attribute.NONE, Skill.NONE, Skill.NONE),
        VETERAN("POWERFUL AND DISCIPLINED, EXCELLING IN COMBAT AND PHYSICAL PROWESS.", Attribute.POWER, Attribute.LOGIC, Skill.MELEE, Skill.LORE),
        ZEALOT("DRIVEN BY CONVICTION, BLENDING RAW STRENGTH WITH PERSUASIVE INFLUENCE.", Attribute.POWER, Attribute.AGILITY, Skill.INFLUENCE, Skill.STEALTH),
        VANGUARD("SWIFT AND TACTICAL, EXPERT IN RANGED COMBAT AND AGILITY-BASED MANEUVERS.", Attribute.AGILITY, Attribute.VITALITY, Skill.RANGED, Skill.WILLPOWER),
        NOMAD("ADAPTIVE WANDERERS SKILLED IN STEALTH AND SURVIVAL, GUIDED BY INSTINCT.", Attribute.AGILITY, Attribute.SPIRIT, Skill.STEALTH, Skill.INFLUENCE),
        WARDEN("DEFENDERS OF ORDER, STURDY AND RESILIENT WITH EXPERTISE IN DEFENSE.", Attribute.VITALITY, Attribute.LOGIC, Skill.BLOCK, Skill.RANGED),
        SURVIVOR("RESILIENT AND RESOURCEFUL, THRIVING UNDER HARSH CONDITIONS.", Attribute.VITALITY, Attribute.SPIRIT, Skill.SURVIVAL, Skill.BLOCK),
        TECHNICIAN("ANALYTICAL MINDS PROFICIENT IN TECHNOLOGY AND PROBLEM-SOLVING.", Attribute.LOGIC, Attribute.POWER, Skill.TECHNOLOGY, Skill.METABOLISM),
        SCHOLAR("KNOWLEDGE-SEEKERS VALUING INTELLECT AND STRATEGIC THINKING.", Attribute.LOGIC, Attribute.VITALITY, Skill.LORE, Skill.SURVIVAL),
        ORACLE("SPIRITUAL GUIDES WHO CHANNEL INSIGHT AND INNER POWER.", Attribute.SPIRIT, Attribute.POWER, Skill.WILLPOWER, Skill.TECHNOLOGY),
        MEDIC("HEALERS SKILLED IN MEDICINE AND SUPPORTIVE ROLES IN COMBAT.", Attribute.SPIRIT, Attribute.AGILITY, Skill.METABOLISM, Skill.MELEE);

        private final String description;
        private final Attribute attPlus;
        private final Attribute attNeg;
        private final Skill skillPlus;
        private final Skill skillNeg;
        
        Background(String description, Attribute attPlus, Attribute attNeg, Skill skillPlus, Skill skillNeg) {
            this.description = description;
            this.attPlus = attPlus;
            this.attNeg = attNeg;
            this.skillPlus = skillPlus;
            this.skillNeg = skillNeg;
        }

        public String getName() {
            return this.name().replace("_", " ");
        }

        public String getDescription() {
            return description;
        }

        public Attribute getAttPlus() {
            return attPlus;
        }

        public Attribute getAttNeg() {
            return attNeg;
        }

        public Skill getSkillPlus() {
            return skillPlus;
        }

        public Skill getSkillNeg() {
            return skillNeg;
        }

        public static Background random() {
            return Enum.choose(VETERAN, ZEALOT, VANGUARD, NOMAD, WARDEN, SURVIVOR, TECHNICIAN, SCHOLAR, ORACLE, MEDIC);
        }
        
        // Static helper method to get all names as String[]
        public static String[] names() {
            return Arrays.stream(Background.values())
                         .map(Background::getName)
                         .toArray(String[]::new);
        }
    }
}
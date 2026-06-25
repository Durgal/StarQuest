package src.main.java.game.nouns.lifeforms.mechanics;

import src.main.java.game.nouns.lifeforms.mechanics.Attributes.Attribute;


public class Skills {

    public enum Skill {

        NONE("", Attribute.NONE),
        MELEE("COMBAT PROWESS USING CLOSE-QUARTERS WEAPONRY", Attribute.POWER),
        BLOCK("ABILITY TO DEFEND AGAINST ATTACKS AND MITIGATE DAMAGE USING ARMOR", Attribute.POWER),
        RANGED("SKILL IN LONG RANGE COMBAT WITH FIREARMS OR BOWS", Attribute.AGILITY),
        STEALTH("ABILITY TO STEAL, OR CONCEAL ONESELF FROM OTHERS", Attribute.AGILITY),
        METABOLISM("CONTROL AND RESILIENCE OF THE BODY, GOVERNING HEALING, RESISTANCE, AND TRANSFORMATION", Attribute.VITALITY),
        SURVIVAL("ABILITY TO THRIVE IN HARSH ENVIRONMENTS, FIND RESOURCES, AND ADAPT TO CHANGING CONDITIONS", Attribute.VITALITY),
        TECHNOLOGY("UNDERSTANDING AND PROFICIENCY IN TECHNOLOGICAL APPLICATIONS", Attribute.LOGIC),
        LORE("KNOWLEDGE AND WISDOM ACQUIRED THROUGH STUDY, RESEARCH, AND EXPLORATION OF HISTORY, SCIENCE, AND CULTURAL TOPICS", Attribute.LOGIC),
        WILLPOWER("INNER STRENGTH, MENTAL RESILIENCE, AND DETERMINATION TO OVERCOME CHALLENGES AND RESIST MANIPULATION OR COERCION", Attribute.SPIRIT),
        INFLUENCE("ABILITY TO PERSUADE, LEAD, NEGOTIATE, AND INTERACT WITH OTHERS EFFECTIVELY, LEVERAGING CHARISMA AND INTERPERSONAL SKILLS", Attribute.SPIRIT);

        private final String description;
        private final Attribute attribute;

        Skill(String description, Attribute attribute) {
            this.description = description;
            this.attribute = attribute;
        }

        public String getDescription() {
            return description;
        }

        private Attribute getAttribute() {
            return attribute;
        }

        public String get(Skill skill) {
            String value;
            value = switch (skill) {
                case Skill.MELEE -> MELEE.toString();
                case Skill.BLOCK -> BLOCK.toString();
                case Skill.RANGED -> RANGED.toString();
                case Skill.STEALTH -> STEALTH.toString();
                case Skill.METABOLISM -> METABOLISM.toString();
                case Skill.SURVIVAL -> SURVIVAL.toString();
                case Skill.TECHNOLOGY -> TECHNOLOGY.toString();
                case Skill.LORE -> LORE.toString();
                case Skill.WILLPOWER -> WILLPOWER.toString();
                case Skill.INFLUENCE -> INFLUENCE.toString();
                default -> "";
            };
            return value;
        }

    }

    public static Integer MELEE;
    public static Integer BLOCK;
    public static Integer RANGED;
    public static Integer STEALTH;
    public static Integer METABOLISM;
    public static Integer SURVIVAL;
    public static Integer TECHNOLOGY;
    public static Integer LORE;
    public static Integer WILLPOWER;
    public static Integer INFLUENCE;
    
    public Skills(Attributes attributes) {
        MELEE      = attributes.value(Skill.MELEE.getAttribute());
        BLOCK      = attributes.value(Skill.BLOCK.getAttribute());
        RANGED     = attributes.value(Skill.RANGED.getAttribute());
        STEALTH    = attributes.value(Skill.STEALTH.getAttribute());
        METABOLISM = attributes.value(Skill.METABOLISM.getAttribute());
        SURVIVAL   = attributes.value(Skill.SURVIVAL.getAttribute());
        TECHNOLOGY = attributes.value(Skill.TECHNOLOGY.getAttribute());
        LORE       = attributes.value(Skill.LORE.getAttribute());
        WILLPOWER  = attributes.value(Skill.WILLPOWER.getAttribute());
        INFLUENCE  = attributes.value(Skill.INFLUENCE.getAttribute());
    }
}
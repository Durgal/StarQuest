package src.main.java.game.nouns.lifeforms;

import java.util.PriorityQueue;
import java.util.Queue;
import src.main.java.game.data.Data;
import src.main.java.game.nouns.lifeforms.mechanics.Alignment;
import src.main.java.game.nouns.lifeforms.mechanics.Element;
import src.main.java.generators.MarkovChain;
import src.main.java.game.mechanics.Action;
import src.main.java.game.nouns.lifeforms.mechanics.Factions.*;
import src.main.java.game.nouns.lifeforms.mechanics.Occupations;
import src.main.java.utilities.Math;

public class NPC extends Lifeform {
    
    protected Species species;
    protected Faction faction;
    protected Gender gender;
    protected Occupations occupation;
    
    Queue<Action> tasks = new PriorityQueue();
    
    public NPC(Species species) {
        this.name = generateName(species);
        this.species = species;
        this.faction = Faction.random();
        this.occupation = new Occupations();
        this.gender = Gender.random();
        this.skills = species.skills;
        this.statistics = species.statistics;
        this.type = species.type;
        this.alignment = Alignment.random();
        this.element = Element.Category.NONE;
        this.material = species.getMaterial();
        this.size = species.getSize();
        this.physique = species.physique;
    }

    private String generateName(Species species) {
        MarkovChain chain;
        switch(this.gender) {
            case MALE:
                chain = Data.npc_male_generator.get(species);
                break;
            case FEMALE:
                chain = Data.npc_female_generator.get(species);
                break;
            default:
                chain = Data.npc_male_generator.get(species);
                break;
        }
        if (chain == null) return "UNKNOWN";
        int length = 4 + Math.getRandom().nextInt(5); // 4–8 characters
        return chain.generateName(length).toUpperCase();
    }
    
    public CharSequence getSpecies() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public CharSequence getOccupation() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}

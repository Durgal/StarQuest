package src.main.java.game.nouns.lifeforms;

import src.main.java.game.nouns.lifeforms.mechanics.Backgrounds.Background;
import src.main.java.game.nouns.lifeforms.mechanics.Factions.Faction;
import src.main.java.game.nouns.lifeforms.mechanics.Occupations.Occupation;

public class Player extends Lifeform {

    protected Species species;
    protected Faction faction;
    protected Background background;
    protected Gender gender;
    protected Occupation occupation;
    
    public Player() {
        //
    }
    
    public Player(Species species) {
        this.name = "CHRISTOPHER";
        this.species = species;
        this.faction = Faction.RED_BROTHERHOOD;
        this.background = Background.TECHNICIAN; // Background.random();
        this.occupation = Occupation.REAVER;
        this.gender = Gender.random();
        this.material = species.getMaterial();
        this.skills = species.skills;
        this.statistics = species.statistics;
        this.type = species.type;
        this.physique = species.physique;
        this.emotion = Emotion.NONE;
        this.size = species.getSize();
    }

    public String getSpecies() {
        return species.getName();
    }

    public String getOccupation() {
        return occupation.name();
    }
    
    public String getFaction() {
        return faction.getName();
    }
    
    public String getBackground() {
        return background.toString();
    }
    
    public String getGender() {
        return gender.name();
    }
    
}

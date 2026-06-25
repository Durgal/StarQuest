package src.main.java.generators;

import src.main.java.game.nouns.lifeforms.Monster;
import src.main.java.game.nouns.lifeforms.Species;
import src.main.java.game.nouns.lifeforms.mechanics.Attack;

import java.util.HashSet;
import java.util.Set;
import src.main.java.game.nouns.lifeforms.Lifeform;

public class AttackGenerator {

    static Set<Attack> generate(Monster monster) {
        Set<Monster.Characteristics> characteristics = monster.getCharacteristics();
        Lifeform.Classification type = monster.getType();
        return new HashSet<Attack>(); // TODO !
    }

}

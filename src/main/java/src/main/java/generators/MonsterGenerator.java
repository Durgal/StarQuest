package src.main.java.generators;

import src.main.java.game.Material;
import src.main.java.game.nouns.lifeforms.Lifeform;
import src.main.java.game.nouns.lifeforms.Monster;
import src.main.java.game.nouns.lifeforms.Species;
import src.main.java.text_based_mechanics.parts_of_speech.Noun;
import src.main.java.utilities.Enum;
import src.main.java.utilities.Math;
import static src.main.java.game.nouns.lifeforms.Lifeform.Classification.*;

public class MonsterGenerator {

    static Monster monster;

//    public static Monster generate(Species.Type type) {
//        monster = new Monster();
//        switch (type) {
//            case BEAST -> {
//                monster.setPhysique(Enum.random(Lifeform.Physique.class));
//                monster.setSize(Enum.choose(Noun.Size.TINY, Noun.Size.SMALL, Noun.Size.SMALL, Noun.Size.MEDIUM, Noun.Size.MEDIUM, Noun.Size.LARGE));
//                monster.setMaterial(Material.random(type));
//
//                int n = Math.randomRange(1, 3);
//                for (int i = 0; i < n; i++) {
//                    monster.addCharacteristic(
//                    Enum.choose(
//                            Monster.Characteristics.CLAWS,
//                            Monster.Characteristics.FANGS,
//                            Monster.Characteristics.TUSKS,
//                            Monster.Characteristics.HORNS,
//                            Monster.Characteristics.WHISKERS,
//                            Monster.Characteristics.BEAK,
//                            Monster.Characteristics.TONGUE,
//                            Monster.Characteristics.BLUBBER,
//                            Monster.Characteristics.FINS
//                    ));
//                }
//                monster.setDiet(Enum.random(Monster.Diet.class));
//                monster.setLocomotion(Enum.random(Monster.Locomotion.class));
//                monster.setInstinct(Enum.random(Monster.Instinct.class));
//
//                monster.setEyes(Math.choose(0, 1, 2, 3));
//                monster.setArms(Math.choose(0, 2, 2));
//                monster.setLegs(Math.choose(0, 2, 2, 4);
//                monster.setEars(Math.coinToss());
//                monster.setTail(Math.coinToss());
//                wings = (locomotion== Monster.Locomotion.FLY) ? Boolean.TRUE : (locomotion== Monster.Locomotion.BURROW || locomotion== Monster.Locomotion.SWIM) ? Boolean.FALSE : Math.coinToss();
//                gills = (locomotion== Monster.Locomotion.SWIM) ? Boolean.TRUE : Math.coinToss();
//                bioluminescent = Math.bChoose(Boolean.FALSE, Boolean.FALSE, Boolean.FALSE, Boolean.FALSE, Boolean.TRUE);
//            }
//            case PLANT -> {
//                species.physique = Enum.random(Lifeform.Physique.class);
//                size = Enum.choose(Noun.Size.TINY, Noun.Size.SMALL, Noun.Size.SMALL, Noun.Size.MEDIUM, Noun.Size.MEDIUM, Noun.Size.LARGE);
//                material = Material.random(type);
//
//                int n = Math.randomRange(1, 3);
//                for (int i = 0; i < n; i++) {
//                    characteristics.add(Enum.choose(
//                            Monster.Characteristics.TUSKS,
//                            Monster.Characteristics.TENTACLES,
//                            Monster.Characteristics.TONGUE,
//                            Monster.Characteristics.FANGS,
//                            Monster.Characteristics.FRILLS
//                    ));
//                }
//                diet = Enum.random(Monster.Diet.class);
//                locomotion = Enum.choose(Monster.Locomotion.NORMAL, Monster.Locomotion.CLIMB, Monster.Locomotion.JUMP, Monster.Locomotion.FLY);
//                instinct = Enum.random(Monster.Instinct.class);
//
//                eyes = Math.choose(0, 2);
//                arms = Math.choose(0, 2, 2, 4);
//                legs = Math.choose(0, 2, 2);
//                ears = Math.coinToss();
//                tail = Boolean.FALSE;
//                wings = (locomotion== Monster.Locomotion.FLY) ? Boolean.TRUE : (locomotion== Monster.Locomotion.BURROW || locomotion== Monster.Locomotion.SWIM) ? Boolean.FALSE : Math.coinToss();
//                gills = Boolean.FALSE;
//                bioluminescent = Math.bChoose(Boolean.FALSE, Boolean.FALSE, Boolean.FALSE, Boolean.TRUE);
//            }
//            case ABYSSAL -> {
//                species.physique = Enum.random(Lifeform.Physique.class);
//                size = Enum.choose(Noun.Size.TINY, Noun.Size.SMALL, Noun.Size.SMALL, Noun.Size.MEDIUM, Noun.Size.MEDIUM, Noun.Size.LARGE);
//                material = Material.random(type);
//
//                int n = Math.randomRange(1, 3);
//                for (int i = 0; i < n; i++) {
//                    characteristics.add(Enum.choose(
//                            Monster.Characteristics.MONOCULAR,
//                            Monster.Characteristics.MONOCULAR,
//                            Monster.Characteristics.MONOCULAR,
//                            Monster.Characteristics.TENTACLES,
//                            Monster.Characteristics.TONGUE,
//                            Monster.Characteristics.SHELL,
//                            Monster.Characteristics.FANGS,
//                            Monster.Characteristics.MANDIBLES
//                    ));
//                }
//                diet = Monster.Diet.CARNIVORE;
//                locomotion = Enum.random(Monster.Locomotion.class);
//                instinct = Enum.random(Monster.Instinct.class);
//
//                eyes = characteristics.contains(Monster.Characteristics.MONOCULAR) ? Math.choose(1, 2) : Math.choose(0, 0, 0, 0, 1, 2);
//                arms = Math.choose(0, 2, 2, 4);
//                legs = Math.choose(0, 2, 2, 4);
//                ears = Math.coinToss();
//                tail = Math.coinToss();
//                wings = (locomotion== Monster.Locomotion.FLY) ? Boolean.TRUE : (locomotion== Monster.Locomotion.BURROW || locomotion== Monster.Locomotion.SWIM) ? Boolean.FALSE : Math.coinToss();
//                gills = Boolean.TRUE;
//                bioluminescent = Math.bChoose(Boolean.FALSE, Boolean.TRUE);
//            }
//            case HUMANOID -> {
//                species.physique = Enum.random(Lifeform.Physique.class);
//                size = Enum.choose(Noun.Size.TINY, Noun.Size.SMALL, Noun.Size.SMALL, Noun.Size.MEDIUM, Noun.Size.MEDIUM, Noun.Size.LARGE);
//                material = Material.random(type);
//
//                int n = Math.randomRange(1, 3);
//                for (int i = 0; i < n; i++) {
//                    characteristics.add(Enum.random(Monster.Characteristics.class));
//                }
//                diet = Monster.Diet.OMNIVORE;
//                locomotion = Monster.Locomotion.NORMAL;
//                instinct = Enum.random(Monster.Instinct.class);
//
//                eyes = characteristics.contains(Monster.Characteristics.MONOCULAR) ? Math.choose(1, 2) : Math.choose(0, 1, 2, 2, 2, 2);
//                arms = 2;
//                legs = 2;
//                ears = Boolean.TRUE;
//                tail = Math.coinToss();
//                wings = (locomotion== Monster.Locomotion.FLY) ? Boolean.TRUE : (locomotion== Monster.Locomotion.BURROW || locomotion== Monster.Locomotion.SWIM) ? Boolean.FALSE : Math.coinToss();
//                gills = (locomotion== Monster.Locomotion.SWIM) ? Boolean.TRUE : Math.coinToss();
//                bioluminescent = Boolean.FALSE;
//            }
//            case ARTHROPOD -> {
//                species.physique = Enum.random(Lifeform.Physique.class);
//                size = Enum.choose(Noun.Size.TINY, Noun.Size.SMALL, Noun.Size.SMALL, Noun.Size.MEDIUM, Noun.Size.MEDIUM, Noun.Size.LARGE);
//                material = Material.random(type);
//
//                int n = Math.randomRange(1, 3);
//                for (int i = 0; i < n; i++) {
//                    characteristics.add(Enum.choose(
//                            Monster.Characteristics.SHELL,
//                            Monster.Characteristics.SHELL,
//                            Monster.Characteristics.SHELL,
//                            Monster.Characteristics.MANDIBLES,
//                            Monster.Characteristics.MANDIBLES,
//                            Monster.Characteristics.MANDIBLES,
//                            Monster.Characteristics.ANTENNAE,
//                            Monster.Characteristics.ANTENNAE,
//                            Monster.Characteristics.ANTENNAE,
//                            Monster.Characteristics.TENTACLES,
//                            Monster.Characteristics.FANGS
//                    ));
//                }
//                diet = Enum.random(Monster.Diet.class);
//                locomotion = Enum.choose(Monster.Locomotion.NORMAL, Monster.Locomotion.CLIMB, Monster.Locomotion.JUMP, Monster.Locomotion.BURROW, Monster.Locomotion.SWIM);
//                instinct = Enum.random(Monster.Instinct.class);
//
//                eyes = Math.choose(0, 1, 1, 2, 2, 2, 2);
//                arms = Math.choose(0, 2, 2, 4, 8);
//                legs = 0;
//                ears = Boolean.FALSE;
//                tail = Math.coinToss();
//                wings = (locomotion== Monster.Locomotion.FLY) ? Boolean.TRUE : (locomotion== Monster.Locomotion.BURROW || locomotion== Monster.Locomotion.SWIM) ? Boolean.FALSE : Math.coinToss();
//                gills = (locomotion== Monster.Locomotion.SWIM) ? Boolean.TRUE : Math.coinToss();
//                bioluminescent = Math.bChoose(Boolean.FALSE, Boolean.FALSE, Boolean.TRUE);
//            }
//            case CONSTRUCT -> {
//                species.physique = Enum.random(Lifeform.Physique.class);
//                size = Enum.choose(Noun.Size.TINY, Noun.Size.SMALL, Noun.Size.SMALL, Noun.Size.MEDIUM, Noun.Size.MEDIUM, Noun.Size.LARGE);
//                material = Material.random(type);
//
//                int n = 1;
//                for (int i = 0; i < n; i++) {
//                    characteristics.add(Enum.choose(
//                            Monster.Characteristics.ANTENNAE,
//                            Monster.Characteristics.MONOCULAR,
//                            Monster.Characteristics.MANDIBLES,
//                            Monster.Characteristics.FRILLS
//                    ));
//                }
//                diet = Enum.random(Monster.Diet.class);
//                locomotion = Enum.choose(Monster.Locomotion.NORMAL, Monster.Locomotion.CLIMB, Monster.Locomotion.JUMP, Monster.Locomotion.FLY, Monster.Locomotion.BURROW);
//                instinct = Enum.random(Monster.Instinct.class);
//
//                eyes = characteristics.contains(Monster.Characteristics.MONOCULAR) ? Math.choose(1, 2, 3) : Math.choose(0, 1, 2, 3);
//                arms = 2;
//                legs = 2;
//                ears = Boolean.FALSE;
//                tail = Math.coinToss();
//                wings = (locomotion== Monster.Locomotion.FLY) ? Boolean.TRUE : (locomotion== Monster.Locomotion.BURROW || locomotion== Monster.Locomotion.SWIM) ? Boolean.FALSE : Math.coinToss();
//                gills = Boolean.FALSE;
//                bioluminescent = Boolean.FALSE;
//            }
//            case ELEMENTAL -> {
//                species.physique = Enum.random(Lifeform.Physique.class);
//                size = Enum.choose(Noun.Size.TINY, Noun.Size.SMALL, Noun.Size.SMALL, Noun.Size.MEDIUM, Noun.Size.MEDIUM, Noun.Size.LARGE);
//                material = Material.random(type);
//
//                // no typical monster characteristics
//
//                diet = Enum.random(Monster.Diet.class);
//                locomotion = Enum.choose(Monster.Locomotion.NORMAL);
//                instinct = Enum.random(Monster.Instinct.class);
//
//                eyes = Math.choose(0, 1, 2);
//                arms = 2;
//                legs = 2;
//                ears = Boolean.FALSE;
//                tail = Boolean.FALSE;
//                wings = Boolean.FALSE;
//                gills = Boolean.FALSE;
//                bioluminescent = Boolean.FALSE;
//            }
//            case UNKNOWN -> {
//                // TODO: ?
//            }
//            default -> {
//
//            }
//        }
//
//
//        return monster;
//    }

}

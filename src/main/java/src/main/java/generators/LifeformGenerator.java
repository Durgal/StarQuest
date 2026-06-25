package src.main.java.generators;

import src.main.java.game.data.Data;
import src.main.java.game.Material;
import src.main.java.game.nouns.lifeforms.mechanics.Element;
import src.main.java.game.nouns.locations.Planet;
import src.main.java.text_based_mechanics.parts_of_speech.Noun;
import src.main.java.utilities.Enum;
import src.main.java.utilities.Math;

import java.util.HashSet;
import java.util.Set;


import static src.main.java.game.nouns.lifeforms.Lifeform.Classification.*;
import static src.main.java.game.nouns.lifeforms.Lifeform.Classification.ELEMENTAL;
import src.main.java.game.nouns.lifeforms.*;

public class LifeformGenerator {

    public static Set<Civilization> getCivilizations(Planet planet) {

        HashSet<Civilization> civilizations = new HashSet<>();

        // generate all non-monster lifeforms (Civilizations)
        int s = Math.choose(1,1,1,1,2);
        for (int i = 0; i < s; i++) {
            civilizations.add(generateCivilization(planet));
        }

        return civilizations;
    }

    public static Set<Monster> getMonsters(Planet planet) {

        HashSet<Monster> monsters = new HashSet<>();

        // generate all monster-type lifeforms
        int m = 5;
        for (int i = 0; i < m; i++) {
            monsters.add(generateMonster(planet));
        }

        return monsters;
    }

    // generate a Civilization of a certain species
    private static Civilization generateCivilization(Planet planet) {
        Species species;
        switch(planet.getCategory()) {
            case TERRAN -> {
                species = Species.choose(
                        Data.species.get(Species.Type.HUMAN),
                        Data.species.get(Species.Type.HUMAN),
                        Data.species.get(Species.Type.HUMAN),
                        Data.species.get(Species.Type.NUMERIAN),
                        Data.species.get(Species.Type.GUARDIAN),
                        Data.species.get(Species.Type.PROTOZOAN)
                        );
            }
            case JUNGLE -> {
                species = Species.choose(
                        Data.species.get(Species.Type.GUARDIAN),
                        Data.species.get(Species.Type.GUARDIAN),
                        Data.species.get(Species.Type.GUARDIAN),
                        Data.species.get(Species.Type.HUMAN),
                        Data.species.get(Species.Type.ARKON),
                        Data.species.get(Species.Type.PROTOZOAN)
                );
            }
            case VOLCANIC -> {
                species = Species.choose(
                        Data.species.get(Species.Type.IGNEOS),
                        Data.species.get(Species.Type.IGNEOS),
                        Data.species.get(Species.Type.IGNEOS),
                        Data.species.get(Species.Type.NUMERIAN),
                        Data.species.get(Species.Type.HUMAN)
                );
            }
            case DESERT -> {
                species = Species.choose(
                        Data.species.get(Species.Type.PROTOZOAN),
                        Data.species.get(Species.Type.PROTOZOAN),
                        Data.species.get(Species.Type.PROTOZOAN),
                        Data.species.get(Species.Type.HUMAN),
                        Data.species.get(Species.Type.NUMERIAN)
                );
            }
            case OCEANIC -> {
                species = Species.choose(
                        Data.species.get(Species.Type.ARKON),
                        Data.species.get(Species.Type.ARKON),
                        Data.species.get(Species.Type.ARKON),
                        Data.species.get(Species.Type.HUMAN),
                        Data.species.get(Species.Type.NUMERIAN),
                        Data.species.get(Species.Type.GUARDIAN)
                );
            }
            case DESOLATE -> {
                species = Species.choose(
                        Data.species.get(Species.Type.NUMERIAN),
                        Data.species.get(Species.Type.NUMERIAN),
                        Data.species.get(Species.Type.NUMERIAN),
                        Data.species.get(Species.Type.IGNEOS),
                        Data.species.get(Species.Type.IGNEOS),
                        Data.species.get(Species.Type.HUMAN)
                );
            }
            case ARCTIC -> {
                species = Species.choose(
                        Data.species.get(Species.Type.OKAMI),
                        Data.species.get(Species.Type.OKAMI),
                        Data.species.get(Species.Type.OKAMI),
                        Data.species.get(Species.Type.IGNEOS),
                        Data.species.get(Species.Type.HUMAN)
                );
            }
            default -> throw new IllegalStateException("Unexpected value: " + planet.getCategory());
        }
        return new Civilization(species);
    }

    // generate a Monster
    private static Monster generateMonster(Planet planet) {
        Monster monster = new Monster();
        monster.setType(chooseType(planet));
        monster.setElement(chooseElement(planet));

        switch (monster.getType()) {
            case BEAST -> {
                monster.setPhysique(Enum.random(Lifeform.Physique.class));
                monster.setSize(Enum.choose(Noun.Size.TINY, Noun.Size.SMALL, Noun.Size.SMALL, Noun.Size.SMALL, Noun.Size.MEDIUM, Noun.Size.MEDIUM, Noun.Size.MEDIUM, Noun.Size.LARGE));
                monster.setMaterial(Material.random(monster));

                int n = Math.randomRange(1, 3);
                for (int i = 0; i < n; i++) {
                    monster.addCharacteristic(
                    Enum.choose(
                            Monster.Characteristics.CLAWS,
                            Monster.Characteristics.FANGS,
                            Monster.Characteristics.TUSKS,
                            Monster.Characteristics.HORNS,
                            Monster.Characteristics.WHISKERS,
                            Monster.Characteristics.BEAK,
                            Monster.Characteristics.TONGUE,
                            Monster.Characteristics.BLUBBER,
                            Monster.Characteristics.MONOCULAR,
                            Monster.Characteristics.FINS
                    ));
                }
                monster.setDiet(Enum.choose(Monster.Diet.CARNIVORE, Monster.Diet.OMNIVORE, Monster.Diet.HERBIVORE));
                monster.setLocomotion(Enum.random(Monster.Locomotion.class));
                monster.setInstinct(Enum.choose( Monster.Instinct.HERD, Monster.Instinct.CAUTIOUS, Monster.Instinct.HIBERNATOR,
                        Monster.Instinct.HOSTILE, Monster.Instinct.LAZY, Monster.Instinct.NOCTURNAL, Monster.Instinct.SNEAKY,
                        Monster.Instinct.PACK_HUNTER, Monster.Instinct.SCAVENGER, Monster.Instinct.PROTECTIVE));

                monster.setEyes(Math.choose(0, 1, 2, 3));
                monster.setArms(2);
                monster.setLegs(Math.choose(0, 2, 2, 4));
                monster.setTail(Math.coinToss());
                monster.setWings((monster.getLocomotion() == Monster.Locomotion.FLY) ? Boolean.TRUE : (monster.getLocomotion()== Monster.Locomotion.BURROW || monster.getLocomotion()== Monster.Locomotion.SWIM) ? Boolean.FALSE : Math.coinToss());
                monster.setGills((monster.getLocomotion()== Monster.Locomotion.SWIM) ? Boolean.TRUE : Math.coinToss());
            }
            case PLANT -> {
                monster.setPhysique(Enum.random(Lifeform.Physique.class));
                monster.setSize(Enum.choose(Noun.Size.TINY, Noun.Size.SMALL, Noun.Size.SMALL, Noun.Size.SMALL, Noun.Size.MEDIUM, Noun.Size.MEDIUM, Noun.Size.MEDIUM, Noun.Size.LARGE));
                monster.setMaterial(Material.random(monster));

                int n = Math.randomRange(1, 3);
                for (int i = 0; i < n; i++) {
                    monster.addCharacteristic(
                            Enum.choose(
                            Monster.Characteristics.SPORES,
                            Monster.Characteristics.SPORES,
                            Monster.Characteristics.SPORES,
                            Monster.Characteristics.VENOM,
                            Monster.Characteristics.VENOM,
                            Monster.Characteristics.MUCOUS,
                            Monster.Characteristics.TENTACLES,
                            Monster.Characteristics.TONGUE,
                            Monster.Characteristics.FRILLS,
                            Monster.Characteristics.BIOLUMINESCENT,
                            Monster.Characteristics.BIOLUMINESCENT
                    ));
                }
                monster.setDiet(Enum.choose(Monster.Diet.CARNIVORE, Monster.Diet.NONE));
                monster.setLocomotion(Enum.choose(Monster.Locomotion.NORMAL, Monster.Locomotion.CLIMB, Monster.Locomotion.JUMP, Monster.Locomotion.FLY));
                monster.setInstinct(Enum.choose( Monster.Instinct.CAUTIOUS, Monster.Instinct.HIBERNATOR,
                        Monster.Instinct.HOSTILE, Monster.Instinct.HIVE_MIND, Monster.Instinct.NOCTURNAL, Monster.Instinct.SNEAKY,
                        Monster.Instinct.PACK_HUNTER, Monster.Instinct.SCAVENGER, Monster.Instinct.PROTECTIVE));

                monster.setEyes(Math.choose(0, 0, 2));
                monster.setArms(Math.choose(0, 2, 2, 4));
                monster.setLegs(Math.choose(0, 2, 2));
                monster.setTail(Boolean.FALSE);
                monster.setWings((monster.getLocomotion()== Monster.Locomotion.FLY) ? Boolean.TRUE : (monster.getLocomotion()== Monster.Locomotion.BURROW || monster.getLocomotion()== Monster.Locomotion.SWIM) ? Boolean.FALSE : Math.coinToss());
                monster.setGills(Boolean.FALSE);
            }
            case ABYSSAL -> {
                monster.setPhysique(Enum.random(Lifeform.Physique.class));
                monster.setSize(Enum.choose(Noun.Size.TINY, Noun.Size.SMALL, Noun.Size.SMALL, Noun.Size.SMALL, Noun.Size.MEDIUM, Noun.Size.MEDIUM, Noun.Size.MEDIUM, Noun.Size.LARGE));
                monster.setMaterial(Material.random(monster));

                int n = Math.randomRange(1, 3);
                for (int i = 0; i < n; i++) {
                    monster.addCharacteristic(
                            Enum.choose(
                            Monster.Characteristics.MONOCULAR,
                            Monster.Characteristics.MONOCULAR,
                            Monster.Characteristics.MONOCULAR,
                            Monster.Characteristics.TENTACLES,
                            Monster.Characteristics.TENTACLES,
                            Monster.Characteristics.BIOLUMINESCENT,
                            Monster.Characteristics.BIOLUMINESCENT,
                            Monster.Characteristics.BIOLUMINESCENT,
                            Monster.Characteristics.MUCOUS,
                            Monster.Characteristics.MUCOUS,
                            Monster.Characteristics.MUCOUS,
                            Monster.Characteristics.TRANSLUCENT,
                            Monster.Characteristics.TRANSLUCENT,
                            Monster.Characteristics.ELECTROGENESIS,
                            Monster.Characteristics.ELECTROGENESIS,
                            Monster.Characteristics.TONGUE,
                            Monster.Characteristics.SHELL,
                            Monster.Characteristics.SPORES,
                            Monster.Characteristics.VENOM,
                            Monster.Characteristics.FANGS,
                            Monster.Characteristics.MANDIBLES
                    ));
                }
                monster.setDiet(Monster.Diet.CARNIVORE);
                monster.setLocomotion(Enum.random(Monster.Locomotion.class));
                monster.setInstinct(Enum.choose( Monster.Instinct.GIBBERING, Monster.Instinct.GIBBERING, Monster.Instinct.GIBBERING,
                        Monster.Instinct.HOSTILE, Monster.Instinct.HIVE_MIND, Monster.Instinct.HIVE_MIND, Monster.Instinct.NOCTURNAL,
                        Monster.Instinct.PACK_HUNTER, Monster.Instinct.SCAVENGER));

                monster.setEyes(monster.getCharacteristics().contains(Monster.Characteristics.MONOCULAR) ? 1 : Math.choose(0, 0, 0, 0, 1, 2));
                monster.setArms(Math.choose(0, 2, 2, 4));
                monster.setLegs(Math.choose(0, 2, 2, 4));
                monster.setTail(Math.coinToss());
                monster.setWings((monster.getLocomotion()== Monster.Locomotion.FLY) ? Boolean.TRUE : (monster.getLocomotion()== Monster.Locomotion.BURROW || monster.getLocomotion()== Monster.Locomotion.SWIM) ? Boolean.FALSE : Math.coinToss());
                monster.setGills(Boolean.TRUE);
            }
            case ARTHROPOD -> {
                monster.setPhysique(Enum.random(Lifeform.Physique.class));
                monster.setSize(Enum.choose(Noun.Size.TINY, Noun.Size.SMALL, Noun.Size.SMALL, Noun.Size.SMALL, Noun.Size.MEDIUM, Noun.Size.MEDIUM, Noun.Size.MEDIUM, Noun.Size.LARGE));
                monster.setMaterial(Material.random(monster));

                int n = Math.randomRange(1, 3);
                for (int i = 0; i < n; i++) {
                    monster.addCharacteristic(
                            Enum.choose(
                            Monster.Characteristics.SHELL,
                            Monster.Characteristics.SHELL,
                            Monster.Characteristics.SHELL,
                            Monster.Characteristics.MANDIBLES,
                            Monster.Characteristics.MANDIBLES,
                            Monster.Characteristics.MANDIBLES,
                            Monster.Characteristics.ANTENNAE,
                            Monster.Characteristics.ANTENNAE,
                            Monster.Characteristics.ANTENNAE,
                            Monster.Characteristics.VENOM,
                            Monster.Characteristics.VENOM,
                            Monster.Characteristics.TRANSLUCENT,
                            Monster.Characteristics.TRANSLUCENT,
                            Monster.Characteristics.TENTACLES,
                            Monster.Characteristics.MONOCULAR,
                            Monster.Characteristics.FANGS
                    ));
                }
                monster.setDiet(Enum.choose(Monster.Diet.CARNIVORE, Monster.Diet.OMNIVORE));
                monster.setLocomotion(Enum.choose(Monster.Locomotion.NORMAL, Monster.Locomotion.JUMP, Monster.Locomotion.BURROW, Monster.Locomotion.SWIM));
                monster.setInstinct(Enum.choose( Monster.Instinct.HIVE_MIND, Monster.Instinct.HIVE_MIND, Monster.Instinct.HIVE_MIND,
                        Monster.Instinct.HOSTILE, Monster.Instinct.HOSTILE, Monster.Instinct.NOCTURNAL, Monster.Instinct.NOCTURNAL,
                        Monster.Instinct.PACK_HUNTER, Monster.Instinct.SCAVENGER, Monster.Instinct.SCAVENGER, Monster.Instinct.SNEAKY));

                monster.setEyes(Math.choose(0, 1, 1, 2, 2, 2, 2));
                monster.setArms(Math.choose(4, 6, 8));
                monster.setLegs(0);
                monster.setTail(Math.coinToss());
                monster.setWings((monster.getLocomotion()== Monster.Locomotion.FLY) ? Boolean.TRUE : (monster.getLocomotion()== Monster.Locomotion.BURROW || monster.getLocomotion()== Monster.Locomotion.SWIM) ? Boolean.FALSE : Math.coinToss());
                monster.setGills((monster.getLocomotion()== Monster.Locomotion.SWIM) ? Boolean.TRUE : Math.coinToss());
            }
            case CONSTRUCT -> {
                monster.setPhysique(Enum.random(Lifeform.Physique.class));
                monster.setSize(Enum.choose(Noun.Size.TINY, Noun.Size.SMALL, Noun.Size.SMALL, Noun.Size.SMALL, Noun.Size.MEDIUM, Noun.Size.MEDIUM, Noun.Size.MEDIUM, Noun.Size.LARGE));
                monster.setMaterial(Material.random(monster));

                int n = 1;
                for (int i = 0; i < n; i++) {
                    monster.addCharacteristic(
                            Enum.choose(
                            Monster.Characteristics.ELECTROGENESIS,
                            Monster.Characteristics.ELECTROGENESIS,
                            Monster.Characteristics.MONOCULAR,
                            Monster.Characteristics.MANDIBLES,
                            Monster.Characteristics.FRILLS
                    ));
                }
                monster.setDiet(Monster.Diet.NONE);
                monster.setLocomotion(Enum.choose(Monster.Locomotion.NORMAL, Monster.Locomotion.CLIMB, Monster.Locomotion.JUMP, Monster.Locomotion.FLY, Monster.Locomotion.BURROW));
                monster.setInstinct(Enum.choose( Monster.Instinct.SCAVENGER, Monster.Instinct.SCAVENGER, Monster.Instinct.SCAVENGER,
                        Monster.Instinct.HOSTILE, Monster.Instinct.HOSTILE, Monster.Instinct.PACK_HUNTER, Monster.Instinct.PACK_HUNTER,
                        Monster.Instinct.HIBERNATOR, Monster.Instinct.PROTECTIVE, Monster.Instinct.PROTECTIVE));

                monster.setEyes(monster.getCharacteristics().contains(Monster.Characteristics.MONOCULAR) ? Math.choose(1, 2, 3) : Math.choose(0, 1, 2, 3));
                monster.setArms(2);
                monster.setLegs(2);
                monster.setTail(Math.coinToss());
                monster.setWings((monster.getLocomotion()== Monster.Locomotion.FLY) ? Boolean.TRUE : (monster.getLocomotion()== Monster.Locomotion.BURROW || monster.getLocomotion()== Monster.Locomotion.SWIM) ? Boolean.FALSE : Math.coinToss());
                monster.setGills(Boolean.FALSE);
            }
            case ELEMENTAL -> {
                monster.setPhysique(Enum.random(Lifeform.Physique.class));
                monster.setSize(Enum.choose(Noun.Size.TINY, Noun.Size.SMALL, Noun.Size.SMALL, Noun.Size.SMALL, Noun.Size.MEDIUM, Noun.Size.MEDIUM, Noun.Size.MEDIUM, Noun.Size.LARGE));
                monster.setMaterial(Material.random(monster.getElement()));

                switch(monster.getElement()) {
                    case FIRE, EARTH -> {
                        monster.addCharacteristic(Monster.Characteristics.PYROGENESIS);
                    }
                    case WATER, ICE -> {
                        monster.addCharacteristic(Monster.Characteristics.HYDROGENESIS);
                    }
                    case WIND, SOLAR -> {
                        monster.addCharacteristic(Monster.Characteristics.ELECTROGENESIS);
                    }
                    case PLANT -> {
                        monster.addCharacteristic(Monster.Characteristics.SPORES);
                    }
                    case DARK -> {
                        monster.addCharacteristic(Monster.Characteristics.MUCOUS);
                    }
                    case NONE -> {
                        monster.addCharacteristic(Monster.Characteristics.MONOCULAR);
                    }
                }

                monster.setDiet(Monster.Diet.NONE);
                monster.setLocomotion(Enum.choose(Monster.Locomotion.NORMAL));
                monster.setInstinct(Enum.choose( Monster.Instinct.PROTECTIVE, Monster.Instinct.PROTECTIVE, Monster.Instinct.PROTECTIVE,
                        Monster.Instinct.HIBERNATOR, Monster.Instinct.HIBERNATOR, Monster.Instinct.SCAVENGER, Monster.Instinct.SCAVENGER,
                        Monster.Instinct.PACK_HUNTER, Monster.Instinct.PACK_HUNTER, Monster.Instinct.GIBBERING));

                monster.setEyes(monster.getCharacteristics().contains(Monster.Characteristics.MONOCULAR) ? Math.choose(1, 2, 3) : Math.choose(0, 1, 2, 3));
                monster.setArms(2);
                monster.setLegs(2);
                monster.setTail(Boolean.FALSE);
                monster.setWings(Boolean.FALSE);
                monster.setGills(Boolean.FALSE);
            }
            case UNKNOWN -> {
                // TODO: ?
            }
            default -> {

            }
        }

        monster.generateName();

        return monster;
    }

    private static Lifeform.Classification chooseType(Planet planet) {
        Lifeform.Classification category;
        switch(planet.getCategory()) {
            case TERRAN -> {
                category = Enum.choose(BEAST, BEAST, PLANT, ARTHROPOD);
            }
            case JUNGLE -> {
                category = Enum.choose(PLANT, PLANT, PLANT, BEAST);
            }
            case VOLCANIC -> {
                category = Enum.choose(ELEMENTAL, ELEMENTAL, ARTHROPOD, CONSTRUCT);
            }
            case DESERT -> {
                category = Enum.choose(ARTHROPOD, ARTHROPOD, CONSTRUCT, BEAST);
            }
            case OCEANIC -> {
                category = Enum.choose(ABYSSAL, PLANT, ARTHROPOD, BEAST);
            }
            case DESOLATE -> {
                category = Enum.choose(CONSTRUCT, CONSTRUCT, ABYSSAL, ELEMENTAL);
            }
            case ARCTIC -> {
                category = Enum.choose(BEAST, CONSTRUCT, ELEMENTAL, ABYSSAL);
            }
            default -> throw new IllegalStateException("Unexpected value: " + planet.getCategory());
        }
        return category;
    }

    private static Element.Category chooseElement(Planet planet) {
        Element.Category element;
        switch(planet.getCategory()) {
            case TERRAN -> {
                element = Enum.choose(Element.Category.NONE, Element.Category.NONE, Element.Category.WATER, Element.Category.EARTH, Element.Category.PLANT);
            }
            case JUNGLE -> {
                element = Enum.choose(Element.Category.NONE, Element.Category.WATER, Element.Category.PLANT, Element.Category.PLANT);
            }
            case VOLCANIC -> {
                element = Enum.choose(Element.Category.NONE, Element.Category.FIRE, Element.Category.FIRE, Element.Category.EARTH);
            }
            case DESERT -> {
                element = Enum.choose(Element.Category.NONE, Element.Category.WIND, Element.Category.SOLAR, Element.Category.SOLAR);
            }
            case OCEANIC -> {
                element = Enum.choose(Element.Category.NONE, Element.Category.DARK, Element.Category.DARK, Element.Category.WATER);
            }
            case DESOLATE -> {
                element = Enum.choose(Element.Category.NONE, Element.Category.WIND, Element.Category.WIND, Element.Category.DARK);
            }
            case ARCTIC -> {
                element = Enum.choose(Element.Category.NONE, Element.Category.ICE, Element.Category.ICE, Element.Category.DARK);
            }
            default -> throw new IllegalStateException("Unexpected value: " + planet.getCategory());
        }
        return element;
    }
}

package src.main.java.game.data;

import src.main.java.game.nouns.lifeforms.Monster;
import src.main.java.game.nouns.locations.Continent;
import src.main.java.game.nouns.locations.Location;
import src.main.java.game.nouns.locations.Planet;
import src.main.java.game.nouns.lifeforms.Species;
import src.main.java.game.nouns.locations.SolarSystem;
import src.main.java.game.nouns.things.Armor;
import src.main.java.game.nouns.things.Item;
import src.main.java.game.nouns.things.Weapon;
import src.main.java.generators.NameGenerator;
import src.main.java.text_based_mechanics.parts_of_speech.Adjective;
import src.main.java.text_based_mechanics.parts_of_speech.Noun;
import src.main.java.text_based_mechanics.parts_of_speech.Verb;
import src.main.java.text_based_mechanics.parts_of_speech.Word;
import src.main.java.utilities.FileUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.*;
import src.main.java.game.Material;

public class ResourceLoader {

    static void load() {

        // load constant data

        try {
            load_dictionary();
        } catch (Exception e) { System.out.println("ERROR: Could not load Dictionary data"); }

        try {
            load_materials();
        } catch (Exception e) { System.out.println("ERROR: Could not load Material data"); }

        try {
            load_species();
        } catch (Exception e) { System.out.println("ERROR: Could not load Race data"); }

        try {
            load_objects();
        } catch (Exception e) { System.out.println("ERROR: Could not load Object data"); }

        // load pseudo-random data
        try {
            load_markov_chains();
        }  catch (Exception e) { System.out.println("ERROR: Could not load Markov Chains"); }

        try {
            load_monsters();
        } catch (Exception e) { System.out.println("ERROR: Could not load Monster data"); }

        try {
            load_planets();
        } catch (Exception e) { System.out.println("ERROR: Could not load Planet data: "+e); }

    }

    private static void load_materials() throws IOException {
        BufferedReader br = FileUtils.getFileHandler("/data/MATERIALS.txt");
        for (String line; (line = br.readLine()) != null;) {
            String[] l = line.split("\s+");
            if (!l[0].equals("NAME")) {
                Data.materials.put(l[0], new Material(l[0], l[1], l[2], l[3], l[4], l[5], l[6], l[7], l[8], l[9], l[10], l[11], l[12], l[13], l[14], l[15]));
            }
        }
    }

    private static void load_species() throws IOException {
        BufferedReader br = FileUtils.getFileHandler("/data/SPECIES.txt"); // load playable species
        for (String line; (line = br.readLine()) != null;) {
            String[] l = line.split("\s+");
            if (!l[0].equals("NAME")) {
                System.out.println(l[0]+" "+l[1]+" "+l[2]+" "+l[3]+" "+l[4]+" "+l[5]+" "+l[6]+" "+l[7]+" "+l[8]+" "+l[9]+" "+l[10]);
                Data.species.put(Species.Type.valueOf(l[0]), new Species(l[0], l[1], l[2], l[3], l[4], l[5], l[6], l[7], l[8], l[9], l[10]));
            }
        }
    }

    private static void load_monsters() throws IOException {
        BufferedReader br = FileUtils.getFileHandler("/data/MONSTERS.txt");
        for (String line; (line = br.readLine()) != null;) {
            String[] l = line.split("\s+");
            if (!l[0].equals("NAME")) {

                List<String> characteristics = new ArrayList<>();
                for (int c = 13; c < l.length; c++) {
                    characteristics.add(l[c].replaceAll("[^a-zA-Z0-9]", ""));
                }
                
//                System.out.println(l[0]+" "+l[1]+" "+l[2]+" "+l[3]+" "+l[4]+" "+l[5]+" "+l[6]+" "+l[7]+" "+l[8]+" "+l[9]+" "+l[10]+" "+l[11]+" "+l[12]+" "+characteristics);
                Data.monsters.put(l[0], new Monster(l[0], l[1], l[2], l[3], l[4], l[5], l[6], l[7], l[8], l[9], l[10],
                        l[11], l[12], characteristics));
            }
        }
    }

    private static void load_planets() throws IOException {
        BufferedReader br = FileUtils.getFileHandler("/data/PLANETS.txt");
        for (String line; (line = br.readLine()) != null;) {
            String[] l = line.split("\s+");
            if (!l[0].equals("TYPE")) {
//                System.out.println(l[0]+" "+l[1]+" "+l[2]+" "+l[3]+" "+l[4]+" "+l[5]+" "+l[6]+" "+l[7]);
                Data.planets.put(l[0], new Planet(l[0], l[1], l[2], l[3], l[4], l[5], l[6], l[7]));
            }
        }
    }

    private static void load_objects() throws IOException {
        // load items
        BufferedReader br = FileUtils.getFileHandler("/data/ITEMS.txt");
        for (String line; (line = br.readLine()) != null;) {
            String[] l = line.split("\s+");
            if (!l[0].equals("NAME")) {
//                System.out.println(l[0]+" "+l[1]+" "+l[2]+" "+l[3]+" "+l[4]+" "+l[5]);
                Data.objects.put(l[0], new Item(l[0], l[1], l[2], l[3], l[4], l[5]));
            }
        }

        // load weapons
        br = FileUtils.getFileHandler("/data/WEAPONS.txt");
        for (String line; (line = br.readLine()) != null;) {
            String[] l = line.split("\s+");
            if (!l[0].equals("NAME")) {
                Data.objects.put(l[0], new Weapon(l[0], l[1], l[2], l[3]));
            }
        }

        // load armor
        br = FileUtils.getFileHandler("/data/ARMOR.txt");
        for (String line; (line = br.readLine()) != null;) {
            String[] l = line.split("\s+");
            if (!l[0].equals("NAME")) {
                Data.objects.put(l[0], new Armor(l[0], l[1], l[2]));
            }
        }
    }

    private static void load_dictionary() throws IOException {
        // load dictionary
        BufferedReader br = FileUtils.getFileHandler("/data/DICTIONARY.txt");
        for (String line; (line = br.readLine()) != null;) {
            String[] l = line.split(",");
            String word = l[0];
            String pos  = l[1];
            String prob = l[2];
            String syns = l[3].replaceAll("\\[|\\]", "");

            Word input;

//            if (Data.parts_of_speech.containsKey(line)) {
                switch (pos) {
                    case   "NN":
                    case  "NNP":
                    case  "NNS":
                    case "NNPS":
                        input = new Noun(word);
                        input.setProbability(Long.valueOf(prob));
                        input.setSynonyms(List.of(syns.split(" ")));
                        Data.dictionary.put(word, input);
                        break;
                    case "JJD":
                    case "JJC":
                    case "JJS":
                    case "JJQ":
                    case "JJA":
                    case "JJZ":
                        input = new Adjective(word);
                        input.setProbability(Long.valueOf(prob));
                        input.setSynonyms(List.of(syns.split(" ")));
                        Data.dictionary.put(word, input);
                        break;
                    case  "VB":
                    case "VBZ":
                    case "VBD":
                    case "VBN":
                    case "VBG":
                        input = new Verb(word);
                        input.setProbability(Long.valueOf(prob));
                        input.setSynonyms(List.of(syns.split(" ")));
                        Data.dictionary.put(word, input); // TODO: convert past tense etc to present
                        break;
                    case "TO":
                    case "DT":
                        input = new Word(word);
                        input.setProbability(Long.valueOf(prob));
                        input.setSynonyms(List.of(syns.split(" ")));
                        Data.dictionary.put(word, input);
                        break;

                }
//            }
        }
    }

    private static void load_markov_chains() throws IOException {

        // LOAD SOLAR SYSTEM NAMES
        NameGenerator.location(SolarSystem.class);

        // LOAD PLANET NAMES
        NameGenerator.location(Planet.class);

        // LOAD CONTINENT NAMES
        NameGenerator.location(Continent.class);

        // LOAD LOCATION NAMES
        NameGenerator.location(Location.class);

        // LOAD CIVILIZATION NAMES
        NameGenerator.civilization();

        // LOAD NPC NAMES
        NameGenerator.npc();

        // LOAD MONSTER NAMES
        NameGenerator.monster();
    }

}

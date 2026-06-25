package src.main.java.text_based_mechanics.parts_of_speech.spell_check;

import src.main.java.game.data.Data;
import src.main.java.text_based_mechanics.parts_of_speech.Word;
import src.main.java.utilities.FileUtils;

import java.nio.file.Path;
import java.util.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class SpellChecker {

    private static Map<String, Long> WORDS = new HashMap<>();
    private static Long N;

    // return incorrect words
    public static String [] check(String [] input) {
        List<String> incorrect_words = new ArrayList<>();
        for (String word : input) {
            if (!Data.dictionary.containsKey(word)) {
                incorrect_words.add(word);
            }
        }
        return incorrect_words.toArray(new String[0]);
    }

    public static void correct(String[] input) {

        if (input.length == 0) {
            return;
        }

        // Read in word probabilities and create the WORDS dictionary
        for(Map.Entry<String, Word> entry : Data.dictionary.entrySet()) {
            String word = entry.getKey();
            Long prob   = entry.getValue().getProbability();
            if (WORDS.containsKey(word)) {
                WORDS.put(word, prob + 1L);
            }
            else {
                WORDS.put(word, 1L);
            }
        }

        N = WORDS.values().stream().mapToLong(Long::longValue).sum();

        // Test the correction method
        for (String word : input) {
            System.out.println("CORRECTED: " + correction(String.join(" ", word)));
        }
    }

    // probability of the word
    private static double P(String word) {
        return (double) WORDS.getOrDefault(word, 0L) / N;
    }

    // most probable spelling correction for word
    private static String correction(String word) {
        List<String> candidates = new ArrayList<>(candidates(word));
        Collections.sort(candidates, (w1, w2) -> -Double.compare(P(w1), P(w2)));
        return candidates.get(0);
    }

    // generate possible spelling corrections for word
    private static List<String> candidates(String word) {
        List<String> candidates = new ArrayList<>(known(new String[] { word }));
        if (candidates.isEmpty()) {
            candidates = new ArrayList<>(known(edits1(word)));
            if (candidates.isEmpty()) {
                candidates = new ArrayList<>(known(edits2(word)));
                if (candidates.isEmpty()) {
                    candidates.add(word);
                }
            }
        }
        return candidates;
    }

    // the subset of words that appear in the dictionary of WORDS
    private static List<String> known(String[] words) {
        List<String> knownWords = new ArrayList<>();
        for (String word : words) {
            if (WORDS.containsKey(word)) {
                knownWords.add(word);
            }
        }
        return knownWords;
    }

    // the subset of words that appear in the dictionary of WORDS
    private static Set<String> known(Set<String> words) {
        return WORDS.keySet().stream().filter(words::contains).collect(Collectors.toSet());
    }

    // all edits that are only 1 edit away from current word
    public static Set<String> edits1(String word) {
        String letters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";

        // split the word
        Set<String> splits = new HashSet<>();
        for (int i = 0; i < word.length(); i++) {
            splits.add(word.substring(0, i) + " " + word.substring(i));
        }

        // come up with alternative spellings by deleting letters from word
        Set<String> deletes = new HashSet<>();
        for (String split : splits) {
            String[] parts = split.split(" ");
            if (parts[1].length() > 0) {
                deletes.add(parts[0] + parts[1].substring(1));
            }
        }

        // come up with alternative spellings by switching letters around
        Set<String> transposes = new HashSet<>();
        for (String split : splits) {
            String[] parts = split.split(" ");
            if (parts[1].length() > 1) {
                transposes.add(parts[0] + parts[1].charAt(1) + parts[1].charAt(0) + parts[1].substring(2));
            }
        }

        // come up with alternative spellings when replacing letters
        Set<String> replaces = new HashSet<>();
        for (String split : splits) {
            String[] parts = split.split(" ");
            if (parts[1].length() > 0) {
                for (int i = 0; i < letters.length(); i++) {
                    replaces.add(parts[0] + letters.charAt(i) + parts[1].substring(1));
                }
            }
        }

        // come up with alternative spellings with insertions of letters
        Set<String> inserts = new HashSet<>();
        for (String split : splits) {
            String[] parts = split.split(" ");
            for (int i = 0; i < letters.length(); i++) {
                inserts.add(parts[0] + letters.charAt(i) + parts[1]);
            }
        }

        Set<String> result = new HashSet<>();
        result.addAll(deletes);
        result.addAll(transposes);
        result.addAll(replaces);
        result.addAll(inserts);
        return result;
    }

    // all edits that are only 2 edits away from the current word
    public static Set<String> edits2(String word) {
        Set<String> result = new HashSet<>();
        for (String edit1 : edits1(word)) {
            result.addAll(edits1(edit1));
        }
        return result;
    }


    // used to cross-validate dictionary words with probabilities in a second list
//    public static void connectProbs() {
//        for(Map.Entry<String,Word> word : Data.dictionary.entrySet()) {
//            try {
////                System.out.println(word.getKey()+","+Data.probabilities.get(word.getKey()).toString());
//                String key = word.getKey();
//                try {
//                    String probability = Data.probabilities.get(key).toString();
//                    //FileUtils.appendToFile(Path.of("C:/Users/Christopher/IdeaProjects/StarQuest/test.txt"), key+","+probability+"\n");
//                } catch (Exception e) {
//                    System.out.println(key);
//                }
//            } catch (Exception e) { /* do nothing! */ }
//        }
//    }

//        public static void connectPartsOfSpeech() {
//        for(Map.Entry<String,Word> word : Data.dictionary.entrySet()) {
//            try {
////                System.out.println(word.getKey()+","+Data.parts_of_speech.get(word.getKey()));
//                String key = word.getKey();
//                try {
//                    String pos = Data.parts_of_speech.get(key);
//                    FileUtils.appendToFile(Path.of("C:/Users/Christopher/IdeaProjects/StarQuest/test.txt"), key+","+pos+"\n");
//                } catch (Exception e) {
//                    System.out.println(key);
//                }
//            } catch (Exception e) { /* do nothing! */ }
//        }
//    }

//    public static void make_master_dictionary() {
//        for (Map.Entry<String,Word> word : Data.dictionary.entrySet()) {
//            try {
////                System.out.println(word.getKey()+","+Data.parts_of_speech.get(word.getKey()));
//                String key = word.getKey();
//                Path of = Path.of("C:/Users/Christopher/IdeaProjects/StarQuest/resources/DICTIONARY2.txt");
//                try {
//                    String pos = Data.parts_of_speech.get(key);
//                    FileUtils.appendToFile(of, key+","+pos);
//                } catch (Exception e) {
//                    FileUtils.appendToFile(of, key+",null");
////                    System.out.println(key);
//                }
//                try {
//                    String prb = Data.probabilities.get(key).toString();
//                    FileUtils.appendToFile(of, ","+prb);
//                } catch (Exception e) {
//                    FileUtils.appendToFile(of, ",1");
////                    System.out.println(key);
//                }
//                try {
//                    String synonyms = Data.synonyms.get(key).toString();
//                    FileUtils.appendToFile(of, ","+synonyms+"\n");
//                } catch (Exception e) {
//                    FileUtils.appendToFile(of, ",[]"+"\n");
////                    System.out.println(key);
//                }
//            } catch (Exception e) { /* do nothing! */ }
//        }
//    }

//    // USE: SpellChecker.print_adj_q("JJA");
//    public static void print_adj_q(String adj) {
//        for (Map.Entry<String,Word> word : Data.dictionary.entrySet()) {
//            try {
//                String key = word.getKey();
//                Path of = Path.of("C:/Users/Christopher/IdeaProjects/StarQuest/resources/adjectives_temp.txt");
//                try {
//                    String pos = Data.parts_of_speech.get(key);
//                    if (pos.equals(adj)) {
//                        FileUtils.appendToFile(of, key + ",\n");
//                    }
//                } catch (Exception e) {
//                    //
//                }
//            } catch (Exception e) { /* do nothing! */ }
//        }
//    }
}

package src.main.java.text_based_mechanics.parser;

import src.main.java.game.data.ResourceLoader;
import opennlp.tools.postag.POSModel;
import opennlp.tools.postag.POSTaggerME;
import opennlp.tools.tokenize.Tokenizer;
import opennlp.tools.tokenize.TokenizerME;
import opennlp.tools.tokenize.TokenizerModel;
import org.languagetool.JLanguageTool;
import org.languagetool.language.AmericanEnglish;
import org.languagetool.rules.Rule;
import org.languagetool.rules.RuleMatch;
import src.main.java.text_based_mechanics.parts_of_speech.Noun;
import src.main.java.text_based_mechanics.parts_of_speech.Preposition;
import src.main.java.text_based_mechanics.parts_of_speech.Verb;
import src.main.java.text_based_mechanics.parts_of_speech.spell_check.SpellChecker;


import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import src.main.java.utilities.Math;

public class  Parser {

    private static final Map<String, String> VERB_MAP = new HashMap<>();
    static {
        // Picking up / dropping
        VERB_MAP.put("PICK UP", "PICKUP");
        VERB_MAP.put("TAKE OUT", "TAKEOUT");
        VERB_MAP.put("PUT DOWN", "PUTDOWN");
        VERB_MAP.put("SET DOWN", "SETDOWN");

        // Looking / examining
        VERB_MAP.put("CHECK OUT", "CHECKOUT");
        VERB_MAP.put("LOOK AROUND", "LOOKAROUND");
        VERB_MAP.put("TURN AROUND", "TURNAROUND");

        // Movement
        VERB_MAP.put("GO IN", "GOIN");
        VERB_MAP.put("GO INTO", "GOINTO");
        VERB_MAP.put("GO OUT", "GOOUT");
        VERB_MAP.put("GO UP", "GOUP");
        VERB_MAP.put("GO DOWN", "GODOWN");
        VERB_MAP.put("CLIMB UP", "CLIMBUP");
        VERB_MAP.put("CLIMB DOWN", "CLIMBDOWN");
        VERB_MAP.put("GET IN", "GETIN");
        VERB_MAP.put("GET OUT", "GETOUT");
        VERB_MAP.put("GO AFTER", "GOAFTER");

        // Interactions
        VERB_MAP.put("PUT ON", "PUTON");
        VERB_MAP.put("TAKE OFF", "TAKEOFF");
        VERB_MAP.put("TURN ON", "TURNON");
        VERB_MAP.put("TURN OFF", "TURNOFF");
        VERB_MAP.put("OPEN UP", "OPENUP");
        VERB_MAP.put("CLOSE UP", "CLOSEUP");
        VERB_MAP.put("SHUT DOWN", "SHUTDOWN");
        VERB_MAP.put("KNOCK DOWN", "KNOCKDOWN");
        VERB_MAP.put("KNOCK OVER", "KNOCKOVER");

        // Miscellaneous common ones
        VERB_MAP.put("LOOK UP", "LOOKUP");
        VERB_MAP.put("LOOK DOWN", "LOOKDOWN");
        VERB_MAP.put("MOVE ON", "MOVEON");
        VERB_MAP.put("HOLD ON", "HOLDON");
        VERB_MAP.put("HANG ON", "HANGON");
        VERB_MAP.put("GIVE UP", "GIVEUP");
        VERB_MAP.put("WAKE UP", "WAKEUP");
        VERB_MAP.put("SIT DOWN", "SITDOWN");
        VERB_MAP.put("STAND UP", "STANDUP");
        VERB_MAP.put("LIE DOWN", "LIEDOWN");
        VERB_MAP.put("BLOW OUT", "BLOWOUT");
        VERB_MAP.put("PUT OUT", "PUTOUT");
        VERB_MAP.put("GET DOWN", "GETDOWN");
        VERB_MAP.put("HIT ON", "HITON");
        VERB_MAP.put("BEAT UP", "BEATUP");
    }

    static ArrayList<Command> commands;

    public static Command parse(String input) {

        Command command = null;
        Boolean valid = false;

        while(!valid) {

            commands = new ArrayList<>();

            input = input.toUpperCase();
            input = optimizeSentence(input);

            String[] sentence = input.split(" AND | THEN ");

            // collect the sequence of commands
            for (String cmd : sentence) {
                cmd = removeParticles(cmd);
                commands.add(new Command(cmd));
            }

            if (commands.size() != 1) {
                System.out.println("PLEASE USE A SINGLE COMMAND!");
                return null;
            }

            command = commands.get(0);
            valid = command.isValid();

            if (!valid) {
                String output = Math.choose("THAT COMMAND IS INVALID.",
                                            "YOU CANT DO THAT.",
                                            "THAT COMMAND DOES NOT MAKE SENSE.",
                                            "YOU CANT DO THAT.",
                                            "THAT PHRASE IS FORBIDDEN.",
                                            "YOUR INSTRUCTIONS ARE NOT VALID.");
                System.out.println(output);
                return null;
            }
        }

        return command;
    }

    // combine known multi-verb idiosyncrasies to single verb (ie "PICK UP" >>> "GET") // TODO: remove them from Verb list after implementing this
    private static String optimizeSentence(String in) {

        // combine common phrases into single verb
        for (Map.Entry<String, String> entry : VERB_MAP.entrySet()) {
            in = in.replaceAll("\\b" + entry.getKey() + "\\b", entry.getValue());
        }

        String[] sentence = in.split("\\s+");
        String command = "";


        for (String word : sentence) {
            if (Verb.isVerb(word)) {
                command += Verb.getAction(word) + " ";
            }
            else if (Preposition.isPreposition(word)) {
                command += Preposition.getPreposition(word) + " ";
            }
            else if (Noun.isNoun(word)) {
                command += word + " ";
            }
        }

        return command;
    }

    // remove anything that is not a Noun, Verb, or Preposition
    private static String removeParticles(String in) {
        String[] sentence = in.split("\\s+");
        String command = "";

        // POS Testing
//        try {
//            String[] tokens = tokenize(in);
//            posDebug(tokens);
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }

        // TODO: if global settings set to spell check, then do the following
        boolean spell_check = false;
        if (spell_check) {
            String[] bad = SpellChecker.check(sentence);
            SpellChecker.correct(bad);
        }

        for (String word : sentence) {
            if (Noun.isNoun(word) || Verb.isVerb(word) || Preposition.isPreposition(word)) {
                command += word + " ";
            }
        }

        return command;
    }

    // tokenize the sentence
    private String[] tokenize(String input) throws IOException {
        InputStream tokenModelIn = ResourceLoader.class.getResourceAsStream("/en-token.bin"); //new FileInputStream("en-token.bin");
        TokenizerModel tokenModel = new TokenizerModel(tokenModelIn);
        Tokenizer tokenizer = new TokenizerME(tokenModel);
        String tokens[] = tokenizer.tokenize(input);
        return tokens;
    }

    // Parts-Of-Speech Tagging Debug
    private void posDebug(String[] tokens) throws IOException {
        // reading parts-of-speech model to a stream
        InputStream posModelIn = ResourceLoader.class.getResourceAsStream("/en-pos-maxent.bin");
        // loading the parts-of-speech model from stream
        POSModel posModel = new POSModel(posModelIn);
        // initializing the parts-of-speech tagger with model
        POSTaggerME posTagger = new POSTaggerME(posModel);
        // Tagger tagging the tokens
        String tags[] = posTagger.tag(tokens);
        // Getting the probabilities of the tags given to the tokens
        double probs[] = posTagger.probs();

        System.out.println("Token\t:\tTag\t:\tProbability\n---------------------------------------------");
        for(int i=0;i<tokens.length;i++){
            System.out.println(tokens[i]+"\t:\t"+tags[i]+"\t:\t"+probs[i]);
        }
    }

    // spell check testing
    private void spell_check(String input) throws IOException {
        JLanguageTool langTool = new JLanguageTool(new AmericanEnglish());
        for (Rule rule : langTool.getAllRules()) {
            if (!rule.isDictionaryBasedSpellingRule()) {
                langTool.disableRule(rule.getId());
            }
        }
        List<RuleMatch> matches = langTool.check(input);
        for (RuleMatch match : matches) {
            System.out.println("Potential typo at characters " +
                    match.getFromPos() + "-" + match.getToPos() + ": " +
                    match.getMessage());
            System.out.println("Suggested correction(s): " +
                    match.getSuggestedReplacements());
        }
    }

}

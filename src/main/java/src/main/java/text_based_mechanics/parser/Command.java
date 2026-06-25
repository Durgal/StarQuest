package src.main.java.text_based_mechanics.parser;

import src.main.java.text_based_mechanics.parts_of_speech.Noun;
import src.main.java.text_based_mechanics.parts_of_speech.Preposition;
import src.main.java.text_based_mechanics.parts_of_speech.Verb;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Command {

    String[] sentence;

    // COMMAND STRUCTURE ~~~~~~~~~~~~~~~~~~~~~~~
    //
    // VERB
    //
    // VERB + NOUN
    //
    // VERB + PREPOSITION + NOUN
    //
    // VERB + NOUN + PREPOSITION + NOUN
    //
    // ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

    List<Noun> nouns;
    Verb verb; // required
    Preposition preposition; // optional

    public Command(String in) {
        sentence = in.split("\\s+");
        nouns = new ArrayList<>();

        for (String word : sentence) {
            if (Noun.isNoun(word)) {
                nouns.add(new Noun(word));
            }
            else if (Verb.isVerb(word)) {
                verb = new Verb(word);
            }
            else if (Preposition.isPreposition(word)) {
                preposition = new Preposition(word);
            }
        }
    }

    public List<String> getCommands() {
        return Arrays.asList(sentence);
    }

    public Boolean isValid() {
        return verb != null &&  nouns.size() <= 2;
    }

    public List<Noun> getNouns() {
        return nouns;
    }

    public Verb getVerb() {
        return verb;
    }

    public Preposition getPreposition() {
        return preposition;
    }
}

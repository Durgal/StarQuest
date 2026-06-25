package src.main.java.text_based_mechanics.parts_of_speech;

import java.io.Serializable;
import java.util.List;
import java.util.UUID;


public class Word implements Serializable {

    private static final long serialVersionUID  = 1L;

    protected String id;
    
    protected String word;
    protected List<String> synonyms; // TODO: maybe some kind of tree where higher probabilities end at leaf nodes
    protected Long probability;

    // Parent for all parts of speech
    protected Word() { 
        this.id = UUID.randomUUID().toString();
        this.word = UUID.randomUUID().toString();
    }
    
    //
    public Word(String word) {
        this.word = word;
        this.id = UUID.randomUUID().toString();
    }
    
    @Override
    public String toString() { return word; }
    
    @Override
    public int hashCode() { return id.hashCode(); }
    
    @Override
    public boolean equals(Object word) {
        // 1. Check for reference equality
        if (this == word) {
            return true;
        }
        // 2. Check for null and type compatibility
        if (word == null || !(word instanceof Word)) {
            return false;
        }
        // 3. Cast the object and compare relevant fields
        Word other = (Word) word;
        return this.toString().equals(other.toString());
    }

    public String getId() { return id; }

    public void setProbability(Long probability) {
        this.probability = probability;
    }

    public Long getProbability() { return this.probability; }

    public void setSynonyms(List<String> synonyms) {
        this.synonyms = synonyms;
    }

    public List<String> getSynonyms() { return this.synonyms; }

//    // return a random Word from a list
//    public static Word choose(List<Word> words) {
//        return words.get(Math.getRandom().nextInt(words.size()));
//    }

}

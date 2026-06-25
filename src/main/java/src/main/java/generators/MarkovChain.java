package src.main.java.generators;
import src.main.java.game.data.Data;

import java.util.*;

import src.main.java.utilities.Math;

public class MarkovChain {
    private final Map<String, List<Character>> chain = new HashMap<>();
    private final Random random = Math.getRandom();
    private final int order;

    public MarkovChain(List<String> trainingData, int order) {
        this.order = order;
        buildChain(trainingData);
    }

    private void buildChain(List<String> trainingData) {
        for (String name : trainingData) {
            String padded = "~".repeat(order) + name + "~"; // Start/end markers
            for (int i = 0; i <= padded.length() - order - 1; i++) {
                String key = padded.substring(i, i + order);
                char nextChar = padded.charAt(i + order);
                chain.computeIfAbsent(key, k -> new ArrayList<>()).add(nextChar);
            }
        }
    }

    public String generateName(int maxLength) {
        StringBuilder sb = new StringBuilder();
        String current = "~".repeat(order);

        while (true) {
            List<Character> options = chain.get(current);
            if (options == null || options.isEmpty()) break;

            char next = options.get(random.nextInt(options.size()));
            if (next == '~' || sb.length() >= maxLength) break;

            sb.append(next);
            current = current.substring(1) + next;
        }
        return sb.toString();
    }
}

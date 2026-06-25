package src.main.java.utilities;

import src.main.java.game.data.Data;

import java.util.*;

public class Math {
    static final Random random = new Random(Data.SEED);

    public static Random getRandom() {
        return random;
    }

    // return a random number given a range
    public static int randomRange(int min, int max) {
        return random.nextInt((max - min) + 1) + min;
    }

//    // return a random number
//    public static int random() {
//        return random.nextInt((int)System.currentTimeMillis());
//    }

    // return TRUE or FALSE
    public static Boolean coinToss() {
        return random.nextBoolean();
    }

    public static void shuffle(List list) {
        Collections.shuffle(list, random);
    }

    public static Integer choose(Integer ... integers) {
        List<Integer> list = new ArrayList<>(Arrays.asList(integers));
        shuffle(list);
        return list.subList(0, list.size()).get(0);
    }

    public static String choose(String ... strings) {
        List<String> list = new ArrayList<>(Arrays.asList(strings));
        shuffle(list);
        return list.subList(0, list.size()).get(0);
    }

    public static <E extends Comparable<E>> E random(Set<E> set) {
        List<E> list = new ArrayList<>(set);
        Collections.sort(list); // deterministic order

        return list.get(random.nextInt(list.size()));
    }

    public static <E extends Comparable<E>> E random(List<E> list) {
        Collections.sort(list); // non-deterministic order
        return list.get(random.nextInt(list.size()));
    }

    public static <K,V extends Comparable<V>> V shuffle(Map<K,V> map) {
        List<V> values = new ArrayList<>(map.values());
        Collections.shuffle(values, random); // reproducible shuffle using seeded Random
        return values.get(0); // first element from the shuffled list
    }

}

package src.main.java.utilities;

import java.util.*;

public class Enum {

    // select an Enum directly from the enum class
    public static <T extends java.lang.Enum<?>> T random(Class<T> clazz) {
        int x = Math.getRandom().nextInt(clazz.getEnumConstants().length);
        return clazz.getEnumConstants()[x];
    }

    // select an Enum from a subset list of enum class
    public static <T extends java.lang.Enum<?>> T choose(T ... enums) {
        List<T> list = new ArrayList<>(Arrays.asList(enums));
        Collections.shuffle(list, Math.getRandom());
        return list.subList(0, list.size()).get(0);
    }

    // return the name of the enumeration list
    public static String getName(Class clazz) {
        return clazz.getSimpleName().toUpperCase();
    }

    // get all values in the enumeration as an array of Strings
    public static List<String> getNames(Class<? extends java.lang.Enum<?>> e) {
        return List.of(Arrays.stream(e.getEnumConstants()).map(java.lang.Enum::name).toArray(String[]::new));
    }

    // TODO: doesn't work yet
    public static <T extends java.lang.Enum<?>> List<T> iterate(Class<T> enums) {
        List<T> list = new ArrayList<>();

        for (T e : enums.getEnumConstants()) {
            list.add(e);
        }

        return list;
    }

    public static <E extends java.lang.Enum<E>> int size(Class<E> enums) {
        return enums.getEnumConstants().length;
    }
}

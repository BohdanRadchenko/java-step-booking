package org.booking.utils;

import java.util.List;
import java.util.stream.Collectors;

public class StringWorker {
    private static List<Character> toChars(String str) {
        return str.chars().mapToObj(c -> (char) c).toList();
    }

    public static String toLowerCase(String string) {
        return toChars(string)
                .stream()
                .map(Character::toLowerCase)
                .map(String::valueOf)
                .reduce("", String::concat);
    }

    public static String toUpperCase(String string) {
        return toChars(string)
                .stream()
                .map(Character::toUpperCase)
                .map(String::valueOf)
                .reduce("", String::concat);
    }

    public static String toCapitalize(String string) {
        List<Character> characters = toChars(toLowerCase(string));
        characters.set(0, Character.toUpperCase(characters.get(0)));
        return characters
                .stream()
                .map(String::valueOf)
                .collect(Collectors.joining(""));
    }
}

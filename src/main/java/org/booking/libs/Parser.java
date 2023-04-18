package org.booking.libs;

import java.util.Arrays;
import java.util.Set;

public class Parser {
    private static final Set<String> exitWords = Set.of("exit", "ex", "e", "quit", "q");
    private static final Set<String> backWords = Set.of("back", "b");

    private static boolean containsWords(String str, Set<String> words) {
        return Arrays.stream(str.split(" ")).anyMatch(words::contains);
    }

    public static boolean parseIsExit(String str) {
        return containsWords(str, exitWords);
    }

    public static boolean parseIsBack(String str) {
        return containsWords(str, backWords);
    }

    public static int parseInt(String str) throws NumberFormatException {
        try {
            return Integer.parseInt(str);
        } catch (NumberFormatException ex) {
            throw new NumberFormatException(ex.getMessage());
        }
    }
}

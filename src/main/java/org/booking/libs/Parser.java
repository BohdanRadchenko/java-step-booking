package org.booking.libs;

import java.util.Arrays;
import java.util.Set;

public class Parser {
    private static final Set<String> exitWords = Set.of("exit", "ex", "e", "quit", "q");
    private static final Set<String> backWords = Set.of("back", "b");

    private static boolean matcher(String str, Set<String> matchWords) {
        return Arrays.stream(str.split(" ")).anyMatch(matchWords::contains);
    }

    public static boolean parseIsExit(String str) {
        return matcher(str, exitWords);
    }

    public static boolean parseIsBack(String str) {
        return matcher(str, backWords);
    }

    public static int parseInt(String str) throws NumberFormatException {
        try {
            return Integer.parseInt(str);
        } catch (NumberFormatException ex) {
            throw new NumberFormatException(ex.getMessage());
        }
    }
}

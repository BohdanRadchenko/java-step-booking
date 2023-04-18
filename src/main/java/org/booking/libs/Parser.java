package org.booking.libs;

import java.util.Arrays;
import java.util.Set;

public class Parser {
    private static final Set<String> exitWords = Set.of(new String[]{"exit", "ex", "e", "quit", "q"});
    private static final Set<String> backWords = Set.of(new String[]{"back", "b"});

    public static boolean parseIsExit(String str) {
        return Arrays.stream(str.split(" ")).anyMatch(exitWords::contains);
    }

    public static boolean parseIsBack(String str) {
        return Arrays.stream(str.split(" ")).anyMatch(backWords::contains);
    }

    public static int parseInt(String str) throws NumberFormatException {
        try {
            return Integer.parseInt(str);
        } catch (NumberFormatException ex) {
            throw new NumberFormatException(ex.getMessage());
        }
    }
}

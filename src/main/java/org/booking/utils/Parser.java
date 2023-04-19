package org.booking.utils;

import java.util.Arrays;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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

    public static String parseRegex(String str, String regex) throws RuntimeException {
        boolean status = Pattern.matches(regex, str);
        if (!status) {
            throw new RuntimeException("Error parse format");
        }
        return str;
    }

    public static String parseUtm(String utm) throws RuntimeException {
        return parseRegex(utm, "\\d{2}\\w{1}");
    }
}

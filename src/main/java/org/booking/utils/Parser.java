package org.booking.utils;

import org.booking.helpers.Constants;

import java.util.Arrays;
import java.util.Set;
import java.util.regex.Pattern;

public class Parser {
    private static final Set<String> EXIT_WORDS = Set.of("exit", "ex", "e", "quit", "q", String.valueOf(Constants.EXIT_CODE));
    private static final Set<String> BACK_WORDS = Set.of("back", "b");
    private static final Set<String> HELP_WORDS = Set.of("help", "h", String.valueOf(Constants.HELP_CODE));
    private static final Set<String> YES_WORDS = Set.of("yes", "y", "yep", "next");
    private static final Set<String> NO_WORDS = Set.of("no", "n", "nope", "not");

    public static boolean containsWords(String str, Set<String> words) {
        return Arrays.stream(StringWorker.toLowerCase(str).split(" ")).anyMatch(words::contains);
    }

    public static boolean parseIsExit(String str) {
        return containsWords(str, EXIT_WORDS);
    }

    public static boolean parseIsBack(String str) {
        return containsWords(str, BACK_WORDS);
    }

    public static boolean parseIsHelp(String str) {
        return containsWords(str, HELP_WORDS);
    }

    public static boolean parseIsYes(String str) {
        return containsWords(str, YES_WORDS);
    }

    public static boolean parseIsNo(String str) {
        return containsWords(str, NO_WORDS);
    }

    public static boolean parseIsCode(String str) {
        return parseIsExit(str)
                || parseIsHelp(str)
                || parseIsBack(str)
                || parseIsNo(str)
                || parseIsYes(str);
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

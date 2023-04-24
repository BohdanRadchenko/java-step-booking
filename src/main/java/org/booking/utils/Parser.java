package org.booking.utils;

import org.booking.helpers.Constants;

import java.util.Arrays;
import java.util.Set;
import java.util.regex.Pattern;

public class Parser {
    private static final Set<String> exitWords = Set.of("exit", "ex", "e", "quit", "q", String.valueOf(Constants.exitCode));
    private static final Set<String> backWords = Set.of("back", "b");
    private static final Set<String> helpWords = Set.of("help", "h", String.valueOf(Constants.helpCode));
    private static final Set<String> yesWords = Set.of("yes", "y", "yep", "next");
    private static final Set<String> noWords = Set.of("no", "n", "nope", "not");

    public static boolean containsWords(String str, Set<String> words) {
        return Arrays.stream(StringWorker.toLowerCase(str).split(" ")).anyMatch(words::contains);
    }

    public static boolean parseIsExit(String str) {
        return containsWords(str, exitWords);
    }

    public static boolean parseIsBack(String str) {
        return containsWords(str, backWords);
    }

    public static boolean parseIsHelp(String str) {
        return containsWords(str, helpWords);
    }

    public static boolean parseIsYes(String str) {
        return containsWords(str, yesWords);
    }

    public static boolean parseIsNo(String str) {
        return containsWords(str, noWords);
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

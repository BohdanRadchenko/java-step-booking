package org.booking.helpers;

import org.booking.utils.Parser;

public class Validation {
    public static boolean login(String string) {
        return string.length() >= Constants.MIN_LOGIN_LENGTH;
    }

    public static boolean password(String string) {
        return string.length() >= Constants.MIN_LOGIN_LENGTH;
    }

    public static boolean bookingId(String str) {
        try {
            Parser.parseRegex(str, "\\w{2}\\d{4}");
            return true;
        } catch (RuntimeException ignored) {
            return false;
        }
    }

    public static boolean flightId(String str) {
        try {
            Parser.parseRegex(str, "\\w{2}\\d{3}");
            return true;
        } catch (RuntimeException ignored) {
            return false;
        }
    }

    public static boolean bookingDate(String str) {
        String separatorPattern = "[/.\\-_ ]{1}";
        String pattern = String.format("\\d{1,2}%s\\d{1,2}%s\\d{2,4}", separatorPattern, separatorPattern);
        try {
            Parser.parseRegex(str, pattern);
            return true;
        } catch (RuntimeException ignored) {
            return false;
        }
    }
}

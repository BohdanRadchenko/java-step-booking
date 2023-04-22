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
            Parser.parseRegex(str, "\\w{3}\\d{1}");
            return true;
        } catch (RuntimeException ignored) {
            return false;
        }
    }
}

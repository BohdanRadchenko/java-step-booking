package org.booking.helpers;

public class Validation {
    public static boolean login(String string) {
        return string.length() >= Constants.MIN_LOGIN_LENGTH;
    }

    public static boolean passwordLength(String string) {
        return string.length() >= Constants.MIN_LOGIN_LENGTH;
    }
}

package org.booking.helpers;

public class Constants {
    public static int exitCode = 0;
    public static String sep1 = "=";
    public static String sep2 = "-";
    public static String logo = "BOOKING MANAGER";
    public static int repeatSpaceCount = 100;

    public static int calcSpaceCount(String string) {
        return repeatSpaceCount / 2 - string.length() / 2;
    }

    public static int MIN_LOGIN_LENGTH = 5;
    public static int MIN_PASSWORD_LENGTH = 8;
}

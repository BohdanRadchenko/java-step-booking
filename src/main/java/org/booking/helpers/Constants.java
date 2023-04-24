package org.booking.helpers;

import java.nio.file.Paths;

public class Constants {
    public static final String dbDir = "db";
    public static final String projectPath = Paths.get("").toAbsolutePath().toString();
    public static final String dbPath = String.format("%s/%s", projectPath, dbDir);
    public static final int exitCode = -1;
    public static final int helpCode = 0;
    public static final String sep1 = "=";
    public static final String sep2 = "-";
    public static final String logo = "BOOKING MANAGER";
    public static final int repeatSpaceCount = 100;

    public static final int calcSpaceCount(String string) {
        return repeatSpaceCount / 2 - string.length() / 2;
    }

    public static final int FLIGHT_RANDOM_COUNT = 9876;

    public static final int MIN_LOGIN_LENGTH = 5;
    public static final int MIN_PASSWORD_LENGTH = 8;

    // Booking createCode constants
    public static final int CHARS = 26;
    public static final int SLICE_LENGTH = 4;
    public static final int WORD_LENGTH = SLICE_LENGTH / 2 + 1;
}

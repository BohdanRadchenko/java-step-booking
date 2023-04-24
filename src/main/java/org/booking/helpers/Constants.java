package org.booking.helpers;

import java.nio.file.Paths;

public class Constants {
    public static final String DB_DIR = "db";
    public static final String PROJECT_PATH = Paths.get("").toAbsolutePath().toString();
    public static final String DB_PATH = String.format("%s/%s", PROJECT_PATH, DB_DIR);
    public static final int EXIT_CODE = -1;
    public static final int HELP_CODE = 0;
    public static final String SEP_1 = "=";
    public static final String SEP_2 = "-";
    public static final String LOGO = "BOOKING MANAGER";
    public static final int REPEAT_SPACE_COUNT = 100;

    public static final int calcSpaceCount(String string) {
        return REPEAT_SPACE_COUNT / 2 - string.length() / 2;
    }

    public static final int MIN_LOGIN_LENGTH = 5;
    public static final int MIN_PASSWORD_LENGTH = 8;

    // Booking createCode constants
    public static final int CHARS = 26;
    public static final int SLICE_LENGTH = 4;
    public static final int WORD_LENGTH = SLICE_LENGTH / 2 + 1;
}

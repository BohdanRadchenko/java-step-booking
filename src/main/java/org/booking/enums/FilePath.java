package org.booking.enums;

import org.booking.helpers.Constants;

public enum FilePath {
    LOG("application.log"),
    USER("user.bin"),
    FLIGHT("flights.bin"),
    BOOKING("booking.bin");

    public final String path;
    public final String name;

    FilePath(String name) {
        this.name = name;
        this.path = String.format("%s/%s", Constants.dbPath, name);
    }
}

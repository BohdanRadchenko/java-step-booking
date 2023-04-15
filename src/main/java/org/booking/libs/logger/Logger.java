package org.booking.libs.logger;

import java.util.ArrayList;
import java.util.List;

public final class Logger {
    private static final List<Log> logger = new ArrayList<>();

    private Logger() {
    }

    private static void insertLog(LogType logType, String message) {
        long timestamp = System.currentTimeMillis();
        logger.add(new Log(timestamp, logType, message));
    }

    public static void info(String msg) {
        insertLog(LogType.INFO, msg);
    }

    public static void error(String msg) {
        insertLog(LogType.ERROR, msg);
    }

    public static void start() {
        String sep = "=".repeat(13);
        String msg = String.format("%s Application started %s", sep, sep);
        insertLog(LogType.START, msg);
    }
}

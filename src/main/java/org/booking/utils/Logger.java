package org.booking.utils;

import org.booking.enums.FilePath;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public final class Logger {

    enum LogType {
        START,
        ERROR,
        INFO,
        DEBUG,
        EXIT,
        INPUT;

        public final String name;

        LogType() {
            this.name = String.format("[%s]", this.name());
        }

        @Override
        public String toString() {
            return this.name;
        }
    }

    static class Log {
        private final long timestamp;
        private final LogType logType;
        private final String message;

        public Log(long timestamp, LogType logType, String message) {
            this.logType = logType;
            this.message = message;
            this.timestamp = timestamp;
        }

        @Override
        public String toString() {
            String formattedTime = DateUtil.of(timestamp).formatter("dd.MM.yyyy HH:mm:ss");
            return String.format("%s %-8s: %s\r\n", formattedTime, logType, message);
        }
    }

    private Logger() {
    }

    private static void save(Log log) throws RuntimeException {
        try {
            String msg = log.logType == LogType.START ? String.format("\n%s", log.toString()) : log.toString();
            FileWorker.updateText(FilePath.LOG, msg);
        } catch (IOException ex) {
            throw new RuntimeException(ex.getMessage());
        }
    }

    private static void insertLog(LogType logType, String message) {
        long timestamp = DateUtil.of().getMillis();
        Log log = new Log(timestamp, logType, message);
        try {
            save(log);
        } catch (RuntimeException ex) {
            Console.error(String.format("Save log error. %s", ex.getMessage()));
        }
    }

    public static void exit(int code) {
        insertLog(LogType.EXIT, String.format("System exit code %d.", code));
    }

    public static void exit() {
        exit(0);
    }

    public static void input(String msg) {
        insertLog(LogType.INPUT, msg);
    }

    public static void info(String msg) {
        insertLog(LogType.INFO, msg);
    }

    public static void error(String msg) {
        insertLog(LogType.ERROR, msg);
    }

    public static void debug(String msg) {
        insertLog(LogType.DEBUG, msg);
    }

    public static void start() {
        String sep = "=".repeat(13);
        String msg = String.format("%s Application started %s", sep, sep);
        insertLog(LogType.START, msg);
    }
}

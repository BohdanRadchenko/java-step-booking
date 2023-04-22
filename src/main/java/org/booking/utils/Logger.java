package org.booking.utils;

import org.booking.enums.FilePath;
import org.booking.helpers.Constants;

import java.io.IOException;

public final class Logger {

    private static final int pre = 34;

    enum LogType {
        START,
        ERROR,
        INFO,
        EXIT,
        MESSAGE,
        COMMAND,
        CLEAR;

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
            return this.logType == LogType.CLEAR
                    ? message
                    : String.format("%s %-12s: %s\r\n", formattedTime, logType, message);
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
            Console.error(String.format("Insert log error. %s", ex.getMessage()));
        }
    }

    public static void exit(int code) {
        insertLog(LogType.EXIT, String.format("System exit code %d.", code));
    }

    public static void exit() {
        exit(0);
    }

    public static void input(String msg) {
        insertLog(LogType.COMMAND, msg);
    }

    public static void info(String msg) {
        insertLog(LogType.INFO, msg);
    }

    public static void out(String msg) {
        insertLog(LogType.MESSAGE, msg);
    }

    public static void error(String msg) {
        insertLog(LogType.ERROR, msg);
    }

    public static void start() {
        String msg = " Application started ";
        int sepLength = (Constants.repeatSpaceCount - pre) / 2 - (msg.length() / 2);
        String sep = Constants.sep1.repeat(sepLength);
        String res = String.format("%s%s%s", sep, msg, sep);
        insertLog(LogType.START, res);
    }

    public static void separator() {
        String sep = Constants.sep2.repeat(Constants.repeatSpaceCount);
        insertLog(LogType.CLEAR, sep);
    }
}

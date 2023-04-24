package org.booking.utils;

import org.booking.enums.FilePath;
import org.booking.helpers.Constants;

import java.io.IOException;
import java.util.*;

public final class Logger {

    private static boolean doSave = true;

    enum LogType {
        START,
        ERROR,
        INFO,
        EXIT,
        MESSAGE,
        COMMAND,
        DATA,
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

    static class Log implements Comparable<Log> {
        private final long timestamp;
        private final LogType logType;
        private final String message;

        Log(long timestamp, LogType logType, String message) {
            this.logType = logType;
            this.message = message;
            this.timestamp = timestamp;
        }

        Log(LogType logType, String message) {
            this(DateUtil.of().getMillis(), logType, message);
        }

        @Override
        public int hashCode() {
            return Objects.hash(timestamp, logType, message);
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Log that = (Log) o;
            return timestamp == that.timestamp && logType == that.logType && message.equals(that.message);
        }

        @Override
        public int compareTo(Log that) {
            if (this.timestamp < that.timestamp) return -1;
            if (this.timestamp > that.timestamp) return +1;
            return 0;
        }

        @Override
        public String toString() {
            String formattedTime = DateUtil.of(timestamp).formatter("dd.MM.yyyy HH:mm:ss");
            return this.logType == LogType.CLEAR
                    ? message
                    : String.format("%s %-12s: %s\r\n", formattedTime, logType, message);
        }
    }

    private static List<Log> logger = new ArrayList<>();

    private Logger() {
    }

    public static void setSave(boolean set) {
        Logger.doSave = set;
    }

    private static void save() throws RuntimeException {
        FilePath path = FilePath.LOG;
        Collections.sort(logger);
        for (int i = 0; i < logger.size(); i++) {
            Log log = logger.get(i);
            try {
                FileWorker.updateText(path, log.toString());
                logger.remove(i);
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        }
    }

    private static void insertLog(LogType logType, String message) {
        if (!doSave) return;
        Log log = new Log(logType, message);
        logger.add(log);
        try {
            save();
        } catch (RuntimeException ex) {
            Console.error(String.format("Insert log error. %s", ex.getMessage()));
        }
    }

    public static void data(String msg) {
        insertLog(LogType.DATA, msg);
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
        int sepLength = Constants.REPEAT_SPACE_COUNT / 2 - (msg.length() / 2);
        String sep = Constants.SEP_1.repeat(sepLength - 1);
        String res = String.format("\n%s%s%s", sep, msg, sep);
        insertLog(LogType.START, res);
    }

    public static void separator() {
        String sep = Constants.SEP_2.repeat(Constants.REPEAT_SPACE_COUNT);
        insertLog(LogType.CLEAR, sep);
    }
}

package org.booking.libs.logger;

import org.booking.libs.DateUtil;

final class Log {
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
        String formattedTime = DateUtil.of(timestamp).formatter("dd/MM/yyyy HH:mm:ss");
        return String.format("%s %-8s: %s\r\n", formattedTime, logType, message);
    }
}
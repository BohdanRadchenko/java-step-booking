package org.booking.libs.logger;

public enum LogType {
    START,
    ERROR,
    INFO,
    DEBUG,
    WARNING,
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

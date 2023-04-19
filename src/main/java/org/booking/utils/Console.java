package org.booking.utils;

public final class Console extends Input {

    public static void print(String str) {
        System.out.print(str);
    }

    public static void reset() {
        print(ConsoleColors.RESET);
    }

    public static void ln() {
        System.out.println();
    }

    public static void error(String msg) {
        print(ConsoleColors.RED);
        print(msg);
        reset();
    }

    public static void input(String msg) {
        print(ConsoleColors.BLUE_UNDERLINED);
        print(msg);
        reset();
    }

    public static void log(String msg) {
        System.out.println(msg);
    }

    public static void warning(String msg) {
        print(ConsoleColors.YELLOW);
        print(msg);
        reset();
    }
}
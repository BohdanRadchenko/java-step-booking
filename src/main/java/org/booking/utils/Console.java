package org.booking.utils;

public final class Console extends Input implements ConsoleColors {

    public static void print(String str) {
        System.out.print(str);
    }

    public static void reset() {
        print(RESET);
    }

    public static void ln() {
        System.out.println();
    }

    public static void title(String msg) {
        print(CYAN_BOLD);
        print(msg);
        reset();
    }

    public static void msg(String msg) {
        print(CYAN);
        print(msg);
        reset();
    }

    public static void hide(String msg) {
        print(WHITE);
        print(msg);
        reset();
    }

    public static void input(String msg) {
        print(BLUE_UNDERLINED);
        print(msg);
        reset();
    }

    public static void accept(String msg) {
        print(GREEN);
        print(msg);
        reset();
    }

    public static void warning(String msg) {
        print(YELLOW);
        print(msg);
        reset();
    }

    public static void error(String msg) {
        print(RED);
        print(msg);
        reset();
    }

    public static void log(String msg) {
        System.out.println(msg);
    }
}
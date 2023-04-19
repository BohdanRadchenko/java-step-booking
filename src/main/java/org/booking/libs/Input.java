package org.booking.libs;

import java.io.Console;
import java.util.Scanner;

public class Input {
    private static final Scanner scanner = new Scanner(System.in);
    private static final Console console = System.console();

    private static String nextLine() {
        return scanner.nextLine().trim();
    }

    public static String readString() {
        return nextLine();
    }

    /**
     * Read int
     *
     * @return int value from user input
     * @throws NumberFormatException if string can`t parse to int;
     */
    public static int readInt() throws NumberFormatException {
        String str = nextLine();
        return Parser.parseInt(str);
    }

    /**
     * Read password. If System.console() == null ? call readString : call System.console().readPassword();
     *
     * @return String value from user input
     */
    public static String readPassword() {
        if (console == null) {
            return readString();
        }
        char[] pc = console.readPassword();
        return String.valueOf(pc);
    }
}

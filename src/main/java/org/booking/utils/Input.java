package org.booking.utils;

import org.booking.exceptions.ValidateException;
import org.booking.helpers.Constants;
import org.booking.helpers.Validation;

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
     * @throws ValidateException if password invalid
     */
    public static String readPassword(boolean withCode) throws ValidateException {
        String pass;
        if (console == null) {
            pass = readString();
        } else {
            char[] pc = console.readPassword();
            pass = String.valueOf(pc);
        }
        if (withCode && Parser.parseIsCode(pass)) return pass;
        if (!Validation.password(pass)) {
            throw new ValidateException(
                    String.format(
                            "Invalid input '%s'. Password must be more than %d length.\n",
                            pass, Constants.MIN_PASSWORD_LENGTH));
        }
        return pass;
    }

    public static String readPassword() throws ValidateException {
        return readPassword(false);
    }

    /**
     * Read user login.
     *
     * @return String value from user input
     * @throws ValidateException if login invalid
     */
    public static String readLogin(boolean withCode) throws ValidateException {
        String login = readString();
        if (withCode && Parser.parseIsCode(login)) return login;
        if (!Validation.login(login)) {
            throw new ValidateException(
                    String.format(
                            "Invalid input '%s'. Login must be more than %d length.\n", login, Constants.MIN_LOGIN_LENGTH));
        }
        return login;
    }

    public static String readLogin() throws ValidateException {
        return readLogin(false);
    }
}

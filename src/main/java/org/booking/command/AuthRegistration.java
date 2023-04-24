package org.booking.command;

import org.booking.controllers.Controller;
import org.booking.entity.User;
import org.booking.enums.Message;
import org.booking.exceptions.ValidateException;
import org.booking.utils.Console;

// TODO: 18.04.2023 Example class. Remove.
public class AuthRegistration extends Command {

    public AuthRegistration(Controller controller) {
        super(controller);
    }

    public static Command of(Controller controller) {
        return new AuthRegistration(controller);
    }

    private String parseCode(String string) {
        return string;
        // TODO: 23.04.2023 MVP2 code
        //        if (Parser.parseIsBack(string) || Parser.parseIsHelp(string)) {
        //            MenuStack.back();
        //            return "";
        //        }
        //        if (Parser.parseIsExit(string)) {
        //            MenuStack.exit();
        //            return "";
        //        }
        //        return string;
    }

    private String enterLogin() {
        Console.input(Message.USER_ENTER_LOGIN);
        try {
            String login = parseCode(Console.readLogin(true));
            User u = controller.user.checkLogin(login);
            if (u != null) {
                throw new RuntimeException("User already exist");
            }
            return login;
        } catch (RuntimeException ex) {
            Console.error(ex.getMessage());
            return enterLogin();
        }
    }

    private String enterPassword() {
        Console.input(Message.USER_ENTER_PASSWORD);
        try {
            return parseCode(Console.readPassword(true));
        } catch (ValidateException ex) {
            Console.error(ex.getMessage());
            return enterPassword();
        }
    }

    private String enterConfirmPassword(String pass) {
        Console.input(Message.USER_ENTER_CONFIRM_PASSWORD);
        try {
            String confirm = parseCode(Console.readPassword(true));
            if (!confirm.equals(pass)) {
                throw new ValidateException("Invalid confirm password\n");
            }
            return confirm;
        } catch (ValidateException ex) {
            Console.error(ex.getMessage());
            return enterConfirmPassword(pass);
        }
    }

    private String enterFirstName() {
        Console.input(Message.USER_ENTER_FIRST_NAME);
        return parseCode(Console.readString());
    }

    private String enterLastName() {
        Console.input(Message.USER_ENTER_LAST_NAME);
        return parseCode(Console.readString());
    }

    @Override
    public void execute() {
        String login = enterLogin();
        String pass = enterPassword();
        enterConfirmPassword(pass);
        String firstName = enterFirstName();
        String lastName = enterLastName();
        User user = controller.user.registration(login, pass, firstName, lastName);
        Console.success(String.format("\nWelcome %s", user.getFullName()));
    }
}

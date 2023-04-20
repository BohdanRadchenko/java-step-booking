package org.booking.command;

import org.booking.controllers.Controller;
import org.booking.entity.User;
import org.booking.enums.Message;
import org.booking.exceptions.ValidateException;
import org.booking.utils.Console;
import org.booking.utils.Input;

// TODO: 18.04.2023 Example class. Remove.
public class AuthRegistration extends Command {

    public AuthRegistration(Controller controller) {
        super(controller);
    }

    public static Command of(Controller controller) {
        return new AuthRegistration(controller);
    }

    private String enterLogin() {
        Console.input(Message.USER_ENTER_LOGIN);
        try {
            return Console.readLogin();
        } catch (ValidateException ex) {
            Console.error(ex.getMessage());
            return enterLogin();
        }
    }

    private String enterPassword() {
        Console.input(Message.USER_ENTER_PASSWORD);
        try {
            return Console.readPassword();
        } catch (ValidateException ex) {
            Console.error(ex.getMessage());
            return enterPassword();
        }
    }

    private String enterConfirmPassword(String pass) {
        Console.input(Message.USER_ENTER_CONFIRM_PASSWORD);
        try {
            String confirm = Console.readPassword();
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
        return Console.readString();
    }

    private String enterLastName() {
        Console.input(Message.USER_ENTER_LAST_NAME);
        return Console.readString();
    }

    @Override
    public void execute() {
        String login = enterLogin();
        String pass = enterPassword();
        enterConfirmPassword(pass);
        String firstName = enterFirstName();
        String lastName = enterLastName();
        User user = controller.user.registration(login, pass, firstName, lastName);
        Console.accept(String.format("\nWelcome %s", user.getFullName()));
    }
}

package org.booking.command;

import org.booking.controllers.Controller;
import org.booking.entity.User;
import org.booking.enums.Message;
import org.booking.exceptions.ValidateException;
import org.booking.utils.Console;

// TODO: 18.04.2023 Example class. Remove.
public class AuthLogin extends Command {

    public AuthLogin(Controller controller) {
        super(controller);
    }

    public static Command of(Controller controller) {
        return new AuthLogin(controller);
    }

    private String parseCode(String string) {
        return string;
        // TODO: 23.04.2023 MVP2 code
        //        if (Parser.parseIsBack(string) || Parser.parseIsHelp(string)) {
        //            MenuStack.refresh();
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
            String l = Console.readLogin(true);
            return parseCode(l);
        } catch (ValidateException ex) {
            Console.error(ex.getMessage());
            return enterLogin();
        }
    }

    private String enterPassword() {
        Console.input(Message.USER_ENTER_PASSWORD);
        try {
            String pass = Console.readPassword(true);
            return parseCode(pass);
        } catch (ValidateException ex) {
            Console.error(ex.getMessage());
            return enterPassword();
        }
    }

    @Override
    public void execute() {
        String login = enterLogin();
        String pass = enterPassword();
        User user = controller.user.login(login, pass);
        if (user == null) {
            execute();
            return;
        }
        Console.success(String.format("\nWelcome back %s", user.getFullName()));
    }
}

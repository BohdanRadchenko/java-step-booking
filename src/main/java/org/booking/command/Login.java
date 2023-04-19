package org.booking.command;

import org.booking.utils.Input;

// TODO: 18.04.2023 Example class. Remove.
public class Login extends Command {

    public Login() {
        super();
    }

    public static Command of() {
        return new Login();
    }

    @Override
    public void execute() {
        // TODO: 18.04.2023 example code. Remove
        System.out.println("execute");
        System.out.println("Enter login");
        String login = Input.readString();
        System.out.println("Enter password");
        String pass = Input.readPassword();
        System.out.printf("login: %s;\npassword: %s\n", login, pass);
        if (pass.equals("123")) {
            System.out.println("User is logged in");
        } else {
            System.out.println("Invalid login or password");
            execute();
        }
    }
}

package org.booking.command;

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
    }
}

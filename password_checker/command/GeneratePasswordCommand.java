package password.checker.command;

import password.checker.builder.PasswordGenerator;

public class GeneratePasswordCommand implements Command{

    private PasswordGenerator generator;

    public GeneratePasswordCommand(PasswordGenerator gen) { this.generator = gen;}

    @Override
    public void execute() {
        String password = generator.generatePassword();
        System.out.println("Generated password: " + password);
    }
}

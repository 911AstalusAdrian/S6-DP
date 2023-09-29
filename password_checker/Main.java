package password.checker;

import password.checker.builder.PasswordGenerator;
import password.checker.command.Command;
import password.checker.command.GeneratePasswordCommand;
import password.checker.observer.Observer;
import password.checker.observer.PasswordGenerationObserver;
import password.checker.singleton.PasswordGeneratorSingleton;
import password.checker.strategy.ComplexPasswordGenerationStrategy;
import password.checker.strategy.PasswordGenerationStrategy;
import password.checker.strategy.SimplePasswordGenerationStrategy;

public class Main {
    public static void main(String[] args) {

        PasswordGenerator generator = new PasswordGenerator.Builder(10)
                .includeUppercase(true)
                .includeLowercase(true)
                .includeDigits(true)
                .includeSpecial(true)
                .build();

        Command generatePasswordCommand = new GeneratePasswordCommand(generator);
        Observer passwordGeneratorObserver = new PasswordGenerationObserver();

        generatePasswordCommand.execute();
        passwordGeneratorObserver.update();

        PasswordGeneratorSingleton instance = PasswordGeneratorSingleton.getInstance();
        PasswordGenerator singletonPasswordGenerator = instance.getGenerator();
        System.out.println("Second generated password: " + singletonPasswordGenerator.generatePassword());


        PasswordGenerationStrategy simpleStrategy = new SimplePasswordGenerationStrategy();
        PasswordGenerator simplePasswordGenerator = new PasswordGenerator(simpleStrategy);
        String simplePassword = simplePasswordGenerator.generatePassword(10);
        System.out.println("Simple Password: " + simplePassword);


        PasswordGenerationStrategy complexStrategy = new ComplexPasswordGenerationStrategy();
        PasswordGenerator complexPasswordGenerator = new PasswordGenerator(complexStrategy);
        String complexPassword = complexPasswordGenerator.generatePassword(20);
        System.out.println("Complex Password: " + complexPassword);



    }
}
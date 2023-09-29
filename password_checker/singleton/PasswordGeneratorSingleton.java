package password.checker.singleton;

import password.checker.builder.PasswordGenerator;

public class PasswordGeneratorSingleton {
    private static PasswordGeneratorSingleton instance;
    private PasswordGenerator generator;

    private PasswordGeneratorSingleton() {
        generator = new PasswordGenerator.Builder(10)
                .includeUppercase(true)
                .includeLowercase(true)
                .includeDigits(true)
                .includeSpecial(true)
                .build();
    }

    public static synchronized PasswordGeneratorSingleton getInstance() {
        if (instance == null) {
            instance = new PasswordGeneratorSingleton();
        }
        return instance;
    }

    public PasswordGenerator getGenerator() {
        return generator;
    }
}

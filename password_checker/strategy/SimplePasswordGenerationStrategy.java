package password.checker.strategy;

import java.util.Random;

public class SimplePasswordGenerationStrategy implements PasswordGenerationStrategy{

    private static final String ALL_CHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";

    @Override
    public String generatePassword(int length) {
        StringBuilder password = new StringBuilder();
        Random random = new Random();

        for (int i = 0; i < length; i++) {
            char randomChar = ALL_CHARS.charAt(random.nextInt(ALL_CHARS.length()));
            password.append(randomChar);
        }

        return password.toString();
    }
}

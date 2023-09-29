package password.checker.builder;

import password.checker.strategy.PasswordGenerationStrategy;

import java.util.Random;

public class PasswordGenerator {
    private PasswordGenerationStrategy strategy;
    private int length;
    private boolean includeUppercase;
    private boolean includeLowercase;
    private boolean includeDigits;
    private boolean includeSpecialChar;

    private PasswordGenerator(int len, boolean upper, boolean lower, boolean digits, boolean specChar) {
        this.length = len;
        this.includeUppercase = upper;
        this.includeLowercase = lower;
        this.includeDigits = digits;
        this.includeSpecialChar = specChar;
    }

    public PasswordGenerator(PasswordGenerationStrategy strategy){
        this.strategy = strategy;
    }

    public String generatePassword(int length) {
        return strategy.generatePassword(length);
    }

    public static class Builder {
        private int length;
        private boolean includeUpper;
        private boolean includeLower;
        private boolean includeDigits;
        private boolean includeSpecial;

        public Builder(int length) { this.length = length; }

        public Builder includeUppercase(boolean upper) {
            this.includeUpper = upper;
            return this;
        }

        public Builder includeLowercase(boolean lower) {
            this.includeLower = lower;
            return this;
        }

        public Builder includeDigits(boolean digits) {
            this.includeDigits = digits;
            return this;
        }

        public Builder includeSpecial(boolean special) {
            this.includeSpecial = special;
            return this;
        }

        public PasswordGenerator build() {
            return new PasswordGenerator(length, includeUpper, includeLower, includeDigits, includeSpecial);
        }

    }

    public String generatePassword() {
        StringBuilder password = new StringBuilder();

        String uppercaseChars = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String lowercaseChars = "abcdefghijklmnopqrstuvwxyz";
        String digitChars = "0123456789";
        String specialChars = "!@#$%^&*()";

        Random random = new Random();

        // Generate password based on selected options
        if (includeUppercase) {
            char randomUppercaseChar = uppercaseChars.charAt(random.nextInt(uppercaseChars.length()));
            password.append(randomUppercaseChar);
        }

        if (includeLowercase) {
            char randomLowercaseChar = lowercaseChars.charAt(random.nextInt(lowercaseChars.length()));
            password.append(randomLowercaseChar);
        }

        if (includeDigits) {
            char randomDigitChar = digitChars.charAt(random.nextInt(digitChars.length()));
            password.append(randomDigitChar);
        }

        if (includeSpecialChar) {
            char randomSpecialChar = specialChars.charAt(random.nextInt(specialChars.length()));
            password.append(randomSpecialChar);
        }

        // Generate remaining characters
        int remainingLength = length - password.length();
        String allChars = uppercaseChars + lowercaseChars + digitChars + specialChars;

        for (int i = 0; i < remainingLength; i++) {
            char randomChar = allChars.charAt(random.nextInt(allChars.length()));
            password.append(randomChar);
        }

        // Shuffle the generated password
        char[] passwordArray = password.toString().toCharArray();
        for (int i = 0; i < passwordArray.length; i++) {
            int randomIndex = random.nextInt(passwordArray.length);
            char temp = passwordArray[i];
            passwordArray[i] = passwordArray[randomIndex];
            passwordArray[randomIndex] = temp;
        }

        return new String(passwordArray);
    }

}

package password.checker.observer;

public class PasswordGenerationObserver implements Observer{
    @Override
    public void update() {
        System.out.println("Password Generation complete.");
    }
}

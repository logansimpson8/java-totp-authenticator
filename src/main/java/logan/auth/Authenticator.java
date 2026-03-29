package logan.auth;

public class Authenticator {
    private final MathEngine engine = new MathEngine();
    private final TimeProvider timeProvider = new TimeProvider();

    public boolean checkCode(String secret, int userInput) {
        try {
            long currentStep = timeProvider.getCurrentStep();
            int expectedCode = engine.calculateCode(secret, currentStep);
            return expectedCode == userInput;
        } catch (Exception e) {
            return false;
        }
    }
}

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

    public String getQRUrl(String userSecret, String accountName, String issuer) {
        String otpauthUri = String.format("otpauth://totp/%s:%s?secret=%s&issuer=%s",
                issuer, accountName, userSecret, issuer);
        return "https://api.qrserver.com/v1/create-qr-code/?size=200x200&data=" + otpauthUri;
    }
}

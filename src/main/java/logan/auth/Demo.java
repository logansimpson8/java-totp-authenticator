package logan.auth;
import java.util.concurrent.TimeUnit;

public class Demo {
    public static void main(String[] args) throws Exception {
        Authenticator auth = new Authenticator();
        TimeProvider time = new TimeProvider();
        MathEngine engine = new MathEngine();

        //String userSecret = StringCodeGenerator.generateSecret(); - use this to generate a new secret.
        String userSecret = "YDWFYTFRGTA6FGTZJJ5DQBCMFNKEPFCN";
        // Im leaving the secret here for demo purposes so the system dosent keep resetting the google auth
        // when actually using, store the code securley.

        System.out.println("Master Secret: " + userSecret);
        System.out.println("enter it into Google Authenticator.");
        System.out.println("-------------------------------------------");
        System.out.println("live codes (Press Ctrl+C to stop monitoring)");
        System.out.println();

        while (true) {
            long step = time.getCurrentStep();
            int currentPin = engine.calculateCode(userSecret, step);

            long unixTime = System.currentTimeMillis() / 1000;
            long secondsLeft = 30 - (unixTime % 30);
            System.out.printf("\r[ CURRENT PIN: %06d ]  Expires in: %2ds ", currentPin, secondsLeft);
            Thread.sleep(1000);

            if (secondsLeft == 1) {
                System.out.print("\n--- Pin REFRESHED ---\n");
            }
        }
    }
}

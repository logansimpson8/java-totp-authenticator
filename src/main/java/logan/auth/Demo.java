package logan.auth;

public class Demo {
    public static void main(String[] args) throws Exception {
        Authenticator auth = new Authenticator();
        TimeProvider time = new TimeProvider();
        String userSecret = StringCodeGenerator.generateSecret();

        System.out.println("demo");
        System.out.println("Secret (Base32) string: " + userSecret);
        System.out.println(" ");

        for (int i = 0; i < 60; i++) {
            long step = time.getCurrentStep();
            MathEngine engine = new MathEngine();
            int currentPin = engine.calculateCode(userSecret, step);
            System.out.printf("TimeStep (not relevant to user): " + step + "currentPin: " + currentPin );
            Thread.sleep(30000);
        }
    }
}

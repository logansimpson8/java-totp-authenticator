package logan.auth;

public class TimeProvider {
    private static final int ROTATION_SIZE = 30;
    // 30 is the time between secret code changes
    // 30 cause that's the standard

    // im not doing a 30-second countdown technically
    // currenttime() gets the time since jan 1 1970
    // so its gonna be a number in the billions like 118382932
    public long getCurrentStep() {
        long currentTimeMs = System.currentTimeMillis();
        long currentTimeSeconds = currentTimeMs / 1000;
        // this is now dividing by that big number
        // every 29 calculations, the result will stay the same
        //on the 30th, it will go up by one due to the current
        //time increasing - this is the signal for the secret
        //code to change
        return currentTimeSeconds / ROTATION_SIZE;
    }
}

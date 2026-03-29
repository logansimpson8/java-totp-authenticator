package logan.auth;
import org.apache.commons.codec.binary.Base32;
import java.security.SecureRandom;
import java.util.Arrays;


public class StringCodeGenerator {
    private static final int BYTES_SIZE = 20; // this is just the standard
    // size for authenticating

    public static String generateSecret() {
        byte[] buffer = new byte[BYTES_SIZE];
        new SecureRandom().nextBytes(buffer);
        // remove this after, just looking to make sure the binary bucket
        // is working
        System.out.println("Raw Bytes: " + Arrays.toString(buffer));
        Base32 codec = new Base32(); // base 32 just a shorter
        // hex better for when the "pin" is generated
        return codec.encodeToString(buffer);
    }

    public static void main(String[] args){
        String newSecret = generateSecret();
        System.out.println("The 'secret' code, converted to string:" + newSecret);

    }
}

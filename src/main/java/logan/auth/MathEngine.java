package logan.auth;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import org.apache.commons.codec.binary.Base32;
import java.nio.ByteBuffer;

public class MathEngine {
    public int calculateCode(String secret, long timeStep) throws Exception {
        Base32 codec = new Base32();
        byte[] keyBytes = codec.decode(secret);

        // algorithm does not accept longs
        // so im turning the long back into a byte array which it takes
        byte[] data = ByteBuffer.allocate(8).putLong(timeStep).array();

        // this is the algorithm to mix the data
        SecretKeySpec signKey = new SecretKeySpec(keyBytes, "HmacSHA1");
        Mac mac = Mac.getInstance("HmacSHA1");
        mac.init(signKey);
        byte[] hash = mac.doFinal(data); // the result

        // this is where i generate the 6 digit pin from the hash
        // to start it goes to the last bit of the hash
        // then it is essentially picking a random number
        // between 1 and 15 now and this becomes the offset
        int offset = hash[hash.length - 1] & 0xf;

        // whatever the offset ends up being, thats now the starting
        // point for the code generation.
        // it picks the first one (the offset)
        // and then the 3 bytes after it
        int binary = ((hash[offset] & 0x7f) << 24) |
                ((hash[offset + 1] & 0xff) << 16) |
                ((hash[offset + 2] & 0xff) << 8) |
                (hash[offset + 3] & 0xff);
        // the << is just alligning all the bytes into the int

        // this has now gave 4 bytes
        // they are all stuck together
        // and %1000000 removes all the numbers bar the last 6.

        return binary % 1000000;

        // because i dont even know what the offset will be
        // it becomes impossible to know what bits are going to be used
        // as the indexes of what ones im using isnt hard set
        // but rather calculated each new time.
    }
}

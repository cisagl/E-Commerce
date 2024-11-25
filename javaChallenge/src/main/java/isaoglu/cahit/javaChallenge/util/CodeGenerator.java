package isaoglu.cahit.javaChallenge.util;

import java.util.UUID;

public class CodeGenerator {
    public static String generateUUIDCode() {
        return UUID.randomUUID().toString().replace("-", "").substring(0, 10).toUpperCase();
    }
}

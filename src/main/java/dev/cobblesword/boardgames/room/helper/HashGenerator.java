package dev.cobblesword.boardgames.room.helper;

import java.security.SecureRandom;
import java.util.Base64;

public class HashGenerator {

    private static final SecureRandom random = new SecureRandom();
    private static final int BYTE_LENGTH = 5; // 3 bytes for 24 bits

    public static String generateShortHash() {
        byte[] randomBytes = new byte[BYTE_LENGTH];
        random.nextBytes(randomBytes);

        String hash = Base64.getUrlEncoder().withoutPadding().encodeToString(randomBytes);

        // Return the first 6 characters. Since we're using 3 bytes, the Base64 string
        // will be exactly 6 characters long (no need for substring).
        return hash;
    }

    public static void main(String[] args) {
        System.out.println("Random hash: " + generateShortHash());
    }
}

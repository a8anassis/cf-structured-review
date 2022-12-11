package testbed.review;

/**
 * Encrypts strings based on Julius Caesar
 * algorithm.
 */
public class Crypto2 {

    public static void main(String[] args) {
        String s = "ZZZ";
        final int KEY = 1;

        String encrypted = encrypt(s, KEY);
        System.out.printf("%s --> %s\n", s, encrypted);

        String decrypted = decrypt(encrypted, KEY);
        System.out.printf("%s --> %s\n", encrypted, decrypted);
    }

    public static String encrypt(String s, int key) {
        StringBuilder encrypted = new StringBuilder();
        char ch;

        for (int i = 0; i < s.length(); i++) {
            ch = s.charAt(i);
            if (Character.isUpperCase(ch)) encrypted.append(cipher(ch, key));
            else encrypted.append(ch);
        }

        return encrypted.toString();
    }

    public static String decrypt(String s, int key) {
        StringBuilder decrypted = new StringBuilder();
        char ch;

        for (int i = 0; i < s.length(); i++) {
            ch = s.charAt(i);
            if (Character.isAlphabetic(ch)) decrypted.append(decipher(ch, key));
            else decrypted.append(ch);
        }

        return decrypted.toString();
    }

    public static char cipher(char ch, int key) {
        int m, c;

        m = ch - 65;
        c = (m + key) % 26;

        return (char) (c + 65);
    }

    public static char decipher(char ch, int key) {
        int m, c;

        c = ch - 65;
        m = ((c - key) + 26) % 26;

        return (char) (m + 65);
    }
}

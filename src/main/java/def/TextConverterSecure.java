package def;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;
import java.util.Scanner;

public class TextConverterSecure {
    static Scanner sc = new Scanner(System.in);
    public TextConverterSecure() throws Exception {
        //enter a plaintext string
        System.out.print("Plain text: ");
        String plaintext = sc.nextLine();

        // A 128-bit AES key is generated as a hexadecimal string "0123456789abcdef"
        String key = "0123456789abcdef";
        //The SecretKeySpec is initialized with the byte representation of the key and the "AES" algorithm
        SecretKeySpec secretKey = new SecretKeySpec(key.getBytes(), "AES");

        // Encrypt the text
        String encryptedText = encrypt(plaintext, secretKey);
        System.out.println("Encrypted text: " + encryptedText);

        // Decrypt the text
        String decryptedText = decrypt(encryptedText, secretKey);
        System.out.println("Decrypted text: " + decryptedText);
    }

    private static String encrypt(String plaintext, SecretKeySpec secretKey) throws Exception {
        //A cipher instance is created using the "AES/ECB/PKCS5Padding" transformation, specifying the AES algorithm, ECB mode, and PKCS5Padding padding scheme
        Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
        //The cipher is initialized with the encryption mode and the secretKey
        cipher.init(Cipher.ENCRYPT_MODE, secretKey);
        //The doFinal method is used to encrypt the bytes of the plaintext
        byte[] encryptedBytes = cipher.doFinal(plaintext.getBytes());
        //The encrypted bytes are encoded to a Base64 string using Base64.getEncoder()
        return Base64.getEncoder().encodeToString(encryptedBytes);
    }

    private static String decrypt(String encryptedText, SecretKeySpec secretKey) throws Exception {
        Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
        cipher.init(Cipher.DECRYPT_MODE, secretKey);
        byte[] decodedBytes = Base64.getDecoder().decode(encryptedText);
        byte[] decryptedBytes = cipher.doFinal(decodedBytes);
        return new String(decryptedBytes);
    }
}

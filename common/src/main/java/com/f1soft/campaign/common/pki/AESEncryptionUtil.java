package com.f1soft.campaign.common.pki;

import com.f1soft.campaign.common.util.RandomGenerator;
import lombok.extern.slf4j.Slf4j;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;

/**
 * @author Manjit Shakya <manjit.shakya@f1soft.com>
 */
@Slf4j
public class AESEncryptionUtil {

    /**
     * method to encryptResponseUrl using AES
     *
     * @param strToEncrypt - plaintext
     * @param secretKey
     * @return String - encrypted plaintext
     */
    public static String encrypt(String strToEncrypt, SecretKey secretKey) throws Exception {
        Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
        cipher.init(Cipher.ENCRYPT_MODE, secretKey);
        return Base64.getEncoder().encodeToString(cipher.doFinal(strToEncrypt.getBytes()));
    }

    /**
     * method to decrypt using AES
     *
     * @param strToDecrypt - encoded text
     * @param secretKey
     * @return String - plaintext
     */
    public static String decrypt(String strToDecrypt, SecretKey secretKey) throws Exception {
        Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
        cipher.init(Cipher.DECRYPT_MODE, secretKey);
        return new String(cipher.doFinal(Base64.getDecoder().decode(strToDecrypt)));
    }

    /**
     * Method to generate secret key
     *
     * @return SecretKey
     * @throws Exception
     */
    public static SecretKey generateSecretKey() throws Exception {
        String secretStr = RandomGenerator.generateAlphaNumeric(32);
        return getSecretKey(secretStr);
    }

    /**
     * @param secretKey
     * @return
     * @throws Exception
     */
    public static SecretKey getSecretKey(String secretKey) throws Exception {
        byte[] decodeSecretKey = base64Decode(secretKey);
        return new SecretKeySpec(decodeSecretKey, 0, decodeSecretKey.length, "AES");
    }

    /**
     * @param data
     * @return String - encoded text
     */
    public static String base64Encode(byte[] data) {
        return Base64.getEncoder().encodeToString(data);
    }

    /**
     * @param data - encoded text
     * @return byte[]
     */
    public static byte[] base64Decode(String data) {
        return Base64.getDecoder().decode(data);
    }

    /**
     * @param secretKey
     * @return String
     */
    public static String keyToString(SecretKey secretKey) {
        byte[] encoded = secretKey.getEncoded();
        String encodedKey = base64Encode(encoded);
        return encodedKey;
    }

}

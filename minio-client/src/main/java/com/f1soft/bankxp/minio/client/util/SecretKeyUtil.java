package com.f1soft.bankxp.minio.client.util;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

/**
 *
 * @author b.nox<binod.bhandari@f1soft.com>
 */
public class SecretKeyUtil {

    public static SecretKey generateAesSecretKey() throws NoSuchAlgorithmException {
        KeyGenerator keyGen = KeyGenerator.getInstance("AES");
        keyGen.init(256);
        SecretKey aesKey = keyGen.generateKey();
        return aesKey;
    }

    public static String generateEncodedAesSecretKey() throws NoSuchAlgorithmException {
        KeyGenerator keyGen = KeyGenerator.getInstance("AES");
        keyGen.init(256);
        SecretKey aesKey = keyGen.generateKey();
        byte[] encoded = aesKey.getEncoded();
        return Base64.getEncoder().withoutPadding().encodeToString(encoded);
    }

    public static SecretKey reconstituteAesSecretKey(String encodedSecretKey) {
        byte[] encoded = Base64.getDecoder().decode(encodedSecretKey);
        SecretKey aesKey = new SecretKeySpec(encoded, "AES");
        return aesKey;
    }

}

package com.nikosera.cas.key.factory;

import com.nikosera.cas.config.properties.CasFileConfig;
import com.nimbusds.jose.jwk.RSAKey;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.text.ParseException;

/**
 * @author Narayan Joshi
 * @email narenzoshi@gmail.com
 */
@Slf4j
@Component
@AllArgsConstructor
public class KeyFactory {

    private CasFileConfig casConfig;

    public RSAKey getRSASignerKey() {

        RSAKey key = null;
        try {
            key = RSAKey.parse(casConfig.getRsaKey());
        } catch (ParseException e) {
            log.error("Error generating rsa key ", e);
        }
        return key;
    }

    public SecretKey getAESEncryptorKey() {
        return new SecretKeySpec(casConfig.getAesSecretKey().getBytes(), "AES");
    }
}

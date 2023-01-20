package com.nikosera.cas.config;

import com.nikosera.cas.config.properties.CasFileConfig;
import com.nikosera.cas.key.factory.KeyFactory;
import com.nikosera.cas.totp.algorithm.HashingAlgorithm;
import com.nikosera.cas.totp.code.generator.CodeGenerator;
import com.nikosera.cas.totp.code.generator.DefaultCodeGenerator;
import com.nikosera.cas.totp.code.verifier.CodeVerifier;
import com.nikosera.cas.totp.code.verifier.DefaultCodeVerifier;
import com.nikosera.cas.totp.secret.DefaultSecretGenerator;
import com.nikosera.cas.totp.secret.SecretGenerator;
import com.nikosera.cas.totp.time.SystemTimeProvider;
import com.nikosera.cas.totp.time.TimeProvider;
import com.nimbusds.jose.jwk.RSAKey;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.crypto.SecretKey;

/**
 * @author Narayan Joshi
 * @email narenzoshi@gmail.com
 */
@Slf4j
@Configuration
public class CasConfig {
    @Autowired
    private KeyFactory keyFactory;
    @Autowired
    private CasFileConfig casFileConfig;

    @Bean
    RSAKey signer() {
        return keyFactory.getRSASignerKey();
    }

    @Bean
    SecretKey encryptor() {
        return keyFactory.getAESEncryptorKey();
    }

    @Bean
    SecretGenerator secretGenerator(){
        return new DefaultSecretGenerator();
    }

    @Bean
    HashingAlgorithm hashingAlgorithm() {
        return HashingAlgorithm.SHA1;
    }

    @Bean
    CodeGenerator codeGenerator() {
        return new DefaultCodeGenerator(hashingAlgorithm(), 6);
    }

    @Bean
    TimeProvider timeProvider() {
        return new SystemTimeProvider();
    }

    @Bean
    CodeVerifier codeVerifier() {
        return new DefaultCodeVerifier(codeGenerator(), timeProvider(),casFileConfig);
    }
}

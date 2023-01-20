package com.nikosera.cas.util;

import com.nikosera.cas.config.properties.CasFileConfig;
import com.nikosera.common.exception.InvalidTokenException;
import com.nimbusds.jose.*;
import com.nimbusds.jose.crypto.DirectDecrypter;
import com.nimbusds.jose.crypto.DirectEncrypter;
import com.nimbusds.jose.crypto.RSASSASigner;
import com.nimbusds.jose.crypto.RSASSAVerifier;
import com.nimbusds.jose.jwk.RSAKey;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.SignedJWT;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;

import static com.nikosera.common.constant.MsgConstant.Authorization.JWT.INVALID_TOKEN;

@Slf4j
@Component
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class TokenManager {

    private final RSAKey signingKey;
    private final SecretKey encryptionKey;
    private final CasFileConfig casFileConfig;

    public String toEncryptedToken(JWTClaimsSet claims) throws Exception {

        SignedJWT signedJWT = new SignedJWT(
                new JWSHeader.Builder(JWSAlgorithm.RS256).keyID(signingKey.getKeyID()).build(),
                claims);

        signedJWT.sign(new RSASSASigner(signingKey));

        JWEObject jweObjectOutput = new JWEObject(
                new JWEHeader.Builder(JWEAlgorithm.DIR, EncryptionMethod.A256GCM).contentType("JWT").build(),
                new Payload(signedJWT));

        jweObjectOutput.encrypt(new DirectEncrypter(encryptionKey));

        String encryptedJWT = jweObjectOutput.serialize();

        return encryptedJWT;
    }

    public JWTClaimsSet parseEncryptedToken(String encryptedToken) throws Exception {

        RSAKey senderPublicJWK = signingKey.toPublicJWK();

        JWEObject jweObjectInput = JWEObject.parse(encryptedToken);

        jweObjectInput.decrypt(new DirectDecrypter(encryptionKey));

        Payload payload = jweObjectInput.getPayload();

        JWSVerifier verifier = new RSASSAVerifier(senderPublicJWK);

        SignedJWT signedJWT = payload.toSignedJWT();

        if (!signedJWT.verify(verifier)) {
            log.error("JWT TOKEN EXCEPTION: Signature is not valid");
            throw new InvalidTokenException(INVALID_TOKEN);
        }

        return signedJWT.getJWTClaimsSet();
    }

    public Date calculateExpirationDate(Date date) {
        return new Date(date.getTime() + casFileConfig.getJwtExpiresInAuthenticatedPhase() * 60 * 1000);
    }

    public Date calculateExpirationDateTotp(Date date) {
        return new Date(date.getTime() + casFileConfig.getJwtExpiresInTotpPhase() * 60 * 1000);
    }

    public Date calculateExpirationDateTotpFirstTime(Date date) {
        return new Date(date.getTime() + casFileConfig.getJwtExpiresInQrScanPhase() * 60 * 1000);
    }
}

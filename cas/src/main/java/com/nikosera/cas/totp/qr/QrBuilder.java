package com.nikosera.cas.totp.qr;

import com.nikosera.cas.config.properties.CasFileConfig;
import com.nikosera.cas.constant.JwtTokenConstant;
import com.nikosera.cas.totp.algorithm.HashingAlgorithm;
import com.nikosera.cas.totp.secret.SecretGenerator;
import com.nikosera.cas.util.EncodedImageUrlUtil;
import com.nikosera.common.exception.QrGenerationException;
import com.nikosera.entity.ApplicationUser;
import com.nikosera.repository.repository.core.ApplicationUserRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author Narayan Joshi
 * @email narenzoshi@gmail.com
 */
@Component
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class QrBuilder {

    private final ApplicationUserRepository applicationUserRepository;
    private final SecretGenerator secretGenerator;
    private final QrGenerator qrGenerator;
    private final HashingAlgorithm hashingAlgorithm;
    private final CasFileConfig casFileConfig;

    public String build(ApplicationUser applicationUser){

        if(applicationUser.getIsMfaEnabled() == 'N' && applicationUser.getSecret() == null){
            String secret = secretGenerator.generate();
            applicationUser.setSecret(secret);
            applicationUserRepository.save(applicationUser);
        }

        QrData qrData = new QrData.Builder()
                .digits(casFileConfig.getTotp().getCode().getLength())
                .issuer(JwtTokenConstant.ISSUER.getName())
                .algorithm(hashingAlgorithm)
                .label(applicationUser.getEmailAddress())
                .period(casFileConfig.getTotp().getTime().getPeriod())
                .secret(applicationUser.getSecret())
                .build();

        byte[] qrBytes;

        try {
            qrBytes = qrGenerator.generate(qrData);
        } catch (QrGenerationException e) {
            throw new QrGenerationException("Qr generation failed");
        }

        return EncodedImageUrlUtil.getDataUriForImage(qrBytes, qrGenerator.getImageMimeType());
    }
}

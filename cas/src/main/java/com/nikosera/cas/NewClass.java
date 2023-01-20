package com.nikosera.cas;

import com.nikosera.cas.totp.algorithm.HashingAlgorithm;
import com.nikosera.cas.totp.code.generator.CodeGenerator;
import com.nikosera.cas.totp.code.generator.DefaultCodeGenerator;
import com.nikosera.cas.totp.qr.QrData;
import com.nikosera.cas.totp.qr.QrGenerator;
import com.nikosera.cas.totp.qr.ZxingPngQrGenerator;
import com.nikosera.cas.totp.time.SystemTimeProvider;
import com.nikosera.cas.totp.time.TimeProvider;
import com.nikosera.cas.util.EncodedImageUrlUtil;
import com.nikosera.common.exception.CodeGenerationException;

public class NewClass {
    public static void main(String[] args) {
        QrGenerator qrGenerator = new ZxingPngQrGenerator();
        QrData qrData = new QrData.Builder()
                .digits(6)
                .issuer("NIKOSERA")
                .algorithm(HashingAlgorithm.SHA256)
                .label("narenzoshi@gmail.com")
                .period(30)
                .secret("4KJGWKLGBTAWEIHMCYTPGEZSUOM3VRWY")
                .build();

        byte[] qrBytes;

        try {
            qrBytes = qrGenerator.generate(qrData);

            String imageEncoded = EncodedImageUrlUtil.getDataUriForImage(qrBytes, qrGenerator.getImageMimeType());
            System.out.println(imageEncoded);

            new Thread(() -> {
                TimeProvider timeProvider = new SystemTimeProvider();
                CodeGenerator codeGenerator = new DefaultCodeGenerator(HashingAlgorithm.SHA256, 6);

                try {
                    String gcode = "";
                    while (true) {
                        long currentBucket = System.currentTimeMillis() / 30000L;
                        String code = codeGenerator.generate("4KJGWKLGBTAWEIHMCYTPGEZSUOM3VRWY", currentBucket);
                        if (!code.equals(gcode)) {
                            System.out.println(code);
                        }
                        gcode = code;
                        Thread.sleep(1000);
                    }
                } catch (CodeGenerationException | InterruptedException e) {
                    e.printStackTrace();
                }
            }).start();
        } catch (Exception e) {
        }
    }
}

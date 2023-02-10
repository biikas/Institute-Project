package com.f1soft.campaign.web.image;


import lombok.extern.slf4j.Slf4j;

import javax.imageio.ImageIO;
import javax.imageio.ImageReader;
import javax.imageio.stream.ImageInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Optional;

/**
 * @author Rashim Dhaubanjar
 */
@Slf4j
public class FileChecker {

    public static boolean checkMaliciousFile(InputStream in, String filename) {
        try {
            Optional<String> fileExtension = getExtensionByStringHandling(filename);
            if (fileExtension.isPresent() && fileExtension.get().equalsIgnoreCase("jpg")) {
                fileExtension = Optional.of("jpeg");
            }
            String imageExtension = getImageExtenstion(in);
            if (fileExtension.isPresent() && fileExtension.get().equalsIgnoreCase(imageExtension) && imageExtension != null) {
                return true;
            }
        } catch (IOException ex) {
            log.info("Error message : {}", ex.getMessage());
            if (log.isDebugEnabled()) {
                log.error("Error : ", ex);
            }
            return false;
        }
        return false;
    }

    private static String getImageExtenstion(InputStream in) throws IOException {
        Map<String, String> imageMap = new HashMap<>();
        imageMap.put("png", "png");
        imageMap.put("JPEG", "jpeg");
        imageMap.put("jpg", "jpeg");
        imageMap.put("null", null);
        String mimeType = getImageType(in);
        return imageMap.get(mimeType);
    }

    private static String getImageType(InputStream in) throws IOException {
        try {
            ImageInputStream imageStream = ImageIO.createImageInputStream(in);
            Iterator<ImageReader> iter = ImageIO.getImageReaders(imageStream);
            ImageReader reader = iter.next();
            String formatName = reader.getFormatName();
            return formatName;
        } catch (Exception e) {
            return null;
        }
    }

    public static Optional<String> getExtensionByStringHandling(String filename) {
        return Optional.ofNullable(filename)
                .filter(f -> f.contains("."))
                .map(f -> f.substring(filename.lastIndexOf(".") + 1));
    }

    public static void main(String[] args) {
        Optional<String> fileExtension = getExtensionByStringHandling("test");
        if (fileExtension.isPresent()) {
            System.out.println(fileExtension.get().equalsIgnoreCase("png"));
        }
    }

    public static ByteArrayOutputStream convertInputStreamTobyteArray(InputStream photoStream) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024];
        int len;
        try {
            while ((len = photoStream.read(buffer)) > -1) {
                baos.write(buffer, 0, len);
            }
            baos.flush();
        } catch (IOException ex) {
            return null;
        }
        return baos;
    }
}

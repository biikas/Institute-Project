package com.f1soft.campaign.web.util;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FilenameUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

/**
 * @author Prajwol Hada
 */
@Slf4j
public class FileUploadUtil {

    private FileUploadUtil() {}

    public static String saveFile(String uploadDir, String fileName,
                                  MultipartFile multipartFile) throws IOException {
        Path uploadPath = Paths.get(uploadDir);

        if (!Files.exists(uploadPath)) {
            Files.createDirectories(uploadPath);
        }

        try (InputStream inputStream = multipartFile.getInputStream()) {
            Path filePath = uploadPath.resolve(fileName);
            Files.copy(inputStream, filePath, StandardCopyOption.COPY_ATTRIBUTES);
            return fileName;
        } catch (IOException ioe) {
            log.error("Error: ", ioe);
            throw new IOException("Could not save image file: " + fileName, ioe);
        }
    }

    public static String randomFileName(String fileName) {
        String fileNameWithoutExtension = FilenameUtils.removeExtension(fileName);
        String extension = FilenameUtils.getExtension(fileName);

        return fileNameWithoutExtension.concat(RandomGenerator.generateAlphaNumeric(5)).concat(".").concat(extension);
    }
}

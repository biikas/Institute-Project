package com.nikosera.image.server.service.impl;

import com.nikosera.common.builder.ResponseBuilder;
import com.nikosera.common.constant.MinioBucketMap;
import com.nikosera.common.constant.MsgConstant;
import com.nikosera.common.dto.GenericResponse;
import com.nikosera.common.dto.ImageConfig;
import com.nikosera.common.dto.ImageUploadResponse;
import com.nikosera.common.exception.InvalidDataException;
import com.nikosera.common.util.FileChecker;
import com.nikosera.image.server.operations.MinioObjectOperations;
import com.nikosera.image.server.service.ImageUploadService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.UUID;
import java.util.stream.Collectors;

import static com.nikosera.common.constant.MinioBucketMap.BUCKET;

/**
 * @author Narayan Joshi
 * @email narenzoshi@gmail.com
 */
@Slf4j
@Service
@AllArgsConstructor
public class ImageUploadServiceImpl implements ImageUploadService {

    private MinioObjectOperations minioObjectOperation;

    @Override
    public GenericResponse uploadImage(String type, MultipartFile[] files) {

        for (MultipartFile multipartFile : files) {
            validateImage(multipartFile);
        }

        List<ImageUploadResponse.Image> images = Arrays
                .stream(files)
                .map(file -> saveImageAndGetUrl(type, file))
                .collect(Collectors.toList());

        return ResponseBuilder.buildSuccessMessage(images, MsgConstant.Image.UPLOAD_IMAGE_SUCCESS);
    }

    @Override
    public GenericResponse uploadImage(String type, MultipartFile files) {
        validateImage(files);
        ImageUploadResponse.Image image = saveImageAndGetUrl(type, files);
        return ResponseBuilder.buildSuccessMessage(image, MsgConstant.Image.UPLOAD_IMAGE_SUCCESS);
    }

    private boolean validateImage(MultipartFile multipartFile) {
        try {
            boolean validFile = FileChecker.checkMaliciousFile(multipartFile.getInputStream(), multipartFile.getOriginalFilename());

            if (validFile) {
                return true;
            } else {
                throw new InvalidDataException(MsgConstant.Image.INVALID_IMAGE);
            }
        } catch (IOException e) {
            log.error("Error image upload : {}", e.getMessage());
            throw new InvalidDataException(MsgConstant.Image.INVALID_IMAGE);
        }
    }

    private String saveImage(String type, MultipartFile multipartFile) {
        try {
            String bucket = MinioBucketMap.BUCKET.get(MinioBucketMap.Selector.fromString(type)).getPath();
            log.debug("Image uploading to bucket : {}", bucket);

            String fileName = UUID.randomUUID().toString();
            minioObjectOperation.putObject(bucket, fileName, multipartFile.getInputStream(), multipartFile.getContentType(), multipartFile.getSize());

            return fileName;
        } catch (IOException e) {
            log.error("Error image upload : {}", e.getMessage());
            throw new InvalidDataException(MsgConstant.Image.UPLOAD_IMAGE_FAILED);
        }
    }

    private ImageUploadResponse.Image saveImageAndGetUrl(String type, MultipartFile multipartFile) {
        try {

            final ImageConfig imageConfig = BUCKET.get(MinioBucketMap.Selector.fromString(type));
            String bucket = imageConfig.getPath();
            log.debug("Image uploading to bucket : {}", bucket);
            String fileName = UUID.randomUUID().toString() + Objects.requireNonNull(multipartFile.getOriginalFilename()).toLowerCase();
            minioObjectOperation.putObject(imageConfig.getPath(), fileName, multipartFile.getInputStream(), multipartFile.getContentType(), multipartFile.getSize());

            ImageUploadResponse.Image image = new ImageUploadResponse.Image();
            image.setImage(fileName);
            image.setImageUrl(minioObjectOperation.getObjectUrl(bucket, fileName, imageConfig.getExpiresIn()).getUrl());
            return image;
        } catch (IOException e) {
            log.error("Error image upload : {}", e.getMessage());
            throw new InvalidDataException(MsgConstant.Image.UPLOAD_IMAGE_FAILED);
        }
    }
}

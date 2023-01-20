package com.nikosera.image.server.operations;

import com.nikosera.image.server.config.MinioConfig;
import com.nikosera.image.server.config.MinioConfigurationProperties;
import com.nikosera.image.server.response.MinioResponse;
import com.nikosera.image.server.response.ObjectOperationResponse;
import com.nikosera.image.server.util.Util;
import io.minio.*;
import io.minio.errors.*;
import io.minio.http.Method;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @author Narayan Joshi
 * @email narenzoshi@gmail.com
 */
@Slf4j
@Component
public class MinioObjectOperations extends MinioConfig {

    public MinioObjectOperations(MinioConfigurationProperties minioConfigurationProperties) {
        super(minioConfigurationProperties);
    }

    public ObjectOperationResponse getObject(String fileFullPath, String fileName) {
        ObjectOperationResponse objectOperationResponse = new ObjectOperationResponse();
        try {
            Map<String, String> bucketNameAndFileMap = Util.initializeBucketAndFileName(fileFullPath, fileName);
            minioClient.statObject(
                    StatObjectArgs.builder()
                            .bucket(bucketNameAndFileMap.get("bucketName"))
                            .object(bucketNameAndFileMap.get("fileName"))
                            .build()
            );
            try (InputStream stream = minioClient.getObject(
                    GetObjectArgs
                            .builder()
                            .bucket(bucketNameAndFileMap.get("bucketName"))
                            .object(bucketNameAndFileMap.get("fileName"))
                            .build())
            ) {
                objectOperationResponse.setInputStream(stream);
                objectOperationResponse.setBytes(Util.readFully(stream));
                objectOperationResponse.setMinioResponse(new MinioResponse().success("Object fetched."));
            }

        } catch (ErrorResponseException | InsufficientDataException | InternalException | InvalidBucketNameException | InvalidResponseException | IOException | InvalidKeyException | NoSuchAlgorithmException | ServerException | XmlParserException e) {
            log.error("Error : {}", e.getMessage());
            objectOperationResponse.setMinioResponse(new MinioResponse().failure("Unable to fetched."));
        }
        return objectOperationResponse;
    }

    public boolean doesFileExists(String fileFullPath, String fileName) {
        try {
            Map<String, String> bucketNameAndFileMap = Util.initializeBucketAndFileName(fileFullPath, fileName);
            minioClient.statObject(StatObjectArgs.builder().object(bucketNameAndFileMap.get("fileName"))
                    .bucket(bucketNameAndFileMap.get("bucketName"))
                    .build());
            return true;
        } catch (ErrorResponseException | InsufficientDataException | InternalException | InvalidBucketNameException | InvalidResponseException | IOException | InvalidKeyException | NoSuchAlgorithmException | ServerException | XmlParserException e) {
            log.error("Error : {}", e.getMessage());
            return false;
        }
    }

    public ObjectOperationResponse getObjectUrl(String fileFullPath, String fileName, int expiresInSecond) {
        ObjectOperationResponse objectOperationResponse = new ObjectOperationResponse();
        try {
            Map<String, String> bucketNameAndFileMap = Util.initializeBucketAndFileName(fileFullPath, fileName);
            minioClient.statObject(StatObjectArgs.builder().object(bucketNameAndFileMap.get("fileName"))
                    .bucket(bucketNameAndFileMap.get("bucketName"))
                    .build());

            String url = minioClient.getPresignedObjectUrl(
                    new GetPresignedObjectUrlArgs.Builder()
                            .method(Method.GET)
                            .bucket(bucketNameAndFileMap.get("bucketName"))
                            .object(bucketNameAndFileMap.get("fileName"))
                            .expiry(expiresInSecond, TimeUnit.SECONDS)
                            .build()
            );
            objectOperationResponse.setUrl(url);
            objectOperationResponse.setMinioResponse(new MinioResponse().success("Object fetched."));
        } catch (InvalidExpiresRangeException | ErrorResponseException | InsufficientDataException | InternalException | InvalidBucketNameException | InvalidResponseException | IOException | InvalidKeyException | NoSuchAlgorithmException | ServerException | XmlParserException e) {
            log.error("Error : {}", e.getMessage());
            objectOperationResponse.setMinioResponse(new MinioResponse().failure("Unable to fetched."));
        }
        return objectOperationResponse;
    }

    public MinioResponse putObject(String fileFullPath, String fileName, InputStream inputstream, String contentType, long objectSize) {
        try {
            Map<String, String> bucketNameAndFileMap = Util.initializeBucketAndFileName(fileFullPath, fileName);
            minioClient.putObject(new PutObjectArgs.Builder()
                    .object(bucketNameAndFileMap.get("fileName"))
                    .bucket(bucketNameAndFileMap.get("bucketName"))
                    .contentType(contentType)
                    .stream(inputstream, objectSize, 10485760).build());
            return new MinioResponse().success("file uploaded successfully.");
        } catch (ServerException | XmlParserException | ErrorResponseException | InsufficientDataException | InternalException | InvalidBucketNameException | InvalidResponseException | IOException | InvalidKeyException | NoSuchAlgorithmException e) {
            log.error("Error : {}", e.getMessage());
            return new MinioResponse().failure("unable to upload file");
        }
    }

    public MinioResponse removeObject(String fileFullPath, String fileName) {
        try {
            Map<String, String> bucketNameAndFileMap = Util.initializeBucketAndFileName(fileFullPath, fileName);

            minioClient.statObject(new StatObjectArgs.Builder().object(bucketNameAndFileMap.get("fileName"))
                    .bucket(bucketNameAndFileMap.get("bucketName"))
                    .build());

            minioClient.removeObject(RemoveObjectArgs.builder().bucket(bucketNameAndFileMap.get("bucketName")).object(bucketNameAndFileMap.get("fileName")).build());
            return new MinioResponse().success("file removed successfully.");
        } catch (ServerException | XmlParserException | ErrorResponseException | InsufficientDataException | InternalException | InvalidBucketNameException | InvalidResponseException | IOException | InvalidKeyException | NoSuchAlgorithmException e) {
            log.error("Error : {}", e.getMessage());
            return new MinioResponse().failure("unable to remove file.");
        }
    }
}

//package com.college.bankxp.minio.client.operations;
//
//import com.college.bankxp.minio.client.configLoader.MinioClientConfigConstant;
//import com.college.bankxp.minio.client.configLoader.MinioClientConfigLoader;
//import com.college.bankxp.minio.client.response.MinIOResponse;
//import com.college.bankxp.minio.client.response.ObjectOperationResponse;
//import com.college.bankxp.minio.client.util.SecretKeyUtil;
//import com.college.bankxp.minio.client.util.Util;
//import io.minio.ServerSideEncryption;
//import io.minio.errors.*;
//import io.minio.http.Method;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
//import org.xmlpull.v1.XmlPullParserException;
//
//import java.io.IOException;
//import java.io.InputStream;
//import java.security.InvalidKeyException;
//import java.security.NoSuchAlgorithmException;
//import java.util.HashMap;
//import java.util.Map;
//
///**
// * @author b.nox<binod.bhandari @ college.com>
// */
//@Slf4j
//@Component
//public class MinioObjectOperation extends MinioInitalizer {
//
//    @Autowired
//    private MinioClientConfigLoader minioClientConfigLoader;
//
//    public ObjectOperationResponse getObject(String fileFullPath, String fileName) {
//        ObjectOperationResponse objectOperationReponse = new ObjectOperationResponse();
//        try {
//            Map<String, String> bucketNameAndFileMap = Util.initializeBucketAndFileName(fileFullPath, fileName);
//            minioClient.statObject(bucketNameAndFileMap.get("bucketName"), bucketNameAndFileMap.get("fileName"));
//            try (InputStream stream = minioClient.getObject(bucketNameAndFileMap.get("bucketName"), bucketNameAndFileMap.get("fileName"))) {
//                objectOperationReponse.setInputStream(stream);
//                objectOperationReponse.setBytes(Util.readFully(stream));
//                objectOperationReponse.setMinIOResponse(new MinIOResponse().success("Object fetched."));
//            }
//
//        } catch (ErrorResponseException | InsufficientDataException | InternalException | InvalidArgumentException | InvalidBucketNameException | InvalidResponseException | NoResponseException | IOException | InvalidKeyException | NoSuchAlgorithmException | XmlPullParserException e) {
//            log.error("Error : {}", e.getMessage());
//            objectOperationReponse.setMinIOResponse(new MinIOResponse().failure("Unable to fetched."));
//        }
//        return objectOperationReponse;
//    }
//
//    public boolean doesFileExists(String fileFullPath, String fileName) {
//
//        try {
//            Map<String, String> bucketNameAndFileMap = Util.initializeBucketAndFileName(fileFullPath, fileName);
//            minioClient.statObject(bucketNameAndFileMap.get("bucketName"), bucketNameAndFileMap.get("fileName"));
//            return true;
//        } catch (ErrorResponseException | InsufficientDataException | InternalException | InvalidArgumentException | InvalidBucketNameException | InvalidResponseException | NoResponseException | IOException | InvalidKeyException | NoSuchAlgorithmException | XmlPullParserException e) {
//            log.error("Error : {}", e.getMessage());
//            return false;
//        }
//    }
//
//    public ObjectOperationResponse getObjectUsingSSE(String fileFullPath, String fileName) {
//        ObjectOperationResponse objectOperationReponse = new ObjectOperationResponse();
//        try {
//            Map<String, String> bucketNameAndFileMap = Util.initializeBucketAndFileName(fileFullPath, fileName);
//            minioClient.statObject(bucketNameAndFileMap.get("bucketName"), bucketNameAndFileMap.get("fileName"));
//            ServerSideEncryption sse = ServerSideEncryption.withCustomerKey(SecretKeyUtil.reconstituteAesSecretKey(minioClientConfigLoader.minioConfig(MinioClientConfigConstant.ENCODED_AES_SECRET_KEY)));
//            try (InputStream stream = minioClient.getObject(bucketNameAndFileMap.get("bucketName"), bucketNameAndFileMap.get("fileName"), sse)) {
//                objectOperationReponse.setInputStream(stream);
//                objectOperationReponse.setBytes(Util.readFully(stream));
//                objectOperationReponse.setMinIOResponse(new MinIOResponse().success("Object fetched."));
//            }
//
//        } catch (ErrorResponseException | InsufficientDataException | InternalException | InvalidArgumentException | InvalidBucketNameException | InvalidResponseException | NoResponseException | IOException | InvalidKeyException | NoSuchAlgorithmException | XmlPullParserException e) {
//            log.error("Error : {}", e.getMessage());
//            objectOperationReponse.setMinIOResponse(new MinIOResponse().failure("Unable to fetched."));
//        }
//        return objectOperationReponse;
//    }
//
//    public ObjectOperationResponse getObjectUrl(String fileFullPath, String fileName) {
//        ObjectOperationResponse objectOperationReponse = new ObjectOperationResponse();
//        try {
//            Map<String, String> bucketNameAndFileMap = Util.initializeBucketAndFileName(fileFullPath, fileName);
//            minioClient.statObject(bucketNameAndFileMap.get("bucketName"), bucketNameAndFileMap.get("fileName"));
//            String url = minioClient.getPresignedObjectUrl(Method.GET, bucketNameAndFileMap.get("bucketName"), bucketNameAndFileMap.get("fileName"), (24 * 60 * 60), null);
//            objectOperationReponse.setUrl(url);
//            objectOperationReponse.setMinIOResponse(new MinIOResponse().success("Object fetched."));
//        } catch (InvalidExpiresRangeException | ErrorResponseException | InsufficientDataException | InternalException | InvalidArgumentException | InvalidBucketNameException | InvalidResponseException | NoResponseException | IOException | InvalidKeyException | NoSuchAlgorithmException | XmlPullParserException e) {
//            log.error("Error : {}", e.getMessage());
//            objectOperationReponse.setMinIOResponse(new MinIOResponse().failure("Unable to fetched."));
//        }
//        return objectOperationReponse;
//    }
//
//    public MinIOResponse putObject(String fileFullPath, String fileName, InputStream inputstream, String contentType) {
//        try {
//            Map<String, String> bucketNameAndFileMap = Util.initializeBucketAndFileName(fileFullPath, fileName);
//            minioClient.putObject(bucketNameAndFileMap.get("bucketName"), bucketNameAndFileMap.get("fileName"), inputstream, contentType);
//            return new MinIOResponse().success("file uploaded successfully.");
//        } catch (ErrorResponseException | InsufficientDataException | InternalException | InvalidArgumentException | InvalidBucketNameException | InvalidResponseException | NoResponseException | IOException | InvalidKeyException | NoSuchAlgorithmException | XmlPullParserException e) {
//            log.error("Error : {}", e.getMessage());
//            return new MinIOResponse().failure("unable to upload file");
//        }
//    }
//
//    public MinIOResponse putObjectUsingSSE(String fileFullPath, String fileName, InputStream inputstream, Long size, String contentType) {
//        try {
//            Map<String, String> bucketNameAndFileMap = Util.initializeBucketAndFileName(fileFullPath, fileName);
//            ServerSideEncryption sse = ServerSideEncryption.withCustomerKey(SecretKeyUtil.reconstituteAesSecretKey(minioClientConfigLoader.minioConfig(MinioClientConfigConstant.ENCODED_AES_SECRET_KEY)));
//            minioClient.putObject(bucketNameAndFileMap.get("bucketName"), bucketNameAndFileMap.get("fileName"), inputstream, size, new HashMap<>(), sse, "application/octet-stream");
//            return new MinIOResponse().success("file uploaded successfully.");
//        } catch (ErrorResponseException | InsufficientDataException | InternalException | InvalidArgumentException | InvalidBucketNameException | InvalidResponseException | NoResponseException | IOException | InvalidKeyException | NoSuchAlgorithmException | XmlPullParserException e) {
//            log.error("Error : {}", e.getMessage());
//            return new MinIOResponse().failure("unable to upload file");
//        }
//    }
//
//    public MinIOResponse removeObject(String fileFullPath, String fileName) {
//        try {
//            Map<String, String> bucketNameAndFileMap = Util.initializeBucketAndFileName(fileFullPath, fileName);
//            minioClient.statObject(bucketNameAndFileMap.get("bucketName"), bucketNameAndFileMap.get("fileName"));
//            minioClient.removeObject(bucketNameAndFileMap.get("bucketName"), bucketNameAndFileMap.get("fileName"));
//            return new MinIOResponse().success("file removed successfully.");
//        } catch (ErrorResponseException | InsufficientDataException | InternalException | InvalidArgumentException | InvalidBucketNameException | InvalidResponseException | NoResponseException | IOException | InvalidKeyException | NoSuchAlgorithmException | XmlPullParserException e) {
//            log.error("Error : {}", e.getMessage());
//            return new MinIOResponse().failure("unable to remove file.");
//        }
//    }
//}

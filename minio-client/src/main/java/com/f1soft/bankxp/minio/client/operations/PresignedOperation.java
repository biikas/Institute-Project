//package com.f1soft.bankxp.minio.client.operations;
//
//import com.f1soft.bankxp.minio.client.response.MinIOResponse;
//import com.f1soft.bankxp.minio.client.response.PresignedOperationResponse;
//import com.f1soft.bankxp.minio.client.util.Util;
//import io.minio.errors.MinioException;
//import lombok.extern.slf4j.Slf4j;
//import org.xmlpull.v1.XmlPullParserException;
//
//import java.io.IOException;
//import java.security.InvalidKeyException;
//import java.security.NoSuchAlgorithmException;
//import java.util.Map;
//
///**
// * @author b.nox<binod.bhandari @ f1soft.com>
// */
//@Slf4j
//public class PresignedOperation extends MinioInitalizer {
//
//    public PresignedOperationResponse presignedGetObject(String fileFullPath, String fileName) {
//        PresignedOperationResponse operationResponse = new PresignedOperationResponse();
//        try {
//            Map<String, String> bucketNameAndFileMap = Util.initializeBucketAndFileName(fileFullPath, fileName);
//            String url = minioClient.presignedGetObject(bucketNameAndFileMap.get("bucketName"), bucketNameAndFileMap.get("fileName"));
//            operationResponse.setUrl(url);
//            operationResponse.setMinIOResponse(new MinIOResponse().success("Url Fetched"));
//        } catch (MinioException | NoSuchAlgorithmException | IOException | InvalidKeyException | XmlPullParserException e) {
//            log.error("Error : {}", e.getMessage());
//            operationResponse.setMinIOResponse(new MinIOResponse().failure("unable to fetch"));
//        }
//        return operationResponse;
//    }
//
//    public PresignedOperationResponse presignedGetObject(String fileFullPath, String fileName, int expiresInSecond) {
//        PresignedOperationResponse operationResponse = new PresignedOperationResponse();
//        try {
//            Map<String, String> bucketNameAndFileMap = Util.initializeBucketAndFileName(fileFullPath, fileName);
//
//            String url = minioClient.presignedGetObject(bucketNameAndFileMap.get("bucketName"), bucketNameAndFileMap.get("fileName"), expiresInSecond);
//            operationResponse.setUrl(url);
//            operationResponse.setMinIOResponse(new MinIOResponse().success("Url Fetched"));
//        } catch (MinioException | NoSuchAlgorithmException | IOException | InvalidKeyException | XmlPullParserException e) {
//            log.error("Error : {}", e.getMessage());
//            operationResponse.setMinIOResponse(new MinIOResponse().failure("unable to fetch"));
//        }
//        return operationResponse;
//    }
//
//    public PresignedOperationResponse presignedPutObject(String fileFullPath, String fileName) {
//        PresignedOperationResponse operationResponse = new PresignedOperationResponse();
//        try {
//            Map<String, String> bucketNameAndFileMap = Util.initializeBucketAndFileName(fileFullPath, fileName);
//
//            String url = minioClient.presignedPutObject(bucketNameAndFileMap.get("bucketName"), bucketNameAndFileMap.get("fileName"));
//            operationResponse.setUrl(url);
//            operationResponse.setMinIOResponse(new MinIOResponse().success("Url Fetched"));
//        } catch (MinioException | NoSuchAlgorithmException | IOException | InvalidKeyException | XmlPullParserException e) {
//            log.error("Error : {}", e.getMessage());
//            operationResponse.setMinIOResponse(new MinIOResponse().failure("unable to fetch"));
//        }
//        return operationResponse;
//    }
//
//    public PresignedOperationResponse presignedPutObject(String fileFullPath, String fileName, int expirationTimeInSec) {
//        PresignedOperationResponse operationResponse = new PresignedOperationResponse();
//        try {
//            Map<String, String> bucketNameAndFileMap = Util.initializeBucketAndFileName(fileFullPath, fileName);
//
//            String url = minioClient.presignedPutObject(bucketNameAndFileMap.get("bucketName"), bucketNameAndFileMap.get("fileName"), expirationTimeInSec);
//            operationResponse.setUrl(url);
//            operationResponse.setMinIOResponse(new MinIOResponse().success("Url Fetched"));
//        } catch (MinioException | NoSuchAlgorithmException | IOException | InvalidKeyException | XmlPullParserException e) {
//            log.error("Error : {}", e.getMessage());
//            operationResponse.setMinIOResponse(new MinIOResponse().failure("unable to fetch"));
//        }
//        return operationResponse;
//    }
//
//}

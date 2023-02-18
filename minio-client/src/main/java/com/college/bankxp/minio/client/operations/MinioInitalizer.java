//package com.college.bankxp.minio.client.operations;
//
//
//import com.college.bankxp.minio.client.configLoader.MinioClientConfigConstant;
//import com.college.bankxp.minio.client.configLoader.MinioClientConfigLoader;
//import io.minio.MinioClient;
//import io.minio.errors.InvalidEndpointException;
//import io.minio.errors.InvalidPortException;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.config.ConfigurableBeanFactory;
//import org.springframework.context.annotation.Scope;
//import org.springframework.stereotype.Component;
//
//import javax.annotation.PostConstruct;
//
///**
// * @author Rashim Dhaubanjar
// */
//
//@Slf4j
//@Component
//@Scope(value = ConfigurableBeanFactory.SCOPE_SINGLETON)
//public class MinioInitalizer {
//
//    protected MinioClient minioClient;
//
//    @Autowired
//    private MinioClientConfigLoader minioClientConfigLoader;
//
//    @PostConstruct
//    public void init() {
//        try {
//            minioClient = new MinioClient(minioClientConfigLoader.minioConfig(MinioClientConfigConstant.SERVER_IP), minioClientConfigLoader.minioConfig(MinioClientConfigConstant.ACCESS_KEY), minioClientConfigLoader.minioConfig(MinioClientConfigConstant.SECRET_KEY));
//        } catch (InvalidEndpointException | InvalidPortException e) {
//            log.error("Could not connect to Minio client : ", e);
//        }
//    }
//}

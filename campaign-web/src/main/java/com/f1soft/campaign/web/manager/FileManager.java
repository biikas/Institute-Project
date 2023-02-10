package com.f1soft.campaign.web.manager;

import com.f1soft.campaign.common.constant.MsgConstant;
import com.f1soft.campaign.common.exception.DataNotFoundException;
import com.f1soft.campaign.common.util.ResponseMsg;
import com.f1soft.campaign.common.util.StringUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.sql.Timestamp;

@Slf4j
@Component
public class FileManager {


    public InputStream getFileInputStream(String fileUploadLocation, String fileName) {
        try {
            return new FileInputStream(fileUploadLocation + "/" + fileName);
        } catch (FileNotFoundException e) {
            log.error("Exception : {}", e);
            throw new DataNotFoundException(ResponseMsg.failureResponse(MsgConstant.FileUpload.FILE_NOT_FOUND));
        }
    }

    public String saveFile(MultipartFile file, String fileUploadLocation) {
        try {
            String newFileName = renameFileName(file.getOriginalFilename());
            log.info("New filename :" + newFileName);

            File file1 = new File(fileUploadLocation + "//" + newFileName);
            log.info("New file path" + file1.toPath());

            Files.copy(file.getInputStream(), file1.toPath(), StandardCopyOption.REPLACE_EXISTING);
            return newFileName;
        } catch (Exception e) {
            log.error("Error " + e);
            throw new DataNotFoundException(ResponseMsg.failureResponse(MsgConstant.FileUpload.FILE_PROCESSING_EXCEPTION));
        }
    }

    public void deleteFile(String filePath, String fileName) {
        log.debug("deleting file : {}", fileName);
        try {
            Path path = Paths.get(filePath)
                    .toAbsolutePath()
                    .normalize().resolve(fileName);
            Files.delete(path);
        } catch (IOException ioException) {
            log.error("Exception {}", ioException);
        }
    }

    public String renameFileName(String fileName) {
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        Long time = timestamp.getTime();
        String ext = fileName.substring(fileName.lastIndexOf('.'));
        String newFileName = fileName
                .substring(0, fileName.lastIndexOf('.'))
//                .concat(LoginProvider.getApplicationUser().getId().toString())
                .concat(time.toString())
                .concat(ext);
        return newFileName;
    }

    public void checkFileName(String fileName) {
        if (StringUtil.validateNullOrEmpty(fileName)) {
            throw new DataNotFoundException(ResponseMsg.failureResponse(MsgConstant.FileUpload.FILE_NAME_REQUIRED));
        }
    }
}

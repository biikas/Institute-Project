package com.f1soft.campaign.web.manager;

import com.f1soft.campaign.common.constant.MsgConstant;
import com.f1soft.campaign.common.exception.InvalidDataException;
import com.f1soft.campaign.common.util.ResponseMsg;
import com.f1soft.campaign.web.image.FileChecker;
import com.f1soft.campaign.web.util.FileUploadUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

/**
 * @author Prajwol Hada
 */
@Slf4j
@Component
public class ImageUploadManager {

    public String saveImage(MultipartFile multipartFile, String location) {
        String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
        try {
            return FileUploadUtil.saveFile(location, FileUploadUtil.randomFileName(fileName), multipartFile);
        } catch (IOException ioe) {
            log.error("Error:", ioe);
            throw new InvalidDataException(ResponseMsg.failureResponse(MsgConstant.Image.UPLOAD_IMAGE_FAILED));
        }
    }

    public void validateImage(MultipartFile multipartFile) {
        try {
            boolean validFile = FileChecker.checkMaliciousFile(multipartFile.getInputStream(), multipartFile.getOriginalFilename());
            if (!validFile) {
                throw new InvalidDataException(ResponseMsg.failureResponse(MsgConstant.Image.INVALID_IMAGE));
            }
        } catch (IOException e) {
            log.error("Error image upload : {}", e.getMessage());
            throw new InvalidDataException(ResponseMsg.failureResponse(MsgConstant.Image.INVALID_IMAGE));
        }
    }

    public void validateDimension(MultipartFile multipartFile, int width, int height) {
        try {
            InputStream inputStream = multipartFile.getInputStream();

            BufferedImage bufferedImage = ImageIO.read(inputStream);

            int imageWidth = bufferedImage.getWidth();
            int imageHeight = bufferedImage.getHeight();

            if (imageHeight != height || imageWidth != width) {
                throw new InvalidDataException(ResponseMsg.failureResponse(MsgConstant.Image.INVALID_IMAGE_SIZE));
            }

        } catch (IOException e) {
            throw new InvalidDataException(ResponseMsg.failureResponse(MsgConstant.Image.INVALID_IMAGE));
        }
    }

}

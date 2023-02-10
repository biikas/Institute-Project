package com.f1soft.campaign.web.image;


import com.f1soft.campaign.common.constant.MsgConstant;
import com.f1soft.campaign.common.dto.ServerResponse;
import com.f1soft.campaign.common.exception.InvalidDataException;
import com.f1soft.campaign.common.util.ResponseMsg;
import com.f1soft.campaign.entities.model.ImageType;
import com.f1soft.campaign.repository.ImageTypeRepository;
import com.f1soft.campaign.web.manager.ImageUploadManager;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * @author Rashim Dhaubanjar
 */
@Slf4j
@Service
public class ImageUploadServiceImpl implements ImageUploadService {


    @Autowired
    private ImageTypeRepository imageTypeRepository;
    @Autowired
    private ImageUploadManager imageUploadManager;

    @Override
    public ServerResponse uploadImage(String type, MultipartFile files) {
        Optional<ImageType> imageTypeOptional = imageTypeRepository.findByType(type);

        if (!imageTypeOptional.isPresent()) {
            throw new InvalidDataException(ResponseMsg.failureResponse(MsgConstant.Image.UPLOAD_IMAGE_FAILED));
        }

        boolean checkDimension = imageTypeOptional.get().getCheckDimension() == 'Y';

        imageUploadManager.validateImage(files);

        if (checkDimension) {
            imageUploadManager.validateDimension(files, imageTypeOptional.get().getImageWidth(), imageTypeOptional.get().getImageHeight());
        }

        String savedImageName = imageUploadManager.saveImage(files, imageTypeOptional.get().getLocation());

        List<String> uploadedFileName = new ArrayList<>();
        uploadedFileName.add(savedImageName);

        ImageUploadResponse imageUploadResponse = new ImageUploadResponse(uploadedFileName);

        return ResponseMsg.successResponse(MsgConstant.Image.UPLOAD_IMAGE_SUCCESS, imageUploadResponse);
    }

    @Override
    public ServerResponse uploadImage(String type, MultipartFile[] files) {
        Optional<ImageType> imageTypeOptional = imageTypeRepository.findByType(type);

        if (!imageTypeOptional.isPresent()) {
            throw new InvalidDataException(ResponseMsg.failureResponse(MsgConstant.Image.UPLOAD_IMAGE_FAILED));
        }

        boolean checkDimension = imageTypeOptional.get().getCheckDimension() == 'Y';

        for (MultipartFile multipartFile : files) {
            imageUploadManager.validateImage(multipartFile);
            if (checkDimension) {
                imageUploadManager.validateDimension(multipartFile, imageTypeOptional.get().getImageWidth(), imageTypeOptional.get().getImageHeight());
            }
        }

        List<String> uploadedFileName = new ArrayList<>();
        for (MultipartFile multipartFile : files) {
            String savedImageName = imageUploadManager.saveImage(multipartFile, imageTypeOptional.get().getLocation());
            uploadedFileName.add(savedImageName);
        }

        ImageUploadResponse imageUploadResponse = new ImageUploadResponse(uploadedFileName);

        return ResponseMsg.successResponse(MsgConstant.Image.UPLOAD_IMAGE_SUCCESS, imageUploadResponse);
    }
}

package com.f1soft.campaign.web.image;


import com.f1soft.campaign.common.dto.ServerResponse;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author Rashim Dhaubanjar
 */

public interface ImageUploadService {

    ServerResponse uploadImage(String type, MultipartFile[] files);

    ServerResponse uploadImage(String type, MultipartFile files);
}

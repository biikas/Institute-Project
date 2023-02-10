package com.f1soft.campaign.web.controller.campaign;

import com.f1soft.campaign.common.dto.ServerResponse;
import com.f1soft.campaign.common.util.ResponseBuilder;
import com.f1soft.campaign.web.image.ImageUploadService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author Sabrin Luitel
 */

@Slf4j
@RestController
@RequestMapping("campaign/image")
public class ImageUploadController {

    @Autowired
    private ImageUploadService imageUploadService;

    @PostMapping(value = "upload/{type}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> uploadImage(@PathVariable("type") String type,
                                         @RequestParam(value = "files") MultipartFile[] files) {
        ServerResponse serverResponse = imageUploadService.uploadImage(type, files);
        return ResponseBuilder.response(serverResponse);
    }

    @PostMapping(value = "upload/single/{type}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> uploadSingleImage(@PathVariable("type") String type,
                                               @RequestParam(value = "files") MultipartFile files) {
        ServerResponse serverResponse = imageUploadService.uploadImage(type, files);
        return ResponseBuilder.response(serverResponse);
    }
}

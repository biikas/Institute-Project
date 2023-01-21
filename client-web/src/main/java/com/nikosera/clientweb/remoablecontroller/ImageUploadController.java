package com.nikosera.clientweb.remoablecontroller;

import com.nikosera.common.dto.GenericResponse;
import com.nikosera.image.server.service.ImageUploadService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@Slf4j
@RestController
@RequestMapping("image")
public class ImageUploadController {

    @Autowired
    private ImageUploadService imageUploadService;

    @PostMapping(value = "upload/{type}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> uploadImage(@PathVariable("type") String type,
                                         @RequestParam(value = "files") MultipartFile[] files) {
        GenericResponse response = imageUploadService.uploadImage(type, files);
        return ResponseEntity.ok(response);
    }

    @PostMapping(value = "upload/single/{type}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> uploadSingleImage(@PathVariable("type") String type,
                                               @RequestParam(value = "files") MultipartFile files) {
        GenericResponse response = imageUploadService.uploadImage(type, files);
        return ResponseEntity.ok(response);
    }
}

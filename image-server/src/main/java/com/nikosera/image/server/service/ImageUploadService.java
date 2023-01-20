package com.nikosera.image.server.service;

import com.nikosera.common.dto.GenericResponse;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author Narayan Joshi
 * @email narenzoshi@gmail.com
 */
public interface ImageUploadService {

    GenericResponse uploadImage(String type, MultipartFile[] files);

    GenericResponse uploadImage(String type, MultipartFile files);
}

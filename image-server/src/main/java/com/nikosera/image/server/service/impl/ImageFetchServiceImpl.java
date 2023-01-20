package com.nikosera.image.server.service.impl;

import com.nikosera.image.server.operations.MinioObjectOperations;
import com.nikosera.image.server.service.ImageFetchService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * @author Narayan Joshi
 * @email narenzoshi@gmail.com
 */
@Service
@AllArgsConstructor
public class ImageFetchServiceImpl implements ImageFetchService {

    private final MinioObjectOperations minioObjectOperation;

    @Override
    public String fetchImageUrl(String bucket, String imagePath, int expiresIn) {
        return minioObjectOperation.getObjectUrl(bucket, imagePath, expiresIn).getUrl();
    }
}

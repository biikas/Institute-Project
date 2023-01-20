package com.nikosera.image.server.util;

import com.nikosera.image.server.service.ImageFetchService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import static com.nikosera.common.constant.MinioBucketMap.BUCKET;
import static com.nikosera.common.constant.MinioBucketMap.Selector.USER;

/**
 * @author Narayan Joshi
 * @email narenzoshi@gmail.com
 */
@Component
@AllArgsConstructor
public class ImageUrlAttacher {

    private final ImageFetchService imageFetchService;

    public String attachForUser(String imagePath) {
        return imagePath != null ? imageFetchService.fetchImageUrl(BUCKET.get(USER).getPath(),
                imagePath,
                BUCKET.get(USER).getExpiresIn()) : "";
    }
}

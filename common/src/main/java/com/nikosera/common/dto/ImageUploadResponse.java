package com.nikosera.common.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * @author Narayan Joshi
 * @email narenzoshi@gmail.com
 */
@Getter
@Setter
public class ImageUploadResponse extends ModelBase {
    private List<Image> images;

    @Getter
    @Setter
    public static class Image extends ModelBase {
        private String image;
        private String imageUrl;
    }
}

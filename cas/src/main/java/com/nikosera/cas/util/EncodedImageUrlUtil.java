package com.nikosera.cas.util;

import org.apache.commons.codec.binary.Base64;

/**
 * @author Narayan Joshi
 * @email narenzoshi@gmail.com
 */
public class EncodedImageUrlUtil {
    private static Base64 base64Codec = new Base64();

    /**
     * Given the raw data of an image and the mime type, returns
     * a data URI string representing the image for embedding in
     * HTML/CSS.
     *
     * @param data     The raw bytes of the image.
     * @param mimeType The mime type of the image.
     * @return The data URI string representing the image.
     */
    public static String getDataUriForImage(byte[] data, String mimeType) {
        String encodedData = new String(base64Codec.encode(data));

        return String.format("data:%s;base64,%s", mimeType, encodedData);
    }
}

package com.nikosera.image.server.response;

/**
 * @author Narayan Joshi
 * @email narenzoshi@gmail.com
 */
public class PresignedOperationResponse {

    private String url;

    MinioResponse minioResponse;

    public MinioResponse getMinioResponse() {
        return minioResponse;
    }

    public void setMinioResponse(MinioResponse minioResponse) {
        this.minioResponse = minioResponse;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}

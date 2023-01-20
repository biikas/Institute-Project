package com.nikosera.image.server.response;

import java.io.InputStream;

/**
 * @author Narayan Joshi
 * @email narenzoshi@gmail.com
 */
public class ObjectOperationResponse {
    private InputStream inputStream;
    private byte[] bytes;
    private String url;

    MinioResponse minioResponse;

    public InputStream getInputStream() {
        return inputStream;
    }

    public void setInputStream(InputStream inputStream) {
        this.inputStream = inputStream;
    }

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

    public byte[] getBytes() {
        return bytes;
    }

    public void setBytes(byte[] bytes) {
        this.bytes = bytes;
    }
}

package com.f1soft.bankxp.minio.client.response;

import java.io.InputStream;

/**
 *
 * @author b.nox<binod.bhandari@f1soft.com>
 */
public class ObjectOperationResponse {

    private InputStream inputStream;
    private byte[] bytes;
    private String url;

    MinIOResponse minIOResponse;

    public InputStream getInputStream() {
        return inputStream;
    }

    public void setInputStream(InputStream inputStream) {
        this.inputStream = inputStream;
    }

    public MinIOResponse getMinIOResponse() {
        return minIOResponse;
    }

    public void setMinIOResponse(MinIOResponse minIOResponse) {
        this.minIOResponse = minIOResponse;
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

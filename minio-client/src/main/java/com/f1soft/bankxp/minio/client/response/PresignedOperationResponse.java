package com.f1soft.bankxp.minio.client.response;

/**
 *
 * @author b.nox<binod.bhandari@f1soft.com>
 */
public class PresignedOperationResponse {

    private String url;

    MinIOResponse minIOResponse;

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
}
